package pl.dbjllmjk.View;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import pl.dbjllmjk.Controller.AdminController;
import pl.dbjllmjk.Model.Action;
import pl.dbjllmjk.Model.Operation;
import pl.dbjllmjk.Model.PetTransactionException;

public class OperationTypesTab extends JPanel implements ActionListener, ListSelectionListener {

    private static final long serialVersionUID = 1L;
    private AdminController adminController;
    private JList<Operation> operationTypeList;
    private CheckBoxList connectionsList;
    private JButton addOperationTypeButton, removeOperationTypeButton, saveChangesButton;
    private DefaultListModel<JCheckBox> connectionsModel;

    public OperationTypesTab(AdminController adminController) {
        this.adminController = adminController;
        init();
    }

    public void init() {
        this.setLayout(new GridLayout(1, 2));
        Operation[] activityTypes = this.adminController.getAvaliableOperationTypes();
        operationTypeList = new JList<>(activityTypes);
        operationTypeList.addListSelectionListener(this);
        connectionsModel = new DefaultListModel<>();
        for (JCheckBox box : this.adminController.getActionWithTypeConnections(null)) {
            connectionsModel.addElement(box);
        }
        connectionsList = new CheckBoxList(connectionsModel);
        JPanel leftSide = new JPanel(new GridLayout(2, 1));
        JScrollPane activityScroll = new JScrollPane(operationTypeList);
        JScrollPane connectionScroll = new JScrollPane(connectionsList);
        leftSide.add(activityScroll);
        leftSide.add(connectionScroll);
        this.add(leftSide);
        JPanel rightSide = new JPanel(new GridLayout(3, 1));
        addOperationTypeButton = new JButton("Add operation type");
        addOperationTypeButton.addActionListener(this);
        removeOperationTypeButton = new JButton("Remove operation type");
        removeOperationTypeButton.addActionListener(this);
        saveChangesButton = new JButton("Save changes on selected activity type");
        saveChangesButton.addActionListener(this);
        rightSide.add(addOperationTypeButton);
        rightSide.add(removeOperationTypeButton);
        rightSide.add(saveChangesButton);
        this.add(rightSide);
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        if (arg0.getSource() == this.addOperationTypeButton) {
            String name = (String) JOptionPane.showInputDialog(this,
                    "Choose name for your operation: ", "Create Dialog",
                    JOptionPane.PLAIN_MESSAGE, null, null, "");
            if (name != null) {
                Object[] possibilities = {1, 2, 3, 4, 5};
                Integer value = (Integer) JOptionPane.showInputDialog(this, "Choose value of new operation:",
                        "Create Dialog", JOptionPane.PLAIN_MESSAGE, null, possibilities, "");
                if (value != null) {
                    try {
                        this.adminController.addOperationType(name, value);
                        JOptionPane.showMessageDialog(this, "Operation added!\n( ͡° ͜ʖ ͡°)", "OK",
                                JOptionPane.INFORMATION_MESSAGE);
                        this.adminController.refresh();
                    } catch (PetTransactionException e) {
                        JOptionPane.showMessageDialog(this, e.getMessage() + "\n( ͡° ͜ʖ ͡°)", "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }
        if (arg0.getSource() == this.removeOperationTypeButton) {
            if (this.operationTypeList.getSelectedValue() != null) {
                int option = JOptionPane.showConfirmDialog(null,
                        "Do you want to remove \"" + operationTypeList.getSelectedValue() + "\"?",
                        "Delete operation type confirmation", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    this.adminController.removeActionType(operationTypeList.getSelectedValue());
                    this.adminController.refresh();
                }
            } else {
                JOptionPane.showMessageDialog(this, "No selection!\n( ͡° ͜ʖ ͡°)", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
        if (arg0.getSource() == this.saveChangesButton) {
            List<JCheckBox> updated = new ArrayList<>();
            for (int i = 0; i < this.connectionsList.getModel().getSize(); i++) {
                updated.add((JCheckBox) this.connectionsList.getModel().getElementAt(i));
            }
            try {
                this.adminController.updateActionWithTypeConnections(updated, this.operationTypeList.getSelectedValue());
                JOptionPane.showMessageDialog(this, "List updated!\n( ͡° ͜ʖ ͡°)", "OK",
                        JOptionPane.INFORMATION_MESSAGE);
            } catch (PetTransactionException e) {
                JOptionPane.showMessageDialog(this, e.getMessage() + "\n( ͡° ͜ʖ ͡°)", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }

    }

    @Override
    public void valueChanged(ListSelectionEvent arg0) {
        Action selectedValue = this.operationTypeList.getSelectedValue();
        this.connectionsModel.clear();
        for (JCheckBox box : this.adminController.getActionWithTypeConnections(selectedValue)) {
            connectionsModel.addElement(box);
        }
    }

}
