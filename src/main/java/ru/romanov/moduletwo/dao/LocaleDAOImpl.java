package ru.romanov.moduletwo.dao;

import org.springframework.stereotype.Repository;

import java.util.Locale;

@Repository
public class LocaleDAOImpl implements LocaleDao {

    private Locale locale;

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveLocale(Locale locale) {
        this.locale = locale;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Locale getLocale() {
        return locale;
    }
}
