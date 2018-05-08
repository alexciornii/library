package md.esempla.library.repository;


import md.esempla.library.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorsRepository extends JpaRepository<Author, Long> {

    Author findById(long id);
    Author findByFirstName(String firstName);
    Author findByLastName(String lastName);
    Author findByFirstNameAndLastName(String firstName, String lastName);
}