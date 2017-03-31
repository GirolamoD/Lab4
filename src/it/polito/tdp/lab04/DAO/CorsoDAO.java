package it.polito.tdp.lab04.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class CorsoDAO {

	/*
	 * Ottengo tutti i corsi salvati nel Db
	 */
	public List<Corso> getTuttiICorsi() {

		final String sql = "SELECT * FROM corso";

		List<Corso> corsi = new LinkedList<Corso>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			Statement st = conn.createStatement();

			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {

				// Crea un nuovo JAVA Bean Corso
				String cod = rs.getString("codins");
				int crediti = rs.getInt("crediti");
				String nome = rs.getString("nome");
				int pd = rs.getInt("pd");
				Corso c = new Corso(cod,crediti,nome,pd);
				
				// Aggiungi il nuovo Corso alla lista
				corsi.add(c);
				
			
			}
			conn.close();

			return corsi;

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}

	/*
	 * Dato un codice insegnamento, ottengo il corso
	 */
	public Corso getCorso(Corso corso) {
		final String sql = "SELECT codins,crediti,nome,pd FROM corso WHERE codins=?" ;
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, corso.getCod());
			ResultSet rs = st.executeQuery();
			
			if(rs.next()){
				int crediti = rs.getInt("crediti");
				String nome = rs.getString("nome");
				int pd = rs.getInt("pd");
				corso.setCrediti(crediti);
				corso.setNome(nome);
				corso.setDp(pd);
				conn.close();
				return corso ;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}

	/*
	 * Ottengo tutti gli studenti iscritti al Corso
	 */
	public List<Studente> getStudentiIscrittiAlCorso(Corso corso) {
		
		List<Studente> studenti = new LinkedList<Studente>();
		final String sql = "SELECT studente.matricola,cognome,nome,CDS " +
							"FROM iscrizione,studente "+
							"WHERE iscrizione.matricola=studente.matricola AND codins=?";
		
		Connection conn = ConnectDB.getConnection();
		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, corso.getCod());
			ResultSet rs = st.executeQuery();
			
			while(rs.next()){
				int matricola = rs.getInt("matricola");
				String cognome = rs.getString("cognome");
				String nome = rs.getString("nome");
				String cds = rs.getString("CDS");
				Studente r = new Studente(matricola,nome,cognome,cds);
				studenti.add(r);
			}
			
			conn.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return studenti ;
		
	}

	/*
	 * Data una matricola ed il codice insegnamento,
	 * iscrivi lo studente al corso.
	 */
	public boolean inscriviStudenteACorso(Studente studente, Corso corso) {
		final String sql = "INSERT INTO `iscritticorsi`.`iscrizione` (`matricola`, `codins`) VALUES (?, ?)";
		boolean b = false ;
		Connection conn = ConnectDB.getConnection();
		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, studente.getMatricola());
			st.setString(2, corso.getCod());
			
			int res = st.executeUpdate();
			if(res==1){
				b=true;
			}
			
			conn.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return b;
	}
}
