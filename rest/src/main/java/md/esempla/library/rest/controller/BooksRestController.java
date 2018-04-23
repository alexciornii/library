package md.esempla.library.rest.controller;

import lombok.extern.slf4j.Slf4j;
import md.esempla.library.domain.Book;
import md.esempla.library.repository.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
    public  void update(@RequestBody Book book) {
        this.booksRepository.save(book);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)

    @GetMapping("/{id}")
    public Book book(@PathVariable("id") Long id) {
        return this.booksRepository.findById(id);
    }

    @GetMapping
    Collection<Book> books() {
        return this.booksRepository.findAll();
    }
}