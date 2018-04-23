package md.esempla.library.rest.controller;

import md.esempla.library.domain.Book;
import md.esempla.library.repository.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class BooksRestController {

    @Autowired
    BooksRepository booksRepository;

    @RequestMapping("/books/api")
    Collection<Book> books() {

        return this.booksRepository.findAll();
    }
}
