/**
 *
 * The contents of this file are subject to the Mozilla Public License Version 1.1
 * (the "License"); you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at http://www.mozilla.org/MPL
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

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.uhn.hunit.swing.model;

import ca.uhn.hunit.event.AbstractEvent;
import ca.uhn.hunit.event.ISpecificMessageEvent;
import ca.uhn.hunit.test.TestBatteryImpl;

import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author James
 */
public class MessageComboBoxModel extends DefaultComboBoxModel {
    //~ Static fields/initializers -------------------------------------------------------------------------------------

    private static final long serialVersionUID = 1L;
    public static final String NONE_SELECTED = new Object() + "NONE_SELECTED";

    //~ Instance fields ------------------------------------------------------------------------------------------------

    private final TestBatteryImpl myBattery;

    //~ Constructors ---------------------------------------------------------------------------------------------------

    public MessageComboBoxModel(TestBatteryImpl theBattery, ISpecificMessageEvent theEvent) {
        if (theBattery == null) {
            theBattery = new TestBatteryImpl();
        }

        myBattery = theBattery;

        if (theEvent != null) {
            setSelectedItem(theEvent.getMessage());
        } else {
            setSelectedItem(NONE_SELECTED);
        }
    }

    //~ Methods --------------------------------------------------------------------------------------------------------

    @Override
    public Object getElementAt(int index) {
        if (index == 0) {
            return NONE_SELECTED;
        }

        return myBattery.getMessages().get(index - 1);
    }

    @Override
    public int getSize() {
        return myBattery.getMessages().size() + 1;
    }
}
