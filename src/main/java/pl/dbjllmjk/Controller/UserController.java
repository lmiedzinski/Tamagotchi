package pl.dbjllmjk.Controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import pl.dbjllmjk.Model.Action;
import pl.dbjllmjk.Model.Activity;
import pl.dbjllmjk.Model.Food;
import pl.dbjllmjk.Model.Operation;
import pl.dbjllmjk.Model.Pet;
import pl.dbjllmjk.Model.PetEntry;
import pl.dbjllmjk.Model.PetTransactionException;
import pl.dbjllmjk.Model.UserData;
import pl.dbjllmjk.Model.XmlConverter;
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
     *
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
     *
     * @return An array of {@link Pet} entries
     */
    public PetEntry[] userPetListToPetEntry() {
        this.controller.makeLogicUpdate();
        this.loggedUser.updatePets(this.getPetsForUser());
        List<PetEntry> pets = new ArrayList<>();
        this.loggedUser.getPets().stream().forEach((pet) -> {
            pets.add(new PetEntry(pet.getName(), "img/" + pet.getType() + "_happy.png"));
        });
        return pets.toArray(new PetEntry[pets.size()]);
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
        List<Action> foods = new ArrayList<>();
        Optional<Pet> pet = this.loggedUser.getPets().stream().filter(l -> l.getName().equals(name)).findFirst();
        if (!pet.isPresent()) {
            throw new PetTransactionException("Error: Pet not found");
        }
        pet.get().getActions().stream().forEach((action) -> {
            if (action instanceof Food) {
                foods.add(action);
            }
        });
        if (foods.isEmpty()) {
            throw new PetTransactionException("Error: No food found for this pet");
        }
        return foods.toArray();
    }

    /**
     * @param name of {@link Pet}.
     * @return Array of available {@link Activity}'ies for {@link Pet}.
     * @throws PetTransactionException
     */
    public Object[] getAvaliableActivityTypesForPet(String name) throws PetTransactionException {
        List<Action> activities = new ArrayList<>();
        Optional<Pet> pet = this.loggedUser.getPets().stream().filter(l -> l.getName().equals(name)).findFirst();
        if (!pet.isPresent()) {
            throw new PetTransactionException("Error: Pet not found");
        }
        pet.get().getActions().stream().forEach((action) -> {
            if (action instanceof Activity) {
                activities.add(action);
            }
        });
        if (activities.isEmpty()) {
            throw new PetTransactionException("Error: No activities found for this pet");
        }
        return activities.toArray();
    }

    /**
     * @param name of {@link Pet}.
     * @return Array of available {@link Operation}s for {@link Pet}.
     * @throws PetTransactionException
     */
    public Object[] getAvaliableOperationTypesForPet(String name) throws PetTransactionException {
        List<Action> operations = new ArrayList<>();
        Optional<Pet> pet = this.loggedUser.getPets().stream().filter(l -> l.getName().equals(name)).findFirst();
        if (!pet.isPresent()) {
            throw new PetTransactionException("Error: Pet not found");
        }
        pet.get().getActions().stream().forEach((action) -> {
            if (action instanceof Operation) {
                operations.add(action);
            }
        });
        if (operations.isEmpty()) {
            throw new PetTransactionException("Error: No activities found for this pet");
        }
        return operations.toArray();
    }

    /**
     * Add {@link Pet} to user.
     *
     * @param name of {@link Pet}
     * @param type of {@link Pet}
     * @throws PetTransactionException
     */
    public void addPet(String name, String type) throws PetTransactionException {
        if (name.length() < 3 || type.length() < 3) {
            throw new PetTransactionException("Name or type empty");
        }
        if (name.length() > 25) {
            throw new PetTransactionException("Name too long! Max 25 char.");
        }
        Random r = new Random();
        if (this.loggedUser.getPets().stream().anyMatch(a -> a.getName().equals(name))) {
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
     *
     * @param name of {@link Pet}
     * @throws PetTransactionException
     */
    public void deletePet(String name) throws PetTransactionException {
        Optional<Pet> p = this.loggedUser.getPets().stream().filter(l -> l.getName().equals(name)).findFirst();
        if (!p.isPresent()) {
            throw new PetTransactionException("You don't have pet " + name);
        }
        this.controller.getDataRepository().removePet(p.get());
        this.controller.makeLogicUpdate();
        this.loggedUser.updatePets(this.getPetsForUser());
    }

    /**
     * @return List of {@link Pet}s for currently logged User.
     */
    public List<Pet> getPetsForUser() {
        return this.controller.getDataRepository().getPetsForUser(loggedUser);
    }

    /**
     * @param name of {@link Pet}.
     * @return All data of {@link Pet}.
     * @throws PetTransactionException
     */
    public String[] getPetData(String name) throws PetTransactionException {
        Optional<Pet> p = this.loggedUser.getPets().stream().filter(l -> l.getName().equals(name)).findFirst();
        if (!p.isPresent()) {
            throw new PetTransactionException("You don't have pet " + name);
        }
        String[] petData = new String[8];
        petData[0] = p.get().getName();
        petData[1] = p.get().getType();
        petData[2] = Integer.toString(p.get().getAge());
        petData[3] = Double.toString(p.get().getWeight());
        petData[4] = p.get().getBirthDate().getYear() + "/" + p.get().getBirthDate().getMonth() + "/"
                + p.get().getBirthDate().getDayOfMonth() + " " + p.get().getBirthDate().getHour() + ":"
                + p.get().getBirthDate().getMinute();
        petData[5] = Integer.toString(p.get().getHappiness());
        petData[6] = Integer.toString(p.get().getHunger());
        petData[7] = Integer.toString(p.get().getHealth());
        return petData;
    }

    /**
     * Performs {@link Food} action on {@link Pet}.
     *
     * @param name of {@link Pet}.
     * @param foodType instance of {@link Food} class.
     * @throws PetTransactionException
     */
    public void feedPet(String name, Action foodType) throws PetTransactionException {
        if (foodType == null) {
            throw new PetTransactionException("No food selected!");
        }
        Optional<Pet> p = this.loggedUser.getPets().stream().filter(l -> l.getName().equals(name)).findFirst();
        if (!p.isPresent()) {
            throw new PetTransactionException("You don't have pet: " + name);
        }
        Pet pet = p.get();
        Action a = foodType;
        pet = ActionLogic.feedPet(pet, a);
        List<Pet> petsToUpdate = new ArrayList<>();
        petsToUpdate.add(pet);
        this.controller.getDataRepository().updatePets(petsToUpdate);
        this.controller.makeLogicUpdate();
        this.loggedUser.updatePets(this.getPetsForUser());
        this.userView.getPetTab().changeCurrentPet(this.getPetData(name));
        this.userView.getMenuTab().selectionMade(this.getPetData(name));
    }

    /**
     * Performs {@link Activity} on {@link Pet}.
     *
     * @param name of {@link Pet}.
     * @param playType instance of {@link Activity} class.
     * @throws PetTransactionException
     */
    public void playWithPet(String name, Action playType) throws PetTransactionException {
        if (playType == null) {
            throw new PetTransactionException("No activity selected!");
        }
        Optional<Pet> p = this.loggedUser.getPets().stream().filter(l -> l.getName().equals(name)).findFirst();
        if (!p.isPresent()) {
            throw new PetTransactionException("You don't have pet: " + name);
        }
        Pet pet = p.get();
        Action a = playType;
        pet = ActionLogic.playWithPet(pet, a);
        List<Pet> petsToUpdate = new ArrayList<>();
        petsToUpdate.add(pet);
        this.controller.getDataRepository().updatePets(petsToUpdate);
        this.controller.makeLogicUpdate();
        this.loggedUser.updatePets(this.getPetsForUser());
        this.userView.getPetTab().changeCurrentPet(this.getPetData(name));
        this.userView.getMenuTab().selectionMade(this.getPetData(name));
    }

    /**
     * Performs {@link Operation} on {@link Pet}.
     *
     * @param name of {@link Pet}.
     * @param operationType instance of {@link Operation} class.
     * @throws PetTransactionException
     */
    public void makeOperationOnPet(String name, Action operationType) throws PetTransactionException {
        if (operationType == null) {
            throw new PetTransactionException("No operation selected!");
        }
        Optional<Pet> p = this.loggedUser.getPets().stream().filter(l -> l.getName().equals(name)).findFirst();
        if (!p.isPresent()) {
            throw new PetTransactionException("You don't have pet: " + name);
        }
        Pet pet = p.get();
        Action a = operationType;
        pet = ActionLogic.makeOperationOnPet(pet, a);
        List<Pet> petsToUpdate = new ArrayList<>();
        petsToUpdate.add(pet);
        this.controller.getDataRepository().updatePets(petsToUpdate);
        this.controller.makeLogicUpdate();
        this.loggedUser.updatePets(this.getPetsForUser());
        this.userView.getPetTab().changeCurrentPet(this.getPetData(name));
        this.userView.getMenuTab().selectionMade(this.getPetData(name));
    }

    /**
     * Import {@link Pet} form ax XML file.
     *
     * @param path of the XML file.
     * @param force decision if the {@link Pet} should be removed from database
     * file.
     * @throws PetTransactionException
     */
    public void importPet(String path, boolean force) throws PetTransactionException {
        Pet importedPet = XmlConverter.deserializePet(path);
        if (importedPet == null) {
            throw new PetTransactionException("Couldn't import pet!");
        }
        Optional<Pet> pet = this.loggedUser.getPets().stream().filter(l -> l.getName().equals(importedPet.getName())).findFirst();
        if (pet.isPresent()) {
            if (force) {
                this.controller.getDataRepository().removePet(pet.get());
            } else {
                throw new PetTransactionException("You already have pet \'" + importedPet.getName() + "\'!\nOld - "
                        + pet.get().getType() + ", New - " + importedPet.getType());
            }
        }
        this.controller.getDataRepository().addPet(importedPet, this.loggedUser);
    }

    /**
     * Export currently selected {@link Pet}.
     *
     * @param path of XML file.
     * @param fileName of XML file.
     * @param name of {@link Pet}.
     * @throws PetTransactionException
     */
    public void exportPet(String path, String fileName, String name) throws PetTransactionException {
        if (!fileName.endsWith(".xml")) {
            path = path.substring(0, path.length() - fileName.length());
            fileName += ".xml";
            path += fileName;
        }
        Optional<Pet> selectedPet = this.loggedUser.getPets().stream().filter(l -> l.getName().equals(name)).findFirst();
        if (!selectedPet.isPresent()) {
            throw new PetTransactionException("You don't have pet: " + name);
        }
        XmlConverter.serializePet(selectedPet.get(), path);
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
