package business;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import mybootapp.business.IPersonDao;
import mybootapp.business.SpringBusinessConfig;
import mybootapp.model.Group;
import mybootapp.model.Person;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = SpringBusinessConfig.class)
public class TestPersonDao {

	@Autowired
	IPersonDao dao;
	
	@Test
	public void testAddPerson() {
		Person p = new Person(
				"Geralt", 
				"De Riv",
				"geralt@sorceleurs.org", 
				"boucherDeBlaviken.com", 
				new Date(733699763),
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
				new Date(733699763),
				"1234",
				new Group("Sorceleur"));
		dao.addPerson(p);
		Person p2 = dao.findPerson(p.getId());
		dao.removePerson(p.getId());
		assertTrue(p.getName().equals(p2.getName()));
	}
}
