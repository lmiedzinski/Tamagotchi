package pl.dbjllmjk.Model;
import java.time.LocalDateTime;
import java.util.List;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * Class representing a pet in Tamagotchi project.
 */
 @XStreamAlias("pet")
public class Pet {
	
	/**
	 * Name of a {@link Pet}.
	 */
	private String name;
	
	/**
	 * {@link Pet} type - some kind of species
	 */
	private String type;
	
	/**
	 * {@link Pet} age. Actually, no one in the {@link universe} has discovered a method of
	 * counting days for {@link Pet}s.
	 */
	private int age;
	
	/**
	 * {@link Pet} weight. It means, it's unpredictable.
	 */
	private double weight;
	
	/**
	 * Date of last {@link Food} action performance.
	 */
	private LocalDateTime lastFeedingDate;
	
	/**
	 * Date of last {@link Activity} performance.
	 */
	private LocalDateTime lastActivityDate;
	
	/**
	 * Date of last {@link Operation} performance.
	 */
	private LocalDateTime lastOperationDate;
	
	/**
	 * Date of birth ( ͡° ͜ʖ ͡°)
	 */
	private LocalDateTime birthDate;
	
	/**
	 * Happiness level.
	 */
	private int happiness;
	
	/**
	 * Hunger level.
	 */
	private int hunger;
	
	/**
	 * Health level.
	 */
	private int health;
	
	/**
	 * List of {@link Action}s available for {@link Pet}.
	 */
	private List<Action> actions;

	/**
	 * Constructor with parameters.
	 * @param name
	 * @param type species
	 * @param age
	 * @param weight
	 * @param lastFeedingDate
	 * @param happiness number in Integer scale
	 * @param hunger number in Integer scale
	 * @param health number in Integer scale
	 * @param actions
	 * @param lastActivityDate
	 * @param lastOperationDate
	 * @param birthDate
	 */
	public Pet(String name, String type, int age, double weight, LocalDateTime lastFeedingDate, int happiness,
			int hunger, int health, List<Action> actions, LocalDateTime lastActivityDate,
			LocalDateTime lastOperationDate, LocalDateTime birthDate) {
		super();
		this.name = name;
		this.type = type;
		this.age = age;
		this.weight = weight;
		this.lastFeedingDate = lastFeedingDate;
		this.happiness = happiness;
		this.hunger = hunger;
		this.health = health;
		this.actions = actions;
		this.lastActivityDate = lastActivityDate;
		this.lastOperationDate = lastOperationDate;
		this.birthDate = birthDate;
	}

	/**
	 * @return Name of {@link Pet}.
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return Age of {@link Pet}.
	 */
	public int getAge() {
		return age;
	}

	/**
	 * @return Wieght of {@link Pet}.
	 */
	public double getWeight() {
		return weight;
	}

	/**
	 * @return Last {@link Food} action performance.
	 */
	public LocalDateTime getLastFeedingDate() {
		return lastFeedingDate;
	}

	/**
	 * @return Level of happiness.
	 */
	public int getHappiness() {
		return happiness;
	}

	/**
	 * @return Level of hunger.
	 */
	public int getHunger() {
		return hunger;
	}

	/**
	 * @return Level of health.
	 */
	public int getHealth() {
		return health;
	}

	/**
	 * @return List of {@link Action}s for particular {@link Pet}.
	 */
	public List<Action> getActions() {
		return actions;
	}

	/**
	 * @return Type/Species of {@link Pet}.
	 */
	public String getType() {
		return type;
	}

	/**
	 * @return Last {@link Activity} performance.
	 */
	public LocalDateTime getLastActivityDate() {
		return lastActivityDate;
	}

	/**
	 * @return Last {@link Operation} performance.
	 */
	public LocalDateTime getLastOperationDate() {
		return lastOperationDate;
	}
	
	/**
	 * Sets name for {@link Pet}.
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Sets type/species for {@link Pet}.
	 * @param type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Sets age for {@link Pet}.
	 * @param age
	 */
	public void setAge(int age) {
		this.age = age;
	}

	/**
	 * Sets weight for {@link Pet}.
	 * @param weight
	 */
	public void setWeight(double weight) {
		this.weight = weight;
	}

	/**
	 * Sets last {@link Food} action for {@link Pet}.
	 * @param lastFeedingDate
	 */
	public void setLastFeedingDate(LocalDateTime lastFeedingDate) {
		this.lastFeedingDate = lastFeedingDate;
	}

	/**
	 * Sets last {@link Activity} performance for {@link Pet}.
	 * @param lastActivityDate
	 */
	public void setLastActivityDate(LocalDateTime lastActivityDate) {
		this.lastActivityDate = lastActivityDate;
	}

	/**
	 * Sets last {@link Operation} performance for {@link Pet}.
	 * @param lastOperationDate
	 */
	public void setLastOperationDate(LocalDateTime lastOperationDate) {
		this.lastOperationDate = lastOperationDate;
	}

	/**
	 * Sets happiness for {@link Pet}.
	 * @param happiness
	 */
	public void setHappiness(int happiness) {
		this.happiness = happiness;
	}

	/**
	 * Sets hunger for {@link Pet}.
	 * @param hunger
	 */
	public void setHunger(int hunger) {
		this.hunger = hunger;
	}

	/**
	 * Sets health for {@link Pet}.
	 * @param health
	 */
	public void setHealth(int health) {
		this.health = health;
	}

	/**
	 * Sets list of {@link Action}s for {@link Pet}.
	 * @param actions
	 */
	public void setActions(List<Action> actions) {
		this.actions = actions;
	}
	
	/**
	 * @return Last registered date of birth for {@link Pet}. ( ͡° ͜ʖ ͡°)
	 */
	public LocalDateTime getBirthDate(){
		return this.birthDate;
	}
}
