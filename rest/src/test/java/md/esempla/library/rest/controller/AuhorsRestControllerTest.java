package md.esempla.library.rest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import md.esempla.library.domain.Author;
import md.esempla.library.repository.AuthorsRepository;
import md.esempla.library.rest.RestApp;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.nio.charset.Charset;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RestApp.class)
@WebAppConfiguration
public class AuhorsRestControllerTest {

    private MockMvc mockMvc;

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    @Mock
    private AuhorsRestController authAuhorsRestController;

    @Autowired
    private AuthorsRepository authorsRepository;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(authAuhorsRestController).build();
        Author author = new Author(1, "Mihai", "Eminescu", "413545");
        authorsRepository.save(author);
    }

    @Test
    public void createAuthorTest() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Author author = new Author(1, "Mihai", "Eminescu", "413545");
        String json = mapper.writeValueAsString(author);
        mockMvc.perform(post("http://localhost:8080/api/authors")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isCreated());
    }

    @Test
    public void getByIdTest() throws Exception {
        mockMvc.perform(get("http://localhost:8080/api/authors/2"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.id"), Matchers.is("1"))
                .andExpect(jsonPath("$.*", Matchers.hasSize(0)));
    }

    @Test
    public void updateAuthorsTest() {

    }
}