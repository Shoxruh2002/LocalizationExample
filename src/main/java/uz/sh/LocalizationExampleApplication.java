package uz.sh;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@OpenAPIDefinition
@SpringBootApplication
public class LocalizationExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(LocalizationExampleApplication.class, args);
    }


    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
        sessionLocaleResolver.setDefaultLocale(Locale.US);
        return sessionLocaleResolver;
    }

    @Bean
    public ResourceBundleMessageSource resourceBundleMessageSource() {
        ResourceBundleMessageSource resourceBundleMessageSource = new ResourceBundleMessageSource();
        resourceBundleMessageSource.setUseCodeAsDefaultMessage(true);
        resourceBundleMessageSource.setBasename("messages");
        return resourceBundleMessageSource;
    }

}

@RestController
@RequestMapping( "/hello/world" )
class Controller {

    @Autowired
    MessageSource messageSource;

    @GetMapping( "/hello-header" )
    public String hello(@RequestHeader( required = false, name = "Accept-Language" ) Locale locale) {
        return messageSource.getMessage("hello.world", null, locale);
    }

    @GetMapping( "/hello-param" )
    public String helloparam(@RequestParam( "locale" ) Locale locale) {
        return messageSource.getMessage("hello.world", null, locale);
    }

}

