package ru.romanov.moduletwo.dao;

import java.util.Map;

public interface ResultDAO {

    /**
     * Метод сохраняет ответ пользователя на соответствующий вопрос.
     * @param id идентификатор вопроса
     * @param answer ответ
     */
    void saveAnswer(int id, String answer);

    /**
     * Метод возвращает результы ответов пользователя на все вопросы.
     * @return результаты ответов
     */
    Map<Integer, String> getAnswers();
}
