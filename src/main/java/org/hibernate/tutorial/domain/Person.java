package org.hibernate.tutorial.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Mang on 15/11/7.
 */
public class Person {
    private Long id;
    private int age;
    private String firstname;
    private String lastname;
    private Set events = new HashSet();

    public Person() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Set getEvents() {
        return events;
    }

    public void setEvents(Set events) {
        this.events = events;
    }

    @Override
    public String toString() {
        return "[Person] id: " + id + "\tage: " + age + "\tfirstname: " + firstname + "\tlastname: " + lastname;
    }
}
