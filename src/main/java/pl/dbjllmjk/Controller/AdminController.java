package pl.dbjllmjk.Controller;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JCheckBox;

import pl.dbjllmjk.Model.AccountData;
import pl.dbjllmjk.Model.Action;
import pl.dbjllmjk.Model.Activity;
import pl.dbjllmjk.Model.AdminData;
import pl.dbjllmjk.Model.Food;
import pl.dbjllmjk.Model.NoSuchUserException;
import pl.dbjllmjk.Model.Operation;
import pl.dbjllmjk.Model.Pet;
import pl.dbjllmjk.Model.PetTransactionException;
import pl.dbjllmjk.Model.UserData;
//import pl.dbjllmjk.View.AdminView;
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
//	private AdminView adminView;

	/**
	 * Constructor with parameter.
	 * @param controller includes reference to Main Controller.
	 */
	public AdminController(Controller controller) {
		this.controller = controller;
		this.loggedAdmin = (AdminData) controller.getLoggedAccount();
//		this.adminView = new AdminView(this);
	}

	/**
	 * Add new account.
	 * @param login user/admin login.
	 * @param password user/admin password.
	 * @param name user/admin name.
	 * @param surname user/admin surname.
	 * @param isAdmin if new account should be for admin.
	 * @throws NoSuchUserException
	 */
	public void addAccount(String login, String password, String name, String surname, boolean isAdmin)
			throws NoSuchUserException {
		if(login.trim().length() < 3 || password.trim().length() < 3 || name.trim().length() < 3 || surname.trim().length() < 3) throw new NoSuchUserException("To short fields!");
		if (isAdmin) {
			AdminData ad = null;
			for (AdminData a : this.controller.getDataRepository().getAdmins()) {
				if (a.getLogin().equals(login))
					ad = a;
			}
			if (ad != null)
				throw new NoSuchUserException("Admin " + login + " already exists");
			AdminData newAdmin = new AdminData(login, password, name, surname);
			this.controller.getDataRepository().addData(newAdmin);
		} else {
			UserData ud = null;
			for (UserData u : this.controller.getDataRepository().getUsers()) {
				if (u.getLogin().equals(login))
					ud = u;
			}
			if (ud != null)
				throw new NoSuchUserException("User " + login + " already exists");
			UserData newUser = new UserData(login, password, name, surname);
			this.controller.getDataRepository().addData(newUser);
		}
	}

	/**
	 * Remove account.
	 * @param login of account to be deleted.
	 * @throws NoSuchUserException
	 */
	public void removeAccount(String login) throws NoSuchUserException {
		UserData ud = null;
		for (UserData u : this.controller.getDataRepository().getUsers()) {
			if (u.getLogin().equals(login))
				ud = u;
		}
		if (ud == null) {
			AdminData ad = null;
			for (AdminData a : this.controller.getDataRepository().getAdmins()) {
				if (a.getLogin().equals(login))
					ad = a;
			}
			if (ad == null)
				throw new NoSuchUserException("Account not found");
			if (ad.getLogin().equals(this.loggedAdmin.getLogin()))
				throw new NoSuchUserException("Cannot remove current user!!!");
			this.controller.getDataRepository().removeAdmin(ad);
		} else
			this.controller.getDataRepository().removeUser(ud);

	}

	/**
	 * @return An array of all Admin accounts ({@link AdminData}).
	 */
	public AdminData[] getAdmins() {
		AdminData[] admins = new AdminData[this.controller.getDataRepository().getAdmins().size()];
		this.controller.getDataRepository().getAdmins().toArray(admins);
		return admins;
	}

	/**
	 * @return An array of all User accounts ({@link UserData}).
	 */
	public UserData[] getUsers() {
		UserData[] users = new UserData[this.controller.getDataRepository().getUsers().size()];
		this.controller.getDataRepository().getUsers().toArray(users);
		return users;
	}
	
	/**
	 * Remove {@link Pet} type/species.
	 * @param petType name of type to be deleted.
	 */
	public void removePetType(String petType) {
		this.controller.getDataRepository().removePetType(petType);
	}

	/**
	 * @return An array with all accounts data.
	 */
	public AccountData[] getAccounts() {
		AccountData[] accounts = null;
		List<AccountData> accList = new ArrayList<AccountData>();
		accList.addAll(this.controller.getDataRepository().getAdmins());
		accList.addAll(this.controller.getDataRepository().getUsers());
		accounts = new AccountData[accList.size()];
		accList.toArray(accounts);
		return accounts;
	}

	/**
	 * @return An array with names of {@link Pet} types.
	 */
	public String[] getAvaliablePetTypes() {
		return this.controller.getDataRepository().getAvaliablePetTypes();
	}

	/**
	 * Add {@link Pet} type/species.
	 * @param petType name
	 * @throws PetTransactionException
	 */
	public void addPetType(String petType) throws PetTransactionException {
		if (Arrays.asList(this.getAvaliablePetTypes()).contains(petType))
			throw new PetTransactionException("Pet type " + petType + " already exists!");
		this.controller.getDataRepository().addPetType(petType);
	}

	/**
	 * @return An array of available {@link Food}s for {@link Pet} type/species.
	 */
	public Food[] getAvaliableFoodTypes() {
		Food[] ret = new Food[this.controller.getDataRepository().getAllActions(new Food()).size()];
		ret = this.controller.getDataRepository().getAllActions(new Food()).toArray(ret);
		return ret;
	}
	
	/**
	 * @return An array of available {@link Activity}'ies for {@link Pet} type/species.
	 */
	public Activity[] getAvaliableActivityTypes() {
		Activity[] ret = new Activity[this.controller.getDataRepository().getAllActions(new Activity()).size()];
		ret = this.controller.getDataRepository().getAllActions(new Activity()).toArray(ret);
		return ret;
	}
	
	/**
	 * @return An array of available {@link Operation}s for {@link Pet} type/species.
	 */
	public Operation[] getAvaliableOperationTypes() {
		Operation[] ret = new Operation[this.controller.getDataRepository().getAllActions(new Operation()).size()];
		ret = this.controller.getDataRepository().getAllActions(new Operation()).toArray(ret);
		return ret;
	}

	/**
	 * Allows to connect {@link Action} with {@link Pet} type/species.
	 * @param action
	 * @return An array of available checkboxes for {@link Pet}.
	 */
	public JCheckBox[] getActionWithTypeConnections(Action action) {
		String[] petTypes = this.controller.getDataRepository().getAvaliablePetTypes();
		JCheckBox[] ret = new JCheckBox[petTypes.length];
		if (action == null) {
			for (int i = 0; i < ret.length; i++) {
				ret[i] = new JCheckBox(petTypes[i], false);
			}
			return ret;
		}
		for (int i = 0; i < ret.length; i++) {
			if (this.controller.getDataRepository().getPetTypesForAction(action).contains(petTypes[i]))
				ret[i] = new JCheckBox(petTypes[i], true);
			else
				ret[i] = new JCheckBox(petTypes[i], false);
		}
		return ret;
	}
	
	/**
	 * Add new {@link Food}.
	 * @param name
	 * @param value
	 * @throws PetTransactionException
	 */
	public void addFoodType(String name, int value) throws PetTransactionException{
		if(name.trim().length() < 3) throw new PetTransactionException("Name too short!");
		Action existing = null;
		for(Action action : this.controller.getDataRepository().getAllActions(new Food())){
			if(action.getName().equals(name)) existing = action;
		}
		if(existing != null) throw new PetTransactionException("Food already exists!");
		this.controller.getDataRepository().addAction(new Food(name, value));
	}
	
	/**
	 * Add new {@link Activity}.
	 * @param name
	 * @param value
	 * @throws PetTransactionException
	 */
	public void addActivityType(String name, int value) throws PetTransactionException{
		if(name.trim().length() < 3) throw new PetTransactionException("Name too short!");
		Action existing = null;
		for(Action action : this.controller.getDataRepository().getAllActions(new Activity())){
			if(action.getName().equals(name)) existing = action;
		}
		if(existing != null) throw new PetTransactionException("Activity already exists!");
		this.controller.getDataRepository().addAction(new Activity(name, value));
	}
	
	/**
	 * Add new {@link Operation}.
	 * @param name
	 * @param value
	 * @throws PetTransactionException
	 */
	public void addOperationType(String name, int value) throws PetTransactionException{
		if(name.trim().length() < 3) throw new PetTransactionException("Name too short!");
		Action existing = null;
		for(Action action : this.controller.getDataRepository().getAllActions(new Operation())){
			if(action.getName().equals(name)) existing = action;
		}
		if(existing != null) throw new PetTransactionException("Operation already exists!");
		this.controller.getDataRepository().addAction(new Operation(name, value));
	}
	
	/**
	 * Remove {@link Action}.
	 * @param selected {@link Action}.
	 */
	public void removeActionType(Action selected){
		this.controller.getDataRepository().removeAction(selected);
	}
	
	/**
	 * Performs update of connections between {@link Action} and {@link Pet} type.species.
	 * @param updated {@link Pet} types/species.
	 * @param selected {@link Action}
	 * @throws PetTransactionException
	 */
	public void updateActionWithTypeConnections(List<JCheckBox> updated, Action selected) throws PetTransactionException{
		boolean flag = false;
		for(JCheckBox box : updated){
			if(box.isSelected() && !this.controller.getDataRepository().getPetTypesForAction(selected).contains(box.getText())){
				this.controller.getDataRepository().addActionToPetType(selected, box.getText());
				flag = true;
			} else if(!box.isSelected() && this.controller.getDataRepository().getPetTypesForAction(selected).contains(box.getText())){
				this.controller.getDataRepository().removeActionFromPetType(selected, box.getText());
				flag = true;
			}
		}
		if(!flag) throw new PetTransactionException("No changes made!");
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
//	public AdminView getAdminView() {
//		return adminView;
//	}

	/**
	 * Performs amazingly unique and unbelievable log out.
	 */
	public void logout() {
//		this.adminView.dispose();
		this.controller.anotherLogin();
	}

	/**
	 * Refresh GAI.
	 */
	public void refresh() {
//		int index = this.adminView.getTabbedPane().getSelectedIndex();
//		this.adminView.dispose();
//		this.adminView = new AdminView(this);
//		this.adminView.getTabbedPane().setSelectedIndex(index);
	}
}
