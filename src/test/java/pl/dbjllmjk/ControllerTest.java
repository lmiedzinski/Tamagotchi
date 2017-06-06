/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.dbjllmjk;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import junit.framework.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.dbjllmjk.Controller.AdminController;
import pl.dbjllmjk.Controller.Controller;
import pl.dbjllmjk.Controller.UserController;
import pl.dbjllmjk.Exceptions.PetTransactionException;
import pl.dbjllmjk.Model.Action;
import pl.dbjllmjk.Model.Activity;
import pl.dbjllmjk.Model.DataRepository;
import pl.dbjllmjk.Model.Pet;
import pl.dbjllmjk.Model.UserData;

/**
 * @author Damian
 */
public class ControllerTest {

    @Test
    public void makeLogicUpdateTest() throws PetTransactionException {
        Controller c = new Controller(1);

        Pet pet = new Pet("teest", "dog", 0, 3, LocalDateTime.now(), 10, 10, 10, new ArrayList<Action>(), LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now());
        Pet petCopy = new Pet("teest", "dog", 0, 3, LocalDateTime.now(), 10, 10, 10, new ArrayList<Action>(), LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now());
        DataRepository dr = new DataRepository();
        dr.addPet(pet, new UserData("zenek", "zenek", "zenek", "zenkowski"));
        dr.addPet(petCopy, new UserData("zenek", "zenek", "zenek", "zenkowski"));
        c.makeLogicUpdate();
        pet.setLastActivityDate(LocalDateTime.now().plusMinutes(500));
        c.makeLogicUpdate();
        Assert.assertEquals(pet.getHappiness(), petCopy.getHappiness());
    }

    @Test
    public void makeLogicUpdateTest1() throws PetTransactionException {
        Controller c = new Controller(1);

        Pet pet = new Pet("teest", "dog", 0, 3, LocalDateTime.now(), 10, 10, 10, new ArrayList<Action>(), LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now());
        Pet petCopy = new Pet("teest", "dog", 0, 3, LocalDateTime.now(), 10, 10, 10, new ArrayList<Action>(), LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now());
        DataRepository dr = new DataRepository();
        dr.addPet(pet, new UserData("zenek", "zenek", "zenek", "zenkowski"));
        dr.addPet(petCopy, new UserData("zenek", "zenek", "zenek", "zenkowski"));
        c.makeLogicUpdate();
        pet.setLastFeedingDate(LocalDateTime.now().plusMinutes(500));
        c.makeLogicUpdate();
        Assert.assertEquals(pet.getHunger(), petCopy.getHunger());
    }

    @Test
    public void makeLogicUpdateTest2() throws PetTransactionException {
        Controller c = new Controller(1);

        Pet pet = new Pet("teest", "dog", 0, 3, LocalDateTime.now(), 10, 10, 10, new ArrayList<Action>(), LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now());
        Pet petCopy = new Pet("teest", "dog", 0, 3, LocalDateTime.now(), 10, 10, 10, new ArrayList<Action>(), LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now());
        DataRepository dr = new DataRepository();
        dr.addPet(pet, new UserData("zenek", "zenek", "zenek", "zenkowski"));
        dr.addPet(petCopy, new UserData("zenek", "zenek", "zenek", "zenkowski"));
        c.makeLogicUpdate();
        pet.setLastOperationDate(LocalDateTime.now().plusMinutes(500));
        c.makeLogicUpdate();
        Assert.assertEquals(pet.getHealth(), petCopy.getHealth());
    }

    @Test
    public void makeLogicUpdateTest3() throws PetTransactionException {
        Controller c = new Controller(1);

        Pet pet = new Pet("teest", "dog", 0, 3, LocalDateTime.now(), 10, 10, 10, new ArrayList<Action>(), LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now());
        Pet petCopy = new Pet("teest", "dog", 0, 3, LocalDateTime.now(), 10, 10, 10, new ArrayList<Action>(), LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now());
        DataRepository dr = new DataRepository();
        dr.addPet(pet, new UserData("zenek", "zenek", "zenek", "zenkowski"));
        dr.addPet(petCopy, new UserData("zenek", "zenek", "zenek", "zenkowski"));
        c.makeLogicUpdate();
        pet.setAge(20);
        c.makeLogicUpdate();
        Assert.assertNotSame(pet.getAge(), petCopy.getAge());
    }

    @Test
    public void makeLogicUpdateTest4() throws PetTransactionException {
        Controller c = new Controller(1);

        Pet pet = new Pet("teest", "dog", 0, 3, LocalDateTime.now(), 10, 10, 10, new ArrayList<Action>(), LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now());
        Pet petCopy = new Pet("teest", "dog", 0, 3, LocalDateTime.now(), 10, 10, 10, new ArrayList<Action>(), LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now());
        DataRepository dr = new DataRepository();
        dr.addPet(pet, new UserData("zenek", "zenek", "zenek", "zenkowski"));
        dr.addPet(petCopy, new UserData("zenek", "zenek", "zenek", "zenkowski"));
        c.makeLogicUpdate();
        pet.setHunger(2);
        c.makeLogicUpdate();
        Assert.assertNotSame(pet.getHunger(), petCopy.getHunger());
    }

    @Test
    public void makeLogicUpdateTest5() throws PetTransactionException {
        Controller c = new Controller(1);

        Pet pet = new Pet("teest", "dog", 0, 3, LocalDateTime.now(), 10, 10, 10, new ArrayList<Action>(), LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now());
        Pet petCopy = new Pet("teest", "dog", 0, 3, LocalDateTime.now(), 10, 10, 10, new ArrayList<Action>(), LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now());
        DataRepository dr = new DataRepository();
        dr.addPet(pet, new UserData("zenek", "zenek", "zenek", "zenkowski"));
        dr.addPet(petCopy, new UserData("zenek", "zenek", "zenek", "zenkowski"));
        c.makeLogicUpdate();
        pet.setHappiness(2);
        c.makeLogicUpdate();
        Assert.assertNotSame(pet.getHappiness(), petCopy.getHappiness());
    }

    @Test
    public void makeLogicUpdateTest6() throws PetTransactionException {
        Controller c = new Controller(1);

        Pet pet = new Pet("teest", "dog", 0, 3, LocalDateTime.now(), 10, 10, 10, new ArrayList<Action>(), LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now());
        Pet petCopy = new Pet("teest", "dog", 0, 3, LocalDateTime.now(), 10, 10, 10, new ArrayList<Action>(), LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now());
        DataRepository dr = new DataRepository();
        dr.addPet(pet, new UserData("zenek", "zenek", "zenek", "zenkowski"));
        dr.addPet(petCopy, new UserData("zenek", "zenek", "zenek", "zenkowski"));
        c.makeLogicUpdate();
        pet.setHealth(3);
        c.makeLogicUpdate();
        Assert.assertNotSame(pet.getHealth(), petCopy.getHealth());
    }
}
