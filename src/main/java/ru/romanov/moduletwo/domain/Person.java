package ru.romanov.moduletwo.domain;

import java.util.Objects;

public class Person {

    private String userName;

    private String password;

    public Person() {
    }

    public Person(String name, String patronName) {
        this.userName = name;
        this.password = patronName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(userName, person.userName) &&
                Objects.equals(password, person.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, password);
    }
}
