package md.esempla.library.rest.controller;

import md.esempla.library.domain.Author;
import md.esempla.library.repository.AuthorsRepository;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.*;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AuhorsRestControllerTest {

    @Autowired
    private AuthorsRepository authorsRepository;

    @Test
    public void testCreate() throws ClientProtocolException, IOException {
        HttpUriRequest request = new HttpGet("http://localhost:8080/api/authors");

        HttpResponse response = HttpClientBuilder.create().build().execute(request);

        assertThat(response.getStatusLine().getStatusCode()).isEqualTo(HttpStatus.SC_OK);
    }

    @Test
    public void testGetById() throws IOException {
        List<Author> authors = authorsRepository.findAll();
        Random random = new Random();

        HttpUriRequest request = new HttpGet("http://localhost:8080/api/authors/" + random.nextInt(authors.size()));
        HttpResponse response = HttpClientBuilder.create().build().execute(request);

        assertThat(response.getStatusLine().getStatusCode()).isEqualTo(HttpStatus.SC_OK);
    }

    @Test
    public void testUpdate() {

    }
}