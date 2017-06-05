package pl.dbjllmjk.View;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import pl.dbjllmjk.Controller.AdminController;
import pl.dbjllmjk.Model.AccountData;
import pl.dbjllmjk.Model.NoSuchUserException;

public class AccountsTab extends JPanel implements ActionListener {

    private static final long serialVersionUID = 1L;
    private AdminController adminController;
    private JList<AccountData> usersList;
    private JButton addUserButton, removeUserButton;

    public AccountsTab(AdminController adminController) {
        this.adminController = adminController;
        this.setLayout(new GridLayout(1, 2));
        init();
    }

    public void init() {
        AccountData[] accounts = this.adminController.getAccounts();
        usersList = new JList<>(accounts);
        JPanel leftSide = new JPanel(new GridLayout(1, 1));
        leftSide.add(usersList);
        this.add(leftSide);
        JPanel rightSide = new JPanel(new GridLayout(2, 1));
        addUserButton = new JButton("Add user");
        addUserButton.addActionListener(this);
        removeUserButton = new JButton("Remove user");
        removeUserButton.addActionListener(this);
        rightSide.add(addUserButton);
        rightSide.add(removeUserButton);
        this.add(rightSide);
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        if (arg0.getSource() == addUserButton) {
            String[] possibilities = {"admin", "user"};
            JComboBox<String> combo = new JComboBox<>(possibilities);
            JTextField login = new JTextField("");
            JTextField password = new JTextField("");
            JTextField name = new JTextField("");
            JTextField surname = new JTextField("");
            JPanel userForm = new JPanel(new GridLayout(0, 1));
            userForm.add(new JLabel("Account type:"));
            userForm.add(combo);
            userForm.add(new JLabel("Login:"));
            userForm.add(login);
            userForm.add(new JLabel("Password:"));
            userForm.add(password);
            userForm.add(new JLabel("Name:"));
            userForm.add(name);
            userForm.add(new JLabel("Surname:"));
            userForm.add(surname);
            int result = JOptionPane.showConfirmDialog(null, userForm, "Create new account",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (result == JOptionPane.OK_OPTION) {

                try {
                    if (combo.getSelectedItem() == "admin") {
                        this.adminController.addAccount(login.getText(), password.getText(), name.getText(),
                                surname.getText(), true);
                    } else if (combo.getSelectedItem() == "user") {
                        this.adminController.addAccount(login.getText(), password.getText(), name.getText(),
                                surname.getText(), false);
                    }
                    this.adminController.refresh();
                } catch (NoSuchUserException e) {
                    JOptionPane.showMessageDialog(this, e.getMessage() + "\n( ͡° ͜ʖ ͡°)", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        if (arg0.getSource() == removeUserButton) {
            if (usersList.getSelectedValue() == null) {
                JOptionPane.showMessageDialog(this, "No account selected!!!\n( ͡° ͜ʖ ͡°)", "Error",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                int option = JOptionPane.showConfirmDialog(null,
                        "Do you want to remove \"" + usersList.getSelectedValue().getLogin() + "\" account?",
                        "Delete account confirmation", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    try {
                        this.adminController.removeAccount(usersList.getSelectedValue().getLogin());
                        this.adminController.refresh();
                    } catch (NoSuchUserException e) {
                        JOptionPane.showMessageDialog(this, e.getMessage() + "\n( ͡° ͜ʖ ͡°)", "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }
    }

}
