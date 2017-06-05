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
}
