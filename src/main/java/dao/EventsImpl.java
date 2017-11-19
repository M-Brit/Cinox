package dao;

import beans.Event;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.List;
import utils.HibernateUtil;

public class EventsImpl {

    public static void addEvent(String movie, String cinema, String adress, String date, String time, int idUser, int status) {

        Transaction tx = null;
        try (Session session = HibernateUtil.sessionFactory.openSession()) {
            tx = session.beginTransaction();

            Event ev = new Event(movie, cinema, adress, date, time, idUser, status);
            session.save(ev);

            tx.commit();

        } catch (Exception e) {
            if (tx != null) {
                try {
                    tx.rollback();
                } catch (Exception e2) {
                    System.out.println("Rollback impossible !");
                }
            }
        }
    }

    public static List getEvents(int idUser) {

        try (Session session = HibernateUtil.sessionFactory.openSession()) {
            Query query = session.createQuery("FROM Event WHERE idUser = :idUser");
            query.setParameter("idUser", idUser);
            return query.getResultList();
        }
    }

    public static void acceptOrDecline(int idUser, int decision) {


    }
}
