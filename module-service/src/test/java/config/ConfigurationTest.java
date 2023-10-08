package config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration
@ComponentScan(basePackages = {"com.mjc.school"})
@PropertySource("classpath:application-test.yaml")
public class ConfigurationTest {

//        @Bean
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
//        LocalContainerEntityManagerFactoryBean em
//                = new LocalContainerEntityManagerFactoryBean();
//        em.setDataSource(dataSource());
//        em.setPackagesToScan("com.mjc.school");
//        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//        em.setJpaVendorAdapter(vendorAdapter);
//        return em;
//    }
//
//    @Bean
//    public DataSource dataSource() {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName("org.hibernate.dialect.PostgreSQL82Dialect");
//        dataSource.setUrl("jdbc:postgresql://localhost:5432/news");
//        dataSource.setUsername("postgres");
//        dataSource.setPassword("admin");
//        return dataSource;
//    }
//
//    @Bean
//    public PlatformTransactionManager transactionManager() {
//        JpaTransactionManager transactionManager = new JpaTransactionManager();
//        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
//
//        return transactionManager;
//    }
}
