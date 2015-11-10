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
//    使用set 原因: 1 set 不能包含重复元素, 2 无序
    private Set events = new HashSet();
    private Set emailAddresses = new HashSet();

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

    public Set getEmailAddresses() {
        return emailAddresses;
    }

    public void setEmailAddresses(Set emailAddresses) {
        this.emailAddresses = emailAddresses;
    }

    public void addToEvent(Event event) {
        this.getEvents().add(event);
        event.getParticipants().add(this);
    }

    public void removeFromEvent(Event event) {
        this.getEvents().remove(event);
        event.getParticipants().remove(this);
    }

    @Override
    public String toString() {
        return "[Person] id: " + id + "\tage: " + age + "\tfirstname: " + firstname + "\tlastname: " + lastname;
    }
}
