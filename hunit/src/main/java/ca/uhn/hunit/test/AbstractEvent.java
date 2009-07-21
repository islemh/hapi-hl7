package ca.uhn.hunit.test;

import ca.uhn.hunit.ex.TestFailureException;
import ca.uhn.hunit.iface.AbstractInterface;
import ca.uhn.hunit.run.ExecutionContext;
import ca.uhn.hunit.xsd.Event;
import ca.uhn.hunit.xsd.Interface;

public abstract class AbstractEvent {

	private TestBatteryImpl myBattery;
	private String myInterfaceId;

	public AbstractEvent(TestBatteryImpl theBattery, Event theConfig) {
		Interface ifDef = (Interface) theConfig.getInterfaceId();
		myInterfaceId = ifDef.getId();
		myBattery = theBattery;		
	}
	
	public abstract void execute(ExecutionContext theCtx) throws TestFailureException;

	public TestBatteryImpl getBattery() {
		return myBattery;
	}

	public String getInterfaceId() {
		return myInterfaceId;
	}

	public AbstractInterface getInterface() {
		return myBattery.getInterface(myInterfaceId);
	}
	
}
