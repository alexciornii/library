package com.aciornii.library.controller;

import com.aciornii.library.domain.BookBorrowed;
import com.aciornii.library.repository.BooksBorrowedRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
@RequestMapping("/api/booksborrowed")
public class BooksBorrowedRestController {

    @Autowired
    private BooksBorrowedRepository booksBorrowedRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createBookBorrowed(@RequestBody BookBorrowed bookBorrowed) {
        booksBorrowedRepository.save(bookBorrowed);
        log.info("Create new row in borrowed books");
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Collection<BookBorrowed> getBooksBorrowed() {
        log.info("Get list of borrowed books");
        return this.booksBorrowedRepository.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BookBorrowed getBooksBorrowedById(@PathVariable("id") long id) {
        log.info("Getting book borrowed by id " + id);

        return booksBorrowedRepository.findById(id);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public BookBorrowed updateBookBorrowed(@RequestBody BookBorrowed bookBorrowed) {
        log.info("Update borrowed books...");
        log.info("Before: " + booksBorrowedRepository.findById(bookBorrowed.getId()));
        booksBorrowedRepository.save(bookBorrowed);
        log.info("After: " + bookBorrowed);

        return bookBorrowed;
    }

    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteBookBorrowed(@PathVariable("id") Long id) {
        booksBorrowedRepository.deleteById(id);
    }

}
