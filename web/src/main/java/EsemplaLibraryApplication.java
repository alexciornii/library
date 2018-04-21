import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "md.esempla.library")
public class EsemplaLibraryApplication {

    public static void main(String[] args) {
        SpringApplication.run(EsemplaLibraryApplication.class, args);
    }
}
