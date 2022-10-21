package models.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import models.JpaUtil.JpaUtil;
import models.classes.Biglietto;
import models.classes.Bus;
import models.classes.Deposito;
import models.classes.Mezzo;

public class DepositoDAO {

	private static final Logger logger = LoggerFactory.getLogger(DepositoDAO.class);

	public static void save(Mezzo m, Deposito d) {

		EntityManagerFactory emf = JpaUtil.getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();

		try {

			EntityTransaction t = em.getTransaction();

			d.setMezzo(m);
			d.setStatusMezzo(m.getStatus());

			t.begin();

			em.persist(m);
			em.persist(d);

			t.commit();

		} catch (Exception ex) {

			em.getTransaction().rollback();
			logger.error("Error", ex);
			throw ex;

		} finally {

			em.close();
		}

	}

	public static void saveDeposito(Mezzo m, Deposito d) {

		EntityManagerFactory emf = JpaUtil.getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();

		try {

			EntityTransaction t = em.getTransaction();

			m.toggleStatus();

			Query q = em.createQuery("UPDATE Mezzo a SET status = :s WHERE a.id = :id");

			t.begin();

			q.setParameter("s", m.getStatus());
			q.setParameter("id", m.getIdMezzo());

			q.executeUpdate();

			d.setMezzo(m);
			d.setStatusMezzo(m.getStatus());

			em.persist(d);

			t.commit();

		} catch (Exception ex) {

			em.getTransaction().rollback();
			logger.error("Error", ex);
			throw ex;

		} finally {

			em.close();
		}

	}

	public static Deposito getById(int id) {

		EntityManagerFactory emf = JpaUtil.getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();

		try {

			Deposito p = em.find(Deposito.class, id);
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

			Deposito p = em.find(Deposito.class, id);

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

	public static void refresh(Deposito a) {

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

	public static List<Deposito> cercaStoricoMezzo(Mezzo m) {

		EntityManagerFactory emf = JpaUtil.getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();

		try {

			Query q = em.createQuery("SELECT d FROM Deposito d WHERE d.mezzo.idMezzo = :id");

			q.setParameter("id", m.getIdMezzo());

			List<Deposito> mezzo = q.getResultList();
			
			return mezzo;

		} catch (Exception ex) {

			logger.error("Error", ex);
			throw ex;

		} finally {

			em.close();
		}

	}

}
