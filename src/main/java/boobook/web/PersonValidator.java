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

        if(!person.getName().matches("[A-Z][a-z]+([-][A-Z]([a-z])+)?")) {
        	errors.rejectValue("name", "person.name.format", "format du nom non respecté");
        }
        
       if(!person.getFirstname().matches("[A-Z][a-z]+([-][A-Z]([a-z])+)?")) {
        	errors.rejectValue("firstname", "person.firstname.format", "format du prénom non respecté -> une Majuscule et des minuscules");
        }
        
        if(!person.getEmail().matches("([a-z0-9])+([.]([a-z0-9])+)?@([a-z])+(([.]([a-z])+)?|([-]([a-z])+)?)+.([a-z]){2,}")) {
        	errors.rejectValue("email","person.email.format", "email invalide");
        }
        
        if(!person.getWebsite().matches("([a-z0-9])+(([.]([a-z0-9])+)?|([-]([a-z0-9])+)?)+.([a-z]){2,}")) {
        	errors.rejectValue("website","person.website.format", "website invalide");
        }
        
        if(!person.getPassword().matches("(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*-_=+])[a-zA-Z0-9!@#$%^&*-_=+](?=\\S+$).{8,15}")) {
        	errors.rejectValue("password","person.password.format", "password invalide");
        }

    }

}