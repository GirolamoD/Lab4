package it.polito.tdp.lab04.model;

import java.util.*;

import it.polito.tdp.lab04.DAO.*;

public class Model {
	
	
	public List<Corso> getCorsi() {
		CorsoDAO c = new CorsoDAO();
		return c.getTuttiICorsi();
	}

	public Studente cercaStudente(int matricola) {
		StudenteDAO sd = new StudenteDAO();
		return sd.getStudente(new Studente(matricola,null,null,null));
	}

	public List<Studente> cercaStudentiIscrittiAlCorso(Corso c) {
		CorsoDAO cd = new CorsoDAO();
		return cd.getStudentiIscrittiAlCorso(c);
		
	}

	public List<Corso> cercaCorsiDiUnoStudente(int matricola) {
		StudenteDAO sd = new StudenteDAO();
		return sd.getCorsiDiUnoStudente(matricola);
	}

	public boolean isIscritto(Corso c, int m) {
		StudenteDAO s = new StudenteDAO();
		return s.isIscritto(m, c);
	}
	
	public boolean iscriviStudente(int matricola , Corso c){
		CorsoDAO cd = new CorsoDAO();
		return cd.inscriviStudenteACorso(new Studente(matricola,null,null,null), c);
	}
	

}
