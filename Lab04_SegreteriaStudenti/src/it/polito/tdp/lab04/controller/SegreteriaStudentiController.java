package it.polito.tdp.lab04.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.lab04.model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class SegreteriaStudentiController {

	
	private Model model;
	
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> comboBoxCorsi;

    @FXML
    private Button btnCercaIscritti;

    @FXML
    private TextField txtMatricola;

    @FXML
    private Button btnCompleta;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtCognome;

    @FXML
    private Button btnCercaCorsi;

    @FXML
    private TextArea txtResult;

    @FXML
    private Button btnReset;

    
    /*
     * Questo metodo deve prendere il valore contenuto nel combobox (cioè un nomeCorso)
     * e stampare nella text area in fondo tutti gli studenti iscritti a tale corso
     */
    
    @FXML
    void doCercaIscritti(ActionEvent event) {
    	
    	//Pulisco la view
    	txtResult.clear();
    	
    	String nomeCorsoScelto = comboBoxCorsi.getValue();
    	System.out.println(nomeCorsoScelto);
    	
    	
    	//Se l'utente non seleziona nessun corso viene selezionato automaticamente lo spazio vuoto
    	if(nomeCorsoScelto==null) {
    		comboBoxCorsi.setValue(comboBoxCorsi.getItems().get(0));
    	}
    	
    	
    	if(nomeCorsoScelto.isEmpty()) { 
    		txtResult.appendText("ATTENZIONE: Nessun corso è stato selezionato!\n");
    		return;
    	} 
    	
    	
    	//A questo punto prendo il nome del corso scelto e ricavo il Corso corrispondente
    	Corso c = this.model.getCorsoDatoNome(nomeCorsoScelto);
    	
    	List<String> iscrittiAlCorso = this.model.getIscrittiAlCorso(c);
    	
    	for(String s: iscrittiAlCorso) {
    		txtResult.appendText(s+ "\n");
    	}    	
    
    }
    
    
    /*
     * Questo metodo ha due funzioni:
     * 1)se viene inserito solo lo studente, deve prendere la matricola contenuta nel textfield apposito, controllare se e' presente 
     * nel database, e se lo e' stampare nella text area in fondo tutti i corsi  a cui e' iscritto lo studente;
     * 2)se viene inserito sia lo studente che il corso, allora deve cercare nel db se lo studente è iscritto al corso specificato 
     * e stampare l'esito della ricerca nella textArea
     */
    @FXML
    void doCercaCorsi(ActionEvent event) {
    	
    	txtResult.clear();
    	
    	
    	int matricola;
    	Studente studente;
    	String nomeCorsoScelto = comboBoxCorsi.getValue();
    	List <Corso> corsi = new ArrayList<Corso>();
    	
    	try {
    		
    		matricola=Integer.parseInt(txtMatricola.getText());
   
    		studente=model.getNomeECognome(matricola);
    		System.out.println(studente.toString());
    		//Se non esiste la matricola nel db
    		if(studente == null) {
    			txtResult.appendText("Nessuno studente corrispondente alla matricola!\n");
    			return;
    		}
    		
    		//Se lo studente esiste stampo nei due textfield nome e cognome
    		txtNome.setText(studente.getNome());
        	txtCognome.setText(studente.getCognome());
        	
        	
        	
        	//CASO 1: nessun corso selezionato= bisogna stampare tutti i corsi a cui è iscritto
        	if(nomeCorsoScelto==null) {         		
        		//Se lo studente esiste bisogna stampare nella textResult tutti i corsi a cui è iscritto
        		
        		//Devo crearmi un metodo nel model a cui passo lo studente che mi deve restituire la lista di corsi a cui è iscritto
        		corsi=model.getCorsiACuiEIscritto(studente);
        	
        		//Stampo tutti i corsi nella textArea
        		for (Corso c: corsi) {
        			txtResult.appendText(c.toString()+"\n");
        		}
        	
        	
        	}
        	//CASO 2: corso selezionato= bisogna verificare se la matricola è iscritta al corso selezionato
        	else {
        		Corso c = this.model.getCorsoDatoNome(nomeCorsoScelto);
        		boolean iscritto=false;
        		
        		
        		
        		
        	}
    	}
    	
    	
    	
    	catch (NumberFormatException e) {
    		txtResult.appendText("Devi inserire una matricola composta da 6 cifre\n");
     	} catch(RuntimeException e) {
			txtResult.setText("ERRORE DI CONNESSIONE AL DATABASE!");
     	}  
    }

    /*
     * //Scritta la matricola il pulsante verde deve completare automaticamente i campi nome e cognome,
     *  tanto la matricola e' univoca. FUNZIONA
     */
    @FXML
    void doCompleta(ActionEvent event) {
    	
    	
    	txtResult.clear();
		txtNome.clear();
		txtCognome.clear();
    	
		int matricola;
		Studente s;
		
    	try {
    	
    		matricola=Integer.parseInt(txtMatricola.getText());
    		
    		//Cerco lo studente tramite il metodo nel model
    		s=model.getNomeECognome(matricola);
    		
    		//Se non esiste la matricola nel db
    		if(s == null) {
    			txtResult.appendText("Nessuno studente corrispondente alla matricola!\n");
    			return;
    		}
    		
    		//Se lo studente esiste stampo nei due textfield nome e cognome
    		txtNome.setText(s.getNome());
        	txtCognome.setText(s.getCognome());
    		
    	} catch (NumberFormatException e) {
    		txtResult.appendText("Devi inserire una matricola composta da 6 cifre\n");
     	} catch (RuntimeException e) {
			txtResult.setText("ERRORE DI CONNESSIONE AL DATABASE!\n");
     	}
    	
    	//Studente studente=new Studente(matricola);
    	
    	
    	
    }

    @FXML
    void doIscrizione(ActionEvent event) {

    
    }

    @FXML
    void doReset(ActionEvent event) {
    	//comboBoxCorsi.setValue(comboBoxCorsi.getItems().get(0)); così si mette sul primo valore della lista
    	//comboBoxCorsi.setValue("Corsi");
    	comboBoxCorsi.getSelectionModel().clearSelection();
    	txtMatricola.clear();
    	txtNome.clear(); 
    	txtCognome.clear();   	
    	txtResult.clear();
    	
    }

    @FXML
    void initialize() {
        assert comboBoxCorsi != null : "fx:id=\"comboBoxCorsi\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnCercaIscritti != null : "fx:id=\"btnCercaIscritti\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtMatricola != null : "fx:id=\"txtMatricola\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnCompleta != null : "fx:id=\"btnCompleta\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtCognome != null : "fx:id=\"txtCognome\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnCercaCorsi != null : "fx:id=\"btnCercaCorsi\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";

    }

	public void setModel(Model model) {
		// TODO Auto-generated method stub
		this.model=model;
		comboBoxCorsi.getItems().add("");
		comboBoxCorsi.getItems().addAll(model.getCorsi());
		
	}

}
