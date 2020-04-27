package business;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Collection;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import boobook.business.IPersonDao;
import boobook.business.SpringBusinessConfig;
import boobook.model.Group;
import boobook.model.Person;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = SpringBusinessConfig.class)
public class TestPersonDao {

	@Autowired
	IPersonDao dao;
	
	@Test
	@Transactional
    @Rollback(true)
	public void testAddPerson() {
		Person p = new Person(
				"Geralt", 
				"De Riv",
				"geralt@sorceleurs.org", 
				"boucherDeBlaviken.com", 
				"01-01-1993",
				"1234",
				new Group("Sorceleur"));
		dao.addPerson(p);
	}
		
	@Test
	@Transactional
	@Rollback(true)
	public void testRemovePerson() {
		Person p = new Person(
				"Geralt", 
				"De Riv",
				"geralt@sorceleurs.org", 
				"boucherDeBlaviken.com", 
				"01-01-1993",
				"1234",
				new Group("Sorceleur"));
		dao.addPerson(p);
		dao.removePerson(p.getId());
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void testFindPerson() {
		Person p1 = new Person("p1", "p1b", "mail1", "", "01-01-1900", "1234", new Group("groupe 1"));
		dao.addPerson(p1);
		Person p = dao.findPerson(p1.getId());
		assertEquals(p.getName(), p1.getName(), "Not the same");
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void testUpdatePerson() {
		Person p1 = new Person("p1", "p1b", "mail1", "", "01-01-1900", "1234", new Group("groupe 1"));
		dao.addPerson(p1);
		String updatedName = "p1up";
		p1.setName(updatedName);
		dao.updatePerson(p1);
		Person p = dao.findPerson(p1.getId());
		assertEquals(p.getName(), updatedName, "Not updated");
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void testFindPersonByEmail() {
		Person p1 = new Person("p1", "p1b", "mail1", "", "01-01-1900", "1234", new Group("groupe 1"));
		dao.addPerson(p1);
		Person p = dao.findPersonByEmail(p1.getEmail());
		assertEquals(p.getName(), p1.getName(), "Not the same");
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void testFindAllPersons() {
		Person p1 = new Person("p1", "p1b", "mail1", "", "01-01-1900", "1234", new Group("groupe 1"));
		Person p2 = new Person("p2", "p2b", "mail2", "", "01-01-1900", "1234", new Group("groupe 2"));
		Person p3 = new Person("p3", "p3b", "mail3", "", "01-01-1900", "1234", new Group("groupe 3"));
		dao.addPerson(p1);
		dao.addPerson(p2);
		dao.addPerson(p3);
		Collection<Person> ps = dao.findAllPersons();
		assertNotNull(ps, "Persons is null !");
		assertEquals(3, ps.size(), "Persons size incorect");
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void testFindAllPersonsByGroup() {
		Group g1 = new Group("g1");
		Group g2 = new Group("g1");
		Person p1 = new Person("p1", "p1b", "mail1", "", "01-01-1900", "1234", g1);
		Person p2 = new Person("p2", "p2b", "mail2", "", "01-01-1900", "1234", g1);
		Person p3 = new Person("p3", "p3b", "mail3", "", "01-01-1900", "1234", g2);
		dao.addPerson(p1);
		dao.addPerson(p2);
		dao.addPerson(p3);
		Collection<Person> ps = dao.findAllPersonsByGroup(g1.getId());
		assertNotNull(ps, "Persons by group is null !");
		assertEquals(2, ps.size(), "Persons by group size incorect");
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void testFindAllGroups() {
		Person p1 = new Person("p1", "p1b", "mail1", "", "01-01-1900", "1234", new Group("groupe 1"));
		Person p2 = new Person("p2", "p2b", "mail2", "", "01-01-1900", "1234", new Group("groupe 2"));
		Person p3 = new Person("p3", "p3b", "mail3", "", "01-01-1900", "1234", new Group("groupe 3"));
		dao.addPerson(p1);
		dao.addPerson(p2);
		dao.addPerson(p3);
		Collection<Group> gs = dao.findAllGroups();
		assertNotNull(gs, "Groups is null !");
		assertEquals(3, gs.size(), "Groups size incorect");
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void testAddGroup() {
		Group g = new Group("g1");
		dao.addGroup(g);
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void testRemoveGroup() {
		Group g = new Group("g1");
		dao.addGroup(g);
		dao.removeGroup(g.getId());
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void testFindGroup() {
		Group g = new Group("g1");
		dao.addGroup(g);
		Group g2 = dao.findGroup(g.getId());
		assertEquals(g.getName(), g2.getName(), "Not the same");
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void testFindGroupsByName() {
		Person p1 = new Person("p1", "p1b", "mail1", "", "01-01-1900", "1234", new Group("groupe 1"));
		Person p2 = new Person("p2", "p2b", "mail2", "", "01-01-1900", "1234", new Group("groupe 2"));
		Person p3 = new Person("p3", "p3b", "mail3", "", "01-01-1900", "1234", new Group("groupe 3"));
		Person p4 = new Person("p4", "p4b", "mail4", "", "01-01-1900", "1234", new Group("groupe 4"));
		dao.addPerson(p1);
		dao.addPerson(p2);
		dao.addPerson(p3);
		dao.addPerson(p4);
		Collection<Group> result = dao.findGroupsByName("grou");
		assertEquals(result.size(), 4, "Incorect result size");
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void testFindPersonsByName() {
		Person p1 = new Person("p1", "p1b", "mail1", "", "01-01-1900", "1234", new Group("groupe 1"));
		Person p2 = new Person("p2", "p2b", "mail2", "", "01-01-1900", "1234", new Group("groupe 2"));
		Person p3 = new Person("p3", "p3b", "mail3", "", "01-01-1900", "1234", new Group("groupe 3"));
		Person p4 = new Person("p4", "p4b", "mail4", "", "01-01-1900", "1234", new Group("groupe 4"));
		dao.addPerson(p1);
		dao.addPerson(p2);
		dao.addPerson(p3);
		dao.addPerson(p4);
		Collection<Person> result = dao.findPersonsByName("p");
		assertEquals(result.size(), 4, "Incorect result size");
	}
	
}
