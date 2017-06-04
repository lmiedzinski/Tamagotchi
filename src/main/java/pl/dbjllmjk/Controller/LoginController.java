package pl.dbjllmjk.Controller;
import pl.dbjllmjk.Model.AccountData;
import pl.dbjllmjk.Model.BadPasswordException;
import pl.dbjllmjk.Model.NoSuchUserException;
import pl.dbjllmjk.Model.UserData;
import pl.dbjllmjk.View.LoginView;

/**
 * Controls the Logging Module.
 */
public class LoginController {
	private Controller controller;
	public LoginController(Controller controller) {
		this.controller = controller;
		new LoginView(this);
	}

	/**
	 * Perform logging.
	 * @param login user/admin login
	 * @param password user/admin password
	 * @throws NoSuchUserException
	 * @throws BadPasswordException
	 */
	public void tryToLog(String login, String password) throws NoSuchUserException, BadPasswordException {
		AccountData selectedAccount = null;
		for (AccountData accountData : controller.getDataRepository().getAdmins()) {
			if (accountData.getLogin().equals(login))
				selectedAccount = accountData;
		}
		if (selectedAccount == null) {
			for (AccountData accountData : controller.getDataRepository().getUsers()) {
				if (accountData.getLogin().equals(login))
					selectedAccount = accountData;
			}
		}
		if (selectedAccount == null)
			throw new NoSuchUserException();
		if (!selectedAccount.getPassword().equals(password)) {
			throw new BadPasswordException();
		}
		controller.afterLogin(selectedAccount);
	}

	/**
	 * Process of registration.
	 * @param login new account login
	 * @param password new account password
	 * @param name new account name
	 * @param surname new account surname
	 * @throws NoSuchUserException
	 */
	public void addAccount(String login, String password, String name, String surname) throws NoSuchUserException {
		if(login.trim().length() < 3 || password.trim().length() < 3 || name.trim().length() < 3 || surname.trim().length() < 3) throw new NoSuchUserException("To short fields!");
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
