package ru.romanov.moduletwo.dao;

import ru.romanov.moduletwo.domain.Person;

public interface PersonDAO {

    /**
     * Метод сохраняет данные пользователя.
     * @param person данные пользователя
     */
    void savePerson(Person person);

    /**
     * Получить данные пользователя.
     * @return
     */
    Person getPerson();
}
