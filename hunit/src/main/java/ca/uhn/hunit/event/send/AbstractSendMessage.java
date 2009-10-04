/**
 *
 * The contents of this file are subject to the Mozilla Public License Version 1.1
 * (the "License"); you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at http://www.mozilla.org/MPL/
 * Software distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License for the
 * specific language governing rights and limitations under the License.
 *
 * The Initial Developer of the Original Code is University Health Network. Copyright (C)
 * 2001.  All Rights Reserved.
 *
 * Alternatively, the contents of this file may be used under the terms of the
 * GNU General Public License (the  "GPL"), in which case the provisions of the GPL are
 * applicable instead of those above.  If you wish to allow use of your version of this
 * file only under the terms of the GPL and not to allow others to use your version
 * of this file under the MPL, indicate your decision by deleting  the provisions above
 * and replace  them with the notice and other provisions required by the GPL License.
 * If you do not delete the provisions above, a recipient may use your version of
 * this file under either the MPL or the GPL.
 */
package ca.uhn.hunit.event.send;

import ca.uhn.hunit.event.AbstractEvent;
import ca.uhn.hunit.test.*;
import ca.uhn.hunit.ex.ConfigurationException;
import ca.uhn.hunit.ex.TestFailureException;
import ca.uhn.hunit.iface.AbstractInterface;
import ca.uhn.hunit.iface.TestMessage;
import ca.uhn.hunit.msg.AbstractMessage;
import ca.uhn.hunit.run.ExecutionContext;
import ca.uhn.hunit.xsd.SendMessage;

public abstract class AbstractSendMessage<T> extends AbstractEvent {


	private TestImpl myTest;
	private AbstractMessage myMessageProvider;
	private AbstractInterface myInterface;


	public AbstractSendMessage(TestBatteryImpl theBattery, TestImpl theTest, SendMessage theConfig) throws ConfigurationException {
		super(theBattery, theTest, theConfig);
		
		String messageId = theConfig.getMessageId();
		myMessageProvider = getBattery().getMessage(messageId);
		myInterface = getBattery().getInterface(getInterfaceId());
		myTest = theTest;
	}

	@Override
	public void execute(ExecutionContext theCtx) throws TestFailureException {

		TestMessage<?> message = myMessageProvider.getTestMessage();
		
        message = massageMessage((TestMessage<T>) message);
		
		myInterface.sendMessage(myTest, theCtx, message);
		
	}

	/**
     * Subclasses should override this method to perform any message massaging
     * they need to do. It is ok to just return the object passed in.
     */
	public abstract TestMessage<T> massageMessage(TestMessage<T> theInput) throws TestFailureException;
	
}