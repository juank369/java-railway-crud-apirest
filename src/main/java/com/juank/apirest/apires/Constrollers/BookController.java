package com.juank.apirest.apires.Constrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.juank.apirest.apires.Entities.Book;
import com.juank.apirest.apires.Repositories.BookRepository;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/books")

public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping
    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    @GetMapping("/{id}")
    public Book getBook(@PathVariable Long id){
        return bookRepository.findById(id)
        .orElseThrow(()-> new RuntimeException("book no found"));
    }

    @PostMapping
    public Book createBook(@RequestBody Book book){
        return bookRepository.save(book);
    }

    @PutMapping("/{id}")
    public Book updateBook(@PathVariable Long id, @RequestBody Book bookDetails){
        Book book = bookRepository.findById(id)
        .orElseThrow(()-> new RuntimeException("book no found"));

        book.setName(bookDetails.getName());
        book.setPrice(bookDetails.getPrice());

        return bookRepository.save(book);
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable Long id){
        Book book= bookRepository.findById(id)
        .orElseThrow(()-> new RuntimeException("book no found"));

        bookRepository.delete(book);

        return "the book was removed";
    }
    

    
    
    
    
}
