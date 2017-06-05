/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.dbjllmjk;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.dbjllmjk.Controller.AdminController;
import pl.dbjllmjk.Controller.Controller;
import pl.dbjllmjk.Exceptions.NoSuchUserException;
import pl.dbjllmjk.Model.AccountData;
import pl.dbjllmjk.Model.AdminData;
import pl.dbjllmjk.Model.DataRepository;
import pl.dbjllmjk.Model.UserData;

/**
 *
 * @author Damian
 */
public class AdminViewTest {

    private Logger logger = LoggerFactory.getLogger(AdminViewTest.class);

    @Test
    public void addAccountByAdminTest0() throws NoSuchUserException {
        Controller c = new Controller();
        c.afterLogin(new AdminData("admin", "admin", "Administrator", "Adminsurname"));
        AdminController a = new AdminController(c);
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
    public void addAccountByAdminTest1() throws NoSuchUserException {
        Controller c = new Controller();
        c.afterLogin(new AdminData("admin", "admin", "Administrator", "Adminsurname"));
        AdminController a = new AdminController(c);
        a.addAccount("zenek", "testPassword", "testName", "testSurname", false);
    }

    @Test(expected = NoSuchUserException.class)
    public void addAccountByAdminTest2() throws NoSuchUserException {
        Controller c = new Controller();
        c.afterLogin(new AdminData("admin", "admin", "Administrator", "Adminsurname"));
        AdminController a = new AdminController(c);
        a.addAccount("a", "testPassword", "testName", "testSurname", false);
    }

    @Test(expected = NoSuchUserException.class)
    public void addAccountByAdminTest3() throws NoSuchUserException {
        Controller c = new Controller();
        c.afterLogin(new AdminData("admin", "admin", "Administrator", "Adminsurname"));
        AdminController a = new AdminController(c);
        a.addAccount("testLogin", "a", "testName", "testSurname", false);
    }

    @Test(expected = NoSuchUserException.class)
    public void addAccountByAdminTest4() throws NoSuchUserException {
        Controller c = new Controller();
        c.afterLogin(new AdminData("admin", "admin", "Administrator", "Adminsurname"));
        AdminController a = new AdminController(c);
        a.addAccount("testLogin", "aaaaaaaa", "a", "testSurname", false);
    }

    @Test(expected = NoSuchUserException.class)
    public void addAccountByAdminTest5() throws NoSuchUserException {
        Controller c = new Controller();
        c.afterLogin(new AdminData("admin", "admin", "Administrator", "Adminsurname"));
        AdminController a = new AdminController(c);
        a.addAccount("testLogin", "aaaaaaaa", "testName", "a", false);
    }

    @Test
    public void addAdminAccountByAdminTest0() throws NoSuchUserException {
        Controller c = new Controller();
        c.afterLogin(new AdminData("admin", "admin", "Administrator", "Adminsurname"));
        AdminController a = new AdminController(c);
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
    public void addAdminAccountByAdminTest1() throws NoSuchUserException {
        Controller c = new Controller();
        c.afterLogin(new AdminData("admin", "admin", "Administrator", "Adminsurname"));
        AdminController a = new AdminController(c);
        a.addAccount("admin", "testPassword", "testName", "testSurname", true);
    }

    @Test(expected = NoSuchUserException.class)
    public void addAdminAccountByAdminTest2() throws NoSuchUserException {
        Controller c = new Controller();
        c.afterLogin(new AdminData("admin", "admin", "Administrator", "Adminsurname"));
        AdminController a = new AdminController(c);
        a.addAccount("a", "testPassword", "testName", "testSurname", true);
    }

    @Test(expected = NoSuchUserException.class)
    public void addAdminAccountByAdminTest3() throws NoSuchUserException {
        Controller c = new Controller();
        c.afterLogin(new AdminData("admin", "admin", "Administrator", "Adminsurname"));
        AdminController a = new AdminController(c);
        a.addAccount("testLogin", "a", "testName", "testSurname", true);
    }

    @Test(expected = NoSuchUserException.class)
    public void addAdminAccountByAdminTest4() throws NoSuchUserException {
        Controller c = new Controller();
        c.afterLogin(new AdminData("admin", "admin", "Administrator", "Adminsurname"));
        AdminController a = new AdminController(c);
        a.addAccount("testLogin", "aaaaaaaa", "a", "testSurname", true);
    }

    @Test(expected = NoSuchUserException.class)
    public void addAdminAccountByAdminTest5() throws NoSuchUserException {
        Controller c = new Controller();
        c.afterLogin(new AdminData("admin", "admin", "Administrator", "Adminsurname"));
        AdminController a = new AdminController(c);
        a.addAccount("testLogin", "aaaaaaaa", "testName", "a", true);
    }
}
