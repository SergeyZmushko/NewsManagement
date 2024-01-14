import com.mjc.school.repository.interfaces.AuthorRepository;
import com.mjc.school.repository.model.impl.AuthorModel;

import config.ConfigurationRepositoryTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest(classes = ConfigurationRepositoryTest.class)
@ContextConfiguration(classes = AuthorRepository.class)
@TestPropertySource("classpath:application-test.yaml")
public class AuthorRepositoryTest {

    @Autowired
    private AuthorRepository authorRepository;


    @Test
    public void addAuthorTest(){
        AuthorModel author = new AuthorModel();
        author.setName("Vladimir Bokov");
        AuthorModel authorModel = authorRepository.save(author);
        AuthorModel result = authorRepository.findByName(authorModel.getName()).orElse(null);

        Assertions.assertEquals("Vladimir Bokov", result.getName());

    }
}
