package it.polito.tdp.lab04.model;

import java.util.List;
import java.util.ArrayList;


public class TestModel {

	public static void main(String[] args) {
		
		Model model = new Model();
		
		/*
		 * 	Write here your test model
		 */
		Studente s = model.getNomeECognome(146101);
		System.out.println(s.toString()+"\n");
		
		List<Corso> corsi = new ArrayList<Corso>();
		corsi=model.getCorsiACuiEIscritto(s);
		for(Corso c: corsi) {
			System.out.println(c.toString()+"\n");
		}
		
		//Restituisce true perchè cerco nella lista di corsi a cui  iscritto
		System.out.println(model.isIscritto(s, corsi.get(1))+"\n");
	}
		
		//Quua funziona tutto ma dal controller no
}
