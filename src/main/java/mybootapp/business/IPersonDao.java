package mybootapp.business;

import java.util.Collection;

import mybootapp.model.Group;
import mybootapp.model.Person;


public interface IPersonDao {
	
	void addPerson(Person p);
	void removePerson(long id);
	Collection<Person> findAllPersons(long groupId);
	Person findPerson(long id);

	void addGroup(Group g);
	void removeGroup(long id);
	Collection<Group> findAllGroups();
	Group findGroup(long id);
}
