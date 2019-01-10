package ru.romanov.moduletwo.serviece;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.romanov.moduletwo.config.YamlProps;
import ru.romanov.moduletwo.dao.LocaleDao;

import java.util.Locale;
import java.util.Set;

@Service
public class LocaleServiceImpl implements LocaleService {

    private MessageSource ms;
    private LocaleDao dao;
    private YamlProps yamlProps;

    @Autowired
    public LocaleServiceImpl(MessageSource ms, LocaleDao dao, YamlProps yamlProps){
        this.ms = ms;
        this.dao = dao;
        this.yamlProps = yamlProps;
    }

    @Override
    public String changeLocale(String localeCode) {
        String message = null;
        Set<String> set = yamlProps.getLocalesSet();
        if (set.contains(localeCode)) {
            if (localeCode.equals("def")) {
                dao.saveLocale(Locale.getDefault());
            } else {
                dao.saveLocale(new Locale(localeCode));
            }
            Locale locale = dao.getLocale();
            message = ms.getMessage("Locale_changed_successfully", null, locale);
        } else {
            Locale locale = dao.getLocale();
            message = ms.getMessage("Locale_changing_is_impossible_message", null, locale);
        }
        return message;
    }

    @Override
    public Locale getLocale() {
        return dao.getLocale();
    }
}
