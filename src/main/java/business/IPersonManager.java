package business;

import org.springframework.data.repository.CrudRepository;

import model.Person;

public interface IPersonManager extends CrudRepository<Person, Long>{

}
