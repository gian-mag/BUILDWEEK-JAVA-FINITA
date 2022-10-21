package models.DAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import models.JpaUtil.JpaUtil;
import models.classes.Tratta;

public class TrattaDAO {

	private static final Logger logger = LoggerFactory.getLogger(TrattaDAO.class);

	public static void save(Tratta a) {

		EntityManagerFactory emf = JpaUtil.getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();

		try {

			EntityTransaction t = em.getTransaction();

			t.begin();
			em.persist(a);
			t.commit();

		} catch (Exception ex) {

			em.getTransaction().rollback();
			logger.error("Error", ex);
			throw ex;

		} finally {

			em.close();
		}

	}

	public static Tratta getById(int id) {

		EntityManagerFactory emf = JpaUtil.getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();

		try {

			Tratta p = em.find(Tratta.class, id);
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

			Tratta p = em.find(Tratta.class, id);

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

	public static void refresh(Tratta a) {

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

}
