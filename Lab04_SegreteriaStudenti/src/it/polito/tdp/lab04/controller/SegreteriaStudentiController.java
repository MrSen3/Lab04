package it.polito.tdp.lab04.controller;

import java.net.URL;
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

    @FXML
    void doCercaIscritti(ActionEvent event) {
    	//Questo metodo deve prendere il valore contenuto nel combobox e stampare nella text area in fondo tutti gli studenti iscritti a tale corso
    	
    	//Pulisco la view
    	txtResult.clear();
    	
    	//Se l'utente non seleziona nessun corso viene selezionato automaticamente lo spazio vuoto
    	if(comboBoxCorsi.getValue()==null) {
    		comboBoxCorsi.setValue(comboBoxCorsi.getItems().get(0));
    	}
    	
    	    	
    	Corso corso = new Corso(comboBoxCorsi.getValue());
    	System.out.println(corso.getNome());
    	
    	
    	if(corso.getNome().isEmpty()) { 
    		txtResult.appendText("ATTENZIONE: Nessun corso è stato selezionato!\n");
    		return;
    	} 
    	
    	List<String> iscrittiAlCorso = this.model.getCorso(corso.getNome());
    	
    	for(String s: iscrittiAlCorso) {
    		txtResult.appendText(s+ "\n");
    	}
    	
    	
    
    }
    
    @FXML
    void doCercaCorsi(ActionEvent event) {
    	//Questo metodo deve prendere la matricola contenuta nel textfield apposito, controllare se e' presennte nel database, e se lo e' stampare nella text area in fondo tutti i corsi a cui e' iscritto
    	txtResult.clear();
    	int matricola;
    	Studente s;
    	
    	try {
    		//Questa funziona bene
    		matricola=Integer.parseInt(txtMatricola.getText());
   
    		s=model.getNomeECognome(matricola);
    		
    		if(s==null) {
    			txtResult.appendText("Nessuno studente corrispondente alla matricola!\n");
			return;
		}
    		
    		//Se lo studente esiste bisogna stampare nella textResult tutti i corsi a cui è iscritto
    		model.getCorsiACuiEIscritto(s);
    		
    	} catch (NumberFormatException e) {
    		txtResult.appendText("Devi inserire una matricola composta da 6 cifre\n");
     	} 
    }

    @FXML
    void doCompleta(ActionEvent event) {
    	//Scritta la matricola il pulsante verde deve completare automaticamente i campi nome e cognome, tanto la matricola e' univoca
    	
    	txtResult.clear();
		txtNome.clear();
		txtCognome.clear();
    	
		int matricola;
		Studente s;
		
    	try {
    		//Questa funziona bene
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
