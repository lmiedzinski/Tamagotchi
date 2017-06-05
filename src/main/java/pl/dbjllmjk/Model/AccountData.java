package pl.dbjllmjk.Model;

/**
 * An abstract class representing structure of accounts in application.
 *
 */
public abstract class AccountData {

    /**
     * Login of user or admin.
     */
    protected String login;
    /**
     * Password of user or admin.
     */
    protected String password;
    /**
     * Real user name.
     */
    protected String name;
    /**
     * User surname.
     */
    protected String surname;

    /**
     * All-parameter constructor.
     *
     * @param login user/admin login
     * @param password user/admin password
     * @param name user/admin name
     * @param surname user/admin surname
     */
    public AccountData(String login, String password, String name, String surname) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
    }

    /**
     * Password getter.
     *
     * @return Password of user or admin.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Allows to set password of user/admin.
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Login getter.
     *
     * @return Login of user/admin.
     */
    public String getLogin() {
        return login;
    }

    /**
     * Name getter.
     *
     * @return Name of user/admin.
     */
    public String getName() {
        return name;
    }

    /**
     * Surname getter.
     *
     * @return Surname of user/admin.
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Overriden method toString().
     *
     * @return String in form: [login] ([name],[surname])
     */
    @Override
    public String toString() {
        return login + " (" + name + " " + surname + ")";
    }

}
