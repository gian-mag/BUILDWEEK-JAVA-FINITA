package models.DAO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import models.JpaUtil.JpaUtil;
import models.classes.Biglietto;
import models.classes.Bus;
import models.classes.Mezzo;
import models.classes.Rivenditore;
import models.enums.Vidima;

public class BigliettoDAO {

	private static final Logger logger = LoggerFactory.getLogger(BigliettoDAO.class);

	public static void save(Biglietto a) {

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

	public static Biglietto getById(int id) {

		EntityManagerFactory emf = JpaUtil.getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();

		try {

			Biglietto p = em.find(Biglietto.class, id);
			return p;

		} catch (Exception ex) {

			logger.error("Error getting object", ex);
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
			Biglietto p = em.find(Biglietto.class, id);
			t.begin();
			em.remove(p);
			t.commit();

		} catch (Exception ex) {

			logger.error("Error deleting object", ex);
			throw ex;

		} finally {

			em.close();
		}

	}

	public static void refresh(Biglietto a) {

		EntityManagerFactory emf = JpaUtil.getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();

		try {

			em.refresh(a);

		} finally {

			em.close();
		}

	}

	public static List<Biglietto> numeroTicketPerEmittentePerRangeTempo(Rivenditore r, LocalDate start, LocalDate end) {

		EntityManagerFactory emf = JpaUtil.getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();

		try {
			Query q = em.createQuery(
					"SELECT b FROM Biglietto b WHERE  b.dataEmissione >= :start AND b.dataEmissione <= :end AND b.rivenditore.id = :id");

			q.setParameter("id", r.getId());
			q.setParameter("start", start);
			q.setParameter("end", end);

			List<Biglietto> b = q.getResultList();

			return b;

		} catch (Exception ex) {

			logger.error("Error", ex);
			throw ex;

		} finally {

			em.close();
		}

	}

	public static void vidimaBiglietto(Biglietto b, Mezzo m) {

		EntityManagerFactory emf = JpaUtil.getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();

		try {

			EntityTransaction t = em.getTransaction();

			b.setVidimato(Vidima.TRUE);
			Set<Mezzo> mezzi = new HashSet<>();
			mezzi.add(m);
			b.setMezzo(mezzi);
			b.setDataVidimazione(LocalDate.now());

			Query q = em.createQuery(
					"UPDATE Biglietto b SET vidimato = :s, dataVidimazione = :k WHERE b.idBiglietto = :id");

			Query query = em.createNativeQuery(
					"INSERT INTO mezzo_biglietto (mezzo_id_mezzo, biglietti_idbiglietto) VALUES (:d, :b)");

			t.begin();

			q.setParameter("s", b.getVidimato());
			q.setParameter("id", b.getIdBiglietto());
			q.setParameter("k", b.getDataVidimazione());

			query.setParameter("d", m.getIdMezzo());
			query.setParameter("b", b.getIdBiglietto());

			q.executeUpdate();
			query.executeUpdate();
			t.commit();

		} catch (Exception ex) {
			em.getTransaction().rollback();
			logger.error("Error", ex);
			throw ex;

		} finally {
			em.close();
		}

	}

	public static List<Integer> vidimatiSulMezzo(int idMezzo) {

		EntityManagerFactory emf = JpaUtil.getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();

		try {

			Query q = em
					.createNativeQuery("SELECT biglietti_idbiglietto FROM mezzo_biglietto WHERE mezzo_id_mezzo = :id");

			q.setParameter("id", idMezzo);

			List<Integer> list = q.getResultList();
			return list;

		} catch (Exception ex) {

			logger.error("Error", ex);
			throw ex;

		} finally {

			em.close();
		}

	}

	public static int cercaVidimati(List<Integer> listId) {

		EntityManagerFactory emf = JpaUtil.getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();

		try {

			Query q = em.createQuery("SELECT b FROM Biglietto b WHERE b.vidimato = :v");

			q.setParameter("v", Vidima.TRUE);

			List<Biglietto> lists = q.getResultList();

			int counter = 0;

			for (int i = 0; i < listId.size(); i++) {
				for (int j = 0; j < lists.size(); j++) {
					if (lists.get(j).getIdBiglietto() == listId.get(i)) {
						counter++;
					}
				}

			}
			return counter;

		} catch (Exception ex) {

			logger.error("Error", ex);
			throw ex;

		} finally {

			em.close();
		}
	}

	public static int vidimazioniNelTempo(LocalDate start, LocalDate end) {

		EntityManagerFactory emf = JpaUtil.getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();

		try {

			Query q = em.createQuery(
					"SELECT b FROM Biglietto b WHERE b.dataVidimazione >= :start AND b.dataVidimazione <= :end",
					Biglietto.class);

			q.setParameter("start", start);
			q.setParameter("end", end);

			List<Biglietto> list = q.getResultList();
			return list.size();

		} catch (Exception ex) {

			logger.error("Error", ex);
			throw ex;

		} finally {

			em.close();
		}
	}

}
