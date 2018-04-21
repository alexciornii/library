package md.esempla.library.repository;

import md.esempla.library.domain.BookBorrowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class BooksBorrowedRestController {

    @Autowired
    BooksBorrowedRepository booksBorrowedRepository;

    @RequestMapping("/booksborrowed/api")
    Collection<BookBorrowed> bookBorroweds() {

        return this.booksBorrowedRepository.findAll();
    }
}
