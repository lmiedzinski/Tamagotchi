package pl.dbjllmjk.Model;

/**
 * Class representing {@link Pet} Activity. Extends {@link Action} class.
 */
public class Activity extends Action{
	
	/**
	 * Constructor with parameters.
	 * @param name of activity
	 * @param value of activity
	 * @see Action#Action(String, int)
	 */
	public Activity(String name, int value) {
		super(name, value);
	}

	public Activity() {
		super("",0);
	}

}
