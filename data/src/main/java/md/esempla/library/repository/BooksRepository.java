package md.esempla.library.repository;

import md.esempla.library.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BooksRepository extends JpaRepository<Book, Long> {

    Book findById(long id);
    Book findByName(String name);
    Book findOne(long idBook);
}
