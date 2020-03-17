package com.aciornii.library.repository;

import com.aciornii.library.domain.BookBorrowed;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BooksBorrowedRepository extends JpaRepository<BookBorrowed, Long> {

    BookBorrowed findById(long id);

}
