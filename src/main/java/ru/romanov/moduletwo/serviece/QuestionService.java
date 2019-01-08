package ru.romanov.moduletwo.serviece;

import java.util.Map;

public interface QuestionService {

    /**
     * Метод возвращате количество вопросов теста.
     * @return количество ответов
     */
    int getQuestionsCount() throws Exception;

    /**
     * Метод возвращает текст вопроса для соответствующего иднетнификатора.
     * @param id идентификатор
     * @return текст вопроса
     */
    String getQuestionTextById(int id) throws Exception;

    /**
     * Метод возвращает варианты ответов для соответствующего идентификатора.
     * @param id идентификатор
     * @return варианты ответов
     */
    Map<String, String> getAnswersById(int id) throws Exception;
}
