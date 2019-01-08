package ru.romanov.moduletwo.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import ru.romanov.moduletwo.Presenter;
import ru.romanov.moduletwo.serviece.LocaleService;
import ru.romanov.moduletwo.serviece.PersonService;
import ru.romanov.moduletwo.serviece.QuestionService;
import ru.romanov.moduletwo.serviece.ResultService;

@Configuration
public class AppConfig {

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource =
                new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("bundle");
        messageSource.setDefaultEncoding("windows-1251");
        return messageSource;
    }

    @Bean
    public Presenter presenter(PersonService personService, QuestionService questionService,
                               ResultService resultService, LocaleService localeService,
                               MessageSource messageSource) {
        return new Presenter(personService, questionService, resultService, localeService, messageSource);
    }
}