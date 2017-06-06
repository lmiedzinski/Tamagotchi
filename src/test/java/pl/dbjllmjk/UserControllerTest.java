/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.dbjllmjk;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import pl.dbjllmjk.Controller.AdminController;
import pl.dbjllmjk.Controller.Controller;
import pl.dbjllmjk.Controller.UserController;
import pl.dbjllmjk.Exceptions.NoSuchUserException;
import pl.dbjllmjk.Exceptions.PetTransactionException;
import pl.dbjllmjk.Model.AdminData;
import pl.dbjllmjk.Model.DataRepository;
import pl.dbjllmjk.Model.Pet;
import pl.dbjllmjk.Model.UserData;

public class UserControllerTest {

    @Test
    public void addPetByUserTest1() throws PetTransactionException, NoSuchUserException, UnsupportedEncodingException, NoSuchAlgorithmException {
        Controller c = new Controller(1);
        Optional<AdminData> admin = c.getDataRepository().getAdmins().stream().filter(x -> x.getLogin().equals("admin")).findFirst();
        c.afterLoginT(admin.get());
        AdminController a = new AdminController(c, null);
        a.addAccount("test6", "testPassword", "testName", "testSurname", false);
        Optional<UserData> user = c.getDataRepository().getUsers().stream().filter(x -> x.getLogin().equals("test6")).findFirst();
        if (user.isPresent()) {
            c = new Controller(1);
            c.afterLoginT(user.get());
            UserController uc = new UserController(c, 0);
            uc.addPet("testPet5", "dog");
            Boolean result = false;
            DataRepository dr = new DataRepository();
            Optional<Pet> optionalPet = dr.getPetsForUser(user.get()).stream().filter(x -> x.getName().equals("testPet5")).findFirst();
            if (optionalPet.isPresent()) {
                result = true;
            }
            dr.removePet(optionalPet.get());
            dr.removeUser(user.get());
            Assert.assertTrue(result);
        }
    }

    @Test(expected = PetTransactionException.class)
    public void addPetByUserTest2() throws PetTransactionException, NoSuchUserException, UnsupportedEncodingException, NoSuchAlgorithmException {
        Controller c = new Controller(1);
        Optional<AdminData> admin = c.getDataRepository().getAdmins().stream().filter(x -> x.getLogin().equals("admin")).findFirst();
        c.afterLoginT(admin.get());
        AdminController a = new AdminController(c, null);
        a.addAccount("test10", "testPassword", "testName", "testSurname", false);
        Optional<UserData> user = c.getDataRepository().getUsers().stream().filter(x -> x.getLogin().equals("test10")).findFirst();
        if (user.isPresent()) {
            c = new Controller(1);
            c.afterLoginT(user.get());
            UserController uc = new UserController(c, 0);
            c.getDataRepository().removeUser(user.get());
            uc.addPet("", "dog");
        }
    }

    @Test(expected = PetTransactionException.class)
    public void addPetByUserTest3() throws PetTransactionException, NoSuchUserException, UnsupportedEncodingException, NoSuchAlgorithmException {
        Controller c = new Controller(1);
        Optional<AdminData> admin = c.getDataRepository().getAdmins().stream().filter(x -> x.getLogin().equals("admin")).findFirst();
        c.afterLoginT(admin.get());
        AdminController a = new AdminController(c, null);
        a.addAccount("test10", "testPassword", "testName", "testSurname", false);
        Optional<UserData> user = c.getDataRepository().getUsers().stream().filter(x -> x.getLogin().equals("test10")).findFirst();
        if (user.isPresent()) {
            c = new Controller(1);
            c.afterLoginT(user.get());
            UserController uc = new UserController(c, 0);
            c.getDataRepository().removeUser(user.get());
            uc.addPet("jaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaamnik", "dog");

        }
    }

    @Test
    public void addPetByUserTest4() throws PetTransactionException, NoSuchUserException, UnsupportedEncodingException, NoSuchAlgorithmException {
        Controller c = new Controller(1);
        Optional<AdminData> admin = c.getDataRepository().getAdmins().stream().filter(x -> x.getLogin().equals("admin")).findFirst();
        c.afterLoginT(admin.get());
        AdminController a = new AdminController(c, null);
        a.addAccount("test10", "testPassword", "testName", "testSurname", false);
        Optional<UserData> user = c.getDataRepository().getUsers().stream().filter(x -> x.getLogin().equals("test10")).findFirst();
        if (user.isPresent()) {
            c = new Controller(1);
            c.afterLoginT(user.get());
            UserController uc = new UserController(c, 0);
            uc.addPet("jaaaaamnik", "dog");
            try {
                uc.addPet("jaaaaamnik", "dog");
            } catch (PetTransactionException e) {
                Assert.assertTrue(true);
            } finally {
                DataRepository dr = new DataRepository();
                Optional<Pet> petToDelete = c.getDataRepository().getPetsForUser(user.get()).stream().filter(x -> x.getName().equals("jaaaaamnik")).findFirst();
                if (petToDelete.isPresent()) dr.removePet(petToDelete.get());
                dr.removeUser(user.get());
            }
        }
    }


}
