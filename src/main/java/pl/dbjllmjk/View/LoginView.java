package pl.dbjllmjk.View;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

import pl.dbjllmjk.Controller.LoginController;
import pl.dbjllmjk.Exceptions.BadPasswordException;
import pl.dbjllmjk.Exceptions.NoSuchUserException;

public class LoginView extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private JTextField loginField;
    private JPasswordField passField;
    private JButton loginButton;
    private JButton exitButton;
    private JLabel loginLabel;
    private LoginController loginController;
    private JLabel passwordLabel;
    private JButton registrationButton;
    private JButton authorsButton;

    public LoginView(LoginController loginController) throws HeadlessException {
        super("Tamagotchi - DBJLLMJK");
        this.loginController = loginController;
        this.setResizable(false);
        init();
        this.setSize(500, 800);
        this.setLocationRelativeTo(null);
        this.setLayout(new GridLayout(0, 1));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        if (arg0.getSource() == exitButton) {
            System.exit(0);
        }
        if (arg0.getSource() == registrationButton) {
            String[] possibilities = {"user"};
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
                    if (combo.getSelectedItem() == "user") {
                        this.loginController.addAccount(login.getText(), password.getText(), name.getText(),
                                surname.getText());
                    }
                } catch (NoSuchUserException e) {
                    JOptionPane.showMessageDialog(this, e.getMessage() + "\n( ͡° ͜ʖ ͡°)", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        if (arg0.getSource() == loginButton) {
            try {
                loginController.tryToLog(this.loginField.getText(), new String(this.passField.getPassword()));
                this.setVisible(false);
                dispose();
            } catch (NoSuchUserException e) {
                JOptionPane.showMessageDialog(this, "No such user!\n( ͡° ͜ʖ ͡°)", "Warning",
                        JOptionPane.WARNING_MESSAGE);
            } catch (BadPasswordException e) {
                JOptionPane.showMessageDialog(this, "Bad password!\n( ͡° ͜ʖ ͡°)", "Warning",
                        JOptionPane.WARNING_MESSAGE);
            }
        }
        if (arg0.getSource() == authorsButton) {
            JPanel authorsPanel = new JPanel(new GridLayout(0, 1));
            authorsPanel.add(new JLabel("Authors:"));
            authorsPanel.add(new JLabel("Jacek Lewański 195643"));
            authorsPanel.add(new JLabel("Łukasz Miedziński 195659"));
            authorsPanel.add(new JLabel("Damian Bartosik 195559"));
            authorsPanel.add(new JLabel("Jakub Krysiak 195634"));
            authorsPanel.add(new JLabel("( ͡° ͜ʖ ͡°) ( ͡° ͜ʖ ͡°) ( ͡° ͜ʖ ͡°) ( ͡° ͜ʖ ͡°) ( ͡° ͜ʖ ͡°)"));
            JOptionPane.showConfirmDialog(null, authorsPanel, "Authors",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        }
    }

    private void init() {
        this.loginField = new JTextField("", 13);
        this.passField = new JPasswordField("", 13);
        this.loginButton = new JButton("Login");
        this.exitButton = new JButton("Exit");
        this.loginLabel = new JLabel("  Login: ");
        this.passwordLabel = new JLabel("  Password:");
        this.registrationButton = new JButton("Register");
        this.authorsButton = new JButton("Authors");
        this.authorsButton.addActionListener(this);
        JPanel up = new JPanel(new GridLayout(0, 1));
        JPanel down = new JPanel(new GridLayout(0, 4));
        up.add(this.loginLabel);
        up.add(this.loginField);
        up.add(this.passwordLabel);
        up.add(this.passField);
        down.add(this.loginButton);
        down.add(this.registrationButton);
        down.add(this.authorsButton);
        down.add(this.exitButton);
        add(up);
        add(down);
        this.loginButton.addActionListener(this);
        this.exitButton.addActionListener(this);
        this.registrationButton.addActionListener(this);
        this.passField.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    loginButton.doClick();
                }
            }
        });
    }
}
