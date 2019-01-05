package ru.romanov.moduletwo.serviece;

import ru.romanov.moduletwo.domain.Person;

public interface PersonService {

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
