package ru.romanov.moduletwo.serviece;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.romanov.moduletwo.dao.LocaleDao;

import java.util.Locale;

@Service
public class LocaleServiceImpl implements LocaleService {

    private LocaleDao dao;

    @Autowired
    public LocaleServiceImpl(LocaleDao dao){
        this.dao = dao;
    }

    @Override
    public void saveLocale(Locale locale) {
        dao.saveLocale(locale);
    }

    @Override
    public Locale getLocale() {
        return dao.getLocale();
    }
}
