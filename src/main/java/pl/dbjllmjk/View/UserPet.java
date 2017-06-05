package pl.dbjllmjk.View;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import pl.dbjllmjk.Controller.UserController;
import pl.dbjllmjk.Model.Action;
import pl.dbjllmjk.Exceptions.PetTransactionException;

public class UserPet extends JPanel implements ActionListener {

    private static final long serialVersionUID = 1L;
    private UserController userController;
    private JButton feedButton, playButton, operationButton;
    private JLabel nameLabel, typeLabel, ageLabel, weightLabel, dateLabel;
    private JProgressBar happinessBar, hungerBar, healthBar;
    private ImageImplement panel;

    public UserPet(UserController userController) {
        this.userController = userController;
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        panel = new ImageImplement(new ImageIcon("img/nopet_happy_big.png").getImage());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        this.add(panel);
        this.setVisible(true);
        init();
    }

    private void init() {
        this.feedButton = new JButton("Feed pet");
        this.feedButton.addActionListener(this);
        this.playButton = new JButton("Play with Pet");
        this.playButton.addActionListener(this);
        this.operationButton = new JButton("Make operation on Pet");
        this.operationButton.addActionListener(this);
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
        JPanel infoRight = new JPanel(new GridLayout(4, 2));
        infoRight.add(nameLabel);
        infoRight.add(typeLabel);
        infoRight.add(ageLabel);
        infoRight.add(happinessBar);
        infoRight.add(weightLabel);
        infoRight.add(hungerBar);
        infoRight.add(dateLabel);
        infoRight.add(healthBar);
        infoRight.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        this.add(infoRight);
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.X_AXIS));
        buttonsPanel.add(feedButton);
        buttonsPanel.add(Box.createRigidArea(new Dimension(10, 10)));
        buttonsPanel.add(playButton);
        buttonsPanel.add(Box.createRigidArea(new Dimension(10, 10)));
        buttonsPanel.add(operationButton);
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        this.add(buttonsPanel);
    }

    public void changeCurrentPet(String[] tab) {
        this.removeAll();
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        panel = new ImageImplement(new ImageIcon("img/" + tab[1] + "_happy_big.png").getImage());
        this.add(panel);
        init();
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
        this.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        if (arg0.getSource() == feedButton) {
            Object[] possibilities = null;
            String name = "";
            if (this.nameLabel.getText().length() > 8) {
                name = this.nameLabel.getText().substring(8);
            }
            try {
                possibilities = this.userController.getAvaliableFoodTypesForPet(name);
                Action type = (Action) JOptionPane.showInputDialog(this, "Choose food which you want to be eaten:",
                        "Feed Dialog", JOptionPane.PLAIN_MESSAGE, null, possibilities, "");
                this.userController.feedPet(this.nameLabel.getText().substring(8), type);
            } catch (PetTransactionException e) {
                JOptionPane.showMessageDialog(this, e.getMessage() + "\n( ͡° ͜ʖ ͡°)", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
        if (arg0.getSource() == this.playButton) {
            Object[] possibilities = null;
            String name = "";
            if (this.nameLabel.getText().length() > 8) {
                name = this.nameLabel.getText().substring(8);
            }
            try {
                possibilities = this.userController.getAvaliableActivityTypesForPet(name);
                Action type = (Action) JOptionPane.showInputDialog(this, "Choose activity which you want to be done:",
                        "Activity Dialog", JOptionPane.PLAIN_MESSAGE, null, possibilities, "");
                this.userController.playWithPet(this.nameLabel.getText().substring(8), type);
            } catch (PetTransactionException e) {
                JOptionPane.showMessageDialog(this, e.getMessage() + "\n( ͡° ͜ʖ ͡°)", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
        if (arg0.getSource() == this.operationButton) {
            Object[] possibilities = null;
            String name = "";
            if (this.nameLabel.getText().length() > 8) {
                name = this.nameLabel.getText().substring(8);
            }
            try {
                possibilities = this.userController.getAvaliableOperationTypesForPet(name);
                Action type = (Action) JOptionPane.showInputDialog(this, "Choose operation which you want to be done:",
                        "Operation Dialog", JOptionPane.PLAIN_MESSAGE, null, possibilities, "");
                this.userController.makeOperationOnPet(this.nameLabel.getText().substring(8), type);
            } catch (PetTransactionException e) {
                JOptionPane.showMessageDialog(this, e.getMessage() + "\n( ͡° ͜ʖ ͡°)", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }

    }

    public UserController getUserController() {
        return userController;
    }
}
