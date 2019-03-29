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

	public List<String> getCorsi() {
		// TODO Auto-generated method stub
		CorsoDAO dao = new CorsoDAO();
		List<String> stringheCorsi = new ArrayList<String>();
		
		for(Corso c: dao.getTuttiICorsi()) {
			stringheCorsi.add(c.getNome());
			
		}
		return stringheCorsi;
	}

	public List<String> getCorso(String nomeCorso) {
		// TODO Auto-generated method stub
		StudenteDAO dao = new StudenteDAO();
		List<String> studentiIscritti = new ArrayList<String>();
		
		for(Studente s:  dao.getStudentiIscrittiAlCorso(nomeCorso)) {
			studentiIscritti.add(s.getMatricola()+ " "+s.getNome()+" "+s.getCognome());
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
	
	

}
