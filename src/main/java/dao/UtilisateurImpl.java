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
                tx.rollback();
            }
        }
    }

    public static List rechercheUtilisateurs(String nom) {
        try (Session session = HibernateUtil.sessionFactory.openSession()) {
            Query query = session.createQuery("FROM Utilisateur WHERE pseudo = :nom");
            query.setParameter("nom", nom);
            return query.getResultList();
        }
    }
}
