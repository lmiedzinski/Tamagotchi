package pl.dbjllmjk.Model;

/**
 *{@link Exception} thrown when there is no user ({@link UserData}) or admin ({@link AdminData})
 * which contains of given login.
 */
public class NoSuchUserException extends Exception {

	/**
	 * {@value}
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * {@link Exception#Exception()}
	 */
	public NoSuchUserException() {
		super();
	}

	/**
	 * {@link Exception#Exception(String)
	 */
	public NoSuchUserException(String arg0) {
		super(arg0);
	}
}
