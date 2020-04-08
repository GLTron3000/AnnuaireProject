package mybootapp.business;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import mybootapp.model.Group;
import mybootapp.model.Person;

@Service("databaseFiller")
public class RandomNameFiller {
	String[] nameList = {"Colbert", "Delisle", "Manaudou", "Lortie", "Charbonneau", "Blaise", "Gilson", "Mesny", "Crevier", "Baugé"};
	String[] firstnameList = {"Jean-Marc", "Jean-Guy", "Pierre", "William", "Geoffroy", "Égide", "Godefroy", "Josué", "Gilles", "Gérald"};
	String[] groupList = {"g1", "g2", "g3", "g4"};
	
	@Autowired
	PersonDao dao;
	
	@EventListener(ContextRefreshedEvent.class)
    public void onApplicationEvent(ContextRefreshedEvent event) {
		event.getApplicationContext().getBean(RandomNameFiller.class).fillDB(100);
	}
	
	@Transactional
	public void fillDB(int nbOfNames) {
		Group groupLess = new Group("Sans groupe");
		dao.addGroup(groupLess);
		
		while(nbOfNames != 0) {
			Person p = new Person();
			String name = getRandom(nameList);
			String firstname = getRandom(firstnameList);
			
			p.setFirstname(firstname);
			p.setName(name);
			p.setBirthdate(getRandomDate());
			p.setEmail(name+"."+firstname+"@mail.fr");
			p.setWebsite(name+"."+firstname+".fr");
			p.setGroup(groupLess);
			p.setPassword("1234");
			
			dao.addPerson(p);
			nbOfNames--;
		}
	}
	
	private String getRandom(String[] array) {
	    int rand = new Random().nextInt(array.length);
	    return array[rand];
	}

	private Date getRandomDate() {
		return new Date(ThreadLocalRandom.current().nextInt() * 1000L);
	}

}