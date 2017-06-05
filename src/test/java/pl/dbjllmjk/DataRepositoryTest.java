/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.dbjllmjk;

import com.healthmarketscience.jackcess.Database;
import java.util.Arrays;
import java.util.List;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;
import pl.dbjllmjk.Model.DataRepository;
import pl.dbjllmjk.Model.UserData;


/**
 *
 * @author Kubek
 */
@RunWith(MockitoJUnitRunner.class)
public class DataRepositoryTest {
    
    @Mock
    private static DataRepository mockedDataRepository;
    
    @BeforeClass
    public static void setUp(){
        mockedDataRepository = new DataRepository();
    }
    
    @AfterClass
    public static void tearDown(){
        mockedDataRepository = null;
    }
    
    @Test
    public void testGetUsersSize()
    {
        UserData user1 = new UserData("user1", "pass1", "Albert", "Almighty");
        UserData user2 = new UserData("user2", "pass2", "Brian", "Benevolent");
        
        when(mockedDataRepository.getUsers()).thenReturn(Arrays.asList(user1, user2));
        
        List<UserData> users = mockedDataRepository.getUsers();
        
        assertEquals(2, users.size());
    }
    
    @Test
    public void testGetUsersLogin()
    {
        UserData user1 = new UserData("user1", "pass1", "Albert", "Almighty");
        UserData user2 = new UserData("user2", "pass2", "Brian", "Benevolent");
        
        when(mockedDataRepository.getUsers()).thenReturn(Arrays.asList(user1, user2));
        
        List<UserData> users = mockedDataRepository.getUsers();
        
        assertEquals("user1", users.get(0).getLogin());
    }
    
    @Test
    public void testGetUsersPassword()
    {
        UserData user1 = new UserData("user1", "pass1", "Albert", "Almighty");
        UserData user2 = new UserData("user2", "pass2", "Brian", "Benevolent");
        
        when(mockedDataRepository.getUsers()).thenReturn(Arrays.asList(user1, user2));
        
        List<UserData> users = mockedDataRepository.getUsers();
        
        assertEquals("pass1", users.get(0).getPassword());
    }
    
    @Test
    public void testGetUsersName()
    {
        UserData user1 = new UserData("user1", "pass1", "Albert", "Almighty");
        UserData user2 = new UserData("user2", "pass2", "Brian", "Benevolent");
        
        when(mockedDataRepository.getUsers()).thenReturn(Arrays.asList(user1, user2));
        
        List<UserData> users = mockedDataRepository.getUsers();
        
        assertEquals("Albert", users.get(0).getName());
    }
    
    @Test
    public void testGetUsersSurname()
    {
        UserData user1 = new UserData("user1", "pass1", "Albert", "Almighty");
        UserData user2 = new UserData("user2", "pass2", "Brian", "Benevolent");
        
        when(mockedDataRepository.getUsers()).thenReturn(Arrays.asList(user1, user2));
        
        List<UserData> users = mockedDataRepository.getUsers();
        
        assertEquals("Almighty", users.get(0).getSurname());
    }
}
