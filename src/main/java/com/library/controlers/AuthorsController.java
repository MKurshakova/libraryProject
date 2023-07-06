package com.library.controlers;

import com.library.models.Author;
import com.library.models.Person;
import com.library.services.AuthorsService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@RequestMapping("/authors")
public class AuthorsController {
    private final AuthorsService authorsService;

    @GetMapping()
    public String index(Model model){
        model.addAttribute("authors", authorsService.findAll());
        return "authors/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") long id, Model model) {
        model.addAttribute("author", authorsService.findOne(id));
        model.addAttribute("books", authorsService.getBooksByAuthorId(id));
        return "authors/show";
    }
    @GetMapping("/new")
    public String newAuthor(@ModelAttribute("author")Author author){
        return "authors/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("author") @Valid Author author,
                         BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return "authors/new";
        }
        authorsService.save(author);
        return "redirect:/authors";
    }

    @GetMapping("/{id}/edit")
    public String edit (Model model, @PathVariable("id") long id) {
        model.addAttribute("author", authorsService.findOne(id));
        return "authors/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("author") @Valid Author author,
                         BindingResult bindingResult,
                         @PathVariable("id") long id) {
        if(bindingResult.hasErrors()){
            return "authors/edit";
        }
        authorsService.update(id, author);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") long id) {
        authorsService.delete(id);
        return "redirect:/people";
    }
}
