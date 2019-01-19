package ru.romanov.moduletwo.serviece;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import ru.romanov.moduletwo.config.YamlProps;
import ru.romanov.moduletwo.dao.LocaleDao;

import java.util.Locale;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

public class LocaleServiceImplTest {

    private LocaleServiceImpl localeService;
    private MessageSource ms;
    private LocaleDao dao;
    private YamlProps yamlProps;
    private Locale locale;

    @Before
    public void init() {
        ms = new ReloadableResourceBundleMessageSource();
        ((ReloadableResourceBundleMessageSource)ms).setBasename("bundle");
        ((ReloadableResourceBundleMessageSource)ms).setDefaultEncoding("windows-1251");
        dao = mock(LocaleDao.class);
        yamlProps = new YamlProps();
        yamlProps.setAvailableLocales("def, en, ru");
        localeService = new LocaleServiceImpl(ms, dao, yamlProps);
    }

    @Test
    public void changeLocateDefTest() {
        locale = Locale.getDefault();
        when(dao.getLocale()).thenReturn(locale);
        String message = localeService.changeLocale("def");
        verify(dao).saveLocale(locale);
        verify(dao).getLocale();
        verifyNoMoreInteractions(dao);
        assertEquals(message, "Локаль успешно заменена.");
    }

    @Test
    public void changeLocateEnTest() {
        locale = new Locale("en");
        when(dao.getLocale()).thenReturn(locale);
        String message = localeService.changeLocale("en");
        verify(dao).saveLocale(locale);
        verify(dao).getLocale();
        verifyNoMoreInteractions(dao);
        assertEquals(message, "Locale changed successfully.");
    }

    @Test
    public void changeLocateRuTest() {
        locale = new Locale("ru");
        when(dao.getLocale()).thenReturn(locale);
        String message = localeService.changeLocale("ru");
        verify(dao).saveLocale(locale);
        verify(dao).getLocale();
        verifyNoMoreInteractions(dao);
        assertEquals(message, "Локаль успешно заменена.");
    }

    @Test
    public void changeLocateErrorTest() {
        String message = localeService.changeLocale("abc");
        verify(dao).getLocale();
        verifyNoMoreInteractions(dao);
        assertEquals(message, "Смена локали не возможна. Данная локаль не поддерживается.");
    }

    @Test
    public void getLocaleTest() {
        locale = new Locale("ru");
        when(dao.getLocale()).thenReturn(locale);
        Locale receivedLocale = localeService.getLocale();
        verify(dao).getLocale();
        verifyNoMoreInteractions(dao);
        assertEquals(receivedLocale, locale);
    }
}
