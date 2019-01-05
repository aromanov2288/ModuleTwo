package ru.romanov.moduletwo.domain;

public class Person {

    private String name;

    private String patronName;

    public String getName() {
        return name;
    }

    public Person() {
    }

    public Person(String name, String patronName) {
        this.name = name;
        this.patronName = patronName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronName() {
        return patronName;
    }

    public void setPatronName(String patronName) {
        this.patronName = patronName;
    }
}
