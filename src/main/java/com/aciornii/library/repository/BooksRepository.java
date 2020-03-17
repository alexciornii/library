package com.aciornii.library.repository;

import com.aciornii.library.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BooksRepository extends JpaRepository<Book, Long> {

    Book findById(long id);

}
