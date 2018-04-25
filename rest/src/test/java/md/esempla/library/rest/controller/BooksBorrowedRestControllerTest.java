package md.esempla.library.rest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import md.esempla.library.domain.Author;
import md.esempla.library.domain.Book;
import md.esempla.library.domain.BookBorrowed;
import md.esempla.library.domain.Client;
import md.esempla.library.repository.AuthorsRepository;
import md.esempla.library.repository.BooksBorrowedRepository;
import md.esempla.library.repository.BooksRepository;
import md.esempla.library.repository.ClientsRepository;
import md.esempla.library.rest.RestApp;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.util.Date;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RestApp.class)
@WebAppConfiguration
public class BooksBorrowedRestControllerTest {

    private MockMvc mockMvc;

    private WebApplicationContext webApplicationContext;
    private ObjectMapper objectMapper;
    private List<Author> authorList;
    private List<Client> clientsList;
    private List<Book> bookList;
    private List<BookBorrowed> bookBorrowedList;

    @Autowired
    private BooksBorrowedRepository booksBorrowedRepository;

    @Autowired
    private ClientsRepository clientsRepository;

    @Autowired
    private BooksRepository booksRepository;

    @Autowired
    private AuthorsRepository authorsRepository;

    @Before
    public void setup() {
        mockMvc = webAppContextSetup(webApplicationContext).build();

        objectMapper = new ObjectMapper();

        authorList.add(new Author("Mihai", "Eminescu", "1234578"));
        authorList.add(new Author("Ion", "Creanga", "7895413"));

        bookList.add(new Book("Harap-Alb", authorList.get(0)));
        bookList.add(new Book("Amintiri din copilarie", authorList.get(1)));

        clientsList.add(new Client("Ion", "Ciubotari", "ion.ciub@gmail.com", "+36548981231"));
        clientsList.add(new Client("Dumitru", "Jason", "dima.85@gmail.com", "+95451258156"));

        bookBorrowedList.add(new BookBorrowed(new Date(), clientsList.get(0), bookList.get(0)));
        bookBorrowedList.add(new BookBorrowed(new Date(), clientsList.get(1), bookList.get(1)));
    }

    @Test
    public void createBookBorrowedTest() throws Exception {
        log.info("Testing create books borrowed...");

        Book book = new Book("Otello", new Author("Alexandr", "Ciornii", "123"));
        BookBorrowed bookBorrowed = new BookBorrowed(new Date(),
                new Client("Vitalie",
                        "Negru", "vital_negru@mail.com", "+376516854"), book);

        String bookBorrowedJson = objectMapper.writeValueAsString(bookBorrowed);


        mockMvc.perform(post("/api/booksborrowed")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(bookBorrowedJson))
                .andExpect(status().isOk());
    }
}
