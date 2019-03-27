package it.polito.tdp.lab04.model;

import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;;

public class Model {

	public List<String> getCorsi() {
		// TODO Auto-generated method stub
		CorsoDAO dao = new CorsoDAO();
		List<String> stringheCorsi = new ArrayList<String>();
		
		for(Corso c: dao.getTuttiICorsi()) {
			stringheCorsi.add(c.getNome());
			
		}
		return stringheCorsi;
	}

	public void getCorso(String value) {
		// TODO Auto-generated method stub
		
	}
	
	

}
