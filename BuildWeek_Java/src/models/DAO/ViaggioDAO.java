package models.DAO;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import models.JpaUtil.JpaUtil;
import models.classes.Viaggio;

public class ViaggioDAO {

	private static final Logger logger = LoggerFactory.getLogger(ViaggioDAO.class);

	public static void save(Viaggio a) {

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

	public static Viaggio getById(int id) {

		EntityManagerFactory emf = JpaUtil.getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();

		try {

			Viaggio p = em.find(Viaggio.class, id);
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

			Viaggio p = em.find(Viaggio.class, id);

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

	public static void refresh(Viaggio a) {

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

	public static void termineViaggio(Viaggio a) {

		EntityManagerFactory emf = JpaUtil.getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();

		try {

			EntityTransaction t = em.getTransaction();

			a.setOrarioArrivo(LocalDateTime.now());

			Query q = em.createQuery("UPDATE Viaggio a SET orarioArrivo = :s WHERE a.id = :id");

			t.begin();

			q.setParameter("s", a.getOrarioArrivo());
			q.setParameter("id", a.getIdViaggio());

			q.executeUpdate();
			t.commit();

		} catch (Exception ex) {

			em.getTransaction().rollback();
			logger.error("Error", ex);
			throw ex;

		} finally {

			em.close();
		}

	}

	public static int percorrenze(int idViaggio, int idTratta) {

		EntityManagerFactory emf = JpaUtil.getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();

		try {

			Query q = em.createQuery("SELECT v FROM Viaggio v WHERE v.Viaggio.idViaggio = :m AND v.tratta.id = :t",
					Viaggio.class);
			q.setParameter("m", idViaggio);
			q.setParameter("t", idTratta);

			List<Viaggio> list = q.getResultList();
			return list.size();

		} catch (Exception ex) {

			logger.error("Error", ex);
			throw ex;

		} finally {
			em.close();
		}

	}

}
