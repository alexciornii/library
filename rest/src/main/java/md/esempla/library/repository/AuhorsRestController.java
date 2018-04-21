package md.esempla.library.repository;

import md.esempla.library.domain.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class AuhorsRestController {

    @Autowired
    private AuthorsRepository authorsRepository;

    @RequestMapping("/authorsApi")
    Collection<Author> authors() {
        return this.authorsRepository.findAll();
    }
}
