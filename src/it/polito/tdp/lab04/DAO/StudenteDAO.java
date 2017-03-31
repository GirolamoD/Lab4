package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class StudenteDAO {
	
	
	public List<Studente> getTuttiGliStudenti() {

		final String sql = "SELECT * FROM studente";

		List<Studente> studenti = new LinkedList<Studente>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				// Crea un nuovo JAVA Bean Corso
				int matricola = rs.getInt("matricola");
				String cognome = rs.getString("cognome");
				String nome = rs.getString("nome");
				String cds = rs.getString("CDS");
				Studente r = new Studente(matricola,nome,cognome,cds);
				// Aggiungi il nuovo Corso alla lista
				studenti.add(r);
				
				
			}
			conn.close();

			return studenti;

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}
	
	public Studente getStudente(Studente s) {
		final String sql = "SELECT matricola,cognome,nome,CDS "+
							"FROM studente "+
							"WHERE matricola=?";
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, s.getMatricola());
			ResultSet rs = st.executeQuery();
			
			if(rs.next()){
				s.setCognome(rs.getString("cognome"));
				s.setNome(rs.getString("nome"));
				s.setCds(rs.getString("CDS"));
				return s;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
	
	public List<Corso> getCorsiDiUnoStudente(int m) {
		
		List<Corso> corsi = new LinkedList<Corso>();
		
		final String sql = "SELECT corso.codins,crediti,nome,pd "+
							"FROM corso,iscrizione "+
							"WHERE corso.codins=iscrizione.codins AND matricola=?";
		
		Connection conn = ConnectDB.getConnection();
		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1,m);
			ResultSet rs = st.executeQuery();
			
			while(rs.next()){
				String cod = rs.getString("codins");
				int crediti = rs.getInt("crediti");
				String nome = rs.getString("nome");
				int pd = rs.getInt("pd");
				corsi.add(new Corso(cod,crediti,nome,pd));

			}
			
			conn.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return corsi ;
		
	}
	
	public boolean isIscritto(int m,Corso c) {
		
		final String sql = "SELECT COUNT(*) AS NUM "+
							"FROM iscrizione "+
							"WHERE codins=? AND matricola=?";
		boolean b = false ;
		
		
		Connection conn = ConnectDB.getConnection();
		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1,c.getCod());
			st.setInt(2, m);
			ResultSet rs = st.executeQuery();
			rs.next();
			if(rs.getInt("NUM")==1)
				b=true;
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return b ;
		
	}

	
	



	
}
