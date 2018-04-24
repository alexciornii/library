package md.esempla.library.rest.controller;

import lombok.extern.slf4j.Slf4j;
import md.esempla.library.domain.Author;
import md.esempla.library.repository.AuthorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Slf4j
@RestController
@RequestMapping("/api/authors")
public class AuhorsRestController {

    @Autowired
    private AuthorsRepository authorsRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long create(@RequestBody Author author) {
        authorsRepository.save(author);
        log.info("Create an author : " + author);

        return author.getId();
    }

    @GetMapping
    public Collection<Author> getAll() {
        log.info("Get collections of authors");

        return this.authorsRepository.findAll();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Author getById(@PathVariable("id") Long id) {
        log.info("Get author by id + " + id);

        return this.authorsRepository.findById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteById(@PathVariable("id") Long id) {
        log.info("Deleting author by id " + id);
        authorsRepository.delete(id);
    }

    @RequestMapping()
    @ResponseStatus(HttpStatus.OK)
    public void update(@RequestBody Author author) {
        log.info("Update author " + author);
        authorsRepository.save(author);
    }
}