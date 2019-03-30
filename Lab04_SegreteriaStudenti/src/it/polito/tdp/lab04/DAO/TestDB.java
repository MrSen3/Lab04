package it.polito.tdp.lab04.DAO;

public class TestDB {

	public static void main(String[] args) {
		
		/*
		 * 	This is a main to check the DB connection
		 */
		
		CorsoDAO cdao = new CorsoDAO();
		cdao.getTuttiICorsi();
		
		//cdao.getCorso(new Corso("01KSUPG"));
		//C'è un problema con la classe studentedao
		StudenteDAO sdao = new StudenteDAO();
		sdao.getTuttiStudenti();

	}

}
