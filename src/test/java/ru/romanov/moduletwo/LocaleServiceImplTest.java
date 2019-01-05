package ru.romanov.moduletwo;

import org.junit.Before;
import org.junit.Test;
import ru.romanov.moduletwo.dao.LocaleDao;
import ru.romanov.moduletwo.serviece.LocaleServiceImpl;

import java.util.Locale;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

public class LocaleServiceImplTest {

    private LocaleServiceImpl localeService;
    private LocaleDao dao;
    private Locale locale;

    @Before
    public void init() {
        dao = mock(LocaleDao.class);
        locale = new Locale("ru");
        when(dao.getLocale()).thenReturn(locale);
        localeService = new LocaleServiceImpl(dao);
    }

    @Test
    public void saveLocaleTest() {
        localeService.saveLocale(locale);
        verify(dao).saveLocale(locale);
        verifyNoMoreInteractions(dao);
    }

    @Test
    public void getPersonTest() {
        assertEquals(localeService.getLocale(), locale);
    }
}