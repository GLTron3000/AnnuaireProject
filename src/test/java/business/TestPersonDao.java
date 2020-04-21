package business;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collection;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import boobook.business.IPersonDao;
import boobook.business.SpringBusinessConfig;
import boobook.model.Group;
import boobook.model.Person;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@ContextConfiguration(classes = SpringBusinessConfig.class)
public class TestPersonDao {

	@Autowired(required = true)
	static IPersonDao dao;
	
	static Person p1;
	static Person p2;
	static Person p3;
	static Person p4;
	
	@BeforeAll
	public static void init() {	
		p1 = new Person("p1", "p1b", "mail1", "", "01-01-1900", "1234", new Group("groupe 1"));
		p2 = new Person("p2", "p2b", "mail2", "", "01-01-1900", "1234", new Group("groupe 2"));
		p3 = new Person("p3", "p3b", "mail3", "", "01-01-1900", "1234", new Group("groupe 3"));
		p4 = new Person("p4", "p4b", "mail4", "", "01-01-1900", "1234", new Group("groupe 4"));
		dao.addPerson(p1);
		dao.addPerson(p2);
		dao.addPerson(p3);
		dao.addPerson(p4);
	}
	
	@Test
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
		dao.removePerson(p.getId());
	}
		
	@Test
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
	public void testFindPerson() {	
		Person p = dao.findPerson(p1.getId());
		assertEquals(p.getName(), p1.getName(), "Not the same");
	}
	
	@Test
	public void testUpdatePerson() {
		String updatedName = "p4up";
		p4.setName(updatedName);
		dao.updatePerson(p4);
		Person p = dao.findPerson(p4.getId());
		assertEquals(p.getName(), updatedName, "Not updated");
	}
	
	@Test
	public void testFindPersonByEmail() {
		Person p = dao.findPersonByEmail(p1.getEmail());
		assertEquals(p.getName(), p1.getName(), "Not the same");
	}
	
	@Test
	public void testFindAllPersons() {
		Collection<Person> ps = dao.findAllPersons();
		assertNotNull(ps, "Persons is null !");
		assertEquals(3, ps.size(), "Persons size incorect");
	}
	
	@Test
	public void testFindAllPersonsByGroup() {
		Collection<Person> ps = dao.findAllPersonsByGroup(1);
		assertNotNull(ps, "Persons by group is null !");
		assertEquals(1, ps.size(), "Persons by group size incorect");
	}
	
	@Test
	public void testFindAllGroups() {
		Collection<Group> gs = dao.findAllGroups();
		assertNotNull(gs, "Groups is null !");
		assertEquals(3, gs.size(), "Groups size incorect");
	}
	
	@Test
	public void testAddGroup() {
		Group g = new Group("g1");
		dao.addGroup(g);
	}
	
	@Test
	public void testRemoveGroup() {
		Group g = new Group("g1");
		dao.addGroup(g);
		dao.removeGroup(g.getId());
	}
	
	@Test
	public void testFindGroup() {
		Group g = new Group("g1");
		dao.addGroup(g);
		Group g2 = dao.findGroup(g.getId());
		assertEquals(g.getName(), g2.getName(), "Not the same");
	}
	
	@Test
	public void testFindGroupsByName() {
		Collection<Group> result = dao.findGroupsByName("grou");
		assertTrue(result.size() == 4, "Incorect result size");
	}
	
	@Test
	public void testFindPersonsByName() {
		Collection<Person> result = dao.findPersonsByName("p");
		assertTrue(result.size() == 4, "Incorect result size");
	}
	
}
