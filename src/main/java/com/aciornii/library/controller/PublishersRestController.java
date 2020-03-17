package com.aciornii.library.controller;

import com.aciornii.library.domain.Publisher;
import com.aciornii.library.repository.PublishersRepository;
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

@RestController
@RequestMapping("/publishers/api")
public class PublishersRestController {

    @Autowired
    private PublishersRepository publsRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long create(@RequestBody Publisher publisher) {
        publsRepository.save(publisher);
        return publisher.getId();
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void update(@RequestBody Publisher publisher) {
        publsRepository.save(publisher);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Long id) {
        publsRepository.deleteById(id);
    }

    @GetMapping("/{id}")
    public Publisher getById(@PathVariable("id") Long id) {
        return publsRepository.findById(id).orElse(null);
    }

    @GetMapping
    public Collection<Publisher> publishers() {
        return publsRepository.findAll();
    }

}
