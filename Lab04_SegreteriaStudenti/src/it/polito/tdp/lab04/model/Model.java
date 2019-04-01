package it.polito.tdp.lab04.model;

import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;;

public class Model {
	
	private StudenteDAO studenteDao;
	private CorsoDAO corsoDao;
	
	public Model() {
		studenteDao = new StudenteDAO();
		corsoDao = new CorsoDAO();
	}

	/**
	 * Metodo che ritorna tutti i corsi del DB 
	 * @return
	 */
	public List<String> getCorsi() {
		// TODO Auto-generated method stub
		List<String> stringheCorsi = new ArrayList<String>();
		
		for(Corso c: corsoDao.getTuttiICorsi()) {
			stringheCorsi.add(c.getNome());
			
		}
		return stringheCorsi;
	}

	/*
	 * Metodo che ritorna la lista di matricole iscritte al corso (@corsoScelto)
	 * @param corsoScelto
	 * @return
	 */

	public List<String> getIscrittiAlCorso(Corso corsoScelto) {
		// TODO Auto-generated method stub
		
		List<String> studentiIscritti = new ArrayList<String>();
		List<Studente> studenti = new ArrayList<Studente>();
		
		
		//In studenti salvo tutti gli studenti iscritti (grazie alla query in corsoDAO) al corso selezionato
		studenti=corsoDao.getStudentiIscrittiAlCorso(corsoScelto);
		
		
		//Una volta ottenuti gli studenti aggiungo a una lista di stringhe tutte le info
		for(Studente s:  studenti) {
			studentiIscritti.add(s.getMatricola()+ " "+s.getNome()+" "+s.getCognome()+" "+s.getCds());
		}
		return studentiIscritti;
	}

	
	/**
	 * Metodo che ritorna lo Studente data la matricola (@matricola)
	 * @param matricola
	 * @return
	 */
	public Studente getNomeECognome(int matricola) {
		// TODO Auto-generated method stub
		
		return studenteDao.getNomeCognome(matricola);
	}
	
	

	public List<Corso> getCorsiACuiEIscritto(Studente s) {
		// TODO Auto-generated method stub
		
		//Metodo nel dao che mi ritorna la lista di corsi a cui � iscritto lo studente s
		return studenteDao.getCorsiACuiEIscrittoDao(s);
		
	}

	public Corso getCorsoDatoNome(String nomeCorsoScelto) {
		// TODO Auto-generated method stub
		return corsoDao.cercaCodiceDatoIlNome(nomeCorsoScelto);
	}

	public boolean isIscritto(Studente studente, Corso corso) {
		// TODO Auto-generated method stub
		
		//Qui uso il metodo gi� creato in precedenza, getCorsiACuiEIscrittoDao(studente) per avere la lista di corsi a cui � iscritto lo studente specificato
		List<Corso> corsiACuiEIscritto=new ArrayList<Corso>();
		corsiACuiEIscritto=studenteDao.getCorsiACuiEIscrittoDao(studente);
		
		//Adesso cerca nella lista di corsi  a cui � iscritto se � presente quello specificato dall'utente
		for(Corso c: corsiACuiEIscritto) {
			if (c.equals(corso)) {
				return true;
			}
		}
		return false;
	}

	public boolean iscrivi(Corso c, Studente s) {
		// TODO Auto-generated method stub
		return corsoDao.iscriviDao(c, s);
	}
	
	

}
