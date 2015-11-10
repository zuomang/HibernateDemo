package org.hibernate.tutorial;

import org.hibernate.Session;

import java.util.*;

import org.hibernate.tutorial.domain.Event;
import org.hibernate.tutorial.domain.Person;
import org.hibernate.tutorial.util.HibernateUtil;

public class EventManager {

    public static void main(String[] args) {
        EventManager mgr = new EventManager();
        if (args[0].equals("create")) {
//            mgr.createAndStoreEvent("1510 Upgrade Rehearsal", new Date());
            mgr.createAndStorePerson(20, "zuo", "mang");
        } else if (args[0].equals("list")) {
            List events = mgr.listEvents();
            for (int i = 0; i < events.size(); i++) {
                Event event = (Event) events.get(i);
                System.out.println(event);
            }
        } else if (args[0].equals("addpersontoevent")) {
            long eventId = 1L;
            long personId = 1L;
            mgr.addPersonToEvent(personId, eventId);
            System.out.println("Added person " + personId + " to event " + eventId);
        } else if (args[0].equals("addemailtoperson")) {
            long personId = 1L;
            mgr.addEmailToPerson(personId, "240207862@qq.com");
            System.out.println("Added email 240207862@qq.com to event " + personId);
        }
        HibernateUtil.getSessionFactory().close();
    }

    private long createAndStoreEvent(String title, Date theDate) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Event theEvent = new Event();
        theEvent.setTitle(title);
        theEvent.setDate(theDate);
        session.save(theEvent);

        session.getTransaction().commit();
        return theEvent.getId();
    }

    private long createAndStorePerson(int age, String firstname, String lastname) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Person person = new Person();
        person.setAge(age);
        person.setFirstname(firstname);
        person.setLastname(lastname);
        session.save(person);

        session.getTransaction().commit();
        return person.getId();
    }

    private void addEmailToPerson(Long personId, String emailAddress) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Person aPerson = (Person) session.load(Person.class, personId);
        // adding to the emailAddress collection might trigger a lazy load of the collection
        aPerson.getEmailAddresses().add(emailAddress);

        session.getTransaction().commit();
    }

    private List listEvents() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        List result = session.createQuery("from Event").list();
        session.getTransaction();
        return result;
    }

    private void addPersonToEvent(Long personId, Long eventId) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

//        Person person = (Person) session.load(Person.class, personId);
//        Event event = (Event) session.load(Event.class, eventId);
//        person.getEvents().add(event);

        Person person = (Person) session
                .createQuery("select p from Person p left join fetch p.events where p.id = :pid")
                .setParameter("pid", personId)
                .uniqueResult();
        Event event = (Event) session.load(Event.class, eventId);
        session.getTransaction().commit();

        person.getEvents().add(event);

        Session session1 = HibernateUtil.getSessionFactory().getCurrentSession();
        session1.beginTransaction();
        session1.update(person);
        session1.getTransaction().commit();
    }
}