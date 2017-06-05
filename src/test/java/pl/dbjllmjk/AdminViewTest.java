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
    public void addAccountTest0() throws NoSuchUserException {

        Controller c = new Controller();
        c.afterLogin(new AdminData("admin", "admin", "Administrator", "Adminsurname"));
        AdminController a = new AdminController(c);
        a.addAccount("testLogin7", "testPassword", "testName", "testSurname", false);
        Boolean result = false;
        for (UserData u : new DataRepository().getUsers()) {
            if (u.equals(new UserData("testLogin7", "testPassword", "testName", "testSurname")));
            {
                result = true;
            }
        }
        a.removeAccount("testLogin7");
        Assert.assertTrue(result);
        
    }

}
