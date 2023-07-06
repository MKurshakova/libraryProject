package com.library.services;

import com.library.models.Author;
import com.library.models.Book;
import com.library.models.Person;
import com.library.repositories.BooksRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BooksService {
    private final BooksRepository booksRepository;

    public List<Book> findAll(){
        return booksRepository.findAll();
    }

    public Book findOne(long id){
        Optional<Book> book = booksRepository.findById(id);
        return book.orElse(null);
    }

    public void save(Book book){
        booksRepository.save(book);
    }

    public void update(long id, Book book){
        book.setId(id);
        booksRepository.save(book);
    }

    public void delete(long id){
        booksRepository.deleteById(id);
    }

    public Optional<Person> getBookOwner(long id){
        if(booksRepository.findById(id).isPresent()){
            return Optional.ofNullable(booksRepository.findById(id).get().getOwner());
        }else{
            return Optional.empty();
        }

    }
    public Optional<Author> getBookAuthor(long id){
        if(booksRepository.findById(id).isPresent()){
            return Optional.ofNullable(booksRepository.findById(id).get().getAuthor());
        }else{
            return Optional.empty();
        }
    }

    public void assign(long id, Person person){
        Optional<Book> book = booksRepository.findById(id);
        book.get().setOwner(person);
    }

    public void release(long id) {
        Optional<Book> book = booksRepository.findById(id);
        book.get().setOwner(null);
    }
}
