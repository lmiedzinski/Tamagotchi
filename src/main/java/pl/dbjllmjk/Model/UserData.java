package pl.dbjllmjk.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Class representing user account in application. Extends from
 * {@link AccountData}
 */
public class UserData extends AccountData {

    /**
     * List of {@link Pet}s for User.
     */
    private List<Pet> pets;

    /**
     * Constructor with parameters
     *
     * @param login user login
     * @param password user password
     * @param name user name
     * @param surname user surname
     * @see AccountData#AccountData(String, String, String, String)
     */
    public UserData(String login, String password, String name, String surname) {
        super(login, password, name, surname);
        pets = new ArrayList<>();
    }

    /**
     * Setter for list of {@link Pet}s.
     *
     * @param pets new list of {@link Pet}s.
     */
    public void updatePets(List<Pet> pets) {
        this.pets = pets;
    }

    /**
     * Allows to add {@link Pet} object to list of {@link Pet}s.
     *
     * @param pet instance of {@link Pet} class which should be added.
     */
    public void addPet(Pet pet) {
        if (!pets.contains(pet)) {
            pets.add(pet);
        }
    }

    /**
     * Allows to delete {@link Pet} object from list of {@link Pet}s.
     *
     * @param pet instance of {@link Pet} class which should be deleted.
     */
    public void removePet(Pet pet) {
        if (pets.contains(pet)) {
            pets.remove(pet);
        }
    }

    /**
     * List of {@link Pet}s getter
     *
     * @return List of {@link Pet} objects.
     */
    public List<Pet> getPets() {
        return pets;
    }

    /**
     * @return String in form: [login] ([name],[surname]) (User)
     */
    @Override
    public String toString() {
        return super.toString() + "   (User)";
    }

}
