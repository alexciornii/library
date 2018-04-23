package md.esempla.library.rest.controller;

import md.esempla.library.domain.Publisher;
import md.esempla.library.repository.PublishersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class PublishersRestController {

    @Autowired
    PublishersRepository publsRepository;

    @RequestMapping("/publishers/api")
    Collection<Publisher> publishers() {
        return this.publsRepository.findAll();
    }
}
