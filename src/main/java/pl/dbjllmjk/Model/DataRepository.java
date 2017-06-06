package pl.dbjllmjk.Model;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.healthmarketscience.jackcess.Column;
import com.healthmarketscience.jackcess.Cursor;
import com.healthmarketscience.jackcess.CursorBuilder;
import com.healthmarketscience.jackcess.Database;
import com.healthmarketscience.jackcess.DatabaseBuilder;
import com.healthmarketscience.jackcess.IndexCursor;
import com.healthmarketscience.jackcess.Row;
import com.healthmarketscience.jackcess.Table;

/**
 * Class which is repsonsible for connection with MS_ACCESS DATABASE. Basic
 * operations: -Read -Write -Modify -Delete
 */
public class DataRepository implements DataRepositoryInterface {

    /**
     * Property containing database file.
     */
    private Database data;

    /**
     * List of Users ({@link UserData).
     */
    @SuppressWarnings("unused")
    private List<UserData> users;

    /**
     * List of Admins ({@link AdminData}).
     */
    @SuppressWarnings("unused")
    private List<AdminData> admins;

    /**
     * Default constructor initializes database field by loading data.mdb file
     * in current directory. Additionally it loads all users and admins into
     * memory.
     */
    public DataRepository() {
        try {
            data = DatabaseBuilder.open(new File("data.mdb"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        users = new ArrayList<>();
        admins = new ArrayList<>();
        admins = this.getAdmins();
        users = this.getUsers();
    }

    /**
     * @param row containing {@link Pet} info in database.
     * @return List of {@link Action}s for {@link Pet} given in {@link Row}.
     */
    private List<Action> getActionsForPet(Row row) {
        List<Action> actions = new ArrayList<>();
        try {
            //Activities
            IndexCursor ac = CursorBuilder.createCursor(data.getTable("PetTypeActivities").getIndex("PetTypeID"));
            for (Row activityRow : ac.newEntryIterable(row.get("PetTypeID"))) {
                IndexCursor ca = CursorBuilder.createPrimaryKeyCursor(data.getTable("Activities"));
                for (Row aRow : ca.newEntryIterable(activityRow.get("ActivityID"))) {
                    actions.add(new Activity((String) aRow.get("activityName"), (Integer) aRow.get("activityValue")));
                }
            }
            //Foods
            IndexCursor fc = CursorBuilder.createCursor(data.getTable("PetTypeFoods").getIndex("PetTypeID"));
            for (Row foodRow : fc.newEntryIterable(row.get("PetTypeID"))) {
                IndexCursor cd = CursorBuilder.createPrimaryKeyCursor(data.getTable("Foods"));
                for (Row fRow : cd.newEntryIterable(foodRow.get("FoodID"))) {
                    actions.add(new Food((String) fRow.get("foodName"), (Integer) fRow.get("foodValue")));
                }
            }
            //Operations
            IndexCursor oc = CursorBuilder.createCursor(data.getTable("PetTypeOperations").getIndex("PetTypeID"));
            for (Row operationRow : oc.newEntryIterable(row.get("PetTypeID"))) {
                IndexCursor co = CursorBuilder.createPrimaryKeyCursor(data.getTable("Operations"));
                for (Row oRow : co.newEntryIterable(operationRow.get("OperationID"))) {
                    actions.add(new Operation((String) oRow.get("operationName"), (Integer) oRow.get("operationValue")));
                }
            }
        } catch (IOException e) {
            e.getStackTrace();
        }
        return actions;
    }

    /**
     * @return All user accounts ({@link UserData}) data from database file.
     */
    @SuppressWarnings("deprecation")
    public List<UserData> getUsers() {
        List<UserData> userData = new ArrayList<>();
        try {
            Table userTable = data.getTable("Users");
            for (Row r : userTable) {
                UserData ud = new UserData((String) r.get("userLogin"), (String) r.get("userPassword"), (String) r.get("userName"), (String) r.get("userSurname"));
                List<Pet> pets = new ArrayList<>();
                List<Integer> temp = new ArrayList<>();
                Table userPetsTable = data.getTable("UserPets");
                Table petTypesTable = data.getTable("PetTypes");
                Table petsTable = data.getTable("Pets");
                IndexCursor c = CursorBuilder.createCursor(userPetsTable.getIndex("UserID"));
                for (Row entry : c.newEntryIterable(r.get("UserID"))) {
                    temp.add((Integer) entry.get("PetID")); //przez tablic� UserPets
                }
                for (Integer i : temp) {
                    Row row = CursorBuilder.findRowByPrimaryKey(petsTable, i);
                    Row row2 = CursorBuilder.findRowByPrimaryKey(petTypesTable, (Integer) row.get("PetTypeID"));
                    LocalDateTime dataKarmienia = LocalDateTime.of(row.getDate("lastFeedingDate").getYear(), row.getDate("lastFeedingDate").getMonth() + 1, row.getDate("lastFeedingDate").getDate(), row.getInt("lastFeedingHour"), row.getInt("lastFeedingMinute"));
                    LocalDateTime dataAktywnosci = LocalDateTime.of(row.getDate("lastFeedingDate").getYear(), row.getDate("lastFeedingDate").getMonth() + 1, row.getDate("lastFeedingDate").getDate(), row.getInt("lastFeedingHour"), row.getInt("lastFeedingMinute"));
                    LocalDateTime dataOperacji = LocalDateTime.of(row.getDate("lastFeedingDate").getYear(), row.getDate("lastFeedingDate").getMonth() + 1, row.getDate("lastFeedingDate").getDate(), row.getInt("lastFeedingHour"), row.getInt("lastFeedingMinute"));
                    LocalDateTime dataUrodzin = LocalDateTime.of(row.getDate("birthDate").getYear(), row.getDate("birthDate").getMonth() + 1, row.getDate("birthDate").getDate(), 0, 0);
                    pets.add(new Pet((String) row.get("petName"), (String) row2.get("petTypeName"), (Integer) row.get("petAge"), (Integer) row.get("petWeight"), dataKarmienia, (Integer) row.get("happiness"), (Integer) row.get("hunger"), (Integer) row.get("health"), this.getActionsForPet(row), dataAktywnosci, dataOperacji, dataUrodzin));
                }
                pets.forEach((pet) -> {
                    ud.addPet(pet);
                });
                userData.add(ud);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userData;
    }

    /**
     * @return All admin accounts ({@link AdminData}) data from database file.
     */
    public List<AdminData> getAdmins() {
        List<AdminData> adminData = new ArrayList<>();
        try {
            Table adminTable = data.getTable("Admins");
            for (Row r : adminTable) {
                adminData.add(new AdminData((String) r.get("adminLogin"), (String) r.get("adminPassword"), (String) r.get("adminName"), (String) r.get("adminSurname")));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return adminData;
    }

    /**
     * @return All available {@link Pet} types/species from database file.
     */
    public String[] getAvaliablePetTypes() {
        List<String> tab = new ArrayList<>();
        String[] s = null;
        try {
            Table petTypes = data.getTable("PetTypes");
            s = new String[petTypes.getRowCount()];
            for (Row r : petTypes) {
                tab.add((String) r.get("petTypeName"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tab.toArray(s);
    }

    /**
     * Delete {@link Pet} and its relations in database file.
     *
     * @param p instance of {@link Pet} class.
     */
    public void removePet(Pet p) {
        try {
            Table petsTable = data.getTable("Pets");
            Table userPetsTable = data.getTable("UserPets");
            Row petName = CursorBuilder.findRow(petsTable, Collections.singletonMap("petName", p.getName()));
            Row petTypeId = CursorBuilder.findRow(userPetsTable, Collections.singletonMap("PetID", petName.get("PetID")));
            userPetsTable.deleteRow(petTypeId);
            petsTable.deleteRow(CursorBuilder.findRowByPrimaryKey(petsTable, petName.get("PetID")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Add {@link Pet} and its relations to database file.
     *
     * @param p instance of {@link Pet} class.
     * @param ud instance of {@link UserData} class to specify user for
     * {@link Pet}.
     */
    @SuppressWarnings("deprecation")
    public void addPet(Pet p, UserData ud) {
        try {
            Table usersTable = data.getTable("Users");
            Table userPetsTable = data.getTable("UserPets");
            Table petsTable = data.getTable("Pets");
            Table petTypesTable = data.getTable("PetTypes");
            Date dataKarmienia = new Date(p.getLastFeedingDate().getYear(), p.getLastFeedingDate().getMonth().getValue() - 1, p.getLastFeedingDate().getDayOfMonth());
            Date dataAktywnosci = new Date(p.getLastFeedingDate().getYear(), p.getLastFeedingDate().getMonth().getValue() - 1, p.getLastFeedingDate().getDayOfMonth());
            Date dataOperacji = new Date(p.getLastFeedingDate().getYear(), p.getLastFeedingDate().getMonth().getValue() - 1, p.getLastFeedingDate().getDayOfMonth());
            Date dataUrodzin = new Date(p.getBirthDate().getYear(), p.getBirthDate().getMonth().getValue() - 1, p.getBirthDate().getDayOfMonth());
            Row petTypeRow = CursorBuilder.findRow(petTypesTable, Collections.singletonMap("petTypeName", p.getType()));
            petsTable.addRow(Column.AUTO_NUMBER, (String) p.getName(), (Integer) petTypeRow.get("PetTypeID"), (int) p.getAge(), (int) p.getWeight(), dataKarmienia, (int) p.getHappiness(), (int) p.getHunger(), (int) p.getHealth(), (int) p.getLastFeedingDate().getHour(), (int) p.getLastFeedingDate().getMinute(), p.getLastActivityDate().getHour(), p.getLastActivityDate().getMinute(), p.getLastOperationDate().getHour(), p.getLastOperationDate().getMinute(), dataAktywnosci, dataOperacji, dataUrodzin);
            Row newPetRow = CursorBuilder.findRow(petsTable, Collections.singletonMap("petName", p.getName()));
            Row userIdRow = CursorBuilder.findRow(usersTable, Collections.singletonMap("userLogin", ud.getLogin()));
            userPetsTable.addRow(Column.AUTO_NUMBER, userIdRow.get("UserID"), newPetRow.get("PetID"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param ud user account.
     * @return All {@link Pet}s for specified user ({@link UserData}) from
     * database file.
     */
    @SuppressWarnings("deprecation")
    public List<Pet> getPetsForUser(UserData ud) {
        List<Pet> petList = new ArrayList<>();
        try {
            Table usersTable = data.getTable("Users");
            Table userPetsTable = data.getTable("UserPets");
            Table petsTable = data.getTable("Pets");
            Table petTypesTable = data.getTable("PetTypes");
            List<Integer> temp = new ArrayList<>();
            Row user = CursorBuilder.findRow(usersTable, Collections.singletonMap("userLogin", ud.getLogin()));
            IndexCursor user2pets = CursorBuilder.createCursor(userPetsTable.getIndex("UserID"));
            for (Row u : user2pets.newEntryIterable(user.get("UserID"))) {
                temp.add((Integer) u.get("PetID"));
            }
            for (Integer i : temp) {
                Row row = CursorBuilder.findRowByPrimaryKey(petsTable, i);
                Row row2 = CursorBuilder.findRowByPrimaryKey(petTypesTable, (Integer) row.get("PetTypeID"));
                LocalDateTime dataKarmienia = LocalDateTime.of(row.getDate("lastFeedingDate").getYear(), row.getDate("lastFeedingDate").getMonth() + 1, row.getDate("lastFeedingDate").getDate(), row.getInt("lastFeedingHour"), row.getInt("lastFeedingMinute"));
                LocalDateTime dataAktywnosci = LocalDateTime.of(row.getDate("lastActivityDate").getYear(), row.getDate("lastActivityDate").getMonth() + 1, row.getDate("lastActivityDate").getDate(), row.getInt("lastActivityHour"), row.getInt("lastActivityMinute"));
                LocalDateTime dataOperacji = LocalDateTime.of(row.getDate("lastOperationDate").getYear(), row.getDate("lastOperationDate").getMonth() + 1, row.getDate("lastOperationDate").getDate(), row.getInt("lastOperationHour"), row.getInt("lastOperationMinute"));
                LocalDateTime dataUrodzin = LocalDateTime.of(row.getDate("birthDate").getYear(), row.getDate("birthDate").getMonth() + 1, row.getDate("birthDate").getDate(), 0, 0);
                petList.add(new Pet((String) row.get("petName"), (String) row2.get("petTypeName"), (Integer) row.get("petAge"), (Integer) row.get("petWeight"), dataKarmienia, (Integer) row.get("happiness"), (Integer) row.get("hunger"), (Integer) row.get("health"), this.getActionsForPet(row), dataAktywnosci, dataOperacji, dataUrodzin));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return petList;
    }

    /**
     * Add user or admin account to database file.
     *
     * @param <T> type
     * @param t instance of {@link AccountData} providing data of an account.
     */
    public <T> void addData(T t) {
        try {
            Table dataTable = null;
            if (t instanceof AdminData) {
                dataTable = data.getTable("Admins");
            } else if (t instanceof UserData) {
                dataTable = data.getTable("Users");
            }
            if (dataTable != null) {
                dataTable.addRow(Column.AUTO_NUMBER, (String) ((AccountData) t).getLogin(), (String) ((AccountData) t).getPassword(), (String) ((AccountData) t).getName(), (String) ((AccountData) t).getSurname());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Remove user account and all its {@link Pet}s from database file.
     *
     * @param ud instance of user account ({@link UserData}).
     */
    public void removeUser(UserData ud) {
        try {
            Table usersTable = data.getTable("Users");
            Table userPetsTable = data.getTable("UserPets");
            Table petsTable = data.getTable("Pets");
            Row userRow = CursorBuilder.findRow(usersTable, Collections.singletonMap("userLogin", ud.getLogin()));
            IndexCursor user2Pets = CursorBuilder.createCursor(userPetsTable.getIndex("UserID"));
            List<Integer> temp = new ArrayList<>();
            for (Row user2pet : user2Pets.newEntryIterable(userRow.get("UserID"))) {
                temp.add((Integer) user2pet.get("PetID"));
            }
            for (Integer i : temp) {
                Row petRow = CursorBuilder.findRowByPrimaryKey(petsTable, i);
                petsTable.deleteRow(petRow);
            }
            for (Row user2pet : user2Pets.newEntryIterable(userRow.get("UserID"))) {
                userPetsTable.deleteRow(user2pet);
            }
            usersTable.deleteRow(userRow);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Remove admin account from database file.
     *
     * @param ad instance of admin account ({@link AdminData}).
     */
    public void removeAdmin(AdminData ad) {
        try {
            Table adminsTable = data.getTable("Admins");
            Row adminRow = CursorBuilder.findRow(adminsTable, Collections.singletonMap("adminLogin", ad.getLogin()));
            adminsTable.deleteRow(adminRow);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Add {@link Pet} type/species to database file.
     *
     * @param petTypeName name of species
     */
    public void addPetType(String petTypeName) {
        try {
            Table petsType = data.getTable("PetTypes");
            petsType.addRow(Column.AUTO_NUMBER, petTypeName);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * Delete {@link Pet} type/species and all relations from database file.
     *
     * @param petTypeName name of species
     */
    public void removePetType(String petTypeName) {
        try {
            Table petsType = data.getTable("PetTypes");
            Table petTypeOperationsTable = data.getTable("PetTypeOperations");
            Table petTypeFoodsTable = data.getTable("PetTypeFoods");
            Table petTypeActivitiesTable = data.getTable("PetTypeActivities");
            Table petsTable = data.getTable("Pets");
            Table user2petTable = data.getTable("UserPets");
            Row petTypeRow = CursorBuilder.findRow(petsType, Collections.singletonMap("petTypeName", petTypeName));
            IndexCursor pet2operationsCursor = CursorBuilder.createCursor(petTypeOperationsTable.getIndex("PetTypeID"));
            IndexCursor pet2foodsCursor = CursorBuilder.createCursor(petTypeFoodsTable.getIndex("PetTypeID"));
            IndexCursor pet2activitiesCursor = CursorBuilder.createCursor(petTypeActivitiesTable.getIndex("PetTypeID"));
            Column petTypeColumn = petsTable.getColumn("PetTypeID");
            Cursor petsCursor = CursorBuilder.createCursor(petsTable);
            petsCursor.beforeFirst();
            while (petsCursor.moveToNextRow()) {
                if (petsCursor.currentRowMatches(petTypeColumn, petTypeRow.get("PetTypeID"))) {
                    Cursor userPetsCursor = CursorBuilder.createCursor(user2petTable);
                    userPetsCursor.beforeFirst();
                    while (userPetsCursor.moveToNextRow()) {
                        if (userPetsCursor.currentRowMatches(user2petTable.getColumn("PetID"), petsCursor.getCurrentRow().get("PetID"))) {
                            user2petTable.deleteRow(userPetsCursor.getCurrentRow());
                        }
                    }
                    petsTable.deleteRow(petsCursor.getCurrentRow());
                }
            }
            for (Row r : pet2operationsCursor.newEntryIterable(petTypeRow.get("PetTypeID"))) {
                petTypeOperationsTable.deleteRow(r);
            }
            for (Row r : pet2foodsCursor.newEntryIterable(petTypeRow.get("PetTypeID"))) {
                petTypeFoodsTable.deleteRow(r);
            }
            for (Row r : pet2activitiesCursor.newEntryIterable(petTypeRow.get("PetTypeID"))) {
                petTypeActivitiesTable.deleteRow(r);
            }
            petsType.deleteRow(petTypeRow);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Add {@link Action} type to database file.
     *
     * @param <T> type
     * @param t instance of {@link Action} class providing reasonable data.
     */
    public <T> void addAction(T t) {
        try {
            Table actionTable = null;
            if (t instanceof Food) {
                actionTable = data.getTable("Foods");
            } else if (t instanceof Operation) {
                actionTable = data.getTable("Operations");
            } else if (t instanceof Activity) {
                actionTable = data.getTable("Activities");
            }
            if (actionTable != null) {
                actionTable.addRow(Column.AUTO_NUMBER, ((Action) t).getName(), ((Action) t).getValue());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Delete {@link Action} type and its relations from database file.
     *
     * @param <T> type
     * @param t instance of {@link Action} class.
     */
    public <T> void removeAction(T t) {
        try {
            Table actionsTable = null;
            Table petTypeActionsTable = null;
            Row actionRow = null;
            IndexCursor petTypeActionCursor = null;
            if (t instanceof Activity) {
                actionsTable = data.getTable("Activities");
                petTypeActionsTable = data.getTable("PetTypeActivities");
                actionRow = CursorBuilder.findRow(actionsTable, Collections.singletonMap("activityName", ((Action) t).getName()));
                petTypeActionCursor = CursorBuilder.createCursor(petTypeActionsTable.getIndex("ActivityID"));
                for (Row row : petTypeActionCursor.newEntryIterable(actionRow.get("ActivityID"))) {
                    petTypeActionsTable.deleteRow(row);
                }
            } else if (t instanceof Food) {
                actionsTable = data.getTable("Foods");
                petTypeActionsTable = data.getTable("PetTypeFoods");
                actionRow = CursorBuilder.findRow(actionsTable, Collections.singletonMap("foodName", ((Action) t).getName()));
                petTypeActionCursor = CursorBuilder.createCursor(petTypeActionsTable.getIndex("FoodID"));
                for (Row row : petTypeActionCursor.newEntryIterable(actionRow.get("FoodID"))) {
                    petTypeActionsTable.deleteRow(row);
                }
            } else if (t instanceof Operation) {
                actionsTable = data.getTable("Operations");
                petTypeActionsTable = data.getTable("PetTypeOperations");
                actionRow = CursorBuilder.findRow(actionsTable, Collections.singletonMap("operationName", ((Action) t).getName()));
                petTypeActionCursor = CursorBuilder.createCursor(petTypeActionsTable.getIndex("OperationID"));
                for (Row row : petTypeActionCursor.newEntryIterable(actionRow.get("OperationID"))) {
                    petTypeActionsTable.deleteRow(row);
                }
            }
            if (actionsTable != null && actionRow != null) {
                actionsTable.deleteRow(actionRow);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Connect {@link Action} with {@link Pet} in database file.
     *
     * @param <T> type
     * @param t {@link Action} object.
     * @param petTypeName name of {@link Pet} type.
     */
    public <T> void addActionToPetType(T t, String petTypeName) {
        try {
            Table petTypeActionsTable = null;
            Table petTypesTable = data.getTable("PetTypes");
            Table actionsTable = null;
            Row actionRow = null;
            if (t instanceof Activity) {
                petTypeActionsTable = data.getTable("PetTypeActivities");
                actionsTable = data.getTable("Activities");
                actionRow = CursorBuilder.findRow(actionsTable, Collections.singletonMap("activityName", ((Action) t).getName()));
            } else if (t instanceof Food) {
                petTypeActionsTable = data.getTable("PetTypeFoods");
                actionsTable = data.getTable("Foods");
                actionRow = CursorBuilder.findRow(actionsTable, Collections.singletonMap("foodName", ((Action) t).getName()));
            } else if (t instanceof Operation) {
                petTypeActionsTable = data.getTable("PetTypeOperations");
                actionsTable = data.getTable("Operations");
                actionRow = CursorBuilder.findRow(actionsTable, Collections.singletonMap("operationName", ((Action) t).getName()));
            }
            Row petTypeRow = CursorBuilder.findRow(petTypesTable, Collections.singletonMap("petTypeName", petTypeName));
            if (petTypeActionsTable != null && actionsTable != null && actionRow != null) {
                if (t instanceof Activity) {
                    petTypeActionsTable.addRow(Column.AUTO_NUMBER, petTypeRow.get("PetTypeID"), actionRow.get("ActivityID"));
                } else if (t instanceof Food) {
                    petTypeActionsTable.addRow(Column.AUTO_NUMBER, petTypeRow.get("PetTypeID"), actionRow.get("FoodID"));
                } else if (t instanceof Operation) {
                    petTypeActionsTable.addRow(Column.AUTO_NUMBER, petTypeRow.get("PetTypeID"), actionRow.get("OperationID"));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Disconnect {@link Action} from {@link Pet} in database file.
     *
     * @param <T> type
     * @param t {@link Action} object.
     * @param petTypeName name of {@link Pet} type.
     */
    public <T> void removeActionFromPetType(T t, String petTypeName) {
        try {
            Table petTypeActionsTable = null;
            Table actionsTable = null;
            Table petTypesTable = data.getTable("PetTypes");
            Row actionRow = null;
            if (t instanceof Activity) {
                petTypeActionsTable = data.getTable("PetTypeActivities");
                actionsTable = data.getTable("Activities");
                actionRow = CursorBuilder.findRow(actionsTable, Collections.singletonMap("activityName", ((Action) t).getName()));
            } else if (t instanceof Food) {
                petTypeActionsTable = data.getTable("PetTypeFoods");
                actionsTable = data.getTable("Foods");
                actionRow = CursorBuilder.findRow(actionsTable, Collections.singletonMap("foodName", ((Action) t).getName()));
            } else if (t instanceof Operation) {
                petTypeActionsTable = data.getTable("PetTypeOperations");
                actionsTable = data.getTable("Operations");
                actionRow = CursorBuilder.findRow(actionsTable, Collections.singletonMap("operationName", ((Action) t).getName()));
            }
            Row petTypeRow = CursorBuilder.findRow(petTypesTable, Collections.singletonMap("petTypeName", petTypeName));
            IndexCursor petTypeCursor = CursorBuilder.createCursor(petTypeActionsTable.getIndex("PetTypeID"));
            if (petTypeActionsTable != null && actionsTable != null && actionRow != null) {
                for (Row row : petTypeCursor.newEntryIterable(petTypeRow.get("PetTypeID"))) {
                    if (t instanceof Activity) {
                        if (actionRow.get("ActivityID").equals(row.get("ActivityID"))) {
                            petTypeActionsTable.deleteRow(row);
                        }
                    } else if (t instanceof Food) {
                        if (actionRow.get("FoodID").equals(row.get("FoodID"))) {
                            petTypeActionsTable.deleteRow(row);
                        }
                    } else if (t instanceof Operation) {
                        if (actionRow.get("OperationID").equals(row.get("OperationID"))) {
                            petTypeActionsTable.deleteRow(row);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Update specified {@link Pet}s in database file.
     *
     * @param pets list with {@link Pet}s to update.
     */
    @SuppressWarnings({"deprecation"}) //Very important!
    public void updatePets(List<Pet> pets) {
        try {
            Table petsTable = data.getTable("Pets");
            for (Pet p : pets) {
                Row petRow = CursorBuilder.findRow(petsTable, Collections.singletonMap("petName", p.getName()));
                petRow.put("petAge", p.getAge());
                petRow.put("petWeight", p.getWeight());
                Date dataKarmienia = new Date(p.getLastFeedingDate().getYear(), p.getLastFeedingDate().getMonth().getValue() - 1, p.getLastFeedingDate().getDayOfMonth());
                petRow.put("lastFeedingDate", dataKarmienia);
                petRow.put("happiness", p.getHappiness());
                petRow.put("hunger", p.getHunger());
                petRow.put("health", p.getHealth());
                petRow.put("lastFeedingHour", p.getLastFeedingDate().getHour());
                petRow.put("lastFeedingMinute", p.getLastFeedingDate().getMinute());
                petRow.put("lastActivityHour", p.getLastActivityDate().getHour());
                petRow.put("lastActivityMinute", p.getLastActivityDate().getMinute());
                petRow.put("lastOperationHour", p.getLastOperationDate().getHour());
                petRow.put("lastOperationMinute", p.getLastOperationDate().getMinute());
                Date dataAktywnosci = new Date(p.getLastActivityDate().getYear(), p.getLastActivityDate().getMonth().getValue() - 1, p.getLastActivityDate().getDayOfMonth());
                Date dataOperacji = new Date(p.getLastOperationDate().getYear(), p.getLastOperationDate().getMonth().getValue() - 1, p.getLastOperationDate().getDayOfMonth());
                petRow.put("lastActivityDate", dataAktywnosci);
                petRow.put("lastOperationDate", dataOperacji);
                petsTable.updateRow(petRow);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * @param t specifies the type of {@link Action}.
     * @return Types/species of {@link Pet} for paricular {@link Action}
     */
    public List<String> getPetTypesForAction(Action t) {
        List<String> pets = new ArrayList<>();
        Table petActionTable = null;
        Table actionTable = null;
        String key = null;
        String index = null;
        try {
            Table petTypesTable = data.getTable("PetTypes");
            if (t instanceof Activity) {
                petActionTable = data.getTable("PetTypeActivities");
                actionTable = data.getTable("Activities");
                key = "activityName";
                index = "ActivityID";
            } else if (t instanceof Food) {
                petActionTable = data.getTable("PetTypeFoods");
                actionTable = data.getTable("Foods");
                key = "foodName";
                index = "FoodID";
            } else if (t instanceof Operation) {
                petActionTable = data.getTable("PetTypeOperations");
                actionTable = data.getTable("Operations");
                key = "operationName";
                index = "OperationID";
            }
            Row actionRow = CursorBuilder.findRow(actionTable, Collections.singletonMap(key, t.getName()));
            IndexCursor petActionCursor = CursorBuilder.createCursor(petActionTable.getIndex(index));
            List<Integer> temp = new ArrayList<>();
            for (Row r : petActionCursor.newEntryIterable(actionRow.get(index))) {
                temp.add(r.getInt("PetTypeID"));
            }
            for (Integer i : temp) {
                Row r = CursorBuilder.findRowByPrimaryKey(petTypesTable, i);
                pets.add(r.getString("petTypeName"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pets;
    }

    /**
     * @param <T> type
     * @param t specifies the type of {@link Action}.
     * @return All {@link Action}s available in database file.
     */
    @SuppressWarnings("unchecked")
    public <T> List<T> getAllActions(T t) {
        List<T> lista = new ArrayList<>();
        Table actionTable;
        Cursor actionCursor;
        try {
            if (t instanceof Activity) {
                actionTable = data.getTable("Activities");
                actionCursor = CursorBuilder.createCursor(actionTable);
                actionCursor.beforeFirst();
                while (actionCursor.moveToNextRow()) {
                    lista.add((T) new Activity(actionCursor.getCurrentRow().getString("activityName"), actionCursor.getCurrentRow().getInt("activityValue")));
                }
            } else if (t instanceof Food) {
                actionTable = data.getTable("Foods");
                actionCursor = CursorBuilder.createCursor(actionTable);
                actionCursor.beforeFirst();
                while (actionCursor.moveToNextRow()) {
                    lista.add((T) new Food(actionCursor.getCurrentRow().getString("foodName"), actionCursor.getCurrentRow().getInt("foodValue")));
                }
            } else if (t instanceof Operation) {
                actionTable = data.getTable("Operations");
                actionCursor = CursorBuilder.createCursor(actionTable);
                actionCursor.beforeFirst();
                while (actionCursor.moveToNextRow()) {
                    lista.add((T) new Operation(actionCursor.getCurrentRow().getString("operationName"), actionCursor.getCurrentRow().getInt("operationValue")));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lista;
    }
}
