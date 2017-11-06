package dao;

import beans.Amis;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utils.HibernateUtil;

import java.util.List;

public class AmisImpl {

    public static void ajoutAmi(int idUser, int idAmi) {
        Transaction tx = null;
        try (Session session = HibernateUtil.sessionFactory.openSession()) {
            tx = session.beginTransaction();
            Amis a = new Amis(idUser, idAmi);
            session.save(a);
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

    public static List rechercheAmis(int id) {
        try (Session session = HibernateUtil.sessionFactory.openSession()) {
            Query query = session.createQuery("FROM Amis WHERE idUser = :id");
            query.setParameter("id", id);
            return query.getResultList();
        }
    }
}
