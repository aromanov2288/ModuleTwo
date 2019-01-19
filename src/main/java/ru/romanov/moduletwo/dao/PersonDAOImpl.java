package ru.romanov.moduletwo.dao;

import org.springframework.stereotype.Repository;
import ru.romanov.moduletwo.domain.Person;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PersonDAOImpl implements PersonDAO {

    private List<Person> personList;

    private Person activePerson;

    public PersonDAOImpl() {
        this.personList = new ArrayList<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Person> getPersonList() {
        return personList;
    }

    /**
     * {@inheritDoc}
     */
    public Person getActivePerson() {
        return activePerson;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setActivePerson(Person person) {
        this.activePerson = person;
    }
}
