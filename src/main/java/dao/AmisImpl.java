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
            Amis a = new Amis(idUser, idAmi, 0);
            Amis a2 = new Amis(idAmi, idUser, 2);
            session.save(a);
            session.save(a2);
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

    public static void accepterAmis(int idUser, int idAmi) {
        Transaction tx = null;
        try (Session session = HibernateUtil.sessionFactory.openSession()) {
            tx = session.beginTransaction();
            Query query1 = session.createQuery("from  Amis where idUser=:iduser and idAmi=:idami");
            query1.setParameter("iduser", idUser);
            query1.setParameter("idami", idAmi);
            Query query2 = session.createQuery("from  Amis where idUser=:idami and idAmi=:iduser");
            query2.setParameter("iduser", idUser);
            query2.setParameter("idami", idAmi);
            Amis a1 = (Amis) query1.getResultList().get(0);
            a1.setStatus(1);
            Amis a2 = (Amis) query2.getResultList().get(0);
            a2.setStatus(1);
            session.update(a1);
            session.update(a2);
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

    public static void supressionAmis(int idUser, int idAmi) {
        Transaction tx = null;
        try (Session session = HibernateUtil.sessionFactory.openSession()) {
            tx = session.beginTransaction();
            Query query1 = session.createQuery("from  Amis where idUser=:iduser and idAmi=:idami");
            query1.setParameter("iduser", idUser);
            query1.setParameter("idami", idAmi);
            Query query2 = session.createQuery("from  Amis where idUser=:idami and idAmi=:iduser");
            query2.setParameter("iduser", idUser);
            query2.setParameter("idami", idAmi);
            session.delete(query1.getResultList().get(0));
            session.delete(query2.getResultList().get(0));
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

    public static void supressionTousAmis(int idUser) {
        Transaction tx = null;
        try (Session session = HibernateUtil.sessionFactory.openSession()) {
            tx = session.beginTransaction();
            Query query1 = session.createQuery("delete from  Amis where idUser=:iduser");
            Query query2 = session.createQuery("delete from  Amis where idAmi=:iduser");
            query1.setParameter("iduser", idUser);
            query2.setParameter("iduser", idUser);
            query1.executeUpdate();
            query2.executeUpdate();
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
