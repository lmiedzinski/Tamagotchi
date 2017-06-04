package pl.dbjllmjk.Controller;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import pl.dbjllmjk.Model.Action;
import pl.dbjllmjk.Model.Activity;
import pl.dbjllmjk.Model.Food;
import pl.dbjllmjk.Model.Operation;
import pl.dbjllmjk.Model.Pet;
import pl.dbjllmjk.Model.PetEntry;
import pl.dbjllmjk.Model.PetTransactionException;
import pl.dbjllmjk.Model.UserData;
//import pl.dbjllmjk.Model.XmlConverter;
import pl.dbjllmjk.View.UserView;

/**
 * Logic Layer Implementation for Users.
 */
public class UserController {
	/**
	 * Main Controller Field.
	 */
	private Controller controller;
	
	/**
	 * Currently logged User.
	 */
	private UserData loggedUser;
	
	/**
	 * Graphic User Interface field.
	 */
	private UserView userView;

	/**
	 * Constructor with parameter.
	 * @param controller includes reference to Main Controller.
	 */
	public UserController(Controller controller) {
		this.controller = controller;
		this.loggedUser = (UserData) controller.getLoggedAccount();
		this.loggedUser.updatePets(controller.getDataRepository().getPetsForUser(this.loggedUser));
		this.userView = new UserView(this);
	}

	/**
	 * @return Currently logged user.
	 */
	public UserData getLoggedUser() {
		return loggedUser;
	}

	/**
	 * Prepares {@link Pet}s to display.
	 */
	public PetEntry[] userPetListToPetEntry() {
		this.controller.makeLogicUpdate();
		this.loggedUser.updatePets(this.getPetsForUser());
		PetEntry[] pets = new PetEntry[this.loggedUser.getPets().size()];
		for (int i = 0; i < pets.length; i++) {
			pets[i] = new PetEntry(this.loggedUser.getPets().get(i).getName(),
					"img/" + this.loggedUser.getPets().get(i).getType() + "_happy.png");
		}
		return pets;
	}

	/**
	 * @return An array of available {@link Pet} types/species.
	 */
	public String[] getAvaliablePetTypes() {
		return this.controller.getDataRepository().getAvaliablePetTypes();
	}

	/**
	 * @param name of {@link Pet}.
	 * @return Array of available {@link Food}s for {@link Pet}.
	 * @throws PetTransactionException
	 */
	public Object[] getAvaliableFoodTypesForPet(String name) throws PetTransactionException {
		List<Action> foods = new ArrayList<Action>();
		Pet pet = null;
		for (Pet p : this.loggedUser.getPets()) {
			if (p.getName().equals(name))
				pet = p;
		}
		if (pet == null)
			throw new PetTransactionException("Error: Pet not found");
		for (Action a : pet.getActions()) {
			if (a instanceof Food)
				foods.add(a);
		}
		if (foods.size() == 0)
			throw new PetTransactionException("Error: No food found for this pet");
		return foods.toArray();
	}

	/**
	 * @param name of {@link Pet}.
	 * @return Array of available {@link Activity}'ies for {@link Pet}.
	 * @throws PetTransactionException
	 */
	public Object[] getAvaliableActivityTypesForPet(String name) throws PetTransactionException {
		List<Action> activities = new ArrayList<Action>();
		Pet pet = null;
		for (Pet p : this.loggedUser.getPets()) {
			if (p.getName().equals(name))
				pet = p;
		}
		if (pet == null)
			throw new PetTransactionException("Error: Pet not found");
		for (Action a : pet.getActions()) {
			if (a instanceof Activity)
				activities.add(a);
		}
		if (activities.size() == 0)
			throw new PetTransactionException("Error: No activities found for this pet");
		return activities.toArray();
	}

	/**
	 * @param name of {@link Pet}.
	 * @return Array of available {@link Operation}s for {@link Pet}.
	 * @throws PetTransactionException
	 */
	public Object[] getAvaliableOperationTypesForPet(String name) throws PetTransactionException {
		List<Action> operations = new ArrayList<Action>();
		Pet pet = null;
		for (Pet p : this.loggedUser.getPets()) {
			if (p.getName().equals(name))
				pet = p;
		}
		if (pet == null)
			throw new PetTransactionException("Error: Pet not found");
		for (Action a : pet.getActions()) {
			if (a instanceof Operation)
				operations.add(a);
		}
		if (operations.size() == 0)
			throw new PetTransactionException("Error: No activities found for this pet");
		return operations.toArray();
	}

	/**
	 * Add {@link Pet} to user.
	 * @param name of {@link Pet}
	 * @param type of {@link Pet}
	 * @throws PetTransactionException
	 */
	public void addPet(String name, String type) throws PetTransactionException {
		if (name.length() < 3 || type.length() < 3)
			throw new PetTransactionException("Name or type empty");
		if (name.length() > 25)
			throw new PetTransactionException("Name too long! Max 25 char.");
		Random r = new Random();
		for (Pet pet : this.loggedUser.getPets()) {
			if (pet.getName().equals(name))
				throw new PetTransactionException("You already have pet " + name);
		}
		LocalDateTime daty = LocalDateTime.now();
		Pet p = new Pet(name, type, 0, Double.parseDouble((r.nextInt(100) + 15) + "." + r.nextInt(100)), daty, 5, 5, 5,
				new ArrayList<Action>(), daty, daty, daty);
		this.controller.getDataRepository().addPet(p, this.loggedUser);
		this.controller.makeLogicUpdate();
		this.loggedUser.updatePets(this.getPetsForUser());
	}

	/**
	 * Delete {@link Pet} from user.
	 * @param name of {@link Pet}
	 * @throws PetTransactionException
	 */
	public void deletePet(String name) throws PetTransactionException {
		Pet p = null;
		for (Pet pet : this.loggedUser.getPets()) {
			if (pet.getName().equals(name))
				p = pet;
		}
		if (p == null)
			throw new PetTransactionException("You don't have pet " + name);
		this.controller.getDataRepository().removePet(p);
		this.controller.makeLogicUpdate();
		this.loggedUser.updatePets(this.getPetsForUser());
	}

	/**
	 * @return List of {@link Pet}s for currently logged User.
	 */
	public List<Pet> getPetsForUser() {
		List<Pet> petList = this.controller.getDataRepository().getPetsForUser(loggedUser);
		return petList;
	}

	/**
	 * @param name of {@link Pet}.
	 * @return All data of {@link Pet}.
	 * @throws PetTransactionException
	 */
	public String[] getPetData(String name) throws PetTransactionException {
		Pet p = null;
		for (Pet pet : this.loggedUser.getPets()) {
			if (pet.getName().equals(name))
				p = pet;
		}
		if (p == null)
			throw new PetTransactionException("You don't have pet " + name);
		String[] petData = new String[8];
		petData[0] = p.getName();
		petData[1] = p.getType();
		petData[2] = Integer.toString(p.getAge());
		petData[3] = Double.toString(p.getWeight());
		petData[4] = p.getBirthDate().getYear() + "/" + p.getBirthDate().getMonth() + "/"
				+ p.getBirthDate().getDayOfMonth() + " " + p.getBirthDate().getHour() + ":"
				+ p.getBirthDate().getMinute();
		petData[5] = Integer.toString(p.getHappiness());
		petData[6] = Integer.toString(p.getHunger());
		petData[7] = Integer.toString(p.getHealth());
		return petData;
	}

	/**
	 * Performs {@link Food} action on {@link Pet}.
	 * @param name of {@link Pet}.
	 * @param foodType instance of {@link Food} class.
	 * @throws PetTransactionException
	 */
	public void feedPet(String name, Action foodType) throws PetTransactionException {
		if (foodType == null)
			throw new PetTransactionException("No food selected!");
		Pet p = null;
		for (Pet pet : this.loggedUser.getPets()) {
			if (pet.getName().equals(name))
				p = pet;
		}
		if (p == null)
			throw new PetTransactionException("You don't have pet: " + name);
		Action a = foodType;
		if (a.getValue() > p.getHunger())
			throw new PetTransactionException("You can't feed pet more than " + p.getHunger());
		else {
			p.setHunger(p.getHunger() - a.getValue());
			p.setLastFeedingDate(LocalDateTime.now());
		}
		List<Pet> petsToUpdate = new ArrayList<Pet>();
		petsToUpdate.add(p);
		this.controller.getDataRepository().updatePets(petsToUpdate);
		this.controller.makeLogicUpdate();
		this.loggedUser.updatePets(this.getPetsForUser());
		this.userView.getPetTab().changeCurrentPet(this.getPetData(name));
		this.userView.getMenuTab().selectionMade(this.getPetData(name));
	}

	/**
	 * Performs {@link Activity} on {@link Pet}.
	 * @param name of {@link Pet}.
	 * @param playType instance of {@link Activity} class.
	 * @throws PetTransactionException
	 */
	public void playWithPet(String name, Action playType) throws PetTransactionException {
		if (playType == null)
			throw new PetTransactionException("No activity selected!");
		Pet p = null;
		for (Pet pet : this.loggedUser.getPets()) {
			if (pet.getName().equals(name))
				p = pet;
		}
		if (p == null)
			throw new PetTransactionException("You don't have pet: " + name);
		Action a = playType;
		if (a.getValue() > 10 - p.getHappiness())
			throw new PetTransactionException("You can't play with pet more than " + (10 - p.getHappiness()));
		else {
			p.setHappiness(p.getHappiness() + a.getValue());
			p.setLastActivityDate(LocalDateTime.now());
		}
		List<Pet> petsToUpdate = new ArrayList<Pet>();
		petsToUpdate.add(p);
		this.controller.getDataRepository().updatePets(petsToUpdate);
		this.controller.makeLogicUpdate();
		this.loggedUser.updatePets(this.getPetsForUser());
		this.userView.getPetTab().changeCurrentPet(this.getPetData(name));
		this.userView.getMenuTab().selectionMade(this.getPetData(name));
	}

	/**
	 * Performs {@link Operation} on {@link Pet}.
	 * @param name of {@link Pet}.
	 * @param operationType instance of {@link Operation} class.
	 * @throws PetTransactionException
	 */
	public void makeOperationOnPet(String name, Action operationType) throws PetTransactionException {
		if (operationType == null)
			throw new PetTransactionException("No operation selected!");
		Pet p = null;
		for (Pet pet : this.loggedUser.getPets()) {
			if (pet.getName().equals(name))
				p = pet;
		}
		if (p == null)
			throw new PetTransactionException("You don't have pet: " + name);
		Action a = operationType;
		if (a.getValue() > 10 - p.getHealth())
			throw new PetTransactionException("You can't make operation on pet more than " + (10 - p.getHealth()));
		else {
			p.setHealth(p.getHealth() + a.getValue());
			p.setLastOperationDate(LocalDateTime.now());
		}
		List<Pet> petsToUpdate = new ArrayList<Pet>();
		petsToUpdate.add(p);
		this.controller.getDataRepository().updatePets(petsToUpdate);
		this.controller.makeLogicUpdate();
		this.loggedUser.updatePets(this.getPetsForUser());
		this.userView.getPetTab().changeCurrentPet(this.getPetData(name));
		this.userView.getMenuTab().selectionMade(this.getPetData(name));
	}

	/**
	 * Import {@link Pet} form ax XML file.
	 * @param path of the XML file.
	 * @param force decision if the {@link Pet} should be removed from database file.
	 * @throws PetTransactionException
	 */
	public void importPet(String path, boolean force) throws PetTransactionException {
//		XMLConverter
		Pet importedPet = null; //XmlConverter.deserializePet(path);
		if (importedPet == null)
			throw new PetTransactionException("Couldn't import pet!");
		if (force) {
			for (Pet pet : this.loggedUser.getPets()) {
				if (pet.getName().equals(importedPet.getName())) this.controller.getDataRepository().removePet(pet);
			}
		} else {
			for (Pet pet : this.loggedUser.getPets()) {
				if (pet.getName().equals(importedPet.getName()))
					throw new PetTransactionException("You already have pet \'" + importedPet.getName() + "\'!\nOld - "
							+ pet.getType() + ", New - " + importedPet.getType());
			}
		}
		this.controller.getDataRepository().addPet(importedPet, this.loggedUser);
	}
	
	/**
	 * Export currently selected {@link Pet}.
	 * @param path of XML file.
	 * @param fileName of XML file.
	 * @param name of {@link Pet}.
	 * @throws PetTransactionException
	 */
	public void exportPet(String path, String fileName, String name) throws PetTransactionException{
		if(!fileName.endsWith(".xml")){
			path = path.substring(0, path.length()-fileName.length());
			fileName += ".xml";
			path += fileName;
		}
		Pet selectedPet = null;
		for (Pet pet : this.loggedUser.getPets()) {
			if (pet.getName().equals(name))
				selectedPet = pet;
		}
		if (selectedPet == null) throw new PetTransactionException("You don't have pet: " + name);
		//XmlConverter.serializePet(selectedPet, path);
	}

	/**
	 * Performs amazingly unique and unbelievable log out.
	 */
	public void logout() {
		this.userView.dispose();
		this.controller.anotherLogin();
	}

	/**
	 * Refresh GUI and list of {@link Pet}s.
	 */
	public void refresh() {
		this.userView.dispose();
		this.loggedUser.updatePets(this.controller.getDataRepository().getPetsForUser(this.loggedUser));
		this.userView = new UserView(this);
	}
}
