package com.aciornii.library.repository;

import com.aciornii.library.domain.BookEdited;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BooksEditedRepository extends JpaRepository<BookEdited, Long> {

    BookEdited findById(long id);
}
