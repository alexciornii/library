package md.esempla.library.rest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import md.esempla.library.domain.Author;
import md.esempla.library.repository.AuthorsRepository;
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

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RestApp.class)
@WebAppConfiguration
public class AuhorsRestControllerTest {

    private MockMvc mockMvc;

    private List<Author> authors = new ArrayList<>();

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private AuthorsRepository authorsRepository;

    @Before
    public void setup() throws Exception {
        mockMvc = webAppContextSetup(webApplicationContext).build();
        authorsRepository.deleteAllInBatch();
        authors.add(new Author("Mihail", "Eminescu", "413545"));
        authors.add(new Author("Ion", "Creanga", "46186125"));
        authorsRepository.save(authors);
    }

    @Test
    public void createAuthorTest() throws Exception {
        log.info("Testing authors create...");


        Author author = new Author( "Ion Luca", "Caragiale", "12123378568");
        String json = mapper.writeValueAsString(author);

        mockMvc.perform(post("/api/authors")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(json))
                .andExpect(status().isCreated());
    }

    @Test
    public void getByIdTest() throws Exception {
        log.info("Testing request to get author by id...");

        mockMvc.perform(get("/api/authors/" + authors.get(0).getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(authors.get(0).getId()))
                .andExpect(jsonPath("$.firstName").value(authors.get(0).getFirstName()))
                .andExpect(jsonPath("$.lastName").value(authors.get(0).getLastName()))
                .andExpect(jsonPath("$.phone").value(authors.get(0).getPhone()));
    }

    @Test
    public void updateAuthorsTest() throws Exception {
        log.info("Testing authors update...");

        Author author = authors.get(0);
        author.setFirstName("Gheorghe");

        String authorJson = mapper.writeValueAsString(author);
        mockMvc.perform(put("/api/authors")
                .contentType(contentType)
                .content(authorJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value(author.getFirstName()));
    }

    @Test
    public void deleteAuthorByIdTest() throws Exception {
        log.info("Testing delete authors by id...");

        mockMvc.perform(delete("/api/authors/" + authors.get(0).getId()))
                .andExpect(status().isOk());

        assertThat(authorsRepository.count()).isEqualTo(1);
    }

    @Test
    public void getAuthorsTest() throws Exception {
        log.info("Testing request to get list of authors...");

        mockMvc.perform(get("/api/authors"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id").value(authors.get(0).getId()))
                .andExpect(jsonPath("$[0].firstName").value(authors.get(0).getFirstName()))
                .andExpect(jsonPath("$[0].lastName").value(authors.get(0).getLastName()))
                .andExpect(jsonPath("$[0].phone").value(authors.get(0).getPhone()))
                .andExpect(jsonPath("$[1].id").value(authors.get(1).getId()))
                .andExpect(jsonPath("$[1].firstName").value(authors.get(1).getFirstName()))
                .andExpect(jsonPath("$[1].lastName").value(authors.get(1).getLastName()))
                .andExpect(jsonPath("$[1].phone").value(authors.get(1).getPhone()));
    }
}