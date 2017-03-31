package it.polito.tdp.lab04.model;

import java.util.*;

import it.polito.tdp.lab04.DAO.*;

public class Model {
	
	/**
	 * Permette di ricevere dalle classi DAO i corsi presenti nel DB
	 * @return La lista dei corsi presenti nel DB
	 */
	public List<Corso> getCorsi() {
		CorsoDAO c = new CorsoDAO();
		return c.getTuttiICorsi();
	}

	/**
	 * Data una matricola, chiede alle classi DAO se nel DB è presente
	 * uno studente con quella Matricola
	 * @param matricola la matricola che vogliamo cercare
	 * @return L'oggetto di classe Studente interamente implementato 
	 * se nel DB è presente la matricola, null in caso contrario
	 */
	public Studente cercaStudente(int matricola) {
		StudenteDAO sd = new StudenteDAO();
		return sd.getStudente(new Studente(matricola,null,null,null));
	}

	/**
	 * Dato un corso, chiede alle classi DAO gli studenti iscritto a quel corso
	 * @param c L'oggetto di classe Corso di cui vogliamo conoscere gli studenti iscritti
	 * @return Lista di studenti iscritti al corso passato come parametro
	 */
	public List<Studente> cercaStudentiIscrittiAlCorso(Corso c) {
		CorsoDAO cd = new CorsoDAO();
		return cd.getStudentiIscrittiAlCorso(c);
		
	}

	
	/**
	 * Data la matricola di uno studente, restituisce i corsi a cui lo studente è iscritto
	 * @param matricola la matricola dello Studente di cui voglio ricercare i corsi
	 * @return La lista di corsi ai quali lo studente è iscritto
	 */
	public List<Corso> cercaCorsiDiUnoStudente(int matricola) {
		StudenteDAO sd = new StudenteDAO();
		return sd.getCorsiDiUnoStudente(matricola);
	}

	/**
	 * Dati un corso e una matricola, permette di sapere se lo studente è iscritto
	 * al corso
	 * @param c Oggetto di classe Corso
	 * @param m matricola dello studente
	 * @return true se lo studente è iscritto, false in caso contrario
	 */
	public boolean isIscritto(Corso c, int m) {
		StudenteDAO s = new StudenteDAO();
		return s.isIscritto(m, c);
	}
	
	/**
	 * Dati una matricola e un corso, permette di iscrivere lo studente
	 * con quella matricola al corso
	 * @param matricola La matricola dello studente che voglio iscrivere
	 * @param c Il corso al quale voglio iscrivere lo studente
	 * @return true se l'iscrizione è andata a buon fine, false in caso contrario
	 */
	public boolean iscriviStudente(int matricola , Corso c){
		CorsoDAO cd = new CorsoDAO();
		return cd.inscriviStudenteACorso(new Studente(matricola,null,null,null), c);
	}
	

}
