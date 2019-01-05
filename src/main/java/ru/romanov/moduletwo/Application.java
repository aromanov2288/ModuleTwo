package ru.romanov.moduletwo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import ru.romanov.moduletwo.config.YamlProps;

@SpringBootApplication
@EnableConfigurationProperties(YamlProps.class)
public class Application implements CommandLineRunner {

    private Presenter presenter;

    @Autowired
    public Application(Presenter presenter) {
        this.presenter = presenter;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        presenter.startTest();
    }
}

