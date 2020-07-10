package com.example.authorbookspring.controller;

import com.example.authorbookspring.model.Author;
import com.example.authorbookspring.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class AuthorController {
    @Autowired
    private AuthorRepository authorRepository;

    @GetMapping("/")
    public String homePage(ModelMap modelMap) {
        List<Author> allAuthors = authorRepository.findAll();
        modelMap.addAttribute("authors", allAuthors);
        return "home";
    }

    @PostMapping("/addAuthor")
    public String addAuthor(@ModelAttribute Author author) {
        authorRepository.save(author);
        return "redirect:/";
    }

    @GetMapping("/allAuthors")
    public String allAuthors(ModelMap modelMap){
        List<Author> all = authorRepository.findAll();
        modelMap.addAttribute("allAuthors",all);
        return "authors";
    }

    @GetMapping("/deleteAuthor")
    public String deleteAuthorById(@RequestParam("id") int id){
        authorRepository.deleteById(id);
        return "redirect:/allAuthors";
    }

    @GetMapping("/getAuthor")
    public String getAuthorById(@RequestParam("id") int id,ModelMap modelMap){
        Author authorById = authorRepository.getOne(id);
        modelMap.addAttribute("author",authorById);
        return "updateAuthor";
    }


    @PostMapping("/updateAuthorData")
    public String updateAuthorData(@RequestParam("id") int id,@RequestParam("name")
            String name,@RequestParam("surname") String surname,@RequestParam("email") String email,@RequestParam("gender") String gender){
        Author author = new Author();
        author.setId(id);
        author.setName(name);
        author.setSurname(surname);
        author.setEmail(email);
        author.setGender(gender);
        authorRepository.save(author);
        return "redirect:/allAuthors";
    }
}
