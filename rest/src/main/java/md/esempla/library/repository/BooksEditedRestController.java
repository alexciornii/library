package md.esempla.library.repository;

import md.esempla.library.domain.BookEdited;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class BooksEditedRestController {

    @Autowired
    BooksEditedRepository booksEditedRepository;

    @RequestMapping("/editedbooks/api")
    Collection<BookEdited> bookEditeds() {

        return this.booksEditedRepository.findAll();
    }
}
