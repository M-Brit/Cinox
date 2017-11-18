package dao;

import beans.NoteMoyenne;
import beans.NoteUtilisateur;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utils.HibernateUtil;

import java.util.List;

public class NoteMoyenneImpl {

    public static float getMoyenne (int idfilm) {
        try (Session session = HibernateUtil.sessionFactory.openSession()) {
            Query query = session.createQuery("FROM NoteMoyenne WHERE idFilm = :id1");
            query.setParameter("id1", idfilm);
            List l = query.getResultList();
            if (l.isEmpty()) {
                return -1;
            } else {
                return ((NoteMoyenne)l.get(0)).getNote();
            }
        }
    }

    public static void addMoyenne(int idFilm, float moyenne) {
        Transaction tx = null;
        try (Session session = HibernateUtil.sessionFactory.openSession()) {
            tx = session.beginTransaction();
            Query query1 = session.createQuery("from  NoteMoyenne where idFilm=:id");
            query1.setParameter("id", idFilm);
            List l = query1.getResultList();
            if (l.isEmpty()) {
                NoteMoyenne n = new NoteMoyenne(idFilm, moyenne);
                session.save(n);
            }else {
                NoteMoyenne n = (NoteMoyenne) l.get(0);
                n.setNote(moyenne);
                session.update(n);
            }
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

}
