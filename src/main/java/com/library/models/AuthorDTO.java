package com.library.models;

import jakarta.validation.constraints.NotEmpty;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@NoArgsConstructor
@Getter
@Setter
public class AuthorDTO {
        private long id;

        private String name;

        private String surname;

        private LocalDate birthday;

        private LocalDate deathDay;

         private String country;

        private String biography;

        private List<Book> books = new ArrayList<>();
}
