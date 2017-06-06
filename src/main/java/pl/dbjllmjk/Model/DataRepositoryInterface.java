/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.dbjllmjk.Model;

import com.healthmarketscience.jackcess.Row;
import java.util.List;

/**
 *
 * @author Kubek
 */
public interface DataRepositoryInterface {
    
    List<UserData> getUsers();
    List<AdminData> getAdmins();
    String[] getAvaliablePetTypes();
    void removePet(Pet p);
    void addPet(Pet p, UserData ud);
    List<Pet> getPetsForUser(UserData ud);
    <T> void addData(T t);
    void removeUser(UserData ud);
    void removeAdmin(AdminData ad);
    void addPetType(String petTypeName);
    void removePetType(String petTypeName);
    <T> void addAction(T t);
    <T> void removeAction(T t);
    <T> void addActionToPetType(T t, String petTypeName);
    <T> void removeActionFromPetType(T t, String petTypeName);
    void updatePets(List<Pet> pets);
    List<String> getPetTypesForAction(Action t);
    <T> List<T> getAllActions(T t);
    
}
