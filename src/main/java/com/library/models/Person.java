package com.library.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.pl.PESEL;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@NoArgsConstructor
@Getter
@Setter
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotEmpty(message = "name field should be filled in")
    @Size(min = 2, max = 100, message = "name must be more than 1 and less than 101 characters")
    private String name;
    @NotEmpty(message = "surname field should be filled in")
    @Size(min = 2, max = 100, message = "surname must be more than 1 and less than 101 characters")
    private String surname;
    @Temporal(TemporalType.DATE)
    @Column(name = "date_of_birth")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Past
    private LocalDate birthday;
    @Email(message = "invalid email")
    private String email;
    @PESEL
    private String pesel;

    @OneToMany(mappedBy = "owner")
    private List<Book> books = new ArrayList<>();

    public Person(String name,
                  String surname,
                  LocalDate birthday,
                  String email,
                  String pesel) {
        this.name = name;
        this.surname = surname;
        this.birthday = birthday;
        this.email = email;
        this.pesel = pesel;
    }
}
