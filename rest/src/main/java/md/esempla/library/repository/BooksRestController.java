package md.esempla.library.repository;

import md.esempla.library.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class BooksRestController {

    @Autowired
    BooksRepository booksRepository;

    @RequestMapping("/booksApi")
    Collection<Book> books() {

        return this.booksRepository.findAll();
    }
}
