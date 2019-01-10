package ru.romanov.moduletwo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@ConfigurationProperties
public class YamlProps {

    private String fileName;
    private String availableLocales;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getAvailableLocales() {
        return availableLocales;
    }

    public void setAvailableLocales(String availableLocales) {
        this.availableLocales = availableLocales;
    }

    public Set<String> getLocalesSet() {
        String [] locales = availableLocales.split(", ");
        return new HashSet<>(Arrays.asList(locales));
    }
}

