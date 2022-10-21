package models.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import models.JpaUtil.JpaUtil;
import models.classes.Mezzo;
import models.enums.Status;

public class MezzoDAO {

	private static final Logger logger = LoggerFactory.getLogger(MezzoDAO.class);

	public static Mezzo getById(int id) {

		EntityManagerFactory emf = JpaUtil.getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();

		try {

			Mezzo p = em.find(Mezzo.class, id);
			return p;

		} catch (Exception ex) {

			logger.error("Error", ex);
			throw ex;

		} finally {

			em.close();
		}

	}

	public static void delete(int id) {

		EntityManagerFactory emf = JpaUtil.getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();

		try {

			EntityTransaction t = em.getTransaction();

			Mezzo p = em.find(Mezzo.class, id);

			t.begin();
			em.remove(p);
			t.commit();

		} catch (Exception ex) {

			em.getTransaction().rollback();
			logger.error("Error", ex);
			throw ex;

		} finally {

			em.close();
		}

	}

	public static void refresh(Mezzo a) {

		EntityManagerFactory emf = JpaUtil.getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();

		try {

			em.refresh(a);

		} catch (Exception ex) {

			logger.error("Error", ex);
			throw ex;

		} finally {

			em.close();
		}

	}

	// MEZZI ATTIVI (METODO IN PIU')
	public static List<Mezzo> getActive(Status s) {

		EntityManagerFactory emf = JpaUtil.getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();

		try {

			Query q = em.createQuery("SELECT m FROM Mezzo m WHERE m.status = :s", Mezzo.class);

			q.setParameter("s", s);

			return q.getResultList();

		} catch (Exception ex) {

			logger.error("Error", ex);
			throw ex;

		} finally {
			em.close();
		}

	}

}
