package md.esempla.library.rest.controller;

import md.esempla.library.domain.Client;
import md.esempla.library.repository.ClientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/clients")
public class ClientsRepositoryRestController {

    @Autowired
    private ClientsRepository clientsRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long create(@RequestBody Client client) {
        clientsRepository.save(client);
        return client.getId();
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void update(@RequestBody Client client) {
        clientsRepository.save(client);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void delete(@PathVariable("id") Long id) {
        clientsRepository.delete(id);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Client getById(@PathVariable("id") Long id) {
        return clientsRepository.findById(id);
    }

    @GetMapping
    public Collection<Client> clients() {
        return clientsRepository.findAll();
    }
}