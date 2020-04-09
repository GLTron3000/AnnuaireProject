package mybootapp.business;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mybootapp.model.Group;
import mybootapp.model.Person;
import mybootapp.model.User;

@Service("directoryManager")
public class DirectoryManager implements IDirectoryManager{
	
	@Autowired
    IPersonDao dao;

	@Override
	// créer un utilisateur anonyme
	public User newUser() {
		User user = new User();
		return user;
	}

	@Override
	 // chercher une personne
	public Person findPerson(User user, long personId) {
		if(user.GetIsLogged())
			return dao.findPerson(personId);
		else {
			Person person = dao.findPerson(personId);
			person.setBirthdate(null);
			person.setEmail(null);
			return person;
		}
	}

	@Override
	// chercher un groupe
	public Group findGroup(User user, long groupId) {
		return dao.findGroup(groupId);
	}

	@Override
	// chercher les personnes d'un groupe
	public Collection<Person> findAllPersons(User user, long groupId) {
		if(user.GetIsLogged())
			return dao.findAllPersons(groupId);
		else {
			Collection<Person> persons = dao.findAllPersons(groupId);
			for(Person person : persons) {
				person.setBirthdate(null);
				person.setEmail(null);
			}
			return persons;
		}
	}
	
	@Override
	public Collection<Person> findPersonsByName(User user, String name) {
		if(user.GetIsLogged())
			return dao.findPersonsByName(name);
		else {
			Collection<Person> persons = dao.findPersonsByName(name);
			for(Person person : persons) {
				person.setBirthdate(null);
				person.setEmail(null);
			}
			return persons;
		}
	}
	
	@Override
	public Collection<Group> findAllGroup(User user) {
		return dao.findAllGroups();
	}
	
	@Override
	public Collection<Group> findGroupsByName(User user, String name) {
		return dao.findGroupsByName(name);
	}

	@Override
	// identifier un utilisateur
	public boolean login(User user, long personId, String password) {
		user.setIsLogged(true);
		//TODO//
		return false;
	}

	@Override
	// oublier l'utilisateur
	public void logout(User user) {
		user.setPerson(null);
		user.setIsLogged(false);
	}

	@Override
	// enregistrer une personne
	public void savePerson(User user, Person p) {
		dao.addPerson(p);
	}
}
