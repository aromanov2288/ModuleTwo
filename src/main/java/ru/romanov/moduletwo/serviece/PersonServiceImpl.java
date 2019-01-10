package ru.romanov.moduletwo.serviece;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.romanov.moduletwo.dao.PersonDAO;
import ru.romanov.moduletwo.domain.Person;

import java.util.List;
import java.util.Locale;

@Service
public class PersonServiceImpl implements PersonService {

    private LocaleService localeService;
    private MessageSource ms;
    private PersonDAO personDAO;

    @Autowired
    public PersonServiceImpl(LocaleService localeService, MessageSource ms, PersonDAO personDAO) {
        this.localeService = localeService;
        this.ms = ms;
        this.personDAO = personDAO;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean registerPerson(Person person) {
        List<Person> personList = personDAO.getPersonList();
        for(Person personItem : personList) {
            if (personItem.getUserName().equals(person.getUserName())) {
                return false;
            }
        }
        personList.add(person);
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean loginPerson(Person person) {
        List<Person> personList = personDAO.getPersonList();
        for(Person personItem : personList) {
            if (personItem.getUserName().equals(person.getUserName())
                    && personItem.getPassword().equals(person.getPassword())) {
                personDAO.setActivePerson(person);
                return true;
            }
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String logoutPerson() {
        Locale locale = localeService.getLocale();
        personDAO.setActivePerson(null);
        return ms.getMessage("Logout_is_successful_message", null, locale);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Person getActivePerson() {
        return personDAO.getActivePerson();
    }
}
