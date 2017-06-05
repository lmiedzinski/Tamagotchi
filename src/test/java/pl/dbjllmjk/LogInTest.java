/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.dbjllmjk;

import javax.crypto.BadPaddingException;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.dbjllmjk.Controller.Controller;
import pl.dbjllmjk.Controller.LoginController;
import pl.dbjllmjk.Exceptions.BadPasswordException;
import pl.dbjllmjk.Exceptions.NoSuchUserException;

/**
 *
 * @author Damian
 */
public class LogInTest {

    private Logger logger = LoggerFactory.getLogger(AdminViewTest.class);

    @Test(expected = NoSuchUserException.class)
    public void LoginWithBadIDTest() throws NoSuchUserException,BadPasswordException {
        LoginController ln = new LoginController(new Controller());
        ln.tryToLog("uwgfyagfuykgayfgkajfgaekfagyfgk", "gneyshgkshukhi");
    }
    
    @Test(expected = BadPasswordException.class)
    public void LoginWithPwTest() throws NoSuchUserException,BadPasswordException {
        LoginController ln = new LoginController(new Controller());
        ln.tryToLog("admin", "gneyshgkshukhi");
    }
}
