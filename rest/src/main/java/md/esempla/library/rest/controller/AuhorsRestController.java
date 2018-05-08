package md.esempla.library.rest.controller;

import lombok.extern.slf4j.Slf4j;
import md.esempla.library.domain.Author;
import md.esempla.library.repository.AuthorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Slf4j
@RestController
@RequestMapping("/api/authors")
public class AuhorsRestController {

    @Autowired
    private AuthorsRepository authorsRepository;

    @PostMapping()
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

        return this.authorsRepository.findById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteauthorById(@PathVariable("id") Long id) {
        log.info("Deleting author by id " + id);
        authorsRepository.delete(id);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public Author updateAuthor(@RequestBody Author author) {
        log.info("Update author " + author);
        authorsRepository.save(author);

        return author;
    }
}