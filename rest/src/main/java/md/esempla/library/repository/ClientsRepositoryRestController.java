package md.esempla.library.repository;

import md.esempla.library.domain.Client;
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
