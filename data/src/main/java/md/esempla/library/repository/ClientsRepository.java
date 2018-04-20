package md.esempla.library.repository;

import md.esempla.library.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientsRepository extends JpaRepository<Client, Long> {

    Client findById(long id);
    Client findByFirstName(String name);
    Client findByLastName(String lastName);
    Client findByFirstNameAndLastName(String firstName, String lastName);
}
