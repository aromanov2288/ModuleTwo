package ru.romanov.moduletwo.serviece;

import ru.romanov.moduletwo.domain.Person;

public interface PersonService {

    /**
     * Метод регистрирует нового пользователя.
     * @param person данные пользователя
     * @return результат
     */
    boolean registerPerson(Person person);

    /**
     * Мето осуществляте вход пользователя.
     * @param person данные пользователя
     * @return результат
     */
    boolean loginPerson(Person person);

    /**
     * Метод сбрасывает активного пользователя.
     * @return сообщение с результатом
     */
    String logoutPerson();

    /**
     * Метод возвращает данные активно пользователя.
     * @return пользователь
     */
    Person getActivePerson();
}
