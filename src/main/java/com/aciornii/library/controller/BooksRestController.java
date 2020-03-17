package com.aciornii.library.controller;

import com.aciornii.library.domain.Book;
import com.aciornii.library.repository.BooksRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@Slf4j
@RestController
@RequestMapping("/api/booksedited")
public class BooksRestController {

    @Autowired
    private BooksRepository booksRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long create(@RequestBody Book bookEdited) {
        this.booksRepository.save(bookEdited);
        return bookEdited.getId();
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void update(@RequestBody Book book) {
        this.booksRepository.save(book);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)

    @GetMapping("/{id}")
    public Book book(@PathVariable("id") Long id) {
        return this.booksRepository.findById(id).orElse(null);
    }

    @GetMapping
    Collection<Book> books() {
        return this.booksRepository.findAll();
    }

}
