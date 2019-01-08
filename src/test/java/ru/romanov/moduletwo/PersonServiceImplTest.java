package ru.romanov.moduletwo;

import org.junit.Before;
import org.junit.Test;
import ru.romanov.moduletwo.dao.PersonDAO;
import ru.romanov.moduletwo.domain.Person;
import ru.romanov.moduletwo.serviece.PersonServiceImpl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

public class PersonServiceImplTest {

    private PersonServiceImpl personService;
    private PersonDAO dao;
    private Person person;

    @Before
    public void init() {
        dao = mock(PersonDAO.class);
        person = new Person("Иван", "Петров");
        when(dao.getPerson()).thenReturn(person);
        personService = new PersonServiceImpl(dao);
    }

    @Test
    public void savePersonTest() {
        personService.savePerson(person);
        verify(dao).savePerson(person);
        verifyNoMoreInteractions(dao);
    }

    @Test
    public void getPersonTest() {
        assertEquals(personService.getPerson(), person);
    }
}