package md.esempla.library.repository;

import md.esempla.library.domain.BookEdited;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BooksEditedRepository extends JpaRepository<BookEdited, Long> {

    BookEdited findById(long id);
}
