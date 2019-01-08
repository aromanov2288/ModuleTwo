package ru.romanov.moduletwo.dao;

import java.util.Locale;

public interface LocaleDao {

    /**
     * Метод сохраняет локаль приложения.
     * @param locale локаль
     */
    void saveLocale(Locale locale);

    /**
     * Метод возвращает локаль приложения.
     * @return
     */
    Locale getLocale();
}
