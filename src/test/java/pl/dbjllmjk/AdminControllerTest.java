/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.dbjllmjk;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.dbjllmjk.Controller.AdminController;
import pl.dbjllmjk.Controller.Controller;
import pl.dbjllmjk.Exceptions.NoSuchUserException;
import pl.dbjllmjk.Exceptions.PetTransactionException;
import pl.dbjllmjk.Model.AccountData;
import pl.dbjllmjk.Model.Activity;
import pl.dbjllmjk.Model.AdminData;
import pl.dbjllmjk.Model.DataRepository;
import pl.dbjllmjk.Model.Food;
import pl.dbjllmjk.Model.Operation;
import pl.dbjllmjk.Model.UserData;
import pl.dbjllmjk.View.AdminView;

public class AdminControllerTest {

    @Test
    public void addAccountByAdminTest0() throws NoSuchUserException, UnsupportedEncodingException, NoSuchAlgorithmException {
        Controller c = new Controller(1);
        Optional<AdminData> admin = c.getDataRepository().getAdmins().stream().filter(x -> x.getLogin().equals("admin")).findFirst();
        c.afterLoginT(admin.get());
        AdminController a = new AdminController(c, null);
        a.addAccount("test", "testPassword", "testName", "testSurname", false);
        Boolean result = false;
        DataRepository dr = new DataRepository();
        Optional<UserData> userTest = dr.getUsers().stream().filter(x -> x.getLogin().equals("test")).findFirst();
        if (userTest.isPresent()) result = true;
        a.removeAccount("test");
        Assert.assertTrue(result);
    }

    @Test(expected = NoSuchUserException.class)
    public void addAccountByAdminTest1() throws NoSuchUserException, UnsupportedEncodingException, NoSuchAlgorithmException {
        Controller c = new Controller(1);
        Optional<AdminData> admin = c.getDataRepository().getAdmins().stream().filter(x -> x.getLogin().equals("admin")).findFirst();
        c.afterLoginT(admin.get());
        AdminController a = new AdminController(c, null);
        a.addAccount("zenek", "testPassword", "testName", "testSurname", false);
    }

    @Test(expected = NoSuchUserException.class)
    public void addAccountByAdminTest2() throws NoSuchUserException, UnsupportedEncodingException, NoSuchAlgorithmException {
        Controller c = new Controller(1);
        Optional<AdminData> admin = c.getDataRepository().getAdmins().stream().filter(x -> x.getLogin().equals("admin")).findFirst();
        c.afterLoginT(admin.get());
        AdminController a = new AdminController(c, null);
        a.addAccount("a", "testPassword", "testName", "testSurname", false);
    }

    @Test(expected = NoSuchUserException.class)
    public void addAccountByAdminTest3() throws NoSuchUserException, UnsupportedEncodingException, NoSuchAlgorithmException {
        Controller c = new Controller(1);
        Optional<AdminData> admin = c.getDataRepository().getAdmins().stream().filter(x -> x.getLogin().equals("admin")).findFirst();
        c.afterLoginT(admin.get());
        AdminController a = new AdminController(c, null);
        a.addAccount("testLogin", "a", "testName", "testSurname", false);
    }

    @Test(expected = NoSuchUserException.class)
    public void addAccountByAdminTest4() throws NoSuchUserException, UnsupportedEncodingException, NoSuchAlgorithmException {
        Controller c = new Controller(1);
        Optional<AdminData> admin = c.getDataRepository().getAdmins().stream().filter(x -> x.getLogin().equals("admin")).findFirst();
        c.afterLoginT(admin.get());
        AdminController a = new AdminController(c, null);
        a.addAccount("testLogin", "aaaaaaaa", "a", "testSurname", false);
    }

    @Test(expected = NoSuchUserException.class)
    public void addAccountByAdminTest5() throws NoSuchUserException, UnsupportedEncodingException, NoSuchAlgorithmException {
        Controller c = new Controller(1);
        Optional<AdminData> admin = c.getDataRepository().getAdmins().stream().filter(x -> x.getLogin().equals("admin")).findFirst();
        c.afterLoginT(admin.get());
        AdminController a = new AdminController(c, null);
        a.addAccount("testLogin", "aaaaaaaa", "testName", "a", false);
    }

    @Test
    public void addAdminAccountByAdminTest0() throws NoSuchUserException, UnsupportedEncodingException, NoSuchAlgorithmException {
        Controller c = new Controller(1);
        Optional<AdminData> admin = c.getDataRepository().getAdmins().stream().filter(x -> x.getLogin().equals("admin")).findFirst();
        c.afterLoginT(admin.get());
        AdminController a = new AdminController(c, null);
        a.addAccount("test", "testPassword", "testName", "testSurname", true);
        Boolean result = false;
        DataRepository dr = new DataRepository();
        Optional<AdminData> userTest = dr.getAdmins().stream().filter(x -> x.getLogin().equals("test")).findFirst();
        if (userTest.isPresent()) result = true;
        a.removeAccount("test");
        Assert.assertTrue(result);
    }

    @Test(expected = NoSuchUserException.class)
    public void addAdminAccountByAdminTest1() throws NoSuchUserException, UnsupportedEncodingException, NoSuchAlgorithmException {
        Controller c = new Controller(1);
        Optional<AdminData> admin = c.getDataRepository().getAdmins().stream().filter(x -> x.getLogin().equals("admin")).findFirst();
        c.afterLoginT(admin.get());
        AdminController a = new AdminController(c, null);
        a.addAccount("admin", "testPassword", "testName", "testSurname", true);
    }

    @Test(expected = NoSuchUserException.class)
    public void addAdminAccountByAdminTest2() throws NoSuchUserException, UnsupportedEncodingException, NoSuchAlgorithmException {
        Controller c = new Controller(1);
        Optional<AdminData> admin = c.getDataRepository().getAdmins().stream().filter(x -> x.getLogin().equals("admin")).findFirst();
        c.afterLoginT(admin.get());
        AdminController a = new AdminController(c, null);
        a.addAccount("a", "testPassword", "testName", "testSurname", true);
    }

    @Test(expected = NoSuchUserException.class)
    public void addAdminAccountByAdminTest3() throws NoSuchUserException, UnsupportedEncodingException, NoSuchAlgorithmException {
        Controller c = new Controller(1);
        Optional<AdminData> admin = c.getDataRepository().getAdmins().stream().filter(x -> x.getLogin().equals("admin")).findFirst();
        c.afterLoginT(admin.get());
        AdminController a = new AdminController(c, null);
        a.addAccount("testLogin", "a", "testName", "testSurname", true);
    }

    @Test(expected = NoSuchUserException.class)
    public void addAdminAccountByAdminTest4() throws NoSuchUserException, UnsupportedEncodingException, NoSuchAlgorithmException {
        Controller c = new Controller(1);
        Optional<AdminData> admin = c.getDataRepository().getAdmins().stream().filter(x -> x.getLogin().equals("admin")).findFirst();
        c.afterLoginT(admin.get());
        AdminController a = new AdminController(c, null);
        a.addAccount("testLogin", "aaaaaaaa", "a", "testSurname", true);
    }

    @Test(expected = NoSuchUserException.class)
    public void addAdminAccountByAdminTest5() throws NoSuchUserException, UnsupportedEncodingException, NoSuchAlgorithmException {
        Controller c = new Controller(1);
        Optional<AdminData> admin = c.getDataRepository().getAdmins().stream().filter(x -> x.getLogin().equals("admin")).findFirst();
        c.afterLoginT(admin.get());
        AdminController a = new AdminController(c, null);
        a.addAccount("testLogin", "aaaaaaaa", "testName", "a", true);
    }

    @Test
    public void removeAdminTest0() throws NoSuchUserException, UnsupportedEncodingException, NoSuchAlgorithmException {
        Controller c = new Controller(1);
        Optional<AdminData> admin = c.getDataRepository().getAdmins().stream().filter(x -> x.getLogin().equals("admin")).findFirst();
        c.afterLoginT(admin.get());
        AdminController a = new AdminController(c, null);
        a.addAccount("test", "testPassword", "testName", "testSurname", false);
        Boolean result = true;
        a.removeAccount("test");
        DataRepository dr = new DataRepository();
        Optional<UserData> userTest = dr.getUsers().stream().filter(x -> x.getLogin().equals("test")).findFirst();
        if (userTest.isPresent()) result = false;
        Assert.assertTrue(result);
    }

    @Test
    public void removeAdminTest1() throws NoSuchUserException, UnsupportedEncodingException, NoSuchAlgorithmException {
        Controller c = new Controller(1);
        Optional<AdminData> admin = c.getDataRepository().getAdmins().stream().filter(x -> x.getLogin().equals("admin")).findFirst();
        c.afterLoginT(admin.get());
        AdminController a = new AdminController(c, null);
        a.addAccount("test", "testPassword", "testName", "testSurname", true);
        Boolean result = true;
        a.removeAccount("test");
        DataRepository dr = new DataRepository();
        Optional<AdminData> userTest = dr.getAdmins().stream().filter(x -> x.getLogin().equals("test")).findFirst();
        if (userTest.isPresent()) result = false;
        Assert.assertTrue(result);
    }

    @Test(expected = NoSuchUserException.class)
    public void removeAdminTest2() throws NoSuchUserException {
        Controller c = new Controller(1);
        Optional<AdminData> admin = c.getDataRepository().getAdmins().stream().filter(x -> x.getLogin().equals("admin")).findFirst();
        c.afterLoginT(admin.get());
        AdminController a = new AdminController(c, null);
        a.removeAccount("enfehbskfsghefkshekueslgshlfshlk");
    }

    @Test(expected = NoSuchUserException.class)
    public void removeAdminTest3() throws NoSuchUserException {
        Controller c = new Controller(1);
        Optional<AdminData> admin = c.getDataRepository().getAdmins().stream().filter(x -> x.getLogin().equals("admin")).findFirst();
        c.afterLoginT(admin.get());
        AdminController a = new AdminController(c, null);
        a.removeAccount(admin.get().getLogin());
    }

    @Test
    public void addPetTypeTest0() throws PetTransactionException {
        Controller c = new Controller(1);
        Optional<AdminData> admin = c.getDataRepository().getAdmins().stream().filter(x -> x.getLogin().equals("admin")).findFirst();
        c.afterLoginT(admin.get());
        AdminController a = new AdminController(c, null);
        a.addPetType("testType1");
        boolean result = false;
        DataRepository dr = new DataRepository();
        Optional<String> userTest = Arrays.asList(dr.getAvaliablePetTypes()).stream().filter(x -> x.equals("testType1")).findFirst();
        if (userTest.isPresent()) result = true;
        Assert.assertTrue(result);
        a.removePetType("testType1");
    }

    @Test(expected = PetTransactionException.class)
    public void addPetTypeTest1() throws PetTransactionException {
        Controller c = new Controller(1);
        Optional<AdminData> admin = c.getDataRepository().getAdmins().stream().filter(x -> x.getLogin().equals("admin")).findFirst();
        c.afterLoginT(admin.get());
        AdminController a = new AdminController(c, null);
        a.addPetType("testType");
        a.addPetType("testType");
        a.removePetType("testType");
    }

    @Test
    public void addFoodTypeTest0() throws PetTransactionException {
        Controller c = new Controller(1);
        Optional<AdminData> admin = c.getDataRepository().getAdmins().stream().filter(x -> x.getLogin().equals("admin")).findFirst();
        c.afterLoginT(admin.get());
        AdminController a = new AdminController(c, null);
        a.addFoodType("testFood12345", 1);
        boolean result = false;
        DataRepository dr = new DataRepository();
        Optional<Food> userTest = dr.getAllActions(new Food()).stream().filter(x -> x.getName().equals("testFood12345")).findFirst();
        if (userTest.isPresent()) result = true;
        Assert.assertTrue(result);
        a.removeActionType(new Food("testFood12345", 1));
    }

    @Test(expected = PetTransactionException.class)
    public void addFoodTypeTest1() throws PetTransactionException {
        Controller c = new Controller(1);
        Optional<AdminData> admin = c.getDataRepository().getAdmins().stream().filter(x -> x.getLogin().equals("admin")).findFirst();
        c.afterLoginT(admin.get());
        AdminController a = new AdminController(c, null);
        a.addFoodType("testF2", 1);
        a.addFoodType("testF2", 1);
        a.removeActionType(new Food("testF2", 1));
    }

    @Test(expected = PetTransactionException.class)
    public void addFoodTypeTest2() throws PetTransactionException {
        Controller c = new Controller(1);
        Optional<AdminData> admin = c.getDataRepository().getAdmins().stream().filter(x -> x.getLogin().equals("admin")).findFirst();
        c.afterLoginT(admin.get());
        AdminController a = new AdminController(c, null);
        a.addFoodType("te", 1);
    }

    @Test
    public void addActivityTypeTest0() throws PetTransactionException {
        Controller c = new Controller(1);
        Optional<AdminData> admin = c.getDataRepository().getAdmins().stream().filter(x -> x.getLogin().equals("admin")).findFirst();
        c.afterLoginT(admin.get());
        AdminController a = new AdminController(c, null);
        a.addActivityType("testF1k", 1);
        boolean result = false;
        DataRepository dr = new DataRepository();
        Optional<Activity> userTest = dr.getAllActions(new Activity()).stream().filter(x -> x.getName().equals("testF1k")).findFirst();
        if (userTest.isPresent()) result = true;
        Assert.assertTrue(result);
        a.removeActionType(new Activity("testF1k", 1));
    }

    @Test(expected = PetTransactionException.class)
    public void addActivityTypeTest1() throws PetTransactionException {
        Controller c = new Controller(1);
        Optional<AdminData> admin = c.getDataRepository().getAdmins().stream().filter(x -> x.getLogin().equals("admin")).findFirst();
        c.afterLoginT(admin.get());
        AdminController a = new AdminController(c, null);
        a.addActivityType("testF1", 1);
        a.addActivityType("testF1", 1);
        a.removeActionType(new Activity("testF1", 1));
    }

    @Test(expected = PetTransactionException.class)
    public void addActivityTypeTest2() throws PetTransactionException {
        Controller c = new Controller(1);
        Optional<AdminData> admin = c.getDataRepository().getAdmins().stream().filter(x -> x.getLogin().equals("admin")).findFirst();
        c.afterLoginT(admin.get());
        AdminController a = new AdminController(c, null);
        a.addActivityType("te", 1);
    }

    @Test
    public void addOperationTypeTest0() throws PetTransactionException {
        Controller c = new Controller(1);
        Optional<AdminData> admin = c.getDataRepository().getAdmins().stream().filter(x -> x.getLogin().equals("admin")).findFirst();
        c.afterLoginT(admin.get());
        AdminController a = new AdminController(c, null);
        a.addOperationType("testF1k", 1);
        boolean result = false;
        DataRepository dr = new DataRepository();
        Optional<Operation> userTest = dr.getAllActions(new Operation()).stream().filter(x -> x.getName().equals("testF1k")).findFirst();
        if (userTest.isPresent()) result = true;
        Assert.assertTrue(result);
        a.removeActionType(new Operation("testF1k", 1));
    }

    @Test(expected = PetTransactionException.class)
    public void addOperationTypeTest1() throws PetTransactionException {
        Controller c = new Controller(1);
        Optional<AdminData> admin = c.getDataRepository().getAdmins().stream().filter(x -> x.getLogin().equals("admin")).findFirst();
        c.afterLoginT(admin.get());
        AdminController a = new AdminController(c, null);
        a.addOperationType("testF1", 1);
        a.addOperationType("testF1", 1);
        a.removeActionType(new Operation("testF1", 1));
    }

    @Test(expected = PetTransactionException.class)
    public void addOperationTypeTest2() throws PetTransactionException {
        Controller c = new Controller(1);
        Optional<AdminData> admin = c.getDataRepository().getAdmins().stream().filter(x -> x.getLogin().equals("admin")).findFirst();
        c.afterLoginT(admin.get());
        AdminController a = new AdminController(c, null);
        a.addOperationType("te", 1);
    }


    @Test(expected = NullPointerException.class)
    public void removeTypeTest0() throws NullPointerException {
        Controller c = new Controller(1);
        Optional<AdminData> admin = c.getDataRepository().getAdmins().stream().filter(x -> x.getLogin().equals("admin")).findFirst();
        c.afterLoginT(admin.get());
        AdminController a = new AdminController(c, null);
        a.removeActionType(new Food("kkkkkk", 0));
    }


}
