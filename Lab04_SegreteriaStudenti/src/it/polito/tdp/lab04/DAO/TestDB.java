package it.polito.tdp.lab04.DAO;

import it.polito.tdp.lab04.model.*;
import java.util.*;

public class TestDB {

	public static void main(String[] args) {
		
		/*
		 * 	This is a main to check the DB connection
		 */
		
		CorsoDAO cdao = new CorsoDAO();
		StudenteDAO sdao = new StudenteDAO();
		List<Corso> corsi=new ArrayList<>();
		corsi=cdao.getTuttiICorsi();
		List<Studente>studenti= new ArrayList<Studente>();
		studenti=sdao.getTuttiStudenti();
		
		
		
		
		//cdao.iscriviDao(studenti.get(1), studenti.get(1).);

	}

}
