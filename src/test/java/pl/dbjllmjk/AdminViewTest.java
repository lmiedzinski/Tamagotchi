/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.dbjllmjk;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
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

/**
 *
 * @author Damian
 */
public class AdminViewTest {

    private Logger logger = LoggerFactory.getLogger(AdminViewTest.class);

    @Test
    public void addAccountByAdminTest0() throws NoSuchUserException, UnsupportedEncodingException, NoSuchAlgorithmException {
        Controller c = new Controller(1);
        c.afterLoginT(new AdminData("admin", "admin", "Administrator", "Adminsurname"));
        AdminController a = new AdminController(c, null);
        a.addAccount("test", "testPassword", "testName", "testSurname", false);
        Boolean result = false;
        for (UserData u : new DataRepository().getUsers()) {
            if (u.equals(new UserData("test", "testPassword",
                    "testName", "testSurname")));
            {
                result = true;
            }
        }
        a.removeAccount("test");
        Assert.assertTrue(result);
    }

    @Test(expected = NoSuchUserException.class)
    public void addAccountByAdminTest1() throws NoSuchUserException, UnsupportedEncodingException, NoSuchAlgorithmException {
        Controller c = new Controller(1);
        c.afterLoginT(new AdminData("admin", "admin", "Administrator", "Adminsurname"));
        AdminController a = new AdminController(c, null);
        a.addAccount("zenek", "testPassword", "testName", "testSurname", false);
    }

    @Test(expected = NoSuchUserException.class)
    public void addAccountByAdminTest2() throws NoSuchUserException, UnsupportedEncodingException, NoSuchAlgorithmException {
        Controller c = new Controller(1);
        c.afterLoginT(new AdminData("admin", "admin", "Administrator", "Adminsurname"));
        AdminController a = new AdminController(c, null);
        a.addAccount("a", "testPassword", "testName", "testSurname", false);
    }

    @Test(expected = NoSuchUserException.class)
    public void addAccountByAdminTest3() throws NoSuchUserException, UnsupportedEncodingException, NoSuchAlgorithmException {
        Controller c = new Controller(1);
        c.afterLoginT(new AdminData("admin", "admin", "Administrator", "Adminsurname"));
        AdminController a = new AdminController(c, null);
        a.addAccount("testLogin", "a", "testName", "testSurname", false);
    }

    @Test(expected = NoSuchUserException.class)
    public void addAccountByAdminTest4() throws NoSuchUserException, UnsupportedEncodingException, NoSuchAlgorithmException {
        Controller c = new Controller(1);
        c.afterLoginT(new AdminData("admin", "admin", "Administrator", "Adminsurname"));
        AdminController a = new AdminController(c, null);
        a.addAccount("testLogin", "aaaaaaaa", "a", "testSurname", false);
    }

    @Test(expected = NoSuchUserException.class)
    public void addAccountByAdminTest5() throws NoSuchUserException, UnsupportedEncodingException, NoSuchAlgorithmException {
        Controller c = new Controller(1);
        c.afterLoginT(new AdminData("admin", "admin", "Administrator", "Adminsurname"));
        AdminController a = new AdminController(c, null);
        a.addAccount("testLogin", "aaaaaaaa", "testName", "a", false);
    }

    @Test
    public void addAdminAccountByAdminTest0() throws NoSuchUserException, UnsupportedEncodingException, NoSuchAlgorithmException {
        Controller c = new Controller(1);
        c.afterLoginT(new AdminData("admin", "admin", "Administrator", "Adminsurname"));
        AdminController a = new AdminController(c, null);
        a.addAccount("test", "testPassword", "testName", "testSurname", true);
        Boolean result = false;
        for (AdminData u : new DataRepository().getAdmins()) {
            if (u.equals(new UserData("test", "testPassword",
                    "testName", "testSurname")));
            {
                result = true;
            }
        }
        a.removeAccount("test");
        Assert.assertTrue(result);
    }

    @Test(expected = NoSuchUserException.class)
    public void addAdminAccountByAdminTest1() throws NoSuchUserException, UnsupportedEncodingException, NoSuchAlgorithmException {
        Controller c = new Controller(1);
        c.afterLoginT(new AdminData("admin", "admin", "Administrator", "Adminsurname"));
        AdminController a = new AdminController(c, null);
        a.addAccount("admin", "testPassword", "testName", "testSurname", true);
    }

    @Test(expected = NoSuchUserException.class)
    public void addAdminAccountByAdminTest2() throws NoSuchUserException, UnsupportedEncodingException, NoSuchAlgorithmException {
        Controller c = new Controller(1);
        c.afterLoginT(new AdminData("admin", "admin", "Administrator", "Adminsurname"));
        AdminController a = new AdminController(c, null);
        a.addAccount("a", "testPassword", "testName", "testSurname", true);
    }

    @Test(expected = NoSuchUserException.class)
    public void addAdminAccountByAdminTest3() throws NoSuchUserException, UnsupportedEncodingException, NoSuchAlgorithmException {
        Controller c = new Controller(1);
        c.afterLoginT(new AdminData("admin", "admin", "Administrator", "Adminsurname"));
        AdminController a = new AdminController(c, null);
        a.addAccount("testLogin", "a", "testName", "testSurname", true);
    }

    @Test(expected = NoSuchUserException.class)
    public void addAdminAccountByAdminTest4() throws NoSuchUserException, UnsupportedEncodingException, NoSuchAlgorithmException {
        Controller c = new Controller(1);
        c.afterLoginT(new AdminData("admin", "admin", "Administrator", "Adminsurname"));
        AdminController a = new AdminController(c, null);
        a.addAccount("testLogin", "aaaaaaaa", "a", "testSurname", true);
    }

    @Test(expected = NoSuchUserException.class)
    public void addAdminAccountByAdminTest5() throws NoSuchUserException, UnsupportedEncodingException, NoSuchAlgorithmException {
        Controller c = new Controller(1);
        c.afterLoginT(new AdminData("admin", "admin", "Administrator", "Adminsurname"));
        AdminController a = new AdminController(c, null);
        a.addAccount("testLogin", "aaaaaaaa", "testName", "a", true);
    }

    @Test
    public void removeAdminTest0() throws NoSuchUserException, UnsupportedEncodingException, NoSuchAlgorithmException {
        Controller c = new Controller(1);
        c.afterLoginT(new AdminData("admin", "admin", "Administrator", "Adminsurname"));
        AdminController a = new AdminController(c, null);
        a.addAccount("test", "testPassword", "testName", "testSurname", false);
        UserData ud = new UserData("test", "testPassword", "testName", "testSurname");
        DataRepository dataRepository = new DataRepository();
        Boolean result = false;
        for (UserData u : dataRepository.getUsers()) {
            if (u.equals(ud));
            {
                result = true;
            }
        }
        a.removeAccount("test");
        result = false;
        for (UserData u : dataRepository.getUsers()) {
            if (u.equals(ud));
            {
                result = true;
            }
        }
        Assert.assertTrue(result);
    }

    @Test
    public void removeAdminTest1() throws NoSuchUserException, UnsupportedEncodingException, NoSuchAlgorithmException {
        Controller c = new Controller(1);
        c.afterLoginT(new AdminData("admin", "admin", "Administrator", "Adminsurname"));
        AdminController a = new AdminController(c, null);
        a.addAccount("test", "testPassword", "testName", "testSurname", true);
        UserData ud = new UserData("test", "testPassword", "testName", "testSurname");
        DataRepository dataRepository = new DataRepository();
        Boolean result = false;
        for (UserData u : dataRepository.getUsers()) {
            if (u.equals(ud));
            {
                result = true;
            }
        }
        a.removeAccount("test");
        result = false;
        for (UserData u : dataRepository.getUsers()) {
            if (u.equals(ud));
            {
                result = true;
            }
        }
        Assert.assertTrue(result);
    }

    @Test(expected = NoSuchUserException.class)
    public void removeAdminTest2() throws NoSuchUserException {
        Controller c = new Controller(1);
        c.afterLoginT(new AdminData("admin", "admin", "Administrator", "Adminsurname"));
        AdminController a = new AdminController(c, null);
        a.removeAccount("enfehbskfsghefkshekueslgshlfshlk");
    }

    @Test(expected = NoSuchUserException.class)
    public void removeAdminTest3() throws NoSuchUserException {
        Controller c = new Controller(1);
        AdminData admin = new AdminData("admin", "admin", "Administrator", "Adminsurname");
        c.afterLoginT(admin);
        AdminController a = new AdminController(c, null);
        a.removeAccount(admin.getLogin());
    }

//    @Test
//    public void getAdminsTest0() throws NoSuchUserException {
//        Controller c = new Controller(1);
//        c.afterLoginT(new AdminData("admin", "admin", "Administrator", "Adminsurname"));
//        AdminController a = new AdminController(c, null);
//        a.addAccount("testowy1", "aaaaaaaa", "testName", "aaaaaa", true);
//        Object[] ad = a.getAdmins();
//        a.removeAccount("testowy1");
//        Assert.assertTrue(ad.length > 0);
//;
//    }
    @Test
    public void addPetTypeTest0() throws PetTransactionException {
        Controller c = new Controller(1);
        AdminData admin = new AdminData("admin", "admin", "Administrator", "Adminsurname");
        c.afterLoginT(admin);
        AdminController a = new AdminController(c, null);
        String[] types = a.getAvaliablePetTypes();
        int k = types.length;
        a.addPetType("testType1");
        DataRepository d = new DataRepository();
        types = a.getAvaliablePetTypes();
        Assert.assertTrue(k != types.length);
        a.removePetType("testType1");
    }

    @Test(expected = PetTransactionException.class)
    public void addPetTypeTest1() throws PetTransactionException {
        Controller c = new Controller(1);
        AdminData admin = new AdminData("admin", "admin", "Administrator", "Adminsurname");
        c.afterLoginT(admin);
        AdminController a = new AdminController(c, null);
        String[] types = a.getAvaliablePetTypes();
        int k = types.length;
        a.addPetType("testType");
        a.addPetType("testType");
        a.removePetType("testType");
    }

    @Test
    public void addFoodTypeTest0() throws PetTransactionException {
        Controller c = new Controller(1);
        AdminData admin = new AdminData("admin", "admin", "Administrator", "Adminsurname");
        c.afterLoginT(admin);
        AdminController a = new AdminController(c, null);
        String[] types = a.getAvaliablePetTypes();
        int k = types.length;
        a.addFoodType("testF1", 1);
        a.removeActionType(new Food("testF1", 1));
    }

    @Test(expected = PetTransactionException.class)
    public void addFoodTypeTest1() throws PetTransactionException {
        Controller c = new Controller(1);
        AdminData admin = new AdminData("admin", "admin", "Administrator", "Adminsurname");
        c.afterLoginT(admin);
        AdminController a = new AdminController(c, null);
        String[] types = a.getAvaliablePetTypes();
        int k = types.length;
        a.addFoodType("testF2", 1);
        a.addFoodType("testF2", 1);
        a.removeActionType(new Food("testF2", 1));
    }

    @Test(expected = PetTransactionException.class)
    public void addFoodTypeTest2() throws PetTransactionException {
        Controller c = new Controller(1);
        AdminData admin = new AdminData("admin", "admin", "Administrator", "Adminsurname");
        c.afterLoginT(admin);
        AdminController a = new AdminController(c, null);
        String[] types = a.getAvaliablePetTypes();
        int k = types.length;
        a.addFoodType("te", 1);
    }

    @Test
    public void addActivityTypeTest0() throws PetTransactionException {
        Controller c = new Controller(1);
        AdminData admin = new AdminData("admin", "admin", "Administrator", "Adminsurname");
        c.afterLoginT(admin);
        AdminController a = new AdminController(c, null);
        String[] types = a.getAvaliablePetTypes();
        int k = types.length;
        a.addActivityType("testF1k", 1);
        a.removeActionType(new Activity("testF1k", 1));
    }

    @Test(expected = PetTransactionException.class)
    public void addActivityTypeTest1() throws PetTransactionException {
        Controller c = new Controller(1);
        AdminData admin = new AdminData("admin", "admin", "Administrator", "Adminsurname");
        c.afterLoginT(admin);
        AdminController a = new AdminController(c, null);
        String[] types = a.getAvaliablePetTypes();
        int k = types.length;
        a.addActivityType("testF1", 1);
        a.addActivityType("testF1", 1);
        a.removeActionType(new Activity("testF1", 1));
    }

    @Test(expected = PetTransactionException.class)
    public void addActivityTypeTest2() throws PetTransactionException {
        Controller c = new Controller(1);
        AdminData admin = new AdminData("admin", "admin", "Administrator", "Adminsurname");
        c.afterLoginT(admin);
        AdminController a = new AdminController(c, null);
        String[] types = a.getAvaliablePetTypes();
        int k = types.length;
        a.addActivityType("te", 1);
    }

    @Test
    public void addOperationTypeTest0() throws PetTransactionException {
        Controller c = new Controller(1);
        AdminData admin = new AdminData("admin", "admin", "Administrator", "Adminsurname");
        c.afterLoginT(admin);
        AdminController a = new AdminController(c, null);
        String[] types = a.getAvaliablePetTypes();
        int k = types.length;
        a.addOperationType("testFo111", 1);
        a.removeActionType(new Operation("testFo111", 1));
    }

    @Test(expected = PetTransactionException.class)
    public void addOperationTypeTest1() throws PetTransactionException {
        Controller c = new Controller(1);
        AdminData admin = new AdminData("admin", "admin", "Administrator", "Adminsurname");
        c.afterLoginT(admin);
        AdminController a = new AdminController(c, null);
        String[] types = a.getAvaliablePetTypes();
        int k = types.length;
        a.addOperationType("testF1", 1);
        a.addOperationType("testF1", 1);
        a.removeActionType(new Operation("testF1", 1));
    }

    @Test(expected = PetTransactionException.class)
    public void addOperationTypeTest2() throws PetTransactionException {
        Controller c = new Controller(1);
        AdminData admin = new AdminData("admin", "admin", "Administrator", "Adminsurname");
        c.afterLoginT(admin);
        AdminController a = new AdminController(c, null);
        String[] types = a.getAvaliablePetTypes();
        int k = types.length;
        a.addOperationType("te", 1);
    }
    
   
@Test(expected = NullPointerException.class)
    public void removeTypeTest0() throws NullPointerException {
        Controller c = new Controller(1);
        AdminData admin = new AdminData("admin", "admin", "Administrator", "Adminsurname");
        c.afterLoginT(admin);
        AdminController a = new AdminController(c, null);
        a.removeActionType(new Food("kkkkkk", 0));
    }

    
    
}
