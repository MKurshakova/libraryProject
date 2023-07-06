package com.library.models;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.ISBN;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty(message = "title field should be filled in")
    private String title;

    @ManyToOne
    @Valid
    @JoinColumn(name="author_id")
    private Author author;

    @Size(min = 2, max = 100, message = "publisher must be more than 1 and less than 101 characters")
    private String publisher;

    @ManyToOne
    @JoinColumn(name="owner_id")
    private Person owner;

    @Min(value = 1700, message = "year shold be greate then 1700")
    private int year;
    @ISBN
    @NotEmpty
    private String isbn;

    public Book(String title, Author author, int year, String isbn) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.isbn = isbn;
    }
}
