package md.esempla.library.repository;

import md.esempla.library.domain.Author;
import md.esempla.library.domain.Book;
import md.esempla.library.domain.BookEdited;
import md.esempla.library.domain.Publisher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BooksEditedRepositoryTest {

    @Autowired
    private AuthorsRepository authorsRepository;

    @Autowired
    private BooksRepository booksRepository;

    @Autowired
    private PublishersRepository publishersRepository;

    @Autowired
    private BooksEditedRepository booksEditedRepository;

    @Test
    public void bookEdited_whenSave_getOk() {
        createAuthors();
        createBooks();
        createPublishers();
        ArrayList<BookEdited> bookEditeds = new ArrayList<>();
        List<Book> books = booksRepository.findAll();
        bookEditeds.add(new BookEdited(books.get(0), publishersRepository.findOne(1L)));
        bookEditeds.add(new BookEdited(books.get(0), publishersRepository.findById(2L)));
        bookEditeds.add(new BookEdited(books.get(0), publishersRepository.findById(3L)));
        booksEditedRepository.save(bookEditeds);
        assertThat(booksEditedRepository.count()).isEqualTo(bookEditeds.size());
    }

    private void createAuthors() {
        ArrayList<Author> authors = new ArrayList<>(3);
        authors.add(new Author("Mihail", "Eminescu", "+64121364854"));
        authors.add(new Author("Mihail", "Eminescu", "+78945154521"));
        authors.add(new Author("Mihail", "Eminescu", "+85451158625"));
        authorsRepository.save(authors);
    }

    private void createBooks() {
        ArrayList<Book> books = new ArrayList<>();
        Random random = new Random();
        int sizeAuthorsRepository = (int) authorsRepository.count() - 1;
        List<Author> authors = authorsRepository.findAll();

        books.add(new Book("Cartea 1", authors.get(random.nextInt(sizeAuthorsRepository))));
        books.add(new Book("Cartea 2", authors.get(random.nextInt(sizeAuthorsRepository))));
        books.add(new Book("Cartea 3", authors.get(random.nextInt(sizeAuthorsRepository))));
        booksRepository.save(books);
    }

    private void createPublishers() {
        ArrayList<Publisher> publishers = new ArrayList<>(3);
        publishers.add(new Publisher("Editura ARC", "Chisinau", "123"));
        publishers.add(new Publisher("Editura Cartea", "Balti", "456"));
        publishers.add(new Publisher("Editura Povestea", "Tiraspol", "789"));
        publishersRepository.save(publishers);
    }
}