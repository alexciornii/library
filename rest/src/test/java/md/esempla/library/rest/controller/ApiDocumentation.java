package md.esempla.library.rest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import md.esempla.library.domain.Author;
import md.esempla.library.repository.AuthorsRepository;
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

import javax.servlet.RequestDispatcher;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.put;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs(outputDir = "target/generated-snippets")
public class ApiDocumentation {

    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AuthorsRepository authorsRepository;

    @Before
    public void setup() {
        objectMapper = new ObjectMapper();
        authorsRepository.save(new Author("Nicola", "Tesla", "031546253"));
    }

    @After
    public void testDown() {
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
                                        "The author 'http://localhost:8080/authors/123' does not exist"))
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
                .perform(get("/api/authors"))
                .andExpect(status().isOk())
                .andDo(document("{class-name}/{method-name}"));

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
}