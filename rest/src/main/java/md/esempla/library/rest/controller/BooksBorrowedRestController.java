package md.esempla.library.rest.controller;

import lombok.extern.slf4j.Slf4j;
import md.esempla.library.domain.BookBorrowed;
import md.esempla.library.repository.BooksBorrowedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Slf4j
@RestController
@RequestMapping("/api/booksborrowed")
public class BooksBorrowedRestController {

    @Autowired
    private BooksBorrowedRepository booksBorrowedRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody BookBorrowed bookBorrowed) {
        booksBorrowedRepository.save(bookBorrowed);
        log.info("Create new row in borrowed books");
    }

    @GetMapping
    public Collection<BookBorrowed> bookBorroweds() {
        log.info("Get list of borrowed books");
        return this.booksBorrowedRepository.findAll();
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void update(@RequestBody BookBorrowed bookBorrowed) {
        log.info("Update borrowed books...");
        log.info("Before: " + booksBorrowedRepository.findById(bookBorrowed.getId()));
        booksBorrowedRepository.save(bookBorrowed);
        log.info("After: " + bookBorrowed);
    }

    @PostMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        booksBorrowedRepository.delete(id);
    }
}