package ru.romanov.moduletwo.dao;

import ru.romanov.moduletwo.domain.Person;

import java.util.List;

public interface PersonDAO {

    /**
     * Метод возвращает список зарегистрированных пользователей.
     * @return список пользователей
     */
    List<Person> getPersonList();

    /**
     * Метод возвращает активного пользователя.
     * @return пользователь.
     */
    Person getActivePerson();

    /**
     * Метод устанавливает активного пользователя.
     * @param person пользователь
     */
    void setActivePerson(Person person);
}
