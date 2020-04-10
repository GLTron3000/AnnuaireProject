package mybootapp.business;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import mybootapp.model.Group;
import mybootapp.model.Person;

@Repository
@Transactional
public class PersonDao implements IPersonDao {

	@Autowired
    @PersistenceContext(unitName = "myData")
    EntityManager em;
	
		    
	@Override
	public Collection<Group> findAllGroups() {
		String query = "SELECT g FROM Group g";
		TypedQuery<Group> q = em.createQuery(query, Group.class);
		return q.getResultList();
	}
	
	@Override
	public Collection<Group> findGroupsByName(String name) {
		String query = "SELECT g FROM Group g WHERE g.name LIKE :name";
		TypedQuery<Group> q = em.createQuery(query, Group.class);
		q.setParameter("name", "%"+name+"%");
		return q.getResultList();
	}

	@Override
	public Collection<Person> findAllPersons(long groupId) {
		String query = "SELECT p FROM Person p WHERE group.id = :group";
		TypedQuery<Person> q = em.createQuery(query, Person.class);
		q.setParameter(0, groupId);
		return q.getResultList();
	}
	
	@Override
	public Collection<Person> findPersonsByName(String name) {
		String query = "SELECT p FROM Person p WHERE p.name LIKE :name OR p.firstname LIKE :name";
		TypedQuery<Person> q = em.createQuery(query, Person.class);
		q.setParameter("name", "%"+name+"%");
		return q.getResultList();
	}
	
	@Override
	public Person findPersonByEmail(String email) {
		String query = "SELECT p FROM Person p WHERE p.email = : email";
		TypedQuery<Person> q = em.createQuery(query, Person.class);
		q.setParameter("email", email);
		return q.getSingleResult();
	}

	@Override
	public Person findPerson(long id) {
		return em.find(Person.class, id);
	}

	@Override
	public void addPerson(Person p) {
		em.persist(p);
	}

	@Override
	public void addGroup(Group g) {
		em.persist(g);
	}
	
	@Override
	public void removePerson(long id) {
		Person p = em.find(Person.class, id);
		
		// remove from groups ?
		em.remove(p);
	}

	@Override
	public void removeGroup(long id) {
		Group g = em.find(Group.class, id);
		em.remove(g);
	}
	
	@Override
	public Group findGroup(long id) {
		return em.find(Group.class, id);
	}
	
	@Override
	@Modifying
	public void updatePerson(Person person){
		em.merge(person);
	}
	
}
