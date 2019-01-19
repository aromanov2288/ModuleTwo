package ru.romanov.moduletwo.serviece;

public interface TestService {

    /**
     * Метод запускает тест
     * @return сообщение с результатом
     */
    String startTest();

    /**
     * Метод возвращает результат теста
     * @return сообщение с результатом
     */
    String getTestResult();
}
