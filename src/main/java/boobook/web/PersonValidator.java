package boobook.web;

import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import boobook.model.Person;


@Service
public class PersonValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;

        ValidationUtils.rejectIfEmpty(errors, "name",
                "person.name");
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstname",
                "person.firstname");
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email",
                "person.email");
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "website",
                "person.website");
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "birthdate",
                "person.birthdate");
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "group",
                "person.group");
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password",
                "person.password");

       /* if(person.getName().matches(regex)) {
        	errors.reject("nom invalide");
        }
        
        if() {
        	errors.reject("prénom invalide");
        }*/
        
        if(!person.getEmail().matches("^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$")) {
        	errors.reject("email","person.email.format");
        }

    }

}