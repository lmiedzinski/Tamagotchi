package pl.dbjllmjk.Model;

/**
 * Class representing {@link Pet} Food. Extends {@link Action} class.
 */
public class Food extends Action{
	
	/**
	 * Constructor with parameters.
	 * @param name of food
	 * @param value of food
	 * @see Action#Action(String, int)
	 */
	public Food(String name, int value) {
		super(name, value);
	}
	public Food(){super("",0);};
}
