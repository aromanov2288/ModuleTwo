package ru.romanov.moduletwo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;
import ru.romanov.moduletwo.domain.Person;
import ru.romanov.moduletwo.serviece.LocaleService;
import ru.romanov.moduletwo.serviece.PersonService;
import ru.romanov.moduletwo.serviece.TestService;

import java.util.Locale;

@ShellComponent
public class Commands {

    private LocaleService localeService;
    private MessageSource ms;
    private PersonService personService;
    private TestService testService;
    private boolean loginned;
    private boolean testCompleted;

    @Autowired
    public Commands(LocaleService localeService, MessageSource ms,
                    PersonService personService, TestService testService) {
        this.localeService = localeService;
        this.ms = ms;
        this.personService = personService;
        this.testService = testService;
    }

    @ShellMethodAvailability({"logout"})
    public Availability loginCheck() {
        Locale locale = localeService.getLocale();
        String message = ms.getMessage("You_must_first_login", null, locale);
        return loginned ? Availability.available() : Availability.unavailable(message);
    }

    @ShellMethodAvailability({"reg", "login"})
    public Availability logoutCheck() {
        Locale locale = localeService.getLocale();
        String message = ms.getMessage("You_must_first_logout", null, locale);
        return (!loginned) ? Availability.available() : Availability.unavailable(message);
    }

    @ShellMethodAvailability({"result"})
    public Availability testCompletedCheck() {
        Locale locale = localeService.getLocale();
        String message = ms.getMessage("You_must_first_pass_the_test", null, locale);
        return testCompleted ? Availability.available() : Availability.unavailable(message);
    }

    @ShellMethodAvailability({"start"})
    public Availability testNotCompletedCheck() {
        Availability availability = null;
        Locale locale = localeService.getLocale();
        String message;
        if (loginned && !testCompleted) {
            availability = Availability.available();
        }
        if (!loginned) {
            message = ms.getMessage("You_must_first_login", null, locale);
            availability = Availability.unavailable(message);
        }
        if (testCompleted) {
            message = ms.getMessage("The_test_has_already_passed", null, locale);
            availability = Availability.unavailable(message);
        }
        return availability;
    }

    @ShellMethod(value = "New user registration.", key = "reg")
    public String registerUser(@ShellOption String userName, @ShellOption String password) {
        Locale locale = localeService.getLocale();
        if (personService.registerPerson(new Person(userName, password))) {
            return ms.getMessage("Successful_registration_message", null, locale);
        } else {
            return ms.getMessage("Unsuccessful_registration_message", null, locale);
        }
    }

    @ShellMethod(value = "User login.")
    public String login(@ShellOption String userName, @ShellOption String password) {
        Locale locale = localeService.getLocale();
        if (personService.loginPerson(new Person(userName, password))) {
            loginned = true;
            return ms.getMessage("Successful_login_message", null, locale);
        } else {
            return ms.getMessage("Unsuccessful_login_message", null, locale);
        }
    }

    @ShellMethod(value = "User logout")
    public String logout() {
        String message = personService.logoutPerson();
        loginned = false;
        testCompleted = false;
        return message;
    }

    @ShellMethod(value = "Locale changing.", key = "locale")
    public String changeLocale(@ShellOption String localeCode) {
        return localeService.changeLocale(localeCode);
    }

    @ShellMethod(value = "Start testing.", key = "start")
    public String startTest() {
        String message = testService.startTest();
        testCompleted = true;
        return message;
    }

    @ShellMethod(value = "Getting test result.", key = "result")
    public String getTestResult() {
        return testService.getTestResult();
    }
}
