/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.dbjllmjk;

import org.junit.Assert;
import org.junit.Test;
import pl.dbjllmjk.Controller.Controller;
import pl.dbjllmjk.Exceptions.PetTransactionException;
import pl.dbjllmjk.Model.Action;
import pl.dbjllmjk.Model.DataRepository;
import pl.dbjllmjk.Model.Pet;
import pl.dbjllmjk.Model.UserData;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;


public class ControllerTest {

    private void addDifferentPets() {
        Pet pet = new Pet("teest1", "dog", 0, 3, LocalDateTime.now(), 10, 0, 10, new ArrayList<Action>(), LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now().minusDays(5));
        Pet petCopy = new Pet("teest2", "dog", 0, 3, LocalDateTime.now().minusHours(10), 10, 0, 10, new ArrayList<Action>(), LocalDateTime.now().minusHours(10), LocalDateTime.now().minusHours(10), LocalDateTime.now().minusDays(10));
        DataRepository dr = new DataRepository();
        Optional<UserData> userTest = dr.getUsers().stream().filter(x -> x.getLogin().equals("zenek")).findFirst();
        dr.addPet(pet, userTest.get());
        dr.addPet(petCopy, userTest.get());
        Controller c = new Controller(1);
        c.makeLogicUpdate();
    }

    @Test
    public void makeLogicUpdateTest1() throws PetTransactionException {
        addDifferentPets();
        DataRepository dr = new DataRepository();
        Optional<UserData> userTest = dr.getUsers().stream().filter(x -> x.getLogin().equals("zenek")).findFirst();
        Optional<Pet> pet1Test = dr.getPetsForUser(userTest.get()).stream().filter(x -> x.getName().equals("teest1")).findFirst();
        Optional<Pet> pet2Test = dr.getPetsForUser(userTest.get()).stream().filter(x -> x.getName().equals("teest2")).findFirst();
        Assert.assertNotEquals(pet1Test.get().getHappiness(), pet2Test.get().getHappiness());
        dr.removePet(pet1Test.get());
        dr.removePet(pet2Test.get());
    }

    @Test
    public void makeLogicUpdateTest2() throws PetTransactionException {
        addDifferentPets();
        DataRepository dr = new DataRepository();
        Optional<UserData> userTest = dr.getUsers().stream().filter(x -> x.getLogin().equals("zenek")).findFirst();
        Optional<Pet> pet1Test = dr.getPetsForUser(userTest.get()).stream().filter(x -> x.getName().equals("teest1")).findFirst();
        Optional<Pet> pet2Test = dr.getPetsForUser(userTest.get()).stream().filter(x -> x.getName().equals("teest2")).findFirst();
        Assert.assertNotEquals(pet1Test.get().getHunger(), pet2Test.get().getHunger());
        dr.removePet(pet1Test.get());
        dr.removePet(pet2Test.get());
    }

    @Test
    public void makeLogicUpdateTest3() throws PetTransactionException {
        addDifferentPets();
        DataRepository dr = new DataRepository();
        Optional<UserData> userTest = dr.getUsers().stream().filter(x -> x.getLogin().equals("zenek")).findFirst();
        Optional<Pet> pet1Test = dr.getPetsForUser(userTest.get()).stream().filter(x -> x.getName().equals("teest1")).findFirst();
        Optional<Pet> pet2Test = dr.getPetsForUser(userTest.get()).stream().filter(x -> x.getName().equals("teest2")).findFirst();
        Assert.assertNotEquals(pet1Test.get().getHealth(), pet2Test.get().getHealth());
        dr.removePet(pet1Test.get());
        dr.removePet(pet2Test.get());
    }

    @Test
    public void makeLogicUpdateTest4() throws PetTransactionException {
        addDifferentPets();
        DataRepository dr = new DataRepository();
        Optional<UserData> userTest = dr.getUsers().stream().filter(x -> x.getLogin().equals("zenek")).findFirst();
        Optional<Pet> pet1Test = dr.getPetsForUser(userTest.get()).stream().filter(x -> x.getName().equals("teest1")).findFirst();
        Optional<Pet> pet2Test = dr.getPetsForUser(userTest.get()).stream().filter(x -> x.getName().equals("teest2")).findFirst();
        Assert.assertNotEquals(pet1Test.get().getAge(), pet2Test.get().getAge());
        dr.removePet(pet1Test.get());
        dr.removePet(pet2Test.get());
    }

}
