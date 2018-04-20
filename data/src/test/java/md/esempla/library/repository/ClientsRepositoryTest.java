package md.esempla.library.repository;

import md.esempla.library.domain.Client;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClientsRepositoryTest {

    @Autowired
    private ClientsRepository clientsRepository;

    @Test
    public void clients_whenSave_getOk() {
        HashSet<Client> clients= new HashSet<>();
        clients.add(new Client("alek.bodwin@gmail.com", "Alk", "Boldwin", "+45153684351"));
        clients.add(new Client("jim.root@gmail.com", "Jim", "Root", "+456189844"));
        clients.add(new Client("James", "Carton", "james.carton@gmail.com", "+7489185546"));

        clientsRepository.save(clients);

        System.out.println("clientsRepository.count=" + clientsRepository.count());
        System.out.println("clients.size=" + clients.size());
        assertThat(clientsRepository.count()).isEqualTo(clients.size());
    }
}
