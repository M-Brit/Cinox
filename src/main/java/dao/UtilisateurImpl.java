package dao;

import beans.Utilisateur;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.mindrot.jbcrypt.BCrypt;
import utils.HibernateUtil;

import java.util.List;

public class UtilisateurImpl {

    public static void ajoutUtilisateur(String nom, String prenom, String pseudo, String motdepasse, String email) {
        Transaction tx = null;
        try (Session session = HibernateUtil.sessionFactory.openSession()) {
            tx = session.beginTransaction();
            String nvmotdepasse = BCrypt.hashpw(motdepasse, BCrypt.gensalt(12));
            Utilisateur u = new Utilisateur(nom, prenom, pseudo, nvmotdepasse, email);
            session.save(u);
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

    public static List rechercheUtilisateurs(String pseudo) {
        try (Session session = HibernateUtil.sessionFactory.openSession()) {
            Query query = session.createQuery("FROM Utilisateur WHERE pseudo = :pseudo");
            query.setParameter("pseudo", pseudo);
            return query.getResultList();
        }
    }

    public static List rechercheUtilisateurs(int id) {
        try (Session session = HibernateUtil.sessionFactory.openSession()) {
            Query query = session.createQuery("FROM Utilisateur WHERE id = :id");
            query.setParameter("id", id);
            return query.getResultList();
        }
    }

    public static List rechercheEmail(String email) {
        try (Session session = HibernateUtil.sessionFactory.openSession()) {
            Query query = session.createQuery("FROM Utilisateur WHERE email = :email");
            query.setParameter("email", email);
            return query.getResultList();
        }
    }

    public static void supressionUtilisateur(int idUser) {
        Transaction tx = null;
        try (Session session = HibernateUtil.sessionFactory.openSession()) {
            tx = session.beginTransaction();
            Query query1 = session.createQuery("delete from  Utilisateur where id=:iduser");
            query1.setParameter("iduser", idUser);
            query1.executeUpdate();
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
