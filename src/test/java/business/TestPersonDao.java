package business;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collection;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
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
	
	@BeforeAll
	public void init() {		
		dao.addPerson(new Person("p1", "p1", "mail1", "", "01-01-1900", "1234", new Group("groupe 1")));
		dao.addPerson(new Person("p2", "p2", "mail2", "", "01-01-1900", "1234", new Group("groupe 2")));
		dao.addPerson(new Person("p3", "p3", "mail2", "", "01-01-1900", "1234", new Group("groupe 3")));
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
	public void testFindPerson() {
		Person p = new Person(
				"Geralt", 
				"De Riv",
				"geralt@sorceleurs.org", 
				"boucherDeBlaviken.com", 
				"01-01-1993",
				"1234",
				new Group("Sorceleur"));
		dao.addPerson(p);
		Person p2 = dao.findPerson(p.getId());
		dao.removePerson(p.getId());
		assertTrue(p.getName().equals(p2.getName()));
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
	
}
