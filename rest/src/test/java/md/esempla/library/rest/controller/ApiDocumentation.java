package md.esempla.library.rest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.servlet.RequestDispatcher;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs(outputDir = "target/generated-snippets")
public class ApiDocumentation {

    private ObjectMapper objectMapper;
    private List<Author> authorList;
    private List<Client> clientsList;
    private List<Book> bookList;
    private List<BookBorrowed> bookBorrowedList;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AuthorsRepository authorsRepository;

    @Autowired
    private BooksRepository booksRepository;

    @Autowired
    private ClientsRepository clientsRepository;

    @Autowired
    private BooksBorrowedRepository booksBorrowedRepository;

    @Before
    public void setup() {
        objectMapper = new ObjectMapper();
        authorsRepository.save(new Author("Nicola", "Tesla", "031546253"));

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
        this.booksBorrowedRepository.deleteAllInBatch();
        this.clientsRepository.deleteAllInBatch();
        this.booksRepository.deleteAllInBatch();
        this.authorsRepository.deleteAll();
    }

    @Test
    public void errorExample() throws Exception {

        this.mockMvc
                .perform(
                        get("/error")
                                .requestAttr(RequestDispatcher.ERROR_STATUS_CODE, 400)
                                .requestAttr(RequestDispatcher.ERROR_REQUEST_URI, "/authors")
                                .requestAttr(RequestDispatcher.ERROR_MESSAGE,
                                        "The author 'http://localhost:8888/authors/123' does not exist"))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("error", is("Bad Request")))
                .andExpect(jsonPath("timestamp", is(notNullValue())))
                .andExpect(jsonPath("status", is(400)))
                .andExpect(jsonPath("path", is(notNullValue())))
                .andDo(document(
                        "error-example",
                        responseFields(fieldWithPath("error").description("The HTTP error that occurred, e.g. `Bad Request`"),
                                fieldWithPath("message").description("A description of the cause of the error"),
                                fieldWithPath("path").description("The path to which the request was made"),
                                fieldWithPath("status").description("The HTTP status code, e.g. `400`"),
                                fieldWithPath("timestamp")
                                        .description("The time, in milliseconds, at which the error occurred"))));
    }

    @Test
    public void getAuthors() throws Exception {

        this.mockMvc
                .perform(get("/api/authors").accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andDo(document("{class-name}/{method-name}",
                        responseFields(
                                fieldWithPath("[].id").description("author id"),
                                fieldWithPath("[].firstName").description("author first name"),
                                fieldWithPath("[].lastName").description("author last name"),
                                fieldWithPath("[].phone").description("author phone")
                        )));

    }

    @Test
    public void getAuthorById() throws Exception {

        this.mockMvc
                .perform(get("/api/authors/" + authorsRepository.findByFirstName("Nicola").getId())
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andDo(document("{class-name}/{method-name}",
                        responseFields(
                                fieldWithPath("id").description("author id"),
                                fieldWithPath("firstName").description("author first name"),
                                fieldWithPath("lastName").description("author last name"),
                                fieldWithPath("phone").description("author phone")
                        )));
    }

    @Test
    public void updateAuthor() throws Exception {
        String authorJson = objectMapper.writeValueAsString(authorsRepository.findByFirstName("Nicola"));
        this.mockMvc.perform(put("/api/authors").content(authorJson)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andDo(document("{class-name}/{method-name}",
                        requestFields(
                                fieldWithPath("id").description("author id"),
                                fieldWithPath("firstName").description("author first name"),
                                fieldWithPath("lastName").description("author last name"),
                                fieldWithPath("phone").description("author phone")
                        )));
    }

    @Test
    public void createAuthor() throws Exception {
            String authorJson = objectMapper.writeValueAsString(authorsRepository.findByFirstName("Nicola"));

            this.mockMvc.perform(post("/api/authors").content(authorJson)
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .accept(MediaType.APPLICATION_JSON_UTF8))
                    .andExpect(status().isCreated())
                    .andDo(document("{class-name}/{method-name}",
                            requestFields(
                                    fieldWithPath("id").description("author id"),
                                    fieldWithPath("firstName").description("author first name"),
                                    fieldWithPath("lastName").description("author last name"),
                                    fieldWithPath("phone").description("author phone")
                            )));
    }


    @Test
    public void createBorrowedBook() throws Exception {
        String bookJson = objectMapper.writeValueAsString(bookBorrowedList.get(0));

        this.mockMvc.perform(post("/api/booksborrowed")
                .content(bookJson)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isCreated())
                .andDo(document("{class-name}/{method-name}",
                        requestFields(
                                fieldWithPath("id").description("Operation date"),
                                fieldWithPath("date").description("Operation date"),
                                fieldWithPath("client").description("client id").type(JsonFieldType.OBJECT),
                                fieldWithPath("book").description("id of book borrowed").type(JsonFieldType.OBJECT)
                        )));
    }

    @Test
    public void updateBorrowedBook() throws Exception {
        String bookJson = objectMapper.writeValueAsString(bookBorrowedList.get(0));

        this.mockMvc.perform(put("/api/booksborrowed")
                .content(bookJson)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andDo(document("{class-name}/{method-name}",
                        requestFields(
                                fieldWithPath("id").description("Operation id"),
                                fieldWithPath("date").description("Operation date"),
                                fieldWithPath("client").description("client id").type(JsonFieldType.OBJECT),
                                fieldWithPath("book").description("id of book borrowed").type(JsonFieldType.OBJECT)
                        )));
    }

    @Test
    public void getBorrowedBooks() throws Exception {

        this.mockMvc.perform(get("/api/booksborrowed")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andDo(document("{class-name}/{method-name}",
                        responseFields(
                                fieldWithPath("[].id").description("Operation id"),
                                fieldWithPath("[].date").description("Operation date"),
                                fieldWithPath("[].client").description("client id").type(JsonFieldType.OBJECT),
                                fieldWithPath("[].book").description("id of book borrowed").type(JsonFieldType.OBJECT)
                        )));
    }

    @Test
    public void deleteBorrowedBooks() throws Exception {

        this.mockMvc.perform(get("/api/booksborrowed/" + bookBorrowedList.get(0).getId())
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andDo(document("{class-name}/{method-name}"));
    }
}