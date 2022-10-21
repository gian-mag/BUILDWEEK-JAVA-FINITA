package models.DAO;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import models.JpaUtil.JpaUtil;
import models.classes.Abbonamento;
import models.classes.Biglietto;
import models.classes.Mezzo;
import models.classes.Tessera;

public class AbbonamentoDAO {

	private static final Logger logger = LoggerFactory.getLogger(AbbonamentoDAO.class);

	public static void save(Abbonamento a) {

		EntityManagerFactory emf = JpaUtil.getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();

		try {

			EntityTransaction t = em.getTransaction();
			t.begin();
			em.persist(a);
			t.commit();

		} catch (Exception ex) {
			em.getTransaction().rollback();
			logger.error("Error saving object: " + a.getClass().getSimpleName(), ex);
			throw ex;
		} finally {
			em.close();
		}

	}

	public static Abbonamento getById(int id) {

		EntityManagerFactory emf = JpaUtil.getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();

		try {

			Abbonamento p = em.find(Abbonamento.class, id);
			return p;

		} catch (Exception ex) {

			logger.error("Error saving object", ex);
			
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
			Abbonamento p = em.find(Abbonamento.class, id);
			t.begin();
			em.remove(p);
			t.commit();

		} catch (Exception ex) {
			em.getTransaction().rollback();
			logger.error("Error saving object", ex);
			throw ex;
		} finally {
			em.close();
		}

	}

	public static void refresh(Abbonamento a) {

		EntityManagerFactory emf = JpaUtil.getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();
		try {

			em.refresh(a);
		} finally {
			em.close();
		}

	}

	public static void abbonamentoVidima(Biglietto b, Mezzo m) {

		EntityManagerFactory emf = JpaUtil.getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();

		try {

			EntityTransaction t = em.getTransaction();
			Set<Mezzo> mezzi = new HashSet<>();
			mezzi.add(m);
			b.setMezzo(mezzi);

			Query query = em.createNativeQuery(
					"INSERT INTO mezzo_biglietto (mezzo_id_mezzo, biglietti_idbiglietto) VALUES (:d, :b)");

			t.begin();

			query.setParameter("d", m.getIdMezzo());
			query.setParameter("b", b.getIdBiglietto());

			t.commit();

		} catch (Exception ex) {

			em.getTransaction().rollback();
			logger.error("Error saving object", ex);
			throw ex;

		} finally {
			em.close();
		}

	}

	public static List<Abbonamento> cercaAbbonamentoPerTessera(int t) {

		EntityManagerFactory emf = JpaUtil.getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();

		try {
			
			Query q = em.createQuery(
					"SELECT b FROM Biglietto b WHERE b.tessera.id = :tessera");
			
			q.setParameter("tessera", t);
			
			List<Biglietto> r = q.getResultList();
			
			List<Abbonamento> list = new ArrayList();
			
			for(Biglietto p : r) {
				list.add((Abbonamento) p);
			}
			
			return list;

		} catch (Exception ex) {
			logger.error("Error saving object", ex);
			throw ex;
		} finally {
			em.close();
		}

	}

}
