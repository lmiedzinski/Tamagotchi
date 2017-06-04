package pl.dbjllmjk.Controller;

import pl.dbjllmjk.Model.Action;
import pl.dbjllmjk.Model.Pet;
import pl.dbjllmjk.Model.PetTransactionException;

import java.time.LocalDateTime;


/**
 * Klasa z logiką dotyczącą aktywności
 */
public class ActionLogic {

    /**
     * Metoda symulująca karmienie zwierzaka
     *
     * @param p zwierzak
     * @param a pokarm
     * @return zaktualizowany zwierzak
     * @throws PetTransactionException
     */
    public static Pet feedPet(Pet p, Action a) throws PetTransactionException {
        if (p == null) {
            throw new PetTransactionException("No such pet");
        }
        if (a == null) {
            throw new PetTransactionException("No such action");
        }
        if (a.getValue() > p.getHunger())
            throw new PetTransactionException("You can't feed pet more than " + p.getHunger());
        else {
            p.setHunger(p.getHunger() - a.getValue());
            p.setLastFeedingDate(LocalDateTime.now());
        }
        return p;
    }

    /**
     * Metoda symulująca zabawę ze zwierzakiem
     *
     * @param p zwierzak
     * @param a rodzaj zabawy
     * @return zaktualizowany zwierzak
     * @throws PetTransactionException
     */
    public static Pet playWithPet(Pet p, Action a) throws PetTransactionException {
        if (p == null) {
            throw new PetTransactionException("No such pet");
        }
        if (a == null) {
            throw new PetTransactionException("No such action");
        }
        if (a.getValue() > 10 - p.getHappiness())
            throw new PetTransactionException("You can't play with pet more than " + (10 - p.getHappiness()));
        else {
            p.setHappiness(p.getHappiness() + a.getValue());
            p.setLastActivityDate(LocalDateTime.now());
        }
        return p;
    }

    /**
     * Metoda symulująca operację ze zwierzakiem
     *
     * @param p zwierzak
     * @param a rodzaj operacji
     * @return zaktualizowany zwierzak
     * @throws PetTransactionException
     */
    public static Pet makeOperationOnPet(Pet p, Action a) throws PetTransactionException {
        if (p == null) {
            throw new PetTransactionException("No such pet");
        }
        if (a == null) {
            throw new PetTransactionException("No such action");
        }
        if (a.getValue() > 10 - p.getHealth())
            throw new PetTransactionException("You can't make operation on pet more than " + (10 - p.getHealth()));
        else {
            p.setHealth(p.getHealth() + a.getValue());
            p.setLastOperationDate(LocalDateTime.now());
        }
        return p;
    }
}
