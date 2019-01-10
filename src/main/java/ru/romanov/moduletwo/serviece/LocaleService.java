package ru.romanov.moduletwo.serviece;

import java.util.Locale;

public interface LocaleService {

    /**
     * Метод сохраняет локаль приложения.
     * @param localeCode код локали
     * @return сообщение с результатом
     */
    String changeLocale(String localeCode);

    /**
     * Метод возвращает локаль приложения.
     * @return
     */
    Locale getLocale();
}
