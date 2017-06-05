package pl.dbjllmjk.View;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

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
import pl.dbjllmjk.Model.Food;
import pl.dbjllmjk.Exceptions.PetTransactionException;

public class FoodTypesTab extends JPanel implements ActionListener, ListSelectionListener {

    private static final long serialVersionUID = 1L;
    private AdminController adminController;
    private JList<Food> foodTypeList;
    private CheckBoxList connectionsList;
    private JButton addFoodTypeButton, removeFoodTypeButton, saveChangesButton;
    private DefaultListModel<JCheckBox> connectionsModel;

    public FoodTypesTab(AdminController adminController) {
        this.adminController = adminController;
        init();
    }

    public void init() {
        this.setLayout(new GridLayout(1, 2));
        Food[] petTypes = this.adminController.getAvaliableFoodTypes();
        foodTypeList = new JList<>(petTypes);
        foodTypeList.addListSelectionListener(this);
        connectionsModel = new DefaultListModel<>();
        this.adminController.getActionWithTypeConnections(null).entrySet().forEach((entry) -> {
            connectionsModel.addElement(new JCheckBox(entry.getKey(), entry.getValue()));
        });
        connectionsList = new CheckBoxList(connectionsModel);
        JPanel leftSide = new JPanel(new GridLayout(2, 1));
        JScrollPane foodScroll = new JScrollPane(foodTypeList);
        JScrollPane connectionScroll = new JScrollPane(connectionsList);
        leftSide.add(foodScroll);
        leftSide.add(connectionScroll);
        this.add(leftSide);
        JPanel rightSide = new JPanel(new GridLayout(3, 1));
        addFoodTypeButton = new JButton("Add Food type");
        addFoodTypeButton.addActionListener(this);
        removeFoodTypeButton = new JButton("Remove Food type");
        removeFoodTypeButton.addActionListener(this);
        saveChangesButton = new JButton("Save changes on selected food type");
        saveChangesButton.addActionListener(this);
        rightSide.add(addFoodTypeButton);
        rightSide.add(removeFoodTypeButton);
        rightSide.add(saveChangesButton);
        this.add(rightSide);
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        if (arg0.getSource() == this.addFoodTypeButton) {
            String name = (String) JOptionPane.showInputDialog(this,
                    "Choose name for your food: ", "Create Dialog",
                    JOptionPane.PLAIN_MESSAGE, null, null, "");
            if (name != null) {
                Object[] possibilities = {1, 2, 3, 4, 5};
                Integer value = (Integer) JOptionPane.showInputDialog(this, "Choose value of new food:",
                        "Create Dialog", JOptionPane.PLAIN_MESSAGE, null, possibilities, "");
                if (value != null) {
                    try {
                        this.adminController.addFoodType(name, value);
                        JOptionPane.showMessageDialog(this, "Food added!\n( ͡° ͜ʖ ͡°)", "OK",
                                JOptionPane.INFORMATION_MESSAGE);
                        this.adminController.refresh();
                    } catch (PetTransactionException e) {
                        JOptionPane.showMessageDialog(this, e.getMessage() + "\n( ͡° ͜ʖ ͡°)", "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }
        if (arg0.getSource() == this.removeFoodTypeButton) {
            if (this.foodTypeList.getSelectedValue() != null) {
                int option = JOptionPane.showConfirmDialog(null,
                        "Do you want to remove \"" + foodTypeList.getSelectedValue() + "\"?",
                        "Delete food type confirmation", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    this.adminController.removeActionType(foodTypeList.getSelectedValue());
                    this.adminController.refresh();
                }
            } else {
                JOptionPane.showMessageDialog(this, "No selection!\n( ͡° ͜ʖ ͡°)", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
        if (arg0.getSource() == this.saveChangesButton) {
            Map<String, Boolean> updated = new HashMap<>();
            for (int i = 0; i < this.connectionsList.getModel().getSize(); i++) {
                JCheckBox box = (JCheckBox) this.connectionsList.getModel().getElementAt(i);
                updated.put(box.getText(), box.isSelected());
            }
            try {
                this.adminController.updateActionWithTypeConnections(updated, this.foodTypeList.getSelectedValue());
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
        Action selectedValue = this.foodTypeList.getSelectedValue();
        this.connectionsModel.clear();
        this.adminController.getActionWithTypeConnections(selectedValue).entrySet().forEach((entry) -> {
            connectionsModel.addElement(new JCheckBox(entry.getKey(), entry.getValue()));
        });
    }

}
