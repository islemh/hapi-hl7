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
 * FailureIncorrectMessageReceivedTab.java
 *
 * Created on 24-Oct-2009, 6:34:32 PM
 */

package ca.uhn.hunit.swing.ui.run;

import ca.uhn.hunit.ex.IncorrectMessageReceivedException;

/**
 *
 * @author James
 */
public class FailureIncorrectMessageReceivedTab extends javax.swing.JPanel {
    private static final long serialVersionUID = 1L;
    
    /** Creates new form FailureIncorrectMessageReceivedTab */
    public FailureIncorrectMessageReceivedTab() {
        initComponents();
    }

    /** Creates new form FailureIncorrectMessageReceivedTab */
    public FailureIncorrectMessageReceivedTab(IncorrectMessageReceivedException ex) {
        initComponents();

        myDifferenceDescriptionArea.setText(ex.describeReason());
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        myDifferenceDescriptionArea = new javax.swing.JTextArea();

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("ca/uhn/hunit/l10n/UiStrings"); // NOI18N
        jLabel1.setText(bundle.getString("execution.failure.ca.uhn.hunit.ex.IncorrectMessageReceivedException")); // NOI18N

        myDifferenceDescriptionArea.setColumns(20);
        myDifferenceDescriptionArea.setRows(5);
        jScrollPane1.setViewportView(myDifferenceDescriptionArea);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea myDifferenceDescriptionArea;
    // End of variables declaration//GEN-END:variables

}
