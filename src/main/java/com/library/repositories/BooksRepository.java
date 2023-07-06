package com.library.repositories;

import com.library.models.Author;
import com.library.models.Book;
import com.library.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BooksRepository extends JpaRepository<Book, Long> {
    List<Book> findByOwner(Optional<Person> owner);
    List<Book> findByAuthor(Optional<Author> author);
}
