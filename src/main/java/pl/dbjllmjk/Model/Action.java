package pl.dbjllmjk.Model;

/**
 * An abstract class which represents {@link Pet} action in general.
 *
 */
public abstract class Action {
	/**
	 * Name of an action.
	 */
	private String name;
	
	/**
	 * Value of an action which would be added to/subtracted from {@link Pet} parameters. 
	 */
	private int value;
	
	/**
	 * Constructor with parameters.
	 * @param name of an action.
	 * @param value of an action.
	 */
	public Action(String name, int value) {
		this.name = name;
		this.value = value;
	}
	
	/**
	 * Action name getter.
	 * @return A name of an action.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Action value getter.
	 * @return A value of an action.
	 */
	public int getValue() {
		return value;
	}
	
	/**
	 * Overriden method toString().
	 * @return String in form: [name] ([value])
	 */
	@Override
	public String toString(){
		return this.name + " ("+this.value+")";
	}
}
