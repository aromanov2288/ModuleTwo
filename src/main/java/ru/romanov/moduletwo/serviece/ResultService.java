package ru.romanov.moduletwo.serviece;

public interface ResultService {

    /**
     * Метод сохраняет ответ пользователя на соответствующий вопрос.
     * @param id идентификатор вопроса
     * @param answer ответ
     */
    void saveAnswer(int id, String answer);

    /**
     * Метод возвращает количество правильных ответов на вопросы.
     * @return количество правильных ответов
     */
    int getRightAnswersCount() throws Exception;

    /**
     * Метод возвращает сроку с не верными ответами пользователя.
     * @return строка с ответами
     */
    String getWrongAnswersString() throws Exception;
}
