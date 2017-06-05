package pl.dbjllmjk.View;

import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import pl.dbjllmjk.Controller.AdminController;

public class AdminView extends JFrame implements WindowListener {

    private static final long serialVersionUID = 1L;
    private AdminController adminController;
    private JTabbedPane tabbedPane;
    private AccountsTab accountsTab;
    private PetTypesTab petTypesTab;
    private FoodTypesTab foodTypesTab;
    private ActivityTypesTab activityTypesTab;
    private OperationTypesTab operationTypesTab;

    public AdminView(AdminController adminController) throws HeadlessException {
        super("Administrator Panel: " + adminController.getLoggedAdmin().getLogin() + " - Tamagotchi DBJLLMJK");
        this.adminController = adminController;
        this.setResizable(false);
        this.addWindowListener(this);
        init();
        this.setLocation(200, 200);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setSize(new Dimension(700, 500));
        setVisible(true);
    }

    private void init() {
        this.tabbedPane = new JTabbedPane();
        this.accountsTab = new AccountsTab(this.adminController);
        this.petTypesTab = new PetTypesTab(this.adminController);
        this.foodTypesTab = new FoodTypesTab(this.adminController);
        this.activityTypesTab = new ActivityTypesTab(this.adminController);
        this.operationTypesTab = new OperationTypesTab(this.adminController);
        this.tabbedPane.addTab("Accounts", this.accountsTab);
        this.tabbedPane.addTab("Pet types", this.petTypesTab);
        this.tabbedPane.addTab("Food types", this.foodTypesTab);
        this.tabbedPane.addTab("Activity types", this.activityTypesTab);
        this.tabbedPane.addTab("Operation types", this.operationTypesTab);
        this.add(this.tabbedPane);
    }

    @Override
    public void windowActivated(WindowEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void windowClosed(WindowEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void windowClosing(WindowEvent arg0) {
        this.adminController.logout();

    }

    @Override
    public void windowDeactivated(WindowEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void windowDeiconified(WindowEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void windowIconified(WindowEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void windowOpened(WindowEvent arg0) {
        // TODO Auto-generated method stub

    }

    public AdminController getAdminController() {
        return adminController;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public JTabbedPane getTabbedPane() {
        return tabbedPane;
    }

    public AccountsTab getAccountsTab() {
        return accountsTab;
    }

    public PetTypesTab getPetTypesTab() {
        return petTypesTab;
    }

    public FoodTypesTab getFoodTypesTab() {
        return foodTypesTab;
    }

    public ActivityTypesTab getActivityTypesTab() {
        return activityTypesTab;
    }

    public OperationTypesTab getOperationTypesTab() {
        return operationTypesTab;
    }
}
