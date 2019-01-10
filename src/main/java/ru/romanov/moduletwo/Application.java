package ru.romanov.moduletwo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import ru.romanov.moduletwo.config.YamlProps;

@SpringBootApplication
@EnableConfigurationProperties(YamlProps.class)
public class Application  {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

