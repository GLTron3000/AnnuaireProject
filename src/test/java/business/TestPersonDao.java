package business;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import model.Group;
import model.Person;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = SpringBusinessConfig.class)
public class TestPersonDao {

	@Autowired
	PersonDao dao;
	
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
}
