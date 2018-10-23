package com.test.designPattern.Decorator;

public abstract class Decorator implements Person{
    Person person;

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
