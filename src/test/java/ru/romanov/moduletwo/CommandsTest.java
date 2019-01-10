package ru.romanov.moduletwo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.shell.CommandNotCurrentlyAvailable;
import org.springframework.shell.Shell;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = {
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"
})
public class CommandsTest {

    @Autowired
    private Shell shell;

    @Test
    public void registerUserSuccessfulTest() {
        Object message = shell.evaluate(() -> "reg User1 pass1");
        assertEquals(message, "Пользователь успешно зарегистрирован.");
    }

    @Test
    public void registerUserUnsuccessfulTest() {
        Object message = shell.evaluate(() -> "reg User1 pass2");
        assertEquals(message, "Регистрация не возможна. Пользователь с таким именем уже существует.");
    }

    @Test
    public void loginUnsuccessfulTest() {
        Object message = shell.evaluate(() -> "login User2 pass2");
        assertEquals(message, "Не верная пара логин/пароль.");
    }

    @Test
    public void logoutSuccessfulTest() {
        shell.evaluate(() -> "login User1 pass1");
        Object message = shell.evaluate(() -> "logout");
        assertEquals(message, "Вы успешно вышли.");
        shell.evaluate(() -> "logout");
    }

    @Test
    public void localeChangeSuccessfulTest() {
        Object message = shell.evaluate(() -> "locale en");
        assertEquals(message, "Locale changed successfully.");
        shell.evaluate(() -> "locale ru");
    }

    @Test
    public void localeChangeUnsuccessfulTest() {
        Object message = shell.evaluate(() -> "locale by");
        assertEquals(message, "Смена локали не возможна. Данная локаль не поддерживается.");
    }



    @Test
    public void logoutImpossibleTest() {
        Object result = shell.evaluate(() -> "logout");
        assertTrue(result instanceof CommandNotCurrentlyAvailable);
        String message = ((CommandNotCurrentlyAvailable) result).getAvailability().getReason();
        assertEquals(message, "Необходимо сначала залогиниться.");
    }

    @Test
    public void registerImpossibleTest() {
        shell.evaluate(() -> "login User1 pass1");
        Object result = shell.evaluate(() -> "reg User2 pass2");
        assertTrue(result instanceof CommandNotCurrentlyAvailable);
        String message = ((CommandNotCurrentlyAvailable) result).getAvailability().getReason();
        assertEquals(message, "Необходимо сначала разлогиниться.");
        shell.evaluate(() -> "logout");
    }

    @Test
    public void loginImpossibleTest() {
        shell.evaluate(() -> "login User1 pass1");
        Object result = shell.evaluate(() -> "login User1 pass1");
        assertTrue(result instanceof CommandNotCurrentlyAvailable);
        String message = ((CommandNotCurrentlyAvailable) result).getAvailability().getReason();
        assertEquals(message, "Необходимо сначала разлогиниться.");
        shell.evaluate(() -> "logout");
    }

    @Test
    public void startImpossibleTest() {
        Object result = shell.evaluate(() -> "start");
        assertTrue(result instanceof CommandNotCurrentlyAvailable);
        String message = ((CommandNotCurrentlyAvailable) result).getAvailability().getReason();
        assertEquals(message, "Необходимо сначала залогиниться.");
    }
}
