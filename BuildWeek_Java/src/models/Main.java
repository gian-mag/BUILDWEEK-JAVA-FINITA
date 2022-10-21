package models;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import models.DAO.*;
import models.classes.*;
import models.enums.*;

public class Main {

	private static final Logger logger = LoggerFactory.getLogger(Main.class);

	static Emissione emissione;

	static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {

//		salvaUtente();

//		creaBus();
//		creaTram();

//		toggleDeposito(5);

//		creaRivenditore(Zona.CENTRO);
//		creaDistributore(Zona.PARCO);

//		erogaBiglietto(1);

//		erogaTessera(3);

//		erogaAbbonamento(1, 2, Emissione.MENSILE);

//		rinnovaTessera(3);

//		nuovaTratta(Zona.CENTRO, Zona.PARCO);

//		nuovoViaggio(1, 2);

//		termineViaggio(3);

//		controlloValiditaTessera(2);

//		obliteratriceDeDios(4, 6);

//		bigliettiSulMezzo(7);
//
//		vidimatiPerTempo(LocalDate.of(2000, 10, 9), LocalDate.now());
//
//		storicoMezzo(1);
//
//		numeroViaggi(2, 1);

//		controllaAbbonamentoPerTessera(1);

//		cercaMezziInServizio();

		System.out.println("-----------------------------------------------------------------------------------");

		boolean flag = true;

		System.out.println("Benvenuto nell'Circo-lo Comunale di Epicode\n");

		while (flag) {

			try {
				System.out.println("----Scegli chi sei da eseguire (Non sei Lombok?): ----\n ");
				System.out.println(
						"Premi: \n 0) Per uscire \n 1) Rivenditore \n 2) Autista \n 3) Controllore\n 4) Circo-lo\n ");
				Integer opzione = Integer.parseInt(scanner.nextLine());
				System.out.println("Hai scelto l'opzione: " + opzione + "\n");

				switch (opzione) {

				case 0:
					System.out.println("Sei uscito dal Circo-lo!");
					flag = false;
					break;

				// PIATTAFORMA RIVENDITORE
				case 1:

					boolean riwhile = true;

					while (riwhile) {
						System.out.println("Premi: \n 0) Per uscire \n 1) Venditore\n 2) Distributore\n ");
						Integer rivenditore = Integer.parseInt(scanner.nextLine());

						switch (rivenditore) {
						case 0:
							System.out.println("Sei uscito dalla piattaforma Rivenditori!");
							riwhile = false;
							break;
						// METODO RIVENDITORE
						case 1:
							boolean f1 = true;
							while (f1) {
								System.out.println("Hai scelto Venditore");
								System.out.println(
										"Premi: \n 0) Per uscire \n 1) Eroga Tessera \n 2) Controlla Tessera\n 3) Rinnova Tessera\n 4) Eroga Biglietto\n 5) Eroga Abbonamento\n 6) Controlla Abbonamento\n ");
								Integer opt = Integer.parseInt(scanner.nextLine());

								switch (opt) {
								case 0:
									System.out.println("Sei uscito dalla piattaforma Venditori!");
									f1 = false;
									break;
								// METODO RIVENDITORE
								case 1:
									System.out.println("Hai scelto Eroga Tessera");
									System.out.println("Inserisci il tuo Codice Utente: ");
									Integer idUtente = Integer.parseInt(scanner.nextLine());
									try {
										erogaTessera(idUtente);
									} catch (Exception e) {
										logger.error("Qualcosa è andato storto");
									}
									break;

								case 2:
									System.out.println("Hai scelto Controlla Tessera");
									System.out.println("Inserisci il tuo Codice Tessera");
									Integer idTessera = Integer.parseInt(scanner.nextLine());
									try {
										controlloValiditaTessera(idTessera);
									} catch (Exception e) {
										logger.error("Qualcosa è andato storto");
									}
									break;

								case 3:
									System.out.println("Hai scelto Rinnova Tessera");
									System.out.println("Inserisci il tuo Codice Tessera");
									Integer id_tessera = Integer.parseInt(scanner.nextLine());
									try {
										rinnovaTessera(id_tessera);
										System.out.println("Tessera rinnovata con successo!");
									} catch (Exception e) {
										logger.error("Qualcosa è andato storto");
									}
									break;

								case 4:
									System.out.println("Hai scelto Eroga Biglietto");
									System.out.println("Inserisci il tuo Codice Venditore");
									Integer codRiv = Integer.parseInt(scanner.nextLine());
									try {
										compraBiglDaRivenditore(codRiv);
										System.out.println("Biglietto erogato con successo!");
									} catch (Exception e) {
										logger.error("Qualcosa è andato storto");
									}
									break;

								case 5:
									System.out.println("Hai scelto Eroga Abbonamento");
									System.out.println("Inserisci il tuo Codice Venditore");
									Integer codRiv1 = Integer.parseInt(scanner.nextLine());
									System.out.println("Inserisci il Codice Tessera");
									Integer idTesseraUtente = Integer.parseInt(scanner.nextLine());
									System.out.println("Inserisci il Tipo di abbonamento");
									EmissioneEnum();
									try {
										compraAbbDaRivenditore(idTesseraUtente, codRiv1, emissione);
										System.out.println("Abbonamento erogato con successo!");
									} catch (Exception e) {
										logger.error("Qualcosa è andato storto");
									}
									break;

								case 6:
									System.out.println("Hai scelto Controlla Abbonamento");
									System.out.println("Inserisci il tuo Codice Tessera");
									Integer codTess = Integer.parseInt(scanner.nextLine());
									try {
										controllaAbbonamentoPerTessera(codTess);
									} catch (Exception e) {
										logger.error("Qualcosa è andato storto");
									}
									break;
								}

							}

							break;
						// METODO DISTRIBUTORE
						case 2:
							System.out.println("Hai scelto Distributore");
							boolean f2 = true;
							while (f2) {
								System.out.println(
										"Premi: \n 0) Per uscire \n 1) Eroga Biglietto\n 2) Eroga Abbonamento\n ");
								Integer opt = Integer.parseInt(scanner.nextLine());

								switch (opt) {
								case 0:
									System.out.println("Sei uscito dalla piattaforma Distributori!");
									f2 = false;
									break;
								case 1:
									System.out.println("Hai scelto Eroga Biglietto");
									System.out.println("Inserisci il tuo Codice Distributore");
									Integer codDis = Integer.parseInt(scanner.nextLine());
									try {
										compraBiglDaDistributore(codDis);
										System.out.println("Biglietto erogato con successo!");
									} catch (Exception e) {
										logger.error("Qualcosa è andato storto");
									}
									break;

								case 2:
									System.out.println("Hai scelto Eroga Abbonamento");
									System.out.println("Inserisci il tuo Codice Distributore");
									Integer codDis1 = Integer.parseInt(scanner.nextLine());
									System.out.println("Inserisci il Codice Tessera");
									Integer idTesseraUtente = Integer.parseInt(scanner.nextLine());
									System.out.println("Inserisci il Tipo di abbonamento");
									EmissioneEnum();
									try {
										compraAbbDaDistributore(idTesseraUtente, codDis1, emissione);
										System.out.println("Abbonamento erogato con successo!");
									} catch (Exception e) {
										logger.error("Qualcosa è andato storto");
									}
									break;
								}

							}
							break;
						}
					}

					break;

//				PIATTAFORMA AUTISTA
				case 2:
					System.out.println("Hai scelto Autista");

					boolean f1 = true;
					while (f1) {
						System.out
								.println("Premi: \n 0) Per uscire\n 1) Partenza Nuovo Viaggio\n 2) Termina Viaggio\n ");
						Integer opt = Integer.parseInt(scanner.nextLine());

						switch (opt) {
						case 0:
							System.out.println("Sei uscito dalla piattaforma Circo-lo Autisti-ci!");
							f1 = false;
							break;
						case 1:
							System.out.println("Hai scelto nuovo viaggio");
							System.out.println("Inserisci il tuo Codice Mezzo");
							Integer codMezzo = Integer.parseInt(scanner.nextLine());
							System.out.println("Inserisci il Codice Tratta");
							Integer codTratta = Integer.parseInt(scanner.nextLine());
							try {
								nuovoViaggio(codTratta, codMezzo);
								System.out.println("Brum brum!!");
							} catch (Exception e) {
								logger.error("Qualcosa è andato storto");
							}
							break;

						case 2:
							System.out.println("Termina Viaggio");
							System.out.println("Inserisci il Codice Viaggio");
							Integer codViaggio = Integer.parseInt(scanner.nextLine());
							try {
								termineViaggio(codViaggio);
								System.out.println("Sei fermo, vai in pausa");
							} catch (Exception e) {
								logger.error("Qualcosa è andato storto");
							}
							break;
						}

					}

					break;

//					PIATTAFORMA CONTROLLORE AUTOMATICO
				case 3:
					System.out.println("Hai scelto Controllore!");

					boolean f2 = true;
					while (f2) {
						System.out.println("Premi: \n 0) Per uscire\n 1) Oblitera! \n ");
						Integer opt = Integer.parseInt(scanner.nextLine());

						switch (opt) {
						case 0:
							System.out.println("Sei uscito dalla piattaforma Controllore!");
							f2 = false;
							break;
						case 1:
							System.out.println("Hai scelto Oblitera!");
							System.out.println("Inserisci il tuo Codice Mezzo");
							Integer codMezzo = Integer.parseInt(scanner.nextLine());
							System.out.println("Inserisci il Codice Biglietto");
							Integer codBiglietto = Integer.parseInt(scanner.nextLine());
							try {
								obliteratriceDeDios(codBiglietto, codMezzo);
							} catch (Exception e) {
								logger.error("Qualcosas è andatos stortos");
							}
							break;
						}

					}

					break;

				case 4:
					System.out.println("Hai scelto Circo-lo!");
					boolean f3 = true;
					while (f3) {
						System.out.println(
								"Premi: \n 0) Per uscire\n 1) Crea Istanze\n 2) Cerca Istanze\n 3) Cambia Status Mezzo \n ");
						Integer opt = Integer.parseInt(scanner.nextLine());

						switch (opt) {
						case 0:
							System.out.println("Sei uscito dalla piattaforma Circo-lo");
							f3 = false;
							break;

						case 1:
							System.out.println("Hai scelto Crea Istanze");
							boolean f4 = true;
							while (f4) {

								System.out.println("Premi: \n 0) Per uscire\n 1) Crea Bus\n 2) Crea Tram \n");
								Integer options = Integer.parseInt(scanner.nextLine());

								switch (options) {
								case 0:
									System.out.println("Sei uscito dalla piattaforma Circo-lo!");
									f4 = false;
									break;
								case 1:
									try {
										creaBus();
										System.out.println("Creato con successo!");
									} catch (Exception e) {
										logger.error("Qualcosa è andatos storto");
									}
									break;

								case 2:
									try {
										creaTram();
										System.out.println("Creato con successo!");
									} catch (Exception e) {
										logger.error("Qualcosa è andatos storto");
									}

									break;

								}
							}
							break;

						case 2:
							System.out.println("Hai scelto Cerca Istanze!");

							boolean f5 = true;
							while (f5) {

								System.out.println(
										"Premi: \n 0) Per uscire\n 1) Mezzi in Servizio \n 2) Storico del Mezzo \n "
												+ "3) Biglietti Vidimati per Mezzo\n 4) Biglietti vidimati per Range di Tempo\n "
												+ "5) Numero Viaggi mezzo per tratta \n 6) Biglietti per Rivenditore per Tempo\n");
								Integer options = Integer.parseInt(scanner.nextLine());

								switch (options) {

								case 0:
									System.out.println("Sei uscito dalla piattaforma Controllore!");
									f5 = false;
									break;

								case 1:
									try {
										cercaMezziInServizio();
									} catch (Exception e) {
										logger.error("Error");
									}

									break;

								case 2:
									try {
										System.out.println("Inserisci Codice Mezzo");
										Integer mezzo = Integer.parseInt(scanner.nextLine());
										storicoMezzo(mezzo);
										break;
									} catch (Exception e) {
										logger.error("Error");
									}

								case 3:
									try {
										System.out.println("Inserisci Codice Mezzo");
										Integer mezzo1 = Integer.parseInt(scanner.nextLine());
										bigliettiSulMezzo(mezzo1);
									} catch (Exception e) {
										logger.error("Error");
									}

									break;

								case 4:

									try {
										System.out.println("Data da: (YYYY-MM-GG)");
										System.out.println("Inserisci anno");
										Integer annoS = Integer.parseInt(scanner.nextLine());
										System.out.println("Inserisci mese");
										Integer meseS = Integer.parseInt(scanner.nextLine());
										System.out.println("Inserisci giorno");
										Integer giornoS = Integer.parseInt(scanner.nextLine());

										System.out.println("Data a: (YYYY-MM-GG)");
										System.out.println("Inserisci anno");
										Integer annoE = Integer.parseInt(scanner.nextLine());
										System.out.println("Inserisci mese");
										Integer meseE = Integer.parseInt(scanner.nextLine());
										System.out.println("Inserisci giorno");
										Integer giornoE = Integer.parseInt(scanner.nextLine());
										vidimatiPerTempo(LocalDate.of(annoS, meseS, giornoS),
												LocalDate.of(annoE, meseE, giornoE));
									} catch (NumberFormatException e) {
										logger.error("Inserisci un numero valido!");
									}

									break;

								case 5:
									try {
										System.out.println("Inserisci Codice Mezzo");
										Integer mezzo2 = Integer.parseInt(scanner.nextLine());
										System.out.println("Inserisci Codice Tratta");
										Integer tratta = Integer.parseInt(scanner.nextLine());
										numeroViaggi(mezzo2, tratta);
									} catch (Exception e) {
										logger.error("Error");
									}

									break;

								case 6:
									try {
										System.out.println("Inserisci Codice Rivenditore");
										Integer codRiv = Integer.parseInt(scanner.nextLine());
										System.out.println("Data da: (YYYY-MM-GG)");
										System.out.println("Inserisci anno");
										Integer annoS = Integer.parseInt(scanner.nextLine());
										System.out.println("Inserisci mese");
										Integer meseS = Integer.parseInt(scanner.nextLine());
										System.out.println("Inserisci giorno");
										Integer giornoS = Integer.parseInt(scanner.nextLine());

										System.out.println("Data a: (YYYY-MM-GG)");
										System.out.println("Inserisci anno");
										Integer annoE = Integer.parseInt(scanner.nextLine());
										System.out.println("Inserisci mese");
										Integer meseE = Integer.parseInt(scanner.nextLine());
										System.out.println("Inserisci giorno");
										Integer giornoE = Integer.parseInt(scanner.nextLine());
										cercaBiglietto(codRiv, LocalDate.of(annoS, meseS, giornoS),
												LocalDate.of(annoE, meseE, giornoE));
									} catch (Exception e) {
										logger.error("Error");
									}

									break;
								}

							}

							break;

						case 3:
							System.out.println("Manda il mezzo in manutenzione o viceversa");
							try {
								System.out.println("Inserisci Codice Mezzo");
								Integer mezzo = Integer.parseInt(scanner.nextLine());
								toggleDeposito(mezzo);
								if(MezzoDAO.getById(mezzo).getStatus() == Status.SERVIZIO) {
									System.out.println("Il mezzo è rientrato in servizio!");
								} else {
									System.out.println("Il mezzo è in autocombustione, raggomitolamelDAO");
								}
							} catch (Exception e) {
								logger.error("Qualcosa è andatos storto");
							}

							break;
						}

					}

					break;

				default:
					System.out.println("Scegli un'opzione valida!");
					break;
				}

			} catch (Exception r) {
				logger.error("Fai schifo");
			}

		}

	}

	// SALVA UTENTE ------ OK
	public static void salvaUtente() {

		Utente u1 = new Utente();
		u1.setNome("Pago");
		u1.setCognome("L'Affitto");

		Utente u2 = new Utente();
		u2.setNome("Giancarlo");
		u2.setCognome("Magalli");

		Utente u3 = new Utente();
		u3.setNome("Flavio");
		u3.setCognome("Briatore");

		Utente u4 = new Utente();
		u4.setNome("La");
		u4.setCognome("Gregoraci");

		Utente u5 = new Utente();
		u5.setNome("Leone");
		u5.setCognome("Ferragnez");

		UtenteDAO.save(u1);
		UtenteDAO.save(u2);
		UtenteDAO.save(u3);
		UtenteDAO.save(u4);
		UtenteDAO.save(u5);

	}

	public static void EmissioneEnum() {

		System.out.println("---- Scegli tra:  \n 1) SETTIMANALE \n 2) MENSILE \n");
		int period = Integer.parseInt(scanner.nextLine());
		try {
			switch (period) {
			case 1:
				emissione = Emissione.SETTIMANALE;
				break;

			case 2:
				emissione = Emissione.MENSILE;
				break;
			}
		} catch (Exception e) {
			logger.error("Sbagliato");
		}

	}

	// ? mezzi ------ OK
	static void creaBus() {
		Bus b = new Bus();

		Deposito d = new Deposito();

		DepositoDAO.save(b, d);
	}

	// salva tram ------ OK
	static void creaTram() {
		Tram b = new Tram();

		Deposito d = new Deposito();

		DepositoDAO.save(b, d);
	}

	// storico deposito, cambia status mezzo ------ OK
	static void toggleDeposito(int mezzoId) {

		Mezzo m = MezzoDAO.getById(mezzoId);

		Deposito d = new Deposito();

		DepositoDAO.saveDeposito(m, d);
	}

	// crea Rivenditore ------ OK
	static void creaRivenditore(Zona ztl) {
		Rivenditore r = new Rivenditore();
		r.setZona(ztl);
		RivenditoreDAO.save(r);
	}

	// crea Distributore ------ OK
	static void creaDistributore(Zona ztl) {
		Distributore d = new Distributore();
		d.setZona(ztl);
		DistributoreDAO.save(d);
	}

	// ? biglietti ------ OK
	static void erogaBiglietto(int id) {
		Rivenditore r = RivenditoreDAO.getById(id);
		Biglietto b = new Biglietto();
		b.setRivenditore(r);
		BigliettoDAO.save(b);
	}

	// salva abbonamento
	// aggiungere controllo scadenza tessera ------ OK
	static void erogaAbbonamento(int tesseraId, int id, Emissione em) {
		Tessera t = TesseraDAO.getById(tesseraId);
		Rivenditore r = RivenditoreDAO.getById(id);
		if (t != null) {
			Abbonamento a = new Abbonamento();
			a.setTessera(t);
			a.setRivenditore(r);
			a.setEmissione(em);
			BigliettoDAO.save(a);
		} else {
			System.out.println("Prima di richiedere l'abbonamento, fatti la tessera, pezzente! Che tanto è aggratiss");
		}
	}

	// ------ OK
	static void compraAbbDaRivenditore(int tesseraId, int id, Emissione em) {
		erogaAbbonamento(tesseraId, id, em);
	}

	// ------ OK
	static void compraAbbDaDistributore(int tesseraId, int id, Emissione em) {
		if (DistributoreDAO.getById(id).getInServizio() == StatoServizio.IN_SERVIZIO) {
			erogaAbbonamento(tesseraId, id, em);
		} else {
			System.out.println("distributore non attivo");
		}

	}

	// ------ OK
	static void compraBiglDaRivenditore(int id) {
		erogaBiglietto(id);
	}

	// ------ OK
	static void compraBiglDaDistributore(int id) {
		if (DistributoreDAO.getById(id).getInServizio() == StatoServizio.IN_SERVIZIO) {
			erogaBiglietto(id);
		} else {
			System.out.println("distributore non attivo");
		}

	}

//	  ------ OK
	static void erogaTessera(int userId) {

		Utente u = UtenteDAO.getById(userId);
		if (u.getTessera() != null) {
			System.out.println("Scopino hai già una tessera col codice: " + u.getTessera().getId());
		} else {
			Tessera t = new Tessera();
			t.setUtente(u);
			TesseraDAO.save(t);
			UtenteDAO.collegaTessera(userId, t);
			System.out.println("Tessera erogata con successo!");
		}

	}

//	 ------ OK
	static void rinnovaTessera(int tId) {
		Tessera tess = TesseraDAO.getById(tId);
		TesseraDAO.rinnovaTessera(tess);
	}

	/// ---- METODI ----

	// salva la tratta ---- SISTEMARE IL TEMPO
	static void nuovaTratta(Zona partenza, Zona arrivo) {
		Tratta t = new Tratta();
		t.setPartenza(partenza);
		t.setCapolinea(arrivo);
		t.impostaTempoStimato();
		TrattaDAO.save(t);
	}

	// starta il viaggio --------OK
	static void nuovoViaggio(int idTratta, int idMezzo) {
		Mezzo m = MezzoDAO.getById(idMezzo);
		if (m.getStatus() == Status.SERVIZIO) {
			Tratta t = TrattaDAO.getById(idTratta);
			Viaggio v = new Viaggio();
			v.setTratta(t);
			v.setMezzo(m);
			ViaggioDAO.save(v);
		} else {
			System.out.println("Ma con cosa parti che il tuo mezzo è andato in autocombustione?");
		}

	}

	// termina il viaggio -------------OK
	static void termineViaggio(int idViaggio) {
		Viaggio v = ViaggioDAO.getById(idViaggio);
		ViaggioDAO.termineViaggio(v);
	}

	static void controllaAbbonamentoPerTessera(int idTess) {

		List<Abbonamento> l = AbbonamentoDAO.cercaAbbonamentoPerTessera(idTess);
		if (l.size() == 0) {
			System.out.println("Non hai ancora alcun abbonamento");
		} else {
			for (int i = 0; i < l.size(); i++) {

				if (l.get(i).getValidità().compareTo(LocalDate.now()) >= 0) {
					System.out.println("L'oggetto: " + l.get(i) + " è valido!");
				}

			}
		}

	}

	// controllo e vidima ----------- OK
	static boolean controlloAbbonamento(int id, int idMezzo) {
		LocalDate datascadenza = AbbonamentoDAO.getById(id).getValidità();
		if (LocalDate.now().compareTo(datascadenza) > 0) {
			return false;
		} else {
			Abbonamento a = AbbonamentoDAO.getById(id);
			Mezzo m = MezzoDAO.getById(idMezzo);
			AbbonamentoDAO.abbonamentoVidima(a, m);
			return true;
		}
	}

//	 ------ OK
	static void vidimaBiglietto(int idBiglietto, int idMezzo) {
		Biglietto b = BigliettoDAO.getById(idBiglietto);
		if (b.getVidimato() == Vidima.FALSE) {
			Mezzo m = MezzoDAO.getById(idMezzo);
			BigliettoDAO.vidimaBiglietto(b, m);
			System.out.println("Buon viaggio");
		} else {
			System.out.println("Sei uno scopino imbroglione");
		}
	}

//	 ------ OK
	static void obliteratriceDeDios(int idb, int idm) {
		// non so se va questo if
		if (BigliettoDAO.getById(idb) != null && BigliettoDAO.getById(idb) instanceof Abbonamento) {
			if (controlloAbbonamento(idb, idm)) {
				System.out.println("Buon viaggio");
			} else {
				System.out.println("Abbonamento non valido");
			}
		} else {
			vidimaBiglietto(idb, idm);
		}
	}

	// controllo tessera ------ OK
	static boolean controlloValiditaTessera(int id) {
		Tessera t = TesseraDAO.getById(id);
		if (t != null) {
			if (t.getScadenzaTessera().compareTo(LocalDate.now()) > 0) {
				System.out.println("Tessera valida");
				return true;
			} else {
				System.out.println("Tessera non valida");
				return false;
			}
		} else {
			System.out.println("Tessera non esiste");
			return false;
		}

	}

//	 METODI 

//	vidimazione nel tempo -------- OK
	static void vidimatiPerTempo(LocalDate start, LocalDate end) {
		System.out.println("I numeri di biglietti vidimati da: " + start + " a: " + end + " è uguale a: "
				+ BigliettoDAO.vidimazioniNelTempo(start, end));

	}

//	vidimati nel mezzo   ------------------OK 
	static void bigliettiSulMezzo(int id) {
		System.out.println("I biglietti vidimati sul mezzo: " + id + " sono: "
				+ BigliettoDAO.cercaVidimati(BigliettoDAO.vidimatiSulMezzo(id)));

	}

//	cerca storico nel deposito  ------------------OK
	static void storicoMezzo(int id) {
		List<Deposito> mezzo = DepositoDAO.cercaStoricoMezzo(MezzoDAO.getById(id));
		if (mezzo.size() == 0) {
			System.out.println("Questo mezzo non è registrato nel deposito");
		} else {
			for (Deposito m : mezzo) {
				System.out.println(m);
			}
		}

	}

//	percorrenze (viaggoDAO) --------------OK
	static void numeroViaggi(int idMezzo, int idTratta) {
		System.out.println("Il numero di viaggi effettuati dal mezzo: " + idMezzo + ", sulla tratta: " + idTratta
				+ ", sono: " + ViaggioDAO.percorrenze(idMezzo, idTratta));
	}

// cerca BIGLIETTO

	static void cercaBiglietto(int rivId, LocalDate start, LocalDate end) {
		Rivenditore r = RivenditoreDAO.getById(rivId);
		List<Biglietto> b = BigliettoDAO.numeroTicketPerEmittentePerRangeTempo(r, start, end);
		if (b.size() == 0) {
			System.out.println("Non ci sono biglietti stampati nel periodo di tempo selezionato");
		} else {
			System.out.println("Il numero di biglietti dal " + start + " al " + end + " è: " + b.size());
		}

	}

	// CERCA MEZZI IN SERVIZIO

	static void cercaMezziInServizio() {
		List<Mezzo> list = MezzoDAO.getActive(Status.SERVIZIO);
		if (list.size() == 0) {
			System.out.println("Qui non si lavora, oggi sciopero");
		} else {
			System.out.println("I mezzi in servizio all'attivo sono: " + list.size());
			for (Mezzo m : list) {
				System.out.println(m);
			}
		}
	}
}