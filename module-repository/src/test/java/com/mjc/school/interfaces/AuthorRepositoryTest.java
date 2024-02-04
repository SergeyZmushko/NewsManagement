package com.mjc.school.interfaces;

import com.mjc.school.config.ConfigurationRepositoryTest;
import com.mjc.school.model.impl.AuthorModel;


import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.PostgreSQLContainer;

@SpringBootTest(classes = ConfigurationRepositoryTest.class)
@ExtendWith(SpringExtension.class)
@Import(AuthorRepository.class)
public class AuthorRepositoryTest {

    @Autowired
    private AuthorRepository authorRepository;

    @ClassRule
    public static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:13")
            .withDatabaseName("news-2")
            .withUsername("postgres")
            .withPassword("admin");

    @BeforeClass
    public static void setUp() {
        postgreSQLContainer.start();
    }

    @AfterClass
    public static void tearDown() {
        postgreSQLContainer.stop();
    }


    @Test
    public void addAuthorTest(){
        AuthorModel author = new AuthorModel();
        author.setName("Vladimir Bokov");
        AuthorModel authorModel = authorRepository.save(author);
        AuthorModel result = authorRepository.findByName(authorModel.getName()).orElse(null);

        Assertions.assertEquals("Vladimir Bokov", result.getName());

    }
}
