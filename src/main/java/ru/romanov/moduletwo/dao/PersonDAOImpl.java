package ru.romanov.moduletwo.dao;

import org.springframework.stereotype.Repository;
import ru.romanov.moduletwo.domain.Person;

@Repository
public class PersonDAOImpl implements PersonDAO {

    private Person person;

    /**
     * {@inheritDoc}
     */
    @Override
    public void savePerson(Person person) {
        this.person = person;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Person getPerson() {
        return person;
    }
}
