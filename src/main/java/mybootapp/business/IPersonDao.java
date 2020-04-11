package mybootapp.business;

import java.util.Collection;

import mybootapp.model.Group;
import mybootapp.model.Person;


public interface IPersonDao {
	
	void addPerson(Person p);
	void removePerson(long id);
	Collection<Person> findAllPersons();
	Collection<Person> findAllPersonsByGroup(long groupId);
	Person findPerson(long id);
	Person findPersonByEmail(String email);

	void addGroup(Group g);
	void removeGroup(long id);
	void updatePerson(Person person);
	Collection<Group> findAllGroups();
	Group findGroup(long id);
	
	Collection<Group> findGroupsByName(String name);
	Collection<Person> findPersonsByName(String name);
	
}
