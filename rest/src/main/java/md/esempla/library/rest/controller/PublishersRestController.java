package md.esempla.library.rest.controller;

import md.esempla.library.domain.Publisher;
import md.esempla.library.repository.PublishersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
        publsRepository.delete(id);
    }

    @GetMapping("/{id}")
    public Publisher getById(@PathVariable("id") Long id) {
        return publsRepository.findById(id);
    }

    @GetMapping
    public Collection<Publisher> publishers() {
        return publsRepository.findAll();
    }
}
