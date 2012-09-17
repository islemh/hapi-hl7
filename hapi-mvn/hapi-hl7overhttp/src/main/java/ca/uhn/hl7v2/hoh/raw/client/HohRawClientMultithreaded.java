package ca.uhn.hl7v2.hoh.raw.client;

import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import ca.uhn.hl7v2.hoh.api.IClientMultithreaded;
import ca.uhn.hl7v2.hoh.util.Validate;

/**
 * <p>
 * Raw message sender using the HL7 over HTTP specification which uses a
 * {@link ScheduledExecutorService} to provide advanced functionality such as
 * persistent connections which time out and close automatically.
 * </p>
 * <p>
 * This connector uses an executor service which can start worker threads, so
 * use caution if embedding within a J2EE container.
 * </p>
 */
public class HohRawClientMultithreaded extends AbstractRawClient implements IClientMultithreaded {

	/**
	 * Default {@link #setSocketTimeout(long) Socket Timeout}, 10000ms
	 */
	private static final long DEFAULT_SOCKET_TIMEOUT = 10000;

	private static final org.slf4j.Logger ourLog = org.slf4j.LoggerFactory.getLogger(HohRawClientMultithreaded.class);

	private final ScheduledExecutorService myExecutorService;
	private Map<Socket, Long> myIdleSocketsToTimeBecameIdle = new IdentityHashMap<Socket, Long>();
	private final SimpleDateFormat myLogTimeFormat = new SimpleDateFormat("HH:mm:ss,SSS");
	private boolean myReapingScheduled;
	private long mySocketTimeout = DEFAULT_SOCKET_TIMEOUT;

	/**
	 * Constructor
	 */
	public HohRawClientMultithreaded() {
		myExecutorService = Executors.newScheduledThreadPool(1);
	}

	/**
	 * Constructor
	 * 
	 * @param theHost
	 *            The HOST (name/address). E.g. "192.168.1.1"
	 * @param thePort
	 *            The PORT. E.g. "8080"
	 * @param thePath
	 *            The path being requested (must either be blank or start with
	 *            '/' and contain a path). E.g. "/Apps/Receiver.jsp"
	 */
	public HohRawClientMultithreaded(String theHost, int thePort, String thePath) {
		this();
		
		setHost(theHost);
		setPort(thePort);
		setPath(thePath);
	}

	/**
	 * Constructor
	 * 
	 * @param theHost
	 *            The HOST (name/address). E.g. "192.168.1.1"
	 * @param thePort
	 *            The PORT. E.g. "8080"
	 * @param theUri
	 *            The URI being requested (must either be blank or start with
	 *            '/' and contain a path). E.g. "/Apps/Receiver.jsp"
	 * @param theExecutorService
	 *            The executor service to use for detecting stale sockets
	 */
	public HohRawClientMultithreaded(String theHost, int thePort, String theUri, ScheduledExecutorService theExecutorService) {
		super(theHost, thePort, theUri);
		Validate.notNull(theExecutorService, "executorService");

		myExecutorService = theExecutorService;
	}

	/**
	 * Constructor
	 * 
	 * @param theUrl
	 *            The URL to connect to
	 * @param theExecutorService
	 *            The executor service to use for detecting stale sockets
	 */
	public HohRawClientMultithreaded(URL theUrl) {
		this();
		setUrl(theUrl);
	}

	/**
	 * Constructor
	 * 
	 * @param theUrl
	 *            The URL to connect to
	 * @param theExecutorService
	 *            The executor service to use for detecting stale sockets
	 */
	public HohRawClientMultithreaded(URL theUrl, ScheduledExecutorService theExecutorService) {
		super(theUrl);
		Validate.notNull(theExecutorService, "executorService");

		myExecutorService = theExecutorService;
	}

	@Override
	protected synchronized Socket provideSocket() throws IOException {
		Socket retVal;
		if (myIdleSocketsToTimeBecameIdle.size() == 0) {
			ourLog.info("Creating new remote connection to {}:{}", getHost(), getPort());
			retVal = connect();
		} else {
			retVal = myIdleSocketsToTimeBecameIdle.keySet().iterator().next();
			myIdleSocketsToTimeBecameIdle.remove(retVal);
		}
		return retVal;
	}

	@Override
	protected synchronized void returnSocket(Socket theSocket) {
		long now = System.currentTimeMillis();

		if (ourLog.isDebugEnabled()) {
			ourLog.debug("Returning socket, will be eligible for reaping at " + myLogTimeFormat.format(new Date(now + mySocketTimeout)));
		}

		myIdleSocketsToTimeBecameIdle.put(theSocket, now);
		scheduleReaping();
	}

	private void scheduleReaping() {
		long now = System.currentTimeMillis();
		if (myReapingScheduled) {
			ourLog.debug("Reaping already scheduled");
			return;
		}

		if (myIdleSocketsToTimeBecameIdle.size() < 1) {
			return;
		}

		long earliestReapingTime = Long.MAX_VALUE;
		for (Long next : myIdleSocketsToTimeBecameIdle.values()) {
			long nextReapingTime = next + mySocketTimeout;
			if (nextReapingTime < earliestReapingTime) {
				earliestReapingTime = nextReapingTime;
			}
		}

		long delay = earliestReapingTime - now;
		if (ourLog.isDebugEnabled()) {
			ourLog.debug("Scheduling socket reaping in {} ms at {}", delay, myLogTimeFormat.format(new Date(earliestReapingTime)));
		}

		myExecutorService.schedule(new TimeoutTask(), delay, TimeUnit.MILLISECONDS);
		myReapingScheduled = true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ca.uhn.hl7v2.hoh.raw.client.IClientMultithreaded#setSocketTimeout(long)
	 */
	public synchronized void setSocketTimeout(long theSocketTimeout) {
		mySocketTimeout = theSocketTimeout;
		myReapingScheduled = false;
		scheduleReaping();
	}

	private class TimeoutTask implements Runnable {
		public void run() {

			ourLog.debug("Beginning socket reaping pass");
			try {

				List<Socket> socketsToClose = new ArrayList<Socket>();
				long closeIfActiveBefore = System.currentTimeMillis() - mySocketTimeout;
				synchronized (HohRawClientMultithreaded.this) {

					for (Iterator<Map.Entry<Socket, Long>> iter = myIdleSocketsToTimeBecameIdle.entrySet().iterator(); iter.hasNext();) {
						Entry<Socket, Long> nextEntry = iter.next();
						if (nextEntry.getValue() < closeIfActiveBefore) {
							Socket key = nextEntry.getKey();
							socketsToClose.add(key);
							ourLog.info("Closing idle socket with local port {} because it has been idle since {}", key.getLocalPort(), new Date(nextEntry.getValue()));
							iter.remove();
						} else {
							if (ourLog.isDebugEnabled()) {
								ourLog.debug("Next socket has " + (nextEntry.getValue() - closeIfActiveBefore) + "ms remaining");
							}
						}
					}

					myReapingScheduled = false;
					scheduleReaping();
				}

				for (Socket next : socketsToClose) {
					closeSocket(next);
				}
			} catch (Throwable e) {
				ourLog.error("Failure during reaper pass", e);
			}
		}
	}

}