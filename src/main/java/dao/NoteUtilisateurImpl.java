package dao;

import beans.Amis;
import beans.NoteUtilisateur;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utils.HibernateUtil;

import java.util.List;

public class NoteUtilisateurImpl {

    public static List getNote(int idUser, int idFilm) {
        try (Session session = HibernateUtil.sessionFactory.openSession()) {
            Query query = session.createQuery("FROM NoteUtilisateur WHERE idUser = :id1 AND idFilm = :id2");
            query.setParameter("id1", idUser);
            query.setParameter("id2", idFilm);
            return query.getResultList();
        }
    }

    public static void addNote(int idUser, int idFilm, int note) {
        Transaction tx = null;
        try (Session session = HibernateUtil.sessionFactory.openSession()) {
            tx = session.beginTransaction();
            Query query1 = session.createQuery("from  NoteUtilisateur where idUser=:iduser and idFilm=:idfilm");
            query1.setParameter("iduser", idUser);
            query1.setParameter("idfilm", idFilm);
            List l = query1.getResultList();
            if (l.isEmpty()) {
                NoteUtilisateur n = new NoteUtilisateur(idUser, idFilm, note);
                session.save(n);
            }else {
                NoteUtilisateur n = (NoteUtilisateur) l.get(0);
                n.setNote(note);
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


    public static List calculMoyenne(int idFilm) {
        try (Session session = HibernateUtil.sessionFactory.openSession()) {
            Query query = session.createQuery("FROM NoteUtilisateur WHERE idFilm = :id2");
            query.setParameter("id2", idFilm);
            return query.getResultList();
        }
    }
}
