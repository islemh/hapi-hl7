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
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Hl7V2ExpectSpecificMessageEditorForm.java
 *
 * Created on 17-Oct-2009, 2:27:34 PM
 */

package ca.uhn.hunit.swing.ui.event.expect;

import ca.uhn.hunit.swing.controller.ctx.TestEditorController;
import ca.uhn.hunit.event.expect.Hl7V2ExpectSpecificMessageImpl;
import ca.uhn.hunit.swing.ui.event.AbstractEventEditorForm;

/**
 *
 * @author James
 */
public class Hl7V2ExpectSpecificMessageEditorForm extends AbstractEventEditorForm<Hl7V2ExpectSpecificMessageImpl> {
    private static final long serialVersionUID = 1L;
    
    private Hl7V2ExpectSpecificMessageImpl myEvent;
    private TestEditorController myController;

    /** Creates new form Hl7V2ExpectSpecificMessageEditorForm */
    public Hl7V2ExpectSpecificMessageEditorForm() {
        myEvent = null;

        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        myEventTypeForm = new ca.uhn.hunit.swing.ui.event.EventTypeForm();
        myBaseEventEditorForm = new ca.uhn.hunit.swing.ui.event.BaseEventEditorForm();
        myBaseSpecificMessageEditorForm = new ca.uhn.hunit.swing.ui.event.BaseSpecificMessageEditorForm();
        myBaseExpectMessageEditorForm = new ca.uhn.hunit.swing.ui.event.expect.BaseExpectMessageEditorForm();

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(myEventTypeForm, javax.swing.GroupLayout.DEFAULT_SIZE, 470, Short.MAX_VALUE)
            .addComponent(myBaseExpectMessageEditorForm, javax.swing.GroupLayout.DEFAULT_SIZE, 470, Short.MAX_VALUE)
            .addComponent(myBaseSpecificMessageEditorForm, javax.swing.GroupLayout.DEFAULT_SIZE, 470, Short.MAX_VALUE)
            .addComponent(myBaseEventEditorForm, javax.swing.GroupLayout.DEFAULT_SIZE, 470, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(myEventTypeForm, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(myBaseEventEditorForm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(myBaseSpecificMessageEditorForm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(myBaseExpectMessageEditorForm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private ca.uhn.hunit.swing.ui.event.BaseEventEditorForm myBaseEventEditorForm;
    private ca.uhn.hunit.swing.ui.event.expect.BaseExpectMessageEditorForm myBaseExpectMessageEditorForm;
    private ca.uhn.hunit.swing.ui.event.BaseSpecificMessageEditorForm myBaseSpecificMessageEditorForm;
    private ca.uhn.hunit.swing.ui.event.EventTypeForm myEventTypeForm;
    // End of variables declaration//GEN-END:variables


    /**
     * {@inheritDoc }
     */
    @Override
    public void setController(TestEditorController theController, Hl7V2ExpectSpecificMessageImpl theEvent) {
        myEvent = theEvent;
        myController = theController;

        myBaseEventEditorForm.setController(theController, theEvent);
        myBaseSpecificMessageEditorForm.setController(theController, theEvent);
        myEventTypeForm.setController(theController, theEvent);
        myBaseExpectMessageEditorForm.setController(theController, theEvent);
    }

}