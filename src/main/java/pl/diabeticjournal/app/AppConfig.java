package pl.diabeticjournal.app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.LocaleContextResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import pl.diabeticjournal.controllers.UserController;
import pl.diabeticjournal.repository.UserRepository;
import javax.validation.Validator;
import java.util.Locale;

@Configuration
@EnableWebMvc
@ComponentScan(basePackageClasses = {UserController.class})
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
@EnableTransactionManagement
public class AppConfig implements WebMvcConfigurer {

    @Bean(name = "localeResolver")
    public LocaleContextResolver getLocaleContextResolver() {
        SessionLocaleResolver localeResolver = new SessionLocaleResolver();
        localeResolver.setDefaultLocale(new Locale("pl", "PL"));
        return localeResolver;
    }
    @Bean
    public Validator validator() {
        return new LocalValidatorFactoryBean();
    }
}
