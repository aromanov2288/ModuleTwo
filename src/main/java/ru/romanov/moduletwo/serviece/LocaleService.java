package ru.romanov.moduletwo.serviece;

import java.util.Locale;

public interface LocaleService {

    /**
     * Метод сохраняет локаль приложения.
     * @param locale
     */
    void saveLocale(Locale locale);

    /**
     * Метод возвращает локаль приложения.
     * @return
     */
    Locale getLocale();
}
