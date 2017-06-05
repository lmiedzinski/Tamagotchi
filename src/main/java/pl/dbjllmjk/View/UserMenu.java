package pl.dbjllmjk.View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;

import pl.dbjllmjk.Controller.UserController;
import pl.dbjllmjk.Model.PetEntry;
import pl.dbjllmjk.Model.PetTransactionException;

public class UserMenu extends JPanel implements ActionListener {

    private static final long serialVersionUID = 1L;
    private JList<PetEntry> petlist;
    private JButton addPetButton, removePetButton, logoutButton, exitButton;
    private JLabel nameLabel, typeLabel, ageLabel, weightLabel, dateLabel;
    private JProgressBar happinessBar, hungerBar, healthBar;
    private UserController userController;
    private UserPet petTab;
    private PetListSelectionHandler plsh;

    public UserMenu(UserController userController, UserPet petTab) {
        this.userController = userController;
        this.petTab = petTab;
        init();
    }

    @SuppressWarnings("unchecked")
    public void init() {
        this.setLayout(new GridLayout(2, 1));
        this.plsh = new PetListSelectionHandler(this);
        this.addPetButton = new JButton("Add Pet");
        this.addPetButton.addActionListener(this);
        this.removePetButton = new JButton("Remove Pet");
        this.removePetButton.addActionListener(this);
        this.logoutButton = new JButton("Logout");
        this.logoutButton.addActionListener(this);
        this.exitButton = new JButton("Exit");
        this.exitButton.addActionListener(this);
        this.nameLabel = new JLabel("Name: ");
        this.typeLabel = new JLabel("Type: ");
        this.ageLabel = new JLabel("Age: ");
        this.weightLabel = new JLabel("Weight: ");
        this.dateLabel = new JLabel("Date of Birth: ");
        this.happinessBar = new JProgressBar();
        this.happinessBar.setValue(0);
        this.happinessBar.setMinimum(0);
        this.happinessBar.setMaximum(10);
        this.happinessBar.setString("Happiness: " + this.happinessBar.getValue());
        this.happinessBar.setStringPainted(true);
        this.hungerBar = new JProgressBar();
        this.hungerBar.setValue(0);
        this.hungerBar.setMinimum(0);
        this.hungerBar.setMaximum(10);
        this.hungerBar.setString("Hunger: " + this.hungerBar.getValue());
        this.hungerBar.setStringPainted(true);
        this.healthBar = new JProgressBar();
        this.healthBar.setValue(0);
        this.healthBar.setMinimum(0);
        this.healthBar.setMaximum(10);
        this.healthBar.setString("Health: " + this.healthBar.getValue());
        this.healthBar.setStringPainted(true);
        petlist = new JList<>(userController.userPetListToPetEntry());
        petlist.addListSelectionListener(plsh);
        petlist.setCellRenderer(new PetCellRenderer());
        petlist.setVisibleRowCount(4);
        JScrollPane pane = new JScrollPane(petlist);
        add(pane);
        JPanel bottom = new JPanel(new BorderLayout());
        JPanel bottomLeft = new JPanel();
        bottomLeft.setLayout(new BoxLayout(bottomLeft, BoxLayout.Y_AXIS));
        bottomLeft.add(addPetButton);
        bottomLeft.add(Box.createRigidArea(new Dimension(10, 10)));
        bottomLeft.add(removePetButton);
        bottomLeft.add(Box.createRigidArea(new Dimension(10, 10)));
        bottomLeft.add(logoutButton);
        bottomLeft.add(Box.createRigidArea(new Dimension(10, 10)));
        bottomLeft.add(exitButton);
        bottomLeft.setBorder(BorderFactory.createEmptyBorder(40, 10, 10, 10));
        bottom.add(bottomLeft, BorderLayout.WEST);
        JPanel bottomRight = new JPanel(new GridLayout(4, 2));
        bottomRight.add(nameLabel);
        bottomRight.add(typeLabel);
        bottomRight.add(ageLabel);
        bottomRight.add(happinessBar);
        bottomRight.add(weightLabel);
        bottomRight.add(hungerBar);
        bottomRight.add(dateLabel);
        bottomRight.add(healthBar);
        bottom.add(bottomRight, BorderLayout.EAST);
        add(bottom);
    }

    public void selectionMade() {
        try {
            String[] tab = this.userController.getPetData(this.petlist.getSelectedValue().getTitle());
            this.petTab.changeCurrentPet(tab);
            this.nameLabel.setText("Name:   " + tab[0]);
            this.typeLabel.setText("Type:   " + tab[1]);
            this.ageLabel.setText("Age:   " + tab[2]);
            this.weightLabel.setText("Weight:   " + tab[3]);
            this.dateLabel.setText("Date of Birth:   " + tab[4] + "      ");
            this.happinessBar.setValue(Integer.parseInt(tab[5]));
            this.happinessBar.setString("Happiness: " + this.happinessBar.getValue());
            this.hungerBar.setValue(Integer.parseInt(tab[6]));
            this.hungerBar.setString("Hunger: " + this.hungerBar.getValue());
            this.healthBar.setValue(Integer.parseInt(tab[7]));
            this.healthBar.setString("Health: " + this.healthBar.getValue());
        } catch (PetTransactionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void selectionMade(String[] tab) {
        this.petTab.changeCurrentPet(tab);
        this.nameLabel.setText("Name:   " + tab[0]);
        this.typeLabel.setText("Type:   " + tab[1]);
        this.ageLabel.setText("Age:   " + tab[2]);
        this.weightLabel.setText("Weight:   " + tab[3]);
        this.dateLabel.setText("Date of Birth:   " + tab[4] + "      ");
        this.happinessBar.setValue(Integer.parseInt(tab[5]));
        this.happinessBar.setString("Happiness: " + this.happinessBar.getValue());
        this.hungerBar.setValue(Integer.parseInt(tab[6]));
        this.hungerBar.setString("Hunger: " + this.hungerBar.getValue());
        this.healthBar.setValue(Integer.parseInt(tab[7]));
        this.healthBar.setString("Health: " + this.healthBar.getValue());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == exitButton) {
            System.exit(0);
        }
        if (e.getSource() == logoutButton) {
            this.userController.logout();
        }
        if (e.getSource() == addPetButton) {
            Object[] possibilities = this.userController.getAvaliablePetTypes();
            if (possibilities.length != 0) {
                String type = (String) JOptionPane.showInputDialog(this, "Choose pet which you want to be created:",
                        "Create Dialog", JOptionPane.PLAIN_MESSAGE, null, possibilities, "");
                if (type == null) {
                    type = "";
                }
                String name = (String) JOptionPane.showInputDialog(this,
                        "Choose name (3<x<25 char.) for your extraordinary " + type + ":", "Create Dialog",
                        JOptionPane.PLAIN_MESSAGE, null, null, "");
                if (name == null) {
                    name = "";
                }
                try {
                    this.userController.addPet(name, type);
                    this.userController.refresh();
                } catch (PetTransactionException e1) {
                    JOptionPane.showMessageDialog(this, e1.getMessage() + "\n( ͡° ͜ʖ ͡°)", "Warning",
                            JOptionPane.WARNING_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "No pets! Sorry!\n( ͡° ͜ʖ ͡°)", "Warning",
                        JOptionPane.WARNING_MESSAGE);
            }
        }
        if (e.getSource() == removePetButton) {
            if (this.petlist.getSelectedValue() == null) {
                JOptionPane.showMessageDialog(this, "No pet selected!!!\n( ͡° ͜ʖ ͡°)", "Error",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                int option = JOptionPane.showConfirmDialog(null,
                        "Do you want to remove selected pet (" + this.petlist.getSelectedValue().getTitle() + ")?",
                        "Pet deletion confirm", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    try {
                        this.userController.deletePet(this.petlist.getSelectedValue().getTitle());
                        this.userController.refresh();
                    } catch (PetTransactionException e1) {
                        JOptionPane.showMessageDialog(this, e1.getMessage() + "\n( ͡° ͜ʖ ͡°)", "Warning",
                                JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        }
    }

    public JList<PetEntry> getPetlist() {
        return petlist;
    }
}
