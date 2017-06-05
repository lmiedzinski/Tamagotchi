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
import pl.dbjllmjk.Model.Activity;
import pl.dbjllmjk.Model.PetTransactionException;

public class ActivityTypesTab extends JPanel implements ActionListener, ListSelectionListener {

    private static final long serialVersionUID = 1L;
    private AdminController adminController;
    private JList<Activity> activityTypeList;
    private CheckBoxList connectionsList;
    private JButton addActivityTypeButton, removeActivityTypeButton, saveChangesButton;
    private DefaultListModel<JCheckBox> connectionsModel;

    public ActivityTypesTab(AdminController adminController) {
        this.adminController = adminController;
        init();
    }

    public void init() {
        this.setLayout(new GridLayout(1, 2));
        Activity[] activityTypes = this.adminController.getAvaliableActivityTypes();
        activityTypeList = new JList<>(activityTypes);
        activityTypeList.addListSelectionListener(this);
        connectionsModel = new DefaultListModel<>();
        for (JCheckBox box : this.adminController.getActionWithTypeConnections(null)) {
            connectionsModel.addElement(box);
        }
        connectionsList = new CheckBoxList(connectionsModel);
        JPanel leftSide = new JPanel(new GridLayout(2, 1));
        JScrollPane activityScroll = new JScrollPane(activityTypeList);
        JScrollPane connectionScroll = new JScrollPane(connectionsList);
        leftSide.add(activityScroll);
        leftSide.add(connectionScroll);
        this.add(leftSide);
        JPanel rightSide = new JPanel(new GridLayout(3, 1));
        addActivityTypeButton = new JButton("Add activity type");
        addActivityTypeButton.addActionListener(this);
        removeActivityTypeButton = new JButton("Remove activity type");
        removeActivityTypeButton.addActionListener(this);
        saveChangesButton = new JButton("Save changes on selected activity type");
        saveChangesButton.addActionListener(this);
        rightSide.add(addActivityTypeButton);
        rightSide.add(removeActivityTypeButton);
        rightSide.add(saveChangesButton);
        this.add(rightSide);
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        if (arg0.getSource() == this.addActivityTypeButton) {
            String name = (String) JOptionPane.showInputDialog(this,
                    "Choose name for your activity: ", "Create Dialog",
                    JOptionPane.PLAIN_MESSAGE, null, null, "");
            if (name != null) {
                Object[] possibilities = {1, 2, 3, 4, 5};
                Integer value = (Integer) JOptionPane.showInputDialog(this, "Choose value of new activity:",
                        "Create Dialog", JOptionPane.PLAIN_MESSAGE, null, possibilities, "");
                if (value != null) {
                    try {
                        this.adminController.addActivityType(name, value);
                        JOptionPane.showMessageDialog(this, "Activity added!\n( ͡° ͜ʖ ͡°)", "OK",
                                JOptionPane.INFORMATION_MESSAGE);
                        this.adminController.refresh();
                    } catch (PetTransactionException e) {
                        JOptionPane.showMessageDialog(this, e.getMessage() + "\n( ͡° ͜ʖ ͡°)", "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }
        if (arg0.getSource() == this.removeActivityTypeButton) {
            if (this.activityTypeList.getSelectedValue() != null) {
                int option = JOptionPane.showConfirmDialog(null,
                        "Do you want to remove \"" + activityTypeList.getSelectedValue() + "\"?",
                        "Delete activity type confirmation", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    this.adminController.removeActionType(activityTypeList.getSelectedValue());
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
                this.adminController.updateActionWithTypeConnections(updated, this.activityTypeList.getSelectedValue());
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
        Action selectedValue = this.activityTypeList.getSelectedValue();
        this.connectionsModel.clear();
        for (JCheckBox box : this.adminController.getActionWithTypeConnections(selectedValue)) {
            connectionsModel.addElement(box);
        }
    }

}
