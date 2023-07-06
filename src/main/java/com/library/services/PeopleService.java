package com.library.services;

import com.library.models.Book;
import com.library.models.Person;
import com.library.repositories.BooksRepository;
import com.library.repositories.PeopleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class PeopleService {
    private final PeopleRepository peopleRepository;
    private final BooksRepository booksRepository;
    public List<Person> findAll(){
        return peopleRepository.findAll();
    }

    public Person findOne(long id){
        Optional<Person> foundPerson = peopleRepository.findById(id);
        return foundPerson.orElse(null);
    }
    public Optional<Person> findByEmail(String email){
        return peopleRepository.findByEmail(email);
    }
    public Optional<Person> findByPesel(String pesel){
        return peopleRepository.findByEmail(pesel);
    }
    @Transactional
    public  void save(Person person){
        peopleRepository.save(person);
    }
    @Transactional
    public void update(long id, Person updatedPerson){
        updatedPerson.setId(id);
        peopleRepository.save(updatedPerson);
    }
    @Transactional
    public void delete(long id){
        peopleRepository.deleteById(id);
    }

    @Transactional
    public List<Book> getBooksByPersonId(long id){
        Optional<Person> owner = peopleRepository.findById(id);
        if(owner.isPresent()) {
            return booksRepository.findByOwner(owner);
        }
        return new ArrayList<>();
    }
}
