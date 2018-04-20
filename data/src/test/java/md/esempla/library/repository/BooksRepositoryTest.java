package md.esempla.library.repository;

import md.esempla.library.domain.Author;
import md.esempla.library.domain.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BooksRepositoryTest {

    @Autowired
    AuthorsRepository authorsRepository;

    @Autowired
    BooksRepository booksRepository;

    @Test
    public void book_whenSave_getOk() {
        Author author = new Author(1L, "Mihai", "Eminescu", "+37368454613");
        Book book = new Book(1L, "Luceafarul", author);
        authorsRepository.save(author);
        booksRepository.save(book);
        assertThat(booksRepository.findById(1L)).isEqualTo(book);
    }

}
