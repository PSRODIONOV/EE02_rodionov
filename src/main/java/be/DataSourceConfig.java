package be;

import be.access.UserDAO;
import be.access.UserDAOImpl;
import org.flywaydb.core.Flyway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

/*
@Configuration
@EnableTransactionManagement

 */
public class DataSourceConfig {

    /*
    **
    Альтернатива application-context
     */

    public DataSourceConfig() {
        super();
    }

    /*@Primary
    @Bean(name = "entityManagerFactory")
    @DependsOn("flyway")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em
                = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan(new String[] { "com.accenture.flowershop.fe.entity" });

        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(additionalProperties());
        return em;
    }
    @Primary
    @Bean(name = "dataSource")
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUsername("sa");
        dataSource.setPassword("1234");
        dataSource.setUrl("jdbc:h2:~/flowershop");
        return dataSource;
    }
    @Primary
    @Bean(name = "transactionManager")
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager transactionManager
                = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(
                entityManagerFactory().getObject());
        return transactionManager;
    }

    @Bean(name = "flyway", initMethod = "migrate")
    public Flyway flyway(){
        Flyway flyway = new Flyway();
        flyway.setDataSource(dataSource());
        flyway.setLocations("dbscripts");
        flyway.setBaselineOnMigrate(false);
        flyway.setOutOfOrder(true);
        flyway.setSqlMigrationPrefix("v");
        flyway.setSqlMigrationSeparator("__");
        flyway.setPlaceholderPrefix("@{");
        flyway.setPlaceholderSuffix("}");
        return flyway;
    }

    @Bean(name = "dataSource")
    public JndiObjectFactoryBean jndiObjectFactoryBean() {
        JndiObjectFactoryBean bean = new JndiObjectFactoryBean();
        bean.setJndiName("jdbc/flowerShopDS");
        bean.setResourceRef(true);
        return bean;
    }


    @Bean(name = "userDAO")
    public UserDAO userDAO(){
        return new UserDAOImpl();
    }

    Properties additionalProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", "create");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");

        return properties;
    }

     */
}
