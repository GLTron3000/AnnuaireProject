package business;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import model.Group;
import model.Person;
import model.User;

@Repository
@Transactional
public class DirectoryManager implements IDirectoryManager{
	
	@Autowired
    @PersistenceContext(unitName = "myData")
    EntityManager em;

	@Override
	// créer un utilisateur anonyme
	public User newUser() {
		User user = new User();
		return user;
	}

	@Override
	 // chercher une personne
	public Person findPerson(User user, long personId) {
		Person person = em.find(Person.class,personId);
		person.setBirthdate(null);
		person.setEmail(null);
		return person;
	}

	@Override
	// chercher un groupe
	public Group findGroup(User user, long groupId) {
		return em.find(Group.class, groupId);
	}

	@Override
	// chercher les personnes d'un groupe
	public Collection<Person> findAllPersons(User user, long groupId) {
		String query = "SELECT p.name, p.firstname, p.website,  FROM Person p WHERE group.id = :group";
		TypedQuery<Person> q = em.createQuery(query, Person.class);
		q.setParameter(0, groupId);
		return q.getResultList();
	}

	@Override
	// identifier un utilisateur
	public boolean login(User user, long personId, String password) {
		
		return false;
	}

	@Override
	// oublier l'utilisateur
	public void logout(User user) {
		user.setPerson(null);
	}

	@Override
	// enregistrer une personne
	public void savePerson(User user, Person p) {
		user.setPerson(p);
	}
	

}
