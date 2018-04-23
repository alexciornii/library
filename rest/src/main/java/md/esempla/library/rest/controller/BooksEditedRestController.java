package md.esempla.library.rest.controller;

import lombok.extern.slf4j.Slf4j;
import md.esempla.library.domain.BookEdited;
import md.esempla.library.repository.BooksEditedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Slf4j
@RestController
@RequestMapping("/api/editedbooks")
public class BooksEditedRestController {

    @Autowired
    private BooksEditedRepository booksEditedRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long create(@RequestBody BookEdited bookEdited) {
        booksEditedRepository.save(bookEdited);

        return bookEdited.getId();
    }

    @GetMapping
    public Collection<BookEdited> bookEditeds() {
        log.info("Get list of book edited");

        return this.booksEditedRepository.findAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        booksEditedRepository.delete(id);
    }

    @PutMapping
    public void update(@RequestBody BookEdited bookEdited) {
        booksEditedRepository.save(bookEdited);
    }
}