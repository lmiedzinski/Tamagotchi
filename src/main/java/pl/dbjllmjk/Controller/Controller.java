package pl.dbjllmjk.Controller;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import pl.dbjllmjk.Model.AccountData;
import pl.dbjllmjk.Model.AdminData;
import pl.dbjllmjk.Model.DataRepository;
import pl.dbjllmjk.Model.Pet;
import pl.dbjllmjk.Model.UserData;

/**
 * Main Controller - responsible for launching login module
 * and changing level of happiness/hunger/health for {@link Pet}s. 
 */
public class Controller {

	/**
	 * Data repository field.
	 */
	private DataRepository dataRepository;
	
	/**
	 * Stores current logged user/admin data ({@link AccountData}).
	 */
	private AccountData loggedAccount;

	public Controller() {
		this.dataRepository = new DataRepository();
		this.loggedAccount = null;
		new LoginController(this);
	}

	/**
	 * Launch controllers for user/admin after logging in.
	 * @param loggedAccount user/admin account
	 */
	public void afterLogin(AccountData loggedAccount) {
		this.loggedAccount = loggedAccount;
		if (loggedAccount instanceof AdminData) {
			new AdminController(this);
		} else if (loggedAccount instanceof UserData) {
			new UserController(this);
		}
	}

	/**
	 * Change login account.
	 */
	public void anotherLogin() {
		this.loggedAccount = null;
		new LoginController(this);
	}

	/**
	 * @param fromDateTime
	 * @param toDateTime
	 * @return Difference between two dates
	 */
	private long calculateDifferenceBetweenDates(LocalDateTime fromDateTime, LocalDateTime toDateTime) {
		long minutes = 0;
		minutes = ChronoUnit.MINUTES.between(fromDateTime, toDateTime);
		return minutes;
	}

	/**
	 * Controls {@link Pet}'s parameters. In other words, tutorial: how to piss of gamers - your only customers.
	 */
	public void makeLogicUpdate() {
		// 60 min = +1 hunger point
		// 30 min = -1 happiness point
		// 240 min = -1 health point
		List<UserData> users = this.dataRepository.getUsers();
		List<Pet> petsToUpdate = new ArrayList<Pet>();
		// Check every pet in database
		for (UserData user : users) {
			for (Pet pet : user.getPets()) {
				// Flag for saving changes
				boolean changed = false;
				// Checking age
				if (pet.getBirthDate().until(LocalDateTime.now(), ChronoUnit.DAYS) != pet.getAge()){
					pet.setAge((int) pet.getBirthDate().until(LocalDateTime.now(), ChronoUnit.DAYS));
					changed = true;
				}
				// Checking hunger
				//System.out.println((LocalDateTime)pet.getLastFeedingDate()+ " " + LocalDateTime.now()+" "+calculateDifferenceBetweenDates(pet.getLastFeedingDate(), LocalDateTime.now()));
				if (calculateDifferenceBetweenDates(pet.getLastFeedingDate(), LocalDateTime.now()) > 60) {
					int points = (int) (calculateDifferenceBetweenDates(pet.getLastFeedingDate(), LocalDateTime.now())
							/ 60);
					pet.setHunger(pet.getHunger() + points);
					if(pet.getHunger() > 10) pet.setHunger(10);
					pet.setLastFeedingDate(LocalDateTime.now());
					changed = true;
				}
				// Checking happiness
				if (calculateDifferenceBetweenDates(pet.getLastActivityDate(), LocalDateTime.now()) > 30) {
					int points = (int) (calculateDifferenceBetweenDates(pet.getLastActivityDate(), LocalDateTime.now())
							/ 30);
					pet.setHappiness(pet.getHappiness() - points);
					if(pet.getHappiness() < 0) pet.setHappiness(0);
					pet.setLastActivityDate(LocalDateTime.now());
					changed = true;
				}
				// Checking health
				if (calculateDifferenceBetweenDates(pet.getLastOperationDate(), LocalDateTime.now()) > 240) {
					int points = (int) (calculateDifferenceBetweenDates(pet.getLastOperationDate(), LocalDateTime.now())
							/ 240);
					if(points > 10) points = 10;
					pet.setHealth(pet.getHealth() - points);
					if(pet.getHealth() < 0) pet.setHealth(0);
					pet.setLastOperationDate(LocalDateTime.now());
					changed = true;
				}
				// Save if necessary
				if (changed)
					petsToUpdate.add(pet);
			}
		}
		// Update database if necessary
		if (petsToUpdate.size() > 0)
			this.dataRepository.updatePets(petsToUpdate);
	}

	/**
	 * @return Data Repository from Model Layer
	 */
	public DataRepository getDataRepository() {
		return dataRepository;
	}

	/**
	 * @return Currently logged account.
	 */
	public AccountData getLoggedAccount() {
		return loggedAccount;
	}
}
