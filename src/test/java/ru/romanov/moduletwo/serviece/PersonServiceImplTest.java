package ru.romanov.moduletwo.serviece;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import ru.romanov.moduletwo.dao.PersonDAO;
import ru.romanov.moduletwo.dao.ResultDAO;
import ru.romanov.moduletwo.domain.Person;
import ru.romanov.moduletwo.domain.Result;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

public class PersonServiceImplTest {


    private PersonServiceImpl personService;
    private LocaleService localeService;
    private MessageSource ms;
    private PersonDAO personDAO;
    private List<Person> personList;

    @Before
    public void init() {
        localeService = mock(LocaleService.class);
        ms = new ReloadableResourceBundleMessageSource();
        ((ReloadableResourceBundleMessageSource)ms).setBasename("bundle");
        ((ReloadableResourceBundleMessageSource)ms).setDefaultEncoding("windows-1251");
        personDAO = mock(PersonDAO.class);
        personList = new ArrayList<>(Arrays.asList(new Person("User1", "pass1")));
        when(personDAO.getPersonList()).thenReturn(personList);
        personService = new PersonServiceImpl(localeService, ms, personDAO);
    }

    @Test
    public void successfulRegisterNewPersonTest() {
        Person newPerson = new Person("User2", "pass2");
        boolean result = personService.registerPerson(newPerson);
        verify(personDAO).getPersonList();
        verifyNoMoreInteractions(personDAO);
        Set<Person> personsSet = new HashSet<>(personList);
        assertTrue(personsSet.contains(newPerson));
        assertTrue(result);
    }

    @Test
    public void unsuccessfulRegisterNewPersonTest() {
        Person newPerson = new Person("User1", "pass3");
        boolean result = personService.registerPerson(newPerson);
        verify(personDAO).getPersonList();
        verifyNoMoreInteractions(personDAO);
        Set<Person> personsSet = new HashSet<>(personList);
        assertFalse(personsSet.contains(newPerson));
        assertFalse(result);
    }

    @Test
    public void successfulLoginTest() {
        Person person = new Person("User1", "pass1");
        boolean result = personService.loginPerson(person);
        verify(personDAO).getPersonList();
        verify(personDAO).setActivePerson(person);
        verifyNoMoreInteractions(personDAO);
        assertTrue(result);
    }

    @Test
    public void unsuccessfulLoginOneTest() {
        Person person = new Person("User1", "pass2");
        boolean result = personService.loginPerson(person);
        verify(personDAO).getPersonList();
        verifyNoMoreInteractions(personDAO);
        assertFalse(result);
    }

    @Test
    public void unsuccessfulLoginTwoTest() {
        Person person = new Person("User2", "pass1");
        boolean result = personService.loginPerson(person);
        verify(personDAO).getPersonList();
        verifyNoMoreInteractions(personDAO);
        assertFalse(result);
    }

    @Test
    public void logoutTest() {
        when(localeService.getLocale()).thenReturn(new Locale("ru"));
        String message = personService.logoutPerson();
        verify(personDAO).setActivePerson(null);
        verifyNoMoreInteractions(personDAO);
        assertEquals(message, "Вы успешно вышли.");
    }

    @Test
    public void getActivePersonTest() {
        Person person = new Person("User3", "pass3");
        when(personDAO.getActivePerson()).thenReturn(person);
        Person receivedPerson = personService.getActivePerson();
        verify(personDAO).getActivePerson();
        verifyNoMoreInteractions(personDAO);
        assertEquals(receivedPerson, person);
    }
}
