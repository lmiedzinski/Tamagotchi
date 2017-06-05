package pl.dbjllmjk.View;

import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import pl.dbjllmjk.Controller.UserController;
import pl.dbjllmjk.Model.PetTransactionException;

public class UserView extends JFrame implements WindowListener, ActionListener {

    private static final long serialVersionUID = 1L;
    private UserController userController;
    private JTabbedPane tabbedPane;
    private UserPet petTab;
    private UserMenu menuTab;
    private JMenuItem menuItem1, menuItem2;

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public UserPet getPetTab() {
        return petTab;
    }

    public UserMenu getMenuTab() {
        return menuTab;
    }

    public UserView(UserController userController) throws HeadlessException {
        super("User Panel: " + userController.getLoggedUser().getLogin() + " - Tamagotchi DBJLLMJK");
        this.userController = userController;
        this.setResizable(false);
        this.addWindowListener(this);
        init();
        this.setLocation(200, 200);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setSize(new Dimension(700, 500));
        setVisible(true);
    }

    private void init() {
        JMenuBar menuBar;
        JMenu menu;
        menuBar = new JMenuBar();
        menu = new JMenu("File");
        menuItem1 = new JMenuItem("Import pet...");
        menuItem2 = new JMenuItem("Export pet...");
        menuItem1.addActionListener(this);
        menuItem2.addActionListener(this);
        menu.add(menuItem1);
        menu.add(menuItem2);
        menuBar.add(menu);
        this.setJMenuBar(menuBar);
        this.tabbedPane = new JTabbedPane();
        petTab = new UserPet(this.userController);
        menuTab = new UserMenu(this.userController, petTab);
        this.tabbedPane.addTab("Menu", menuTab);
        this.tabbedPane.addTab("Pet", petTab);
        add(this.tabbedPane);
    }

    public void windowClosing(WindowEvent e) {
        this.userController.logout();
    }

    @Override
    public void windowActivated(WindowEvent arg0) {

    }

    @Override
    public void windowClosed(WindowEvent arg0) {

    }

    @Override
    public void windowDeactivated(WindowEvent arg0) {

    }

    @Override
    public void windowDeiconified(WindowEvent arg0) {

    }

    @Override
    public void windowIconified(WindowEvent arg0) {

    }

    @Override
    public void windowOpened(WindowEvent arg0) {

    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        if (arg0.getSource() == this.menuItem1) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Select file with your pet");
            fileChooser.setAcceptAllFileFilterUsed(false);
            fileChooser.setMultiSelectionEnabled(false);
            fileChooser.setFileFilter(new FileNameExtensionFilter("XML File", "xml"));
            int responce = fileChooser.showOpenDialog(this);
            switch (responce) {
                case JFileChooser.APPROVE_OPTION:
                    try {
                        this.userController.importPet(fileChooser.getSelectedFile().getAbsolutePath(), false);
                        JOptionPane.showMessageDialog(this, "Success! Pet imported!\n( ͡° ͜ʖ ͡°)", "Success",
                                JOptionPane.INFORMATION_MESSAGE);
                        this.userController.refresh();
                    } catch (PetTransactionException e) {
                        if (e.getMessage().contains("You already have pet")) {
                            int option = JOptionPane.showConfirmDialog(null,
                                    e.getMessage() + "\nDo you want to replace current pet with new?", "Pet already exist",
                                    JOptionPane.YES_NO_OPTION);
                            if (option == JOptionPane.YES_OPTION) {
                                try {
                                    this.userController.importPet(fileChooser.getSelectedFile().getAbsolutePath(), true);
                                    JOptionPane.showMessageDialog(this, "Success! Pet imported!\n( ͡° ͜ʖ ͡°)", "Success",
                                            JOptionPane.INFORMATION_MESSAGE);
                                    this.userController.refresh();
                                } catch (PetTransactionException e1) {
                                    JOptionPane.showMessageDialog(this, e.getMessage() + "\n( ͡° ͜ʖ ͡°)", "Error",
                                            JOptionPane.ERROR_MESSAGE);
                                }
                            }
                        } else {
                            JOptionPane.showMessageDialog(this, e.getMessage() + "\n( ͡° ͜ʖ ͡°)", "Error",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    break;
                // do nothing
                case JFileChooser.CANCEL_OPTION:
                    break;
                default:
                    JOptionPane.showMessageDialog(this, "Error opening selected file!\n( ͡° ͜ʖ ͡°)", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    break;
            }
        }
        if (arg0.getSource() == this.menuItem2) {
            if (this.menuTab.getPetlist().getSelectedValue() == null) {
                JOptionPane.showMessageDialog(this, "No pet selected!!!\n( ͡° ͜ʖ ͡°)", "Error",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Select file with your pet");
                fileChooser.setAcceptAllFileFilterUsed(false);
                fileChooser.setMultiSelectionEnabled(false);
                fileChooser.setFileFilter(new FileNameExtensionFilter("XML File", "xml"));
                fileChooser.setSelectedFile(new File(this.menuTab.getPetlist().getSelectedValue().getTitle()));
                int responce = fileChooser.showSaveDialog(this);
                if (responce == JFileChooser.APPROVE_OPTION) {
                    try {
                        this.userController.exportPet(fileChooser.getSelectedFile().getAbsolutePath(),
                                fileChooser.getSelectedFile().getName(),
                                this.menuTab.getPetlist().getSelectedValue().getTitle());
                        int option = JOptionPane.showConfirmDialog(null,
                                "Pet saved!\nDo you want to remove saved pet from your account?", "Remove pet",
                                JOptionPane.YES_NO_OPTION);
                        if (option == JOptionPane.YES_OPTION) {
                            this.userController.deletePet(this.menuTab.getPetlist().getSelectedValue().getTitle());
                            JOptionPane.showMessageDialog(this, "Saved pet removed from your account!\n( ͡° ͜ʖ ͡°)", "Success",
                                    JOptionPane.INFORMATION_MESSAGE);
                            this.userController.refresh();
                        }
                    } catch (PetTransactionException e) {
                        JOptionPane.showMessageDialog(this, e.getMessage() + "\n( ͡° ͜ʖ ͡°)", "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }

    }
}
