package ru.romanov.moduletwo.dao;

import ru.romanov.moduletwo.domain.Question;

public interface QuestionDAO {

    /**
     * Метод возвращате количество вопросов теста.
     * @return количество ответов
     */
    int getQuestionsCount() throws Exception;

    /**
     * Метод возвращает вопрос для соответствующего идентификатора.
     * @param id идентификатор
     * @return вопрос
     */
    Question getQuestionById(int id) throws Exception;
}
