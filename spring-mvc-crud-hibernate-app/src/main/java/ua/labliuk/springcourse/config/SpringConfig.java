package ua.labliuk.springcourse.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.Properties;


@Configuration
@ComponentScan("ua.labliuk.springcourse")
@EnableWebMvc
@EnableTransactionManagement
public class SpringConfig implements WebMvcConfigurer {

    private final ApplicationContext applicationContext;

    @Autowired
    public SpringConfig(ApplicationContext applicationContext){
        this.applicationContext = applicationContext;
    }

    @Bean
    public SpringResourceTemplateResolver templateResolver(){
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setApplicationContext(applicationContext);
        templateResolver.setPrefix("/WEB-INF/views/");
        templateResolver.setSuffix(".html");
        return templateResolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine(){
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        templateEngine.setEnableSpringELCompiler(true);
        return templateEngine;
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry){
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setTemplateEngine(templateEngine());
        registry.viewResolver(resolver);
    }

    @Bean
    public DataSource comboPooledDataSource(){
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        try {
            comboPooledDataSource.setDriverClass("com.mysql.cj.jdbc.Driver");
            //comboPooledDataSource.setJdbcUrl("jdbc:mysql://database-1.c6eboyvoj802.us-east-2.rds.amazonaws.com:3306/somedb?useSSL=false");
            comboPooledDataSource.setJdbcUrl("jdbc:mysql://usersdb-1.c6eboyvoj802.us-east-2.rds.amazonaws.com:3306/somedb?useSSL=false");
            //comboPooledDataSource.setJdbcUrl("jdbc:mysql://localhost:3306/somedb?useSSL=false");
            comboPooledDataSource.setUser("root");
            comboPooledDataSource.setPassword("admin1990");
            comboPooledDataSource.setInitialPoolSize(5);
            comboPooledDataSource.setMinPoolSize(5);
            comboPooledDataSource.setAcquireIncrement(5);
            comboPooledDataSource.setMaxPoolSize(20);
            comboPooledDataSource.setMaxStatements(100);
        }
        catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        return comboPooledDataSource;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory(){
        LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
        localSessionFactoryBean.setDataSource(comboPooledDataSource());
        localSessionFactoryBean.setPackagesToScan("ua.labliuk.springcourse");
        Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        hibernateProperties.setProperty("hibernate.show_sql", "true");
        localSessionFactoryBean.setHibernateProperties(hibernateProperties);

        return localSessionFactoryBean;
    }

    @Bean
    public HibernateTransactionManager  transactionManager(){
        HibernateTransactionManager hibernateTransactionManager = new HibernateTransactionManager();
        hibernateTransactionManager.setSessionFactory(sessionFactory().getObject());
        return hibernateTransactionManager;
    }
}
