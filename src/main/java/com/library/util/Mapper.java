package com.library.util;

import com.library.models.Author;
import com.library.models.AuthorDTO;
import org.springframework.stereotype.Component;

@Component
public class Mapper {
        public Author map(AuthorDTO authorDTO) {
            return map(authorDTO, new Author());
        }

        public Author map(AuthorDTO authorDto, Author author) {
            author.setName(authorDto.getName());
            author.setBiography(authorDto.getBiography());
            author.setSurname(authorDto.getSurname());
            author.setId(authorDto.getId());
            return author;
        }
    public AuthorDTO map(Author author, AuthorDTO authorDto) {
        author.setName(authorDto.getName());
        author.setBiography(authorDto.getBiography());
        author.setSurname(authorDto.getSurname());
        author.setId(authorDto.getId());
        return authorDto;
    }
}
