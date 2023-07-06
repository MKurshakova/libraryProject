package com.library.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty(message = "name field should be filled in")
    @Size(min = 2, max = 100, message = "name must be more than 2 and less than 100 characters")
    private String name;

    @NotEmpty(message = "surname field should be filled in")
    @Size(min = 2, max = 100, message = "surname must be more than 2 and less than 100 characters")
    private String surname;

    @Column(name = "date_of_birth")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Past
    private LocalDate birthday;

    @Column(name = "death_date")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Past
    private LocalDate deathDay;

    @Size(min = 2, max = 100, message = "surname must be more than 2 and less than 100 characters")
    private String country;

    private String biography;

    @OneToMany(mappedBy = "author")
    private List<Book> books = new ArrayList<>();


}
