package com.aciornii.library.controller;

import com.aciornii.library.domain.Client;
import com.aciornii.library.repository.ClientsRepository;
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
        clientsRepository.deleteById(id);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Client getById(@PathVariable("id") Long id) {
        return clientsRepository.findById(id).orElse(null);
    }

    @GetMapping
    public Collection<Client> clients() {
        return clientsRepository.findAll();
    }

}
