package md.esempla.library.repository;

import md.esempla.library.domain.Author;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthorsRepositoryTest {

    @Resource
    private AuthorsRepository authorsRepository;

    @Test
    public void author_whenSave_thenGetOk() {
        Author author = new Author(1L, "Mihai", "Eminescu", "+37368454613");

        authorsRepository.save(author);
        Author author2 = authorsRepository.findOne(1L);
        assertThat(author2.getFirstName()).contains("Mihai");
        System.out.println(author2);
    }
}
