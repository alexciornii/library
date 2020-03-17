package com.aciornii.library.controller;

import com.aciornii.library.domain.BookEdited;
import com.aciornii.library.repository.BooksEditedRepository;
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
        booksEditedRepository.deleteById(id);
    }

    @PutMapping
    public void update(@RequestBody BookEdited bookEdited) {
        booksEditedRepository.save(bookEdited);
    }

}
