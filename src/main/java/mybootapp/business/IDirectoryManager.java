package mybootapp.business;

import java.util.Collection;

import mybootapp.model.Group;
import mybootapp.model.Person;
import mybootapp.model.User;

public interface IDirectoryManager{
	
    // créer un utilisateur anonyme
    User newUser();

    // chercher une personne
    Person findPerson(User user, long personId);

    // chercher un groupe
    Group findGroup(User user, long groupId);
    
    // chercher les personnes d'un groupe
    Collection<Person> findAllPersons(User user, long groupId);
    
    // chercher les personnes par le param name
    Collection<Person> findPersonsByName(User user, String name);

    // chercher tous les groupes
    Collection<Group> findAllGroup(User user);
    
    // cherher les groupes par le param name
    Collection<Group> findGroupsByName(User user, String name);
    
    // identifier un utilisateur
    boolean login(User user, long personId, String password);

    // oublier l'utilisateur
    void logout(User user);

    // enregistrer une personne
    void savePerson(User user, Person p);

}
