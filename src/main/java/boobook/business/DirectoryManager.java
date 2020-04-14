package boobook.business;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import boobook.model.Group;
import boobook.model.Person;
import boobook.model.User;

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
	public Collection<Person> findAllPersons(User user) {
		if(user.GetIsLogged())
			return dao.findAllPersons();
		else {
			Collection<Person> persons = dao.findAllPersons();
			for(Person person : persons) {
				person.setBirthdate(null);
				person.setEmail(null);
			}
			return persons;
		}
	}
	
	@Override
	// chercher les personnes d'un groupe
	public Collection<Person> findAllPersonsByGroup(User user, long groupId) {
		if(user.GetIsLogged())
			return dao.findAllPersonsByGroup(groupId);
		else {
			Collection<Person> persons = dao.findAllPersonsByGroup(groupId);
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
	public boolean login(User user, String email, String password) {
		Person p = dao.findPersonByEmail(email);
		
		System.err.println("[MANAGER] login p:"+p);
		
		if(p == null) return false;
		
		System.err.println("[MANAGER] "+p.getPassword()+" vs "+password);
		if(!p.getPassword().equals(password)) return false;
		
		System.err.println("[MANAGER] user is logged in");
		user.setIsLogged(true);
		user.setPerson(p);
		
		return true;
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
		if(p.getId() == user.getPerson().getId()) dao.addPerson(p);
	}
	
	@Override
	public void updatePerson(User user, Person upDatedPerson) {
		System.err.println("[MANAGER] upadet person id:" + upDatedPerson.getId() + " vs " + user.getPerson().getId());
		if(upDatedPerson.getId().equals(user.getPerson().getId())) {
			dao.updatePerson(upDatedPerson);
			//dao.addPerson(upDatedPerson);
			System.err.println("[MANAGER] upadet ok");
		}
	}
}
