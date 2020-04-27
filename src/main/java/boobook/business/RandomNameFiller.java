package boobook.business;

import java.text.DateFormat;
import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import boobook.model.Group;
import boobook.model.Person;

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
		//event.getApplicationContext().getBean(RandomNameFiller.class).fillDB(1000, 100);
	}
	
	@Transactional
	public void fillDB(int nbOfPersons, int nbOfGroups) {
		Group groupLess = new Group("Sans groupe");
		groups.add(groupLess);
		dao.addGroup(groupLess);
		
		for(int i = 0; i < nbOfGroups; i++) {
			groups.add(new Group("Groupe "+i));
		}
		
		groups.forEach(group -> dao.addGroup(group));
		
		while(nbOfPersons != 0) {
			Person p = new Person();
			String name = getRandom(nameList);
			String firstname = getRandom(firstnameList);
			Group g = getRandomGroup(groups);
			
			p.setFirstname(firstname);
			p.setName(name);
			p.setBirthdate(getRandomDate());
			p.setEmail(Normalizer.normalize(name.toLowerCase()+"."+firstname.toLowerCase()+"@mail.fr", Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", ""));
			p.setWebsite(Normalizer.normalize(name.toLowerCase()+"-"+firstname.toLowerCase()+".fr", Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", ""));
			p.setGroup(g);
			p.setPassword("1234");
			
			dao.addPerson(p);
			nbOfPersons--;
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

	private String getRandomDate() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date(ThreadLocalRandom.current().nextInt() * 1000L);
		return dateFormat.format(date);
	}

	private void addAdmin() {
		Person p = new Person();
		p.setFirstname("Admin");
		p.setName("Admin");
		p.setBirthdate("1950-12-22");
		p.setEmail("admin@admin.admin");
		p.setWebsite("boobook.fr");
		p.setGroup(new Group("admins"));
		p.setPassword("1234");
		
		dao.addPerson(p);
	}
}
