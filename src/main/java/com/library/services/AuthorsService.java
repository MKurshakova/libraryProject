package com.library.services;

import com.library.models.Author;
import com.library.models.Book;
import com.library.models.Person;
import com.library.repositories.AuthorsRepository;
import com.library.repositories.BooksRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthorsService {
    private final AuthorsRepository authorsRepository;
    private final BooksRepository booksRepository;

    public List<Author> findAll(){
        return authorsRepository.findAll();
    }

    public Author findOne(long id){
        Optional<Author> author = authorsRepository.findById(id);
        return author.orElse(null);
    }

    public Optional<Author> findByNameAndSurname(String name, String surname){
        Optional<Author> author = authorsRepository.findByNameAndSurname(name,surname);
        return author;
    }

    public void save(Author author){
        authorsRepository.save(author);
    }

    public void update(long id, Author author){
        author.setId(id);
        authorsRepository.save(author);
    }

    public void delete(long id){
        authorsRepository.deleteById(id);
    }

    public List<Book> getBooksByAuthorId(long id){
        Optional<Author> author = authorsRepository.findById(id);
        if(author.isPresent()) {
            return booksRepository.findByAuthor(author);
        }
        return null;
    }


}
