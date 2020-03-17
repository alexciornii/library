package com.aciornii.library.controller;

import com.aciornii.library.domain.Author;
import com.aciornii.library.repository.AuthorsRepository;
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
@RequestMapping("/api/authors")
public class AuthorsRestController {

    @Autowired
    private AuthorsRepository authorsRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createAuthor(@RequestBody Author author) {
        authorsRepository.save(author);
        log.info("Create an author : " + author);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Collection<Author> getListOfAuthors() {
        log.info("Get collections of authors");

        return this.authorsRepository.findAll();
    }

    @GetMapping(value = "/{id}", produces = "application/json; charset=UTF-8")
    public Author getAuthorById(@PathVariable("id") Long id) {
        log.info("Get author by id + " + id);

        return this.authorsRepository.findById(id).orElse(null);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteauthorById(@PathVariable("id") Long id) {
        log.info("Deleting author by id " + id);
        authorsRepository.deleteById(id);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public Author updateAuthor(@RequestBody Author author) {
        log.info("Update author " + author);
        authorsRepository.save(author);

        return author;
    }

}
