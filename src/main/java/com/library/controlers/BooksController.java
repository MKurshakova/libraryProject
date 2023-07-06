package com.library.controlers;

import com.library.models.Author;
import com.library.models.Book;
import com.library.models.Person;
import com.library.services.AuthorsService;
import com.library.services.BooksService;
import com.library.services.PeopleService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.Optional;

@Controller
@AllArgsConstructor
@RequestMapping("/books")
public class BooksController {
    private final BooksService booksService;
    private final AuthorsService authorsService;
    private final PeopleService peopleService;

    @GetMapping()
    public String index(Model model){
        model.addAttribute("books", booksService.findAll());
        return "books/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") long id, Model model,
                       @ModelAttribute("person")Person person){
        model.addAttribute("book", booksService.findOne(id));
        Optional<Person>bookOwner = booksService.getBookOwner(id);
        if(bookOwner.isPresent()){
            model.addAttribute("owner",bookOwner.get());
        }else{
            model.addAttribute("people",peopleService.findAll());
        }
        return "books/show";
    }
    @GetMapping("/new")
    public String newBook(@ModelAttribute("book")Book book, @ModelAttribute("author")Author author){
        return "books/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("book") @Valid Book book, @ModelAttribute("author")@Valid Author author,
                         BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return "book/new";
        }
        Optional<Author>bookAuthor = authorsService.findByNameAndSurname(author.getName(), author.getSurname());
        if(bookAuthor.isPresent()) {
            book.setAuthor(authorsService.findOne(bookAuthor.get().getId()));
            booksService.save(book);
        }
        authorsService.save(author);
        book.setAuthor(author);
        booksService.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String edit (Model model, @PathVariable("id") long id) {
        model.addAttribute("book", booksService.findOne(id));
        model.addAttribute("author", booksService.findOne(id).getAuthor());
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("books") @Valid Book book,
                         @ModelAttribute("author")@Valid Author author,
                         BindingResult bindingResult,
                         @PathVariable("id") long id) {
        if(bindingResult.hasErrors()){
            return "books/edit";
        }
        Book bookInBase = booksService.findOne(id);
        if(Objects.equals(bookInBase.getAuthor().getName(), author.getName())
                && Objects.equals(bookInBase.getAuthor().getSurname(), author.getSurname())){
            booksService.update(id, book);
        }
        book.setAuthor(author);
        booksService.update(id, book);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") long id) {
        booksService.delete(id);
        return "redirect:/books";
    }
    @PatchMapping("/{id}/release")
    public String release(@PathVariable("id") long id){
        booksService.release(id);
        return "redirect:/books/"+id;
    }

    @PatchMapping("/{id}/assign")
    public String assign(@PathVariable("id") long id,
                         @ModelAttribute("person") Person person){
        booksService.assign(id, person);
        return "redirect:/books/"+id;
    }
}
