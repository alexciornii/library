package md.esempla.library.repository;

import md.esempla.library.domain.BookBorrowed;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BooksBorrowedRepository extends JpaRepository<BookBorrowed, Long> {

    BookBorrowed findById(long id);
}
