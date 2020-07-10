package com.example.authorbookspring.controller;

import com.example.authorbookspring.model.Author;
import com.example.authorbookspring.model.Book;
import com.example.authorbookspring.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class BookController {
    @Autowired
    BookRepository bookRepository;

    @PostMapping("/addBook")
    public String addBook(@ModelAttribute Book book){
        bookRepository.save(book);
        return "redirect:/";
    }

    @GetMapping("/allBooks")
    public String getAllBooks(ModelMap modelMap){
        List<Book> allBooks = bookRepository.findAll();
        modelMap.addAttribute("allbooks",allBooks);
        return "book";
    }

    @GetMapping("/deleteBook")
    public String deleteAuthorById(@RequestParam("id") int id){
        bookRepository.deleteById(id);
        return "redirect:/allBooks";
    }

    @GetMapping("/getBook")
    public String getAuthorById(@RequestParam("id") int id,ModelMap modelMap){
        Book bookById = bookRepository.getOne(id);
        modelMap.addAttribute("book",bookById);
        return "updateBook";
    }

    @PostMapping("/updateBookData")
    public String updateAuthorData(@RequestParam("id") int id,@RequestParam("title")
            String title,@RequestParam("description") String description,@RequestParam("price") String price){
        Book book = new Book();
        book.setId(id);
        book.setTitle(title);
        book.setDescription(description);
        book.setPrice(Double.valueOf(price));
        bookRepository.save(book);
        return "redirect:/allBooks";
    }
}
