package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class StudenteDAO {
	
	/*
	 * Ottengo tutti gli studenti salvati nel Db
	 */
	
	public List<Studente> getTuttiStudenti() {

		String sql = "SELECT * FROM studente";

		List<Studente> studenti = new LinkedList<Studente>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				int matricola = rs.getInt("matricola");
				String cognome = rs.getString("cognome");
				String nome = rs.getString("nome");
				String cds = rs.getString("cds");
			
				System.out.println(matricola + " " + cognome + " " + nome + " " + cds);

				studenti.add(new Studente(matricola, cognome, nome, cds));
				
				// Crea un nuovo JAVA Bean Studente
				// Aggiungi il nuovo oggetto Studente alla lista studenti
			}
			
			
			conn.close();

			
		} catch (SQLException e) {
			//e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
		
		return studenti;
		
	}

	
	
	/**
	 * Restituisce uno studente, data un numero di matricola (@matricola)
	 * @param matricola
	 * @return
	 */
	public Studente getNomeCognome(int matricola) {
		// TODO
		String sql = "SELECT * FROM studente where matricola = ? ";
		Studente s = null;
		Connection conn = ConnectDB.getConnection();
		try {
		
			
			//Si blocca qua
			PreparedStatement st = conn.prepareStatement(sql);
			
			
			st.setInt(1, matricola);
			
			ResultSet rs = st.executeQuery();

			if (rs.next()) {
				s = new Studente(matricola, rs.getString("nome"), rs.getString("cognome"), rs.getString("cds"));
			}
			
			conn.close();
			
			return s;
			
		} catch (SQLException e) {
			 e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
		
		
	}





	/*
	 * Ottengo tutti gli studenti iscritti al Corso dato il codice del corso
	 */
	public List<Studente> getStudentiIscrittiAlCorso(String codice) {
		// TODO
		
		List<Studente> studentiIscritti=new ArrayList<Studente>();
		
		
		//Per fare la query mi serve il codice del Corso
		String sql = "SELECT * FROM iscrizione where codins = ? ";
		Connection conn = ConnectDB.getConnection();
		
		try {
			
		PreparedStatement st = conn.prepareStatement(sql);
			
		st.setString(1, codice);
			
		ResultSet rs = st.executeQuery();
		
		while (rs.next()) {
			//Aggiungo le matricole iscritte al corso  che ha il codice passato come parametro
			
				studentiIscritti.add(new Studente(rs.getInt("matricola")));
		}			
		
			conn.close();
			
			//return studentiIscritti;
			
		} catch (SQLException e) {
			 e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
		
	//Quindi ritorna la lista di matricole iscritte al corso
		return studentiIscritti;
	}

	/*
	 * Data una matricola ed il codice insegnamento, iscrivi lo studente al corso.
	 */
	//public boolean inscriviStudenteACorso(Studente studente, Corso corso) {
		// TODO
		// ritorna true se l'iscrizione e' avvenuta con successo
		//return false;
	//}

}
