package it.polito.tdp.lab04.DAO;

import it.polito.tdp.lab04.model.*;

public class TestDAO {

	public static void main(String[] args) {
		CorsoDAO c = new CorsoDAO();
		StudenteDAO s = new StudenteDAO();
		// OK System.out.println(c.getTuttiICorsi());
		// OK System.out.println(s.getTuttiGliStudenti());
		// OK System.out.println(c.getCorso(new Corso("09AQGPG", 0, null, 0)));
		// OK System.out.println(c.getStudentiIscrittiAlCorso(new Corso("02CIXPG", 0, null, 0)));
		// OK System.out.println(s.getTuttiGliStudenti().size());
		// OK System.out.println(s.getStudente(new Studente(167754, null, null, null)));
//		OK c.inscriviStudenteACorso(new Studente(167754,null,null,null), new Corso("09AQGPG",0,null,0));
//		OK System.out.println(s.getCorsiDiUnoStudente(new Studente(167754,null,null,null)));
//		
		
		
	}

}
