/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.dbjllmjk;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.dbjllmjk.Controller.Controller;
import pl.dbjllmjk.Controller.LoginController;
import pl.dbjllmjk.Exceptions.BadPasswordException;
import pl.dbjllmjk.Exceptions.NoSuchUserException;
import pl.dbjllmjk.Model.DataRepository;
import pl.dbjllmjk.Model.UserData;

/**
 * @author Damian
 */
public class LogInTest {

    @Test(expected = NoSuchUserException.class)
    public void LoginWithBadIDTest() throws NoSuchUserException, BadPasswordException {
        LoginController ln = new LoginController(new Controller(1), 1);
        ln.tryToLogT("uwgfyagfuykgayfgkajfgaekfagyfgk", "gneyshgkshukhi");
    }

    @Test(expected = BadPasswordException.class)
    public void LoginWithBadPwTest() throws NoSuchUserException, BadPasswordException {
        LoginController ln = new LoginController(new Controller(1), 1);
        ln.tryToLogT("admin", "gneyshgkshukhi");
    }

    @Test
    public void CorrectLoginTest() throws NoSuchUserException, BadPasswordException {
        LoginController ln = new LoginController(new Controller(1), 1);
        ln.tryToLogT("admin", "admin");
    }

    @Test(expected = NoSuchUserException.class)
    public void CreateAccountByUserTest0() throws NoSuchUserException, BadPasswordException, UnsupportedEncodingException, NoSuchAlgorithmException {
        LoginController ln = new LoginController(new Controller(1), 1);
        ln.addAccount("a", "aaaaa", "aaaa", "aaaa");
    }

    @Test(expected = NoSuchUserException.class)
    public void CreateAccountByUserTest1() throws NoSuchUserException, BadPasswordException, UnsupportedEncodingException, NoSuchAlgorithmException {
        LoginController ln = new LoginController(new Controller(1), 1);
        ln.addAccount("aaaa", "a", "aaaa", "aaaa");
    }

    @Test(expected = NoSuchUserException.class)
    public void CreateAccountByUserTest2() throws NoSuchUserException, BadPasswordException, UnsupportedEncodingException, NoSuchAlgorithmException {
        LoginController ln = new LoginController(new Controller(1), 1);
        ln.addAccount("aaaa", "aaaaa", "a", "aaaa");
    }

    @Test(expected = NoSuchUserException.class)
    public void CreateAccountByUserTest3() throws NoSuchUserException, BadPasswordException, UnsupportedEncodingException, NoSuchAlgorithmException {
        LoginController ln = new LoginController(new Controller(1), 1);
        ln.addAccount("aaaa", "aaaaa", "aaaa", "a");
    }

    @Test(expected = NoSuchUserException.class)
    public void CreateAccountByUserTest4() throws NoSuchUserException, BadPasswordException, UnsupportedEncodingException, NoSuchAlgorithmException {
        LoginController ln = new LoginController(new Controller(1), 1);
        ln.addAccount("admin", "aaaaa", "aaaa", "a");
    }

    @Test
    public void CreateAccountByUserTest5() throws NoSuchUserException, BadPasswordException, UnsupportedEncodingException, NoSuchAlgorithmException {
        LoginController ln = new LoginController(new Controller(1), 1);
        ln.addAccount("aaaa", "aaaa", "aaaa", "aaaa");
        DataRepository d = new DataRepository();
        d.removeUser(new UserData("aaaa", "aaaa", "aaaa", "aaaa"));
    }


}
