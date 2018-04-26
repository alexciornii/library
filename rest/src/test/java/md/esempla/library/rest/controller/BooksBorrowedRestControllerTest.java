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
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BooksBorrowedRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper;
    private List<Author> authorList;
    private List<Client> clientsList;
    private List<Book> bookList;
    private List<BookBorrowed> bookBorrowedList;

    @Autowired
    private WebApplicationContext webApplicationContext;

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
        this.mockMvc = webAppContextSetup(this.webApplicationContext).build();

        objectMapper = new ObjectMapper();

        booksBorrowedRepository.deleteAllInBatch();
        booksRepository.deleteAllInBatch();
        authorsRepository.deleteAllInBatch();
        clientsRepository.deleteAllInBatch();

        authorList = new ArrayList<>();
        authorList.add(new Author("Mihai", "Eminescu", "1234578"));
        authorList.add(new Author("Ion", "Creanga", "7895413"));
        authorsRepository.save(authorList);

        bookList = new ArrayList<>();
        bookList.add(new Book("Harap-Alb", authorList.get(0)));
        bookList.add(new Book("Amintiri din copilarie", authorList.get(1)));
        booksRepository.save(bookList);

        clientsList = new ArrayList<>();
        clientsList.add(new Client("Ion", "Ciubotari", "ion.ciub@gmail.com", "+36548981231"));
        clientsList.add(new Client("Dumitru", "Jason", "dima.85@gmail.com", "+95451258156"));
        clientsRepository.save(clientsList);

        bookBorrowedList = new ArrayList<>();
        bookBorrowedList.add(new BookBorrowed(new Date(), clientsList.get(0), bookList.get(0)));
        bookBorrowedList.add(new BookBorrowed(new Date(), clientsList.get(1), bookList.get(1)));
        booksBorrowedRepository.save(bookBorrowedList);
    }

    @After
    public void testDown() {
        booksBorrowedRepository.deleteAll();
        booksRepository.deleteAll();
        authorsRepository.deleteAll();
        clientsRepository.deleteAll();
    }

    @Test
    public void createBookBorrowedTest() throws Exception {
        log.info("Testing create books borrowed...");

        Author author = new Author("Alexandr", "Ciornii", "123");
        authorsRepository.save(author);

        Book book = new Book("Otello", author);
        booksRepository.save(book);

        Client client = new Client("Vitalie", "Negru", "vital_negru@mail.com", "+376516854");
        clientsRepository.save(client);

        BookBorrowed bookBorrowed = new BookBorrowed(new Date(), client, book);

        String bookBorrowedJson = objectMapper.writeValueAsString(bookBorrowed);

        this.mockMvc.perform(post("/api/booksborrowed")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(bookBorrowedJson))
                .andExpect(status().isCreated());
    }

    @Test
    public void updateBookBorrowedTest() throws Exception {
        log.info("Testing update book borrowed...");

        BookBorrowed bookBorrowed = bookBorrowedList.get(0);
        bookBorrowed.setClient(clientsList.get(1));

        String bookBorrowedJson = objectMapper.writeValueAsString(bookBorrowed);
        mockMvc.perform(put("/api/booksborrowed")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(bookBorrowedJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.client").value(clientsList.get(1)));
    }

    @Test
    public void getBookBorrowedByIdTest() throws Exception {
        log.info("Testing request to get book borrowed by id...");

        mockMvc.perform(get("/api/booksborrowed/" + bookBorrowedList.get(0).getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(bookBorrowedList.get(0).getId()))
                .andExpect(jsonPath("$.date").value(bookBorrowedList.get(0).getDate()))
                .andExpect(jsonPath("$.book").value(bookBorrowedList.get(0).getBook()))
                .andExpect(jsonPath("$.client").value(bookBorrowedList.get(0).getClient()));
    }

    @Test
    public void getListOfBooksBorrowed() throws Exception {
        log.info("Testing request to get list of book borrowed...");

        mockMvc.perform(get("/api/booksborrowed"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(bookBorrowedList.get(0).getId()))
                .andExpect(jsonPath("$[0].date").value(bookBorrowedList.get(0).getDate()))
                .andExpect(jsonPath("$[0].client").value(bookBorrowedList.get(0).getClient()))
                .andExpect(jsonPath("$[0].book").value(bookBorrowedList.get(0).getBook()))
                .andExpect(jsonPath("$[1].id").value(bookBorrowedList.get(1).getId()))
                .andExpect(jsonPath("$[1].date").value(bookBorrowedList.get(1).getDate()))
                .andExpect(jsonPath("$[1].client").value(bookBorrowedList.get(1).getClient()))
                .andExpect(jsonPath("$[1].book").value(bookBorrowedList.get(1).getBook()));
    }
}