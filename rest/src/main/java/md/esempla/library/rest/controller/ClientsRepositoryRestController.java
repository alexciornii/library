package md.esempla.library.rest.controller;

import md.esempla.library.domain.Client;
import md.esempla.library.repository.ClientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class ClientsRepositoryRestController {

    @Autowired
    ClientsRepository clientsRepository;

    @RequestMapping("/clients/api")
    Collection<Client> clients() {

        return this.clientsRepository.findAll();
    }
}
