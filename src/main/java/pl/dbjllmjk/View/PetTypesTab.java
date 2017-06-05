package pl.dbjllmjk.View;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import pl.dbjllmjk.Controller.AdminController;
import pl.dbjllmjk.Exceptions.PetTransactionException;

public class PetTypesTab extends JPanel implements ActionListener {

    private static final long serialVersionUID = 1L;
    private AdminController adminController;
    private JList<String> petTypeList;
    private JButton addPetTypeButton, removePetTypeButton;

    public PetTypesTab(AdminController adminController) {
        this.adminController = adminController;
        this.setLayout(new GridLayout(1, 2));
        init();
    }

    public void init() {
        String[] petTypes = this.adminController.getAvaliablePetTypes();
        petTypeList = new JList<>(petTypes);
        JPanel leftSide = new JPanel(new GridLayout(1, 1));
        leftSide.add(petTypeList);
        this.add(leftSide);
        JPanel rightSide = new JPanel(new GridLayout(2, 1));
        addPetTypeButton = new JButton("Add pet type");
        addPetTypeButton.addActionListener(this);
        removePetTypeButton = new JButton("Remove pet type");
        removePetTypeButton.addActionListener(this);
        rightSide.add(addPetTypeButton);
        rightSide.add(removePetTypeButton);
        this.add(rightSide);
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        if (arg0.getSource() == addPetTypeButton) {
            String newType = (String) JOptionPane.showInputDialog(this, "Enter new pet type:", "Create Pet Type Dialog",
                    JOptionPane.PLAIN_MESSAGE, null, null, "");
            if (newType != null) {
                try {
                    this.adminController.addPetType(newType);
                    this.adminController.refresh();
                } catch (PetTransactionException e) {
                    JOptionPane.showMessageDialog(this, e.getMessage() + "\n( ͡° ͜ʖ ͡°)", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        if (arg0.getSource() == removePetTypeButton) {
            if (petTypeList.getSelectedValue() == null) {
                JOptionPane.showMessageDialog(this, "No pet type selected!!!\n( ͡° ͜ʖ ͡°)", "Error",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                int option = JOptionPane.showConfirmDialog(null,
                        "Do you want to remove \"" + petTypeList.getSelectedValue() + "\" account?",
                        "Delete pet type confirmation", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    this.adminController.removePetType(petTypeList.getSelectedValue());
                    this.adminController.refresh();
                }
            }
        }
    }

}
