package pl.dbjllmjk.Model;

/**
 * Class representing admin account. Extends from {@link AccountData}.
 */
public class AdminData extends AccountData {
	/**
	 * Constuctor with parameters.
	 * @see AccountData#AccountData(String, String, String, String)
	 */
	public AdminData(String login, String password, String name, String surname) {
		super(login, password, name, surname);
	}

	/**
	 * @return String in form: [login] ([name],[surname]) (Admin)
	 */
	@Override
	public String toString() {
		return super.toString() + "   (Admin)";
	}
}
