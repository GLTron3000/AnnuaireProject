package mybootapp.business;

import java.util.ArrayList;
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
	ArrayList<Group> groups = new ArrayList<Group>();
	
	@Autowired
	PersonDao dao;
	
	@EventListener(ContextRefreshedEvent.class)
    public void onApplicationEvent(ContextRefreshedEvent event) {
		event.getApplicationContext().getBean(RandomNameFiller.class).fillDB(100000);
	}
	
	@Transactional
	public void fillDB(int nbOfNames) {
		Group groupLess = new Group("Sans groupe");
		groups.add(groupLess);
		
		//dao.addGroup(groupLess);
		
		for(int i = 0; i < groupList.length; i++) {
			groups.add(new Group(groupList[i]));
		}
		
		groups.forEach(group -> dao.addGroup(group));
		
		while(nbOfNames != 0) {
			Person p = new Person();
			String name = getRandom(nameList);
			String firstname = getRandom(firstnameList);
			Group g = getRandomGroup(groups);
			
			p.setFirstname(firstname);
			p.setName(name);
			p.setBirthdate(getRandomDate());
			p.setEmail(name+"."+firstname+"@mail.fr");
			p.setWebsite(name+"."+firstname+".fr");
			p.setGroup(g);
			p.setPassword("1234");
			
			dao.addPerson(p);
			nbOfNames--;
		}
		
		addAdmin();
	}
	
	private String getRandom(String[] array) {
	    int rand = new Random().nextInt(array.length);
	    return array[rand];
	}
	
	private Group getRandomGroup(ArrayList<Group> array) {
	    int rand = new Random().nextInt(array.size());
	    return array.get(rand);
	}

	private Date getRandomDate() {
		return new Date(ThreadLocalRandom.current().nextInt() * 1000L);
	}

	private void addAdmin() {
		Person p = new Person();
		p.setFirstname("admin");
		p.setName("admin");
		p.setBirthdate(getRandomDate());
		p.setEmail("admin@admin.admin");
		p.setWebsite("boobook.fr");
		p.setGroup(new Group("admins"));
		p.setPassword("1234");
		
		dao.addPerson(p);
	}
}
