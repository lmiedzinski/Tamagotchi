/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.dbjllmjk;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.AfterClass;
import org.junit.Assert;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;
import pl.dbjllmjk.Controller.AdminController;
import pl.dbjllmjk.Controller.Controller;
import pl.dbjllmjk.Controller.UserController;
import pl.dbjllmjk.Model.Action;
import pl.dbjllmjk.Model.Activity;
import pl.dbjllmjk.Model.AdminData;
import pl.dbjllmjk.Model.DataRepository;
import pl.dbjllmjk.Model.DataRepositoryInterface;
import pl.dbjllmjk.Model.Pet;
import pl.dbjllmjk.Model.UserData;

/**
 *
 * @author Kubek
 */
@RunWith(MockitoJUnitRunner.class)
public class DataRepositoryTest {

    @Mock
    private static DataRepositoryInterface mockedDataRepository;

    private Controller c;
    private AdminController ac;
    private UserController u;

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
        c = new Controller(0);
        c.setDataRepository(mockedDataRepository);
        ac = new AdminController(c, null);

        UserData user1 = new UserData("user1", "pass1", "Albert", "Almighty");
        UserData user2 = new UserData("user2", "pass2", "Brian", "Benevolent");

        when(mockedDataRepository.getUsers()).thenReturn(Arrays.asList(user1, user2));

        assertEquals(2, ac.getUsers().length);
    }

    @Test
    public void testGetUsersType() {
        c = new Controller(0);
        c.setDataRepository(mockedDataRepository);
        ac = new AdminController(c, null);

        UserData user1 = new UserData("user1", "pass1", "Albert", "Almighty");
        UserData user2 = new UserData("user2", "pass2", "Brian", "Benevolent");

        when(mockedDataRepository.getUsers()).thenReturn(Arrays.asList(user1, user2));

        assertEquals(UserData[].class, ac.getUsers().getClass());
    }

    @Test
    public void testGetUsersLogin() {
        c = new Controller(0);
        c.setDataRepository(mockedDataRepository);
        ac = new AdminController(c, null);

        UserData user1 = new UserData("user1", "pass1", "Albert", "Almighty");
        UserData user2 = new UserData("user2", "pass2", "Brian", "Benevolent");

        when(mockedDataRepository.getUsers()).thenReturn(Arrays.asList(user1, user2));

        UserData[] users = ac.getUsers();

        assertEquals("user1", users[0].getLogin());
    }

    @Test
    public void testGetUsersPassword() {
        c = new Controller(0);
        c.setDataRepository(mockedDataRepository);
        ac = new AdminController(c, null);

        UserData user1 = new UserData("user1", "pass1", "Albert", "Almighty");
        UserData user2 = new UserData("user2", "pass2", "Brian", "Benevolent");

        when(mockedDataRepository.getUsers()).thenReturn(Arrays.asList(user1, user2));

        UserData[] users = ac.getUsers();

        assertEquals("pass1", users[0].getPassword());
    }

    @Test
    public void testGetUsersName() {
        c = new Controller(0);
        c.setDataRepository(mockedDataRepository);
        ac = new AdminController(c, null);

        UserData user1 = new UserData("user1", "pass1", "Albert", "Almighty");
        UserData user2 = new UserData("user2", "pass2", "Brian", "Benevolent");

        when(mockedDataRepository.getUsers()).thenReturn(Arrays.asList(user1, user2));

        UserData[] users = ac.getUsers();

        assertEquals("Albert", users[0].getName());
    }

    @Test
    public void testGetUsersSurname() {
        c = new Controller(0);
        c.setDataRepository(mockedDataRepository);
        ac = new AdminController(c, null);

        UserData user1 = new UserData("user1", "pass1", "Albert", "Almighty");
        UserData user2 = new UserData("user2", "pass2", "Brian", "Benevolent");

        when(mockedDataRepository.getUsers()).thenReturn(Arrays.asList(user1, user2));

        UserData[] users = ac.getUsers();

        assertEquals("Almighty", users[0].getSurname());
    }

    @Test
    public void testGetUsersWithPets() {
        c = new Controller(0);
        c.setDataRepository(mockedDataRepository);
        ac = new AdminController(c, null);

        UserData user1 = new UserData("user1", "pass1", "Albert", "Almighty");

        LocalDateTime daty = LocalDateTime.now();
        Pet pet1 = new Pet("pet1", "type1", 0, 10, daty, 5, 5, 5,
                new ArrayList<>(), daty, daty, daty);
        user1.addPet(pet1);

        when(mockedDataRepository.getUsers()).thenReturn(Arrays.asList(user1));

        UserData[] users = ac.getUsers();

        assertEquals(pet1, users[0].getPets().get(0));
    }

    @Test
    public void testGetAdminsSize() {
        c = new Controller(0);
        c.setDataRepository(mockedDataRepository);
        ac = new AdminController(c, null);

        AdminData admin1 = new AdminData("admin1", "pass1", "Albert", "Almighty");
        AdminData admin2 = new AdminData("admin2", "pass2", "Ben", "Bashful");

        when(mockedDataRepository.getAdmins()).thenReturn(Arrays.asList(admin1, admin2));

        AdminData[] admins = ac.getAdmins();

        assertEquals(2, admins.length);
    }

    @Test
    public void testGetAdminsType() {
        c = new Controller(0);
        c.setDataRepository(mockedDataRepository);
        ac = new AdminController(c, null);

        AdminData admin1 = new AdminData("admin1", "pass1", "Albert", "Almighty");
        AdminData admin2 = new AdminData("admin2", "pass2", "Ben", "Bashful");

        when(mockedDataRepository.getAdmins()).thenReturn(Arrays.asList(admin1, admin2));

        AdminData[] admins = ac.getAdmins();

        assertEquals(AdminData[].class, admins.getClass());
    }

    @Test
    public void testGetAdminsLogin() {
        c = new Controller(0);
        c.setDataRepository(mockedDataRepository);
        ac = new AdminController(c, null);

        AdminData admin1 = new AdminData("admin1", "pass1", "Albert", "Almighty");
        AdminData admin2 = new AdminData("admin2", "pass2", "Ben", "Bashful");

        when(mockedDataRepository.getAdmins()).thenReturn(Arrays.asList(admin1, admin2));

        AdminData[] admins = ac.getAdmins();

        assertEquals("admin1", admins[0].getLogin());
    }

    @Test
    public void testGetAdminsPassword() {
        c = new Controller(0);
        c.setDataRepository(mockedDataRepository);
        ac = new AdminController(c, null);

        AdminData admin1 = new AdminData("admin1", "pass1", "Albert", "Almighty");
        AdminData admin2 = new AdminData("admin2", "pass2", "Ben", "Bashful");

        when(mockedDataRepository.getAdmins()).thenReturn(Arrays.asList(admin1, admin2));

        AdminData[] admins = ac.getAdmins();

        assertEquals("pass1", admins[0].getPassword());
    }

    @Test
    public void testGetAdminsName() {
        c = new Controller(0);
        c.setDataRepository(mockedDataRepository);
        ac = new AdminController(c, null);

        AdminData admin1 = new AdminData("admin1", "pass1", "Albert", "Almighty");
        AdminData admin2 = new AdminData("admin2", "pass2", "Ben", "Bashful");

        when(mockedDataRepository.getAdmins()).thenReturn(Arrays.asList(admin1, admin2));

        AdminData[] admins = ac.getAdmins();

        assertEquals("Albert", admins[0].getName());
    }

    @Test
    public void testGetAdminsSurname() {
        c = new Controller(0);
        c.setDataRepository(mockedDataRepository);
        ac = new AdminController(c, null);

        AdminData admin1 = new AdminData("admin1", "pass1", "Albert", "Almighty");
        AdminData admin2 = new AdminData("admin2", "pass2", "Ben", "Bashful");

        when(mockedDataRepository.getAdmins()).thenReturn(Arrays.asList(admin1, admin2));

        AdminData[] admins = ac.getAdmins();

        assertEquals("Almighty", admins[0].getSurname());
    }

    @Test
    public void testGetAvaliablePetTypesSize() {
        c = new Controller(0);
        c.setDataRepository(mockedDataRepository);
        UserData user1 = new UserData("user1", "pass1", "Albert", "Almighty");
        c.setLoggedAccount(user1);
        u = new UserController(c, 0);

        String type1 = "Snake";
        String type2 = "Dog";
        String type3 = "Cat";
        String type4 = "Bunny";
        String[] types = {type1, type2, type3, type4};

        when(mockedDataRepository.getAvaliablePetTypes()).thenReturn(types);

        String[] avaliableTypes = u.getAvaliablePetTypes();

        assertEquals(4, avaliableTypes.length);
    }

    @Test
    public void testGetAvaliablePetTypesArrays() {
        c = new Controller(0);
        c.setDataRepository(mockedDataRepository);
        UserData user1 = new UserData("user1", "pass1", "Albert", "Almighty");
        c.setLoggedAccount(user1);
        u = new UserController(c, 0);

        String type1 = "Snake";
        String type2 = "Dog";
        String type3 = "Cat";
        String type4 = "Bunny";
        String[] types = {type1, type2, type3, type4};

        when(mockedDataRepository.getAvaliablePetTypes()).thenReturn(types);

        String[] avaliableTypes = u.getAvaliablePetTypes();

        assertArrayEquals(types, avaliableTypes);
    }

    @Test
    public void testGetPetsForUserSize() {
        c = new Controller(0);
        c.setDataRepository(mockedDataRepository);
        UserData user1 = new UserData("user1", "pass1", "Albert", "Almighty");
        c.setLoggedAccount(user1);
        u = new UserController(c, 0);

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
    public void testGetPetTypesForAction() {
        c = new Controller(0);
        c.setDataRepository(mockedDataRepository);
        ac = new AdminController(c, null);

        Activity a = new Activity("testActivity", 5);
        ArrayList<Action> actions = new ArrayList<Action>();
        actions.add(a);

        Map<String, Boolean> expectedMap = new HashMap<>();
        expectedMap.put("type1", Boolean.FALSE);
        expectedMap.put("type2", Boolean.TRUE);
        expectedMap.put("type3", Boolean.FALSE);

        LocalDateTime daty = LocalDateTime.now();
        Pet pet1 = new Pet("pet1", "type1", 0, 10, daty, 5, 5, 5,
                new ArrayList<>(), daty, daty, daty);
        Pet pet2 = new Pet("pet2", "type2", 0, 12, daty, 4, 5, 5,
                actions, daty, daty, daty);
        Pet pet3 = new Pet("pet3", "type2", 0, 9, daty, 6, 5, 5,
                actions, daty, daty, daty);
        Pet pet4 = new Pet("pet4", "type3", 0, 20, daty, 4, 6, 5,
                new ArrayList<>(), daty, daty, daty);
        String[] s = {"type1", "type2", "type3"};
        when(mockedDataRepository.getAvaliablePetTypes()).thenReturn(s);
        when(mockedDataRepository.getPetTypesForAction(actions.get(0))).thenReturn(Arrays.asList(pet2.getType(), pet3.getType()));
        Map<String, Boolean> resultMap = ac.getActionWithTypeConnections(a);

        assertEquals(expectedMap, resultMap);

    }

    @Test
    public void testGetAvaliablePetTypes() {
        c = new Controller(0);
        c.setDataRepository(mockedDataRepository);
        ac = new AdminController(c, null);

        String[] s = {"type1", "type2", "type3"};

        when(mockedDataRepository.getAvaliablePetTypes()).thenReturn(s);

        Assert.assertArrayEquals(s, ac.getAvaliablePetTypes());
    }

}
