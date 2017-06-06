package pl.dbjllmjk.Controller;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import pl.dbjllmjk.Model.AccountData;
import pl.dbjllmjk.Model.Action;
import pl.dbjllmjk.Model.Activity;
import pl.dbjllmjk.Model.AdminData;
import pl.dbjllmjk.Model.Food;
import pl.dbjllmjk.Exceptions.NoSuchUserException;
import pl.dbjllmjk.Model.Operation;
import pl.dbjllmjk.Model.Pet;
import pl.dbjllmjk.Exceptions.PetTransactionException;
import pl.dbjllmjk.Model.UserData;
import pl.dbjllmjk.Utils.PasswordHashConverter;
import pl.dbjllmjk.View.AdminView;

/**
 * Logic Layer Implementation for Admins.
 */
public class AdminController {

    /**
     * Main Controller Field.
     */
    private Controller controller;

    /**
     * Currently logged Admin.
     */
    private AdminData loggedAdmin;

    /**
     * Graphic Admin Interface field.
     */
    private AdminView adminView;

    /**
     * Constructor with parameter.
     *
     * @param controller includes reference to Main Controller.
     */
    public AdminController(Controller controller) {
        this.controller = controller;
        this.loggedAdmin = (AdminData) controller.getLoggedAccount();
        this.adminView = new AdminView(this);
    }
    
    public AdminController(Controller controller,AdminView adminView) {
        this.controller = controller;
        this.loggedAdmin = (AdminData) controller.getLoggedAccount();
        this.adminView = adminView;
    }

    /**
     * Add new account.
     *
     * @param login user/admin login.
     * @param password user/admin password.
     * @param name user/admin name.
     * @param surname user/admin surname.
     * @param isAdmin if new account should be for admin.
     * @throws NoSuchUserException
     * @throws UnsupportedEncodingException
     * @throws NoSuchAlgorithmException
     */
    public void addAccount(String login, String password, String name, String surname, boolean isAdmin)
            throws NoSuchUserException, UnsupportedEncodingException, NoSuchAlgorithmException {
        if (login.trim().length() < 3 || password.trim().length() < 3 || name.trim().length() < 3 || surname.trim().length() < 3) {
            throw new NoSuchUserException("To short fields!");
        }
        if (isAdmin) {
            Optional<AdminData> oad = this.controller.getDataRepository()
                    .getAdmins()
                    .stream()
                    .filter(l -> l.getLogin().equals(login))
                    .findFirst();
            AdminData ad = (oad.isPresent()) ? oad.get() : null;
            if (ad != null) {
                throw new NoSuchUserException("Admin " + login + " already exists");
            }
            AdminData newAdmin = new AdminData(login, PasswordHashConverter.hashPassword(login, password), name, surname);
            this.controller.getDataRepository().addData(newAdmin);
        } else {
            Optional<UserData> oud = this.controller.getDataRepository()
                    .getUsers()
                    .stream()
                    .filter(l -> l.getLogin().equals(login))
                    .findFirst();
            UserData ud = (oud.isPresent()) ? oud.get() : null;
            if (ud != null) {
                throw new NoSuchUserException("User " + login + " already exists");
            }
            UserData newUser = new UserData(login, PasswordHashConverter.hashPassword(login, password), name, surname);
            this.controller.getDataRepository().addData(newUser);
        }
    }

    /**
     * Remove account.
     *
     * @param login of account to be deleted.
     * @throws NoSuchUserException
     */
    public void removeAccount(String login) throws NoSuchUserException {
        Optional<UserData> oud = this.controller.getDataRepository()
                .getUsers()
                .stream()
                .filter(l -> l.getLogin().equals(login))
                .findFirst();
        UserData ud = (oud.isPresent()) ? oud.get() : null;
        if (ud == null) {
            Optional<AdminData> oad = this.controller.getDataRepository()
                    .getAdmins()
                    .stream()
                    .filter(l -> l.getLogin().equals(login))
                    .findFirst();
            AdminData ad = (oad.isPresent()) ? oad.get() : null;
            if (ad == null) {
                throw new NoSuchUserException("Account not found");
            }
            if (ad.getLogin().equals(this.loggedAdmin.getLogin())) {
                throw new NoSuchUserException("Cannot remove current user!!!");
            }
            this.controller.getDataRepository().removeAdmin(ad);
        } else {
            this.controller.getDataRepository().removeUser(ud);
        }

    }

    /**
     * @return An array of all Admin accounts ({@link AdminData}).
     */
    public AdminData[] getAdmins() {
        List<AdminData> list = this.controller.getDataRepository().getAdmins();
        return list.toArray(new AdminData[list.size()]);
    }

    /**
     * @return An array of all User accounts ({@link UserData}).
     */
    public UserData[] getUsers() {
        List<UserData> list = this.controller.getDataRepository().getUsers();
        return list.toArray(new UserData[list.size()]);
    }

    /**
     * Remove {@link Pet} type/species.
     *
     * @param petType name of type to be deleted.
     */
    public void removePetType(String petType) {
        this.controller.getDataRepository().removePetType(petType);
    }

    /**
     * @return An array with all accounts data.
     */
    public AccountData[] getAccounts() {
        List<AccountData> accountsList = Stream.concat(
                this.controller.getDataRepository().getAdmins().stream(),
                this.controller.getDataRepository().getUsers().stream())
                .collect(Collectors.toList());
        return accountsList.toArray(new AccountData[accountsList.size()]);
    }

    /**
     * @return An array with names of {@link Pet} types.
     */
    public String[] getAvaliablePetTypes() {
        return this.controller.getDataRepository().getAvaliablePetTypes();
    }

    /**
     * Add {@link Pet} type/species.
     *
     * @param petType name
     * @throws PetTransactionException
     */
    public void addPetType(String petType) throws PetTransactionException {
        if (Arrays.asList(this.getAvaliablePetTypes()).contains(petType)) {
            throw new PetTransactionException("Pet type " + petType + " already exists!");
        }
        this.controller.getDataRepository().addPetType(petType);
    }

    /**
     * @return An array of available {@link Food}s for {@link Pet} type/species.
     */
    public Food[] getAvaliableFoodTypes() {
        List<Food> list = this.controller.getDataRepository().getAllActions(new Food());
        return list.toArray(new Food[list.size()]);
    }

    /**
     * @return An array of available {@link Activity}'ies for {@link Pet}
     * type/species.
     */
    public Activity[] getAvaliableActivityTypes() {
        List<Activity> list = this.controller.getDataRepository().getAllActions(new Activity());
        return list.toArray(new Activity[list.size()]);
    }

    /**
     * @return An array of available {@link Operation}s for {@link Pet}
     * type/species.
     */
    public Operation[] getAvaliableOperationTypes() {
        List<Operation> list = this.controller.getDataRepository().getAllActions(new Operation());
        return list.toArray(new Operation[list.size()]);
    }

    /**
     * Allows to connect {@link Action} with {@link Pet} type/species.
     *
     * @param action
     * @return An array of available checkboxes for {@link Pet}.
     */
    public Map<String, Boolean> getActionWithTypeConnections(Action action) {
        Map<String, Boolean> ret = new HashMap<>();
        Arrays.asList(this.controller.getDataRepository().getAvaliablePetTypes())
                .stream()
                .forEach((petType) -> {
                    if (action == null) {
                        ret.put(petType, false);
                    } else {
                        ret.put(petType, (this.controller.getDataRepository().getPetTypesForAction(action).contains(petType)));
                    }
                });
        return ret;
    }

    /**
     * Add new {@link Food}.
     *
     * @param name
     * @param value
     * @throws PetTransactionException
     */
    public void addFoodType(String name, int value) throws PetTransactionException {
        if (name.trim().length() < 3) {
            throw new PetTransactionException("Name too short!");
        }
        Optional<Food> existing = this.controller.getDataRepository()
                .getAllActions(new Food())
                .stream()
                .filter(l -> l.getName().equals(name))
                .findFirst();
        if (existing.isPresent()) {
            throw new PetTransactionException("Food already exists!");
        }
        this.controller.getDataRepository().addAction(new Food(name, value));
    }

    /**
     * Add new {@link Activity}.
     *
     * @param name
     * @param value
     * @throws PetTransactionException
     */
    public void addActivityType(String name, int value) throws PetTransactionException {
        if (name.trim().length() < 3) {
            throw new PetTransactionException("Name too short!");
        }
        Optional<Activity> existing = this.controller.getDataRepository()
                .getAllActions(new Activity())
                .stream()
                .filter(l -> l.getName().equals(name))
                .findFirst();
        if (existing.isPresent()) {
            throw new PetTransactionException("Activity already exists!");
        }
        this.controller.getDataRepository().addAction(new Activity(name, value));
    }

    /**
     * Add new {@link Operation}.
     *
     * @param name
     * @param value
     * @throws PetTransactionException
     */
    public void addOperationType(String name, int value) throws PetTransactionException {
        if (name.trim().length() < 3) {
            throw new PetTransactionException("Name too short!");
        }
        Optional<Operation> existing = this.controller.getDataRepository()
                .getAllActions(new Operation())
                .stream()
                .filter(l -> l.getName().equals(name))
                .findFirst();
        if (existing.isPresent()) {
            throw new PetTransactionException("Operation already exists!");
        }
        this.controller.getDataRepository().addAction(new Operation(name, value));
    }

    /**
     * Remove {@link Action}.
     *
     * @param selected {@link Action}.
     */
    public void removeActionType(Action selected) {
        this.controller.getDataRepository().removeAction(selected);
    }

    /**
     * Performs update of connections between {@link Action} and {@link Pet}
     * type.species.
     *
     * @param updated {@link Pet} types/species.
     * @param selected {@link Action}
     * @throws PetTransactionException
     */
    public void updateActionWithTypeConnections(Map<String, Boolean> updated, Action selected) throws PetTransactionException {
        boolean[] flag = new boolean[]{false};
        updated.forEach((key, value) -> {
            if (value && !this.controller.getDataRepository().getPetTypesForAction(selected).contains(key)) {
                this.controller.getDataRepository().addActionToPetType(selected, key);
                flag[0] = true;
            } else if (!value && this.controller.getDataRepository().getPetTypesForAction(selected).contains(key)) {
                this.controller.getDataRepository().removeActionFromPetType(selected, key);
                flag[0] = true;
            }
        });
        if (!flag[0]) {
            throw new PetTransactionException("No changes made!");
        }
    }

    /**
     * @return Currently logged Admin.
     */
    public AdminData getLoggedAdmin() {
        return loggedAdmin;
    }

    /**
     * @return Reference to GAI.
     */
    public AdminView getAdminView() {
        return adminView;
    }

    /**
     * Performs amazingly unique and unbelievable log out.
     */
    public void logout() {
        this.adminView.dispose();
        this.controller.anotherLogin();
    }

    /**
     * Refresh GAI.
     */
    public void refresh() {
        int index = this.adminView.getTabbedPane().getSelectedIndex();
        this.adminView.dispose();
        this.adminView = new AdminView(this);
        this.adminView.getTabbedPane().setSelectedIndex(index);
    }
}
