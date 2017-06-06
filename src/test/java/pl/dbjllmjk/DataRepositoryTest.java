/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.dbjllmjk;

import com.healthmarketscience.jackcess.Database;
import com.healthmarketscience.jackcess.Table;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;
import pl.dbjllmjk.Controller.Controller;
import pl.dbjllmjk.Controller.LoginController;
import pl.dbjllmjk.Model.Action;
import pl.dbjllmjk.Model.Activity;
import pl.dbjllmjk.Model.AdminData;
import pl.dbjllmjk.Model.DataRepository;
import pl.dbjllmjk.Model.DataRepositoryInterface;
import pl.dbjllmjk.Model.Food;
import pl.dbjllmjk.Model.Operation;
import pl.dbjllmjk.Model.Pet;
import pl.dbjllmjk.Model.UserData;
import sun.reflect.annotation.AnnotationParser;

/**
 *
 * @author Kubek
 */
@RunWith(MockitoJUnitRunner.class)
public class DataRepositoryTest {

    @Mock
    private static DataRepositoryInterface mockedDataRepository;

    
    
    @BeforeClass
    public static void setUp() {
        mockedDataRepository = new DataRepository();
    }

    @AfterClass
    public static void tearDown() {
        mockedDataRepository = null;
    }
    
    @Test
    public void testGetUsersSize() {
        UserData user1 = new UserData("user1", "pass1", "Albert", "Almighty");
        UserData user2 = new UserData("user2", "pass2", "Brian", "Benevolent");

        when(mockedDataRepository.getUsers()).thenReturn(Arrays.asList(user1, user2));

        List<UserData> users = mockedDataRepository.getUsers();

        assertEquals(2, users.size());
    }
    
    @Test
    public void testGetUsersLogin() {
        UserData user1 = new UserData("user1", "pass1", "Albert", "Almighty");
        UserData user2 = new UserData("user2", "pass2", "Brian", "Benevolent");

        when(mockedDataRepository.getUsers()).thenReturn(Arrays.asList(user1, user2));

        List<UserData> users = mockedDataRepository.getUsers();

        assertEquals("user1", users.get(0).getLogin());
    }

    @Test
    public void testGetUsersPassword() {
        UserData user1 = new UserData("user1", "pass1", "Albert", "Almighty");
        UserData user2 = new UserData("user2", "pass2", "Brian", "Benevolent");

        when(mockedDataRepository.getUsers()).thenReturn(Arrays.asList(user1, user2));

        List<UserData> users = mockedDataRepository.getUsers();

        assertEquals("pass1", users.get(0).getPassword());
    }

    @Test
    public void testGetUsersName() {
        UserData user1 = new UserData("user1", "pass1", "Albert", "Almighty");
        UserData user2 = new UserData("user2", "pass2", "Brian", "Benevolent");

        when(mockedDataRepository.getUsers()).thenReturn(Arrays.asList(user1, user2));

        List<UserData> users = mockedDataRepository.getUsers();

        assertEquals("Albert", users.get(0).getName());
    }

    @Test
    public void testGetUsersSurname() {
        UserData user1 = new UserData("user1", "pass1", "Albert", "Almighty");
        UserData user2 = new UserData("user2", "pass2", "Brian", "Benevolent");

        when(mockedDataRepository.getUsers()).thenReturn(Arrays.asList(user1, user2));

        List<UserData> users = mockedDataRepository.getUsers();

        assertEquals("Almighty", users.get(0).getSurname());
    }

    @Test
    public void testGetUsersWithPets() {
        UserData user1 = new UserData("user1", "pass1", "Albert", "Almighty");

        LocalDateTime daty = LocalDateTime.now();
        Pet pet1 = new Pet("pet1", "type1", 0, 10, daty, 5, 5, 5,
                new ArrayList<>(), daty, daty, daty);
        user1.addPet(pet1);

        when(mockedDataRepository.getUsers()).thenReturn(Arrays.asList(user1));

        List<UserData> users = mockedDataRepository.getUsers();

        assertEquals(pet1, users.get(0).getPets().get(0));
    }

    @Test
    public void testGetAdminsSize() {
        AdminData admin1 = new AdminData("admin1", "pass1", "Albert", "Almighty");
        AdminData admin2 = new AdminData("admin2", "pass2", "Ben", "Bashful");

        when(mockedDataRepository.getAdmins()).thenReturn(Arrays.asList(admin1, admin2));

        List<AdminData> admins = mockedDataRepository.getAdmins();

        assertEquals(2, admins.size());
    }

    @Test
    public void testGetAdminsLogin() {
        AdminData admin1 = new AdminData("admin1", "pass1", "Albert", "Almighty");
        AdminData admin2 = new AdminData("admin2", "pass2", "Ben", "Bashful");

        when(mockedDataRepository.getAdmins()).thenReturn(Arrays.asList(admin1, admin2));

        List<AdminData> admins = mockedDataRepository.getAdmins();

        assertEquals("admin1", admins.get(0).getLogin());
    }

    @Test
    public void testGetAdminsPassword() {
        AdminData admin1 = new AdminData("admin1", "pass1", "Albert", "Almighty");
        AdminData admin2 = new AdminData("admin2", "pass2", "Ben", "Bashful");

        when(mockedDataRepository.getAdmins()).thenReturn(Arrays.asList(admin1, admin2));

        List<AdminData> admins = mockedDataRepository.getAdmins();

        assertEquals("pass1", admins.get(0).getPassword());
    }

    @Test
    public void testGetAdminsName() {
        AdminData admin1 = new AdminData("admin1", "pass1", "Albert", "Almighty");
        AdminData admin2 = new AdminData("admin2", "pass2", "Ben", "Bashful");

        when(mockedDataRepository.getAdmins()).thenReturn(Arrays.asList(admin1, admin2));

        List<AdminData> admins = mockedDataRepository.getAdmins();

        assertEquals("Albert", admins.get(0).getName());
    }

    @Test
    public void testGetAdminsSurname() {
        AdminData admin1 = new AdminData("admin1", "pass1", "Albert", "Almighty");
        AdminData admin2 = new AdminData("admin2", "pass2", "Ben", "Bashful");

        when(mockedDataRepository.getAdmins()).thenReturn(Arrays.asList(admin1, admin2));

        List<AdminData> admins = mockedDataRepository.getAdmins();

        assertEquals("Almighty", admins.get(0).getSurname());
    }

    @Test
    public void testGetAvaliablePetTypesSize() {
        String type1 = "Snake";
        String type2 = "Dog";
        String type3 = "Cat";
        String type4 = "Bunny";
        String[] types = {type1, type2, type3, type4};
        
        when(mockedDataRepository.getAvaliablePetTypes()).thenReturn(types);
        
        String[] avaliableTypes = mockedDataRepository.getAvaliablePetTypes();
        
        assertEquals(4, avaliableTypes.length);
    }
    
    @Test
    public void testGetPetsForUserSize(){
        UserData user1 = new UserData("user1", "pass1", "Albert", "Almighty");
        
        LocalDateTime daty = LocalDateTime.now();
        Pet pet1 = new Pet("pet1", "type1", 0, 10, daty, 5, 5, 5,
                new ArrayList<>(), daty, daty, daty);
        Pet pet2 = new Pet("pet2", "type1", 0, 12, daty, 4, 5, 5,
                new ArrayList<>(), daty, daty, daty);
        user1.addPet(pet1);
        user1.addPet(pet2);
        
        when(mockedDataRepository.getPetsForUser(user1)).thenReturn(Arrays.asList(pet1, pet2));
        
        List<Pet> pets = mockedDataRepository.getPetsForUser(user1);
        
        assertEquals(2, pets.size());
    }
    
    @Test
    public void testGetPetTypesForActionCorrect(){
        Activity a = new Activity("testActivity", 5);
        ArrayList<Action> actions = new ArrayList<Action>();
        actions.add(a);

        LocalDateTime daty = LocalDateTime.now();
        Pet pet1 = new Pet("pet1", "type1", 0, 10, daty, 5, 5, 5,
                new ArrayList<>(), daty, daty, daty);
        Pet pet2 = new Pet("pet2", "type2", 0, 12, daty, 4, 5, 5,
                actions, daty, daty, daty);
        Pet pet3 = new Pet("pet3", "type2", 0, 9, daty, 6, 5, 5,
                actions, daty, daty, daty);
        Pet pet4 = new Pet("pet4", "type3", 0, 20, daty, 4, 6, 5,
                new ArrayList<>(), daty, daty, daty);
        
        when(mockedDataRepository.getPetTypesForAction(actions.get(0))).thenReturn(Arrays.asList(pet2.getType()));
        
        ArrayList<String> resultType = new ArrayList<>();
        resultType.add("type2");
        assertEquals(resultType, mockedDataRepository.getPetTypesForAction(a));
        
    }
    
    @Test
    public void testGetAllActionsActivities()
    {
        Activity a1 = new Activity("aktywnosc1", 1);
        Activity a2 = new Activity("aktywnosc2", 1);
        Activity a3 = new Activity("aktywnosc3", 1);
        
        Activity testActivity = new Activity();
        
        when(mockedDataRepository.getAllActions(testActivity)).thenReturn(Arrays.asList(a1, a2, a3));
        
        List<Activity> expectedList = new ArrayList<Activity>();
        expectedList.add(a1);
        expectedList.add(a2);
        expectedList.add(a3);
        
        List<Activity> actualList = mockedDataRepository.getAllActions(testActivity);
        
        assertEquals(expectedList, actualList);
    }
    
    @Test
    public void testGetAllActionsFood()
    {
        Food a1 = new Food("jedzenie1", 1);
        Food a2 = new Food("jedzenie2", 1);
        Food a3 = new Food("jedzenie3", 1);
        
        Food testFood = new Food();
        
        when(mockedDataRepository.getAllActions(testFood)).thenReturn(Arrays.asList(a1, a2, a3));
        
        List<Food> expectedList = new ArrayList<Food>();
        expectedList.add(a1);
        expectedList.add(a2);
        expectedList.add(a3);
        
        List<Food> actualList = mockedDataRepository.getAllActions(testFood);
        
        assertEquals(expectedList, actualList);
    }
    
    @Test
    public void testGetAllActionsOperations()
    {
        Operation a1 = new Operation("jedzenie1", 1);
        Operation a2 = new Operation("jedzenie2", 1);
        Operation a3 = new Operation("jedzenie3", 1);
        
        Operation testOp = new Operation();
        
        when(mockedDataRepository.getAllActions(testOp)).thenReturn(Arrays.asList(a1, a2, a3));
        
        List<Operation> expectedList = new ArrayList<Operation>();
        expectedList.add(a1);
        expectedList.add(a2);
        expectedList.add(a3);
        
        List<Operation> actualList = mockedDataRepository.getAllActions(testOp);
        
        assertEquals(expectedList, actualList);
    }
    
    @Test(expected = IOException.class)
    public void testGetTypesForActionIncrrect(){
        Activity a = new Activity("testActivity", 5);
        ArrayList<Action> actions = new ArrayList<Action>();
        actions.add(a);
        
        Activity nullAction = null;

        LocalDateTime daty = LocalDateTime.now();
        Pet pet1 = new Pet("pet1", "type1", 0, 10, daty, 5, 5, 5,
                new ArrayList<>(), daty, daty, daty);
        Pet pet2 = new Pet("pet2", "type2", 0, 12, daty, 4, 5, 5,
                actions, daty, daty, daty);
        Pet pet3 = new Pet("pet3", "type2", 0, 9, daty, 6, 5, 5,
                actions, daty, daty, daty);
        Pet pet4 = new Pet("pet4", "type3", 0, 20, daty, 4, 6, 5,
                new ArrayList<>(), daty, daty, daty);
        
        when(mockedDataRepository.getPetTypesForAction(nullAction)).thenThrow(new IOException());
        //doThrow(new IOException()).when(mockedDataRepository).getPetTypesForAction(nullAction);
        
        mockedDataRepository.getPetTypesForAction(nullAction);
        
        
        
    }
}
