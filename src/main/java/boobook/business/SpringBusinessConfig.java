package boobook.business;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.support.PersistenceAnnotationBeanPostProcessor;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackageClasses = SpringBusinessConfig.class)
@EnableTransactionManagement
@PropertySource("classpath:application.properties")
public class SpringBusinessConfig {
	/*
     * Définition de la source de données
     */
    @Bean
    public DataSource dataSource(//
            @Value("${datasource.driverName}") String driverName, //
            @Value("${datasource.url}") String url, //
            @Value("${datasource.username}") String user, //
            @Value("${datasource.password}") String password) {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driverName);
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        return dataSource;
    }

    /*
     * Construction de l'entityManagerFactory à partir de la source de données et du
     * choix d'hibernate. Cette configuration remplace le fichier persistence.xml
     */
    @Bean(name = "myData")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(//
            @Autowired DataSource ds) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(ds);
        em.setPackagesToScan(new String[] { "boobook.model" });
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        em.setBeanName("myData");
        // Configuration d'hibernate
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", "create-drop");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.HSQLDialect");
        properties.setProperty("hibernate.show_sql", "true");
        properties.setProperty("hibernate.format_sql", "true");
        em.setJpaProperties(properties);
        return em;
    }

    /*
     * Construction d'un gestionnaire de transaction en liaison avec l'usine à EM.
     */
    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);
        return transactionManager;
    }

    /*
     * Activer le traitement des annotations de gestion du contexte de persistence.
     */
    @Bean
    public PersistenceAnnotationBeanPostProcessor annotationProcessor() {
        return new PersistenceAnnotationBeanPostProcessor();
    }
    
    /*set the properties*/
    
    @Bean("messageSource")
	public ResourceBundleMessageSource messageSource() {
	    ResourceBundleMessageSource r = new ResourceBundleMessageSource();
	    r.setBasenames("/src/main/resources/person");
	    return r;
	}
}
