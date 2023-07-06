package com.library.util;

import com.library.models.Person;
import com.library.services.PeopleService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
@Component
@AllArgsConstructor
public class PersonValidator implements Validator {
    private final PeopleService peopleService;

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;
        if(peopleService.findByPesel(person.getPesel()).isPresent()){
            errors.rejectValue("pesel", "", "this pesel is already taken");
        }
        if(peopleService.findByEmail(person.getEmail()).isPresent()){
            errors.rejectValue("email", "", "this email is already taken");
        }
    }
}
