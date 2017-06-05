package pl.dbjllmjk;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.dbjllmjk.Controller.ActionLogic;
import pl.dbjllmjk.Model.Food;
import pl.dbjllmjk.Model.Pet;
import pl.dbjllmjk.Exceptions.PetTransactionException;

import java.time.LocalDateTime;
import pl.dbjllmjk.Model.Activity;
import pl.dbjllmjk.Model.Operation;

/**
 * Unit test for simple App.
 */
public class AppTest {

    private Logger logger = LoggerFactory.getLogger(AppTest.class);

    @Test
    public void feedPetTest0() {
        Pet p = new Pet("Tester", "Dog", 10, 11.25, LocalDateTime.now(), 10,
                5, 10, null, LocalDateTime.now(),
                LocalDateTime.now(), LocalDateTime.now());
        Food f = new Food("jedzenie", 5);
        try {
            Assert.assertEquals(LocalDateTime.now().getMinute(), ActionLogic.feedPet(p, f).getLastFeedingDate().getMinute());
        } catch (PetTransactionException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void feedPetTest1() {
        Pet p = new Pet("Tester", "Dog", 10, 11.25, LocalDateTime.now(), 10,
                5, 10, null, LocalDateTime.now(),
                LocalDateTime.now(), LocalDateTime.now());
        Food f = new Food("jedzenie", 5);
        try {
            Assert.assertEquals(0, ActionLogic.feedPet(p, f).getHunger());
        } catch (PetTransactionException e) {
            e.printStackTrace();
        }
    }

    @Test(expected = PetTransactionException.class)
    public void feedPetTest2() throws PetTransactionException {
        Pet p = new Pet("Tester", "Dog", 10, 11.25, LocalDateTime.now(), 10,
                3, 10, null, LocalDateTime.now(),
                LocalDateTime.now(), LocalDateTime.now());
        Food f = new Food("jedzenie", 5);
        ActionLogic.feedPet(p, f);
    }

    @Test(expected = PetTransactionException.class)
    public void feedPetTest3() throws PetTransactionException {
        Pet p = null;
        Food f = new Food("jedzenie", 5);
        ActionLogic.feedPet(p, f);
    }

    @Test(expected = PetTransactionException.class)
    public void feedPetTest4() throws PetTransactionException {
        Pet p = new Pet("Tester", "Dog", 10, 11.25, LocalDateTime.now(), 10,
                3, 10, null, LocalDateTime.now(),
                LocalDateTime.now(), LocalDateTime.now());
        Food f = null;
        ActionLogic.feedPet(p, f);
    }
    
    @Test
    public void playWithPetTest0() {
        Pet p = new Pet("Tester", "Dog", 10, 11.25, LocalDateTime.now(), 10,
                5, 10, null, LocalDateTime.now(),
                LocalDateTime.now(), LocalDateTime.now());
         Activity f= new Activity("test", 5);
        try {
            Assert.assertEquals(LocalDateTime.now().getMinute(), ActionLogic.playWithPet(p, f).getLastActivityDate().getMinute());
        } catch (PetTransactionException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void playWithPetTest1() {
        Pet p = new Pet("Tester", "Dog", 10, 11.25, LocalDateTime.now(), 10,
                5, 10, null, LocalDateTime.now(),
                LocalDateTime.now(), LocalDateTime.now());
        Activity f= new Activity("test", 5);
        try {
            Assert.assertEquals(0, ActionLogic.playWithPet(p, f).getHappiness());
        } catch (PetTransactionException e) {
            e.printStackTrace();
        }
    }

    @Test(expected = PetTransactionException.class)
    public void playWithPetTest2() throws PetTransactionException {
        Pet p = new Pet("Tester", "Dog", 10, 11.25, LocalDateTime.now(), 10,
                3, 10, null, LocalDateTime.now(),
                LocalDateTime.now(), LocalDateTime.now());
        Activity f= new Activity("test", 5);
        ActionLogic.playWithPet(p, f);
    }

    @Test(expected = PetTransactionException.class)
    public void playWithPetTest3() throws PetTransactionException {
        Pet p = null;
        Activity f= new Activity("test", 5);
        ActionLogic.playWithPet(p, f);
    }

    @Test(expected = PetTransactionException.class)
    public void playWithPetTest4() throws PetTransactionException {
        Pet p = new Pet("Tester", "Dog", 10, 11.25, LocalDateTime.now(), 10,
                3, 10, null, LocalDateTime.now(),
                LocalDateTime.now(), LocalDateTime.now());
        Activity f= new Activity("test", 5);
        ActionLogic.playWithPet(p, f);
    }
    @Test
    public void makeOperationOnPetTest0() {
        Pet p = new Pet("Tester", "Dog", 10, 11.25, LocalDateTime.now(), 10,
                5, 10, null, LocalDateTime.now(),
                LocalDateTime.now(), LocalDateTime.now());
         Operation f= new Operation("test", 5);
        try {
            Assert.assertEquals(LocalDateTime.now().getMinute(), ActionLogic.makeOperationOnPet(p, f).getLastOperationDate().getMinute());
        } catch (PetTransactionException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void makeOperationOnPetTest1() {
        Pet p = new Pet("Tester", "Dog", 10, 11.25, LocalDateTime.now(), 10,
                5, 10, null, LocalDateTime.now(),
                LocalDateTime.now(), LocalDateTime.now());
        Operation f= new Operation("test", 5);
        try {
            Assert.assertEquals(0, ActionLogic.makeOperationOnPet(p, f).getHealth());
        } catch (PetTransactionException e) {
            e.printStackTrace();
        }
    }

    @Test(expected = PetTransactionException.class)
    public void makeOperationOnPetTest2() throws PetTransactionException {
        Pet p = new Pet("Tester", "Dog", 10, 11.25, LocalDateTime.now(), 10,
                3, 10, null, LocalDateTime.now(),
                LocalDateTime.now(), LocalDateTime.now());
        Operation f= new Operation("test", 5);
        ActionLogic.makeOperationOnPet(p, f);
    }

    @Test(expected = PetTransactionException.class)
    public void makeOperationOnPetTest3() throws PetTransactionException {
        Pet p = null;
        Operation f= new Operation("test", 5);
        ActionLogic.makeOperationOnPet(p, f);
    }

    @Test(expected = PetTransactionException.class)
    public void makeOperationOnPetTest4() throws PetTransactionException {
        Pet p = new Pet("Tester", "Dog", 10, 11.25, LocalDateTime.now(), 10,
                3, 10, null, LocalDateTime.now(),
                LocalDateTime.now(), LocalDateTime.now());
        Operation f= new Operation("test", 5);
        ActionLogic.makeOperationOnPet(p, f);
    }
}
