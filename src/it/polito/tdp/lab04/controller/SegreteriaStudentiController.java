package it.polito.tdp.lab04.controller;

import java.util.*;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;

import it.polito.tdp.lab04.DAO.ConnectDB;
import it.polito.tdp.lab04.model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class SegreteriaStudentiController {
	
	Model model ;
	
    public void setModel(Model model) {
		this.model = model;
		boxCorsi.getItems().addAll(model.getCorsi());
	}

	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Corso> boxCorsi;

    @FXML
    private TextField txtMatricola;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtCognome;

    @FXML
    private TextArea txtResult;

    @FXML
    void doCercaCorsi(ActionEvent event) {
    	txtResult.appendText("\n");
    	if(!controlloCorso()){
    		//Integrare controllo che la matricola inserita sia un numero
    		int matricola = Integer.parseInt(txtMatricola.getText());
    		if(model.cercaStudente(matricola)==null){
    			txtResult.appendText("WARNING: STUDENTE NON TROVATO\n");
    			return;
    		}
    	
    		List<Corso> corsi = model.cercaCorsiDiUnoStudente(matricola);
    		if(corsi.isEmpty()){
    			txtResult.appendText("Lo studente inserito non è iscritto ad alcun corso.\n");
    			return;
    		} else {txtResult.appendText("La matricola inserita e' iscritta ai seguenti corsi:\n");
    			for(Corso c : corsi){
    				txtResult.appendText(c.toString());
    			}
    		}
    	} 
    	else{
    		Corso c = boxCorsi.getValue();
    		int m = Integer.parseInt(txtMatricola.getText());
    		if(model.isIscritto(c,m))
    			txtResult.appendText("Lo studente inserito è iscritto al corso scelto\n");
    		else txtResult.appendText("Lo studente inserito non è iscritto al corso scelto\n");
    	}
    	
    }

    @FXML
    void doCercaIscrittiCorso(ActionEvent event) {
    	if(!controlloCorso()){
    		txtResult.appendText("Non è stato selezionato alcun corso\n");
    		return;
    	}
    	txtResult.appendText("Gli studenti iscritti al corso selezionato sono:\n");
    	for(Studente s : model.cercaStudentiIscrittiAlCorso(boxCorsi.getValue()))
    		txtResult.appendText(s.toString());
    	

    }

    @FXML
    void doCercaNome(ActionEvent event) {
    	//Inserire controllo che l'utente abbia inserito effettivamente una matricola INT
    	
    	Studente s = model.cercaStudente(Integer.parseInt(txtMatricola.getText()));
    	if(s==null){
    		txtNome.setText("STUDENT NON FOUND");
    		txtCognome.setText("STUDENT NOT FOUND");
    	} else {
    		txtNome.setText(s.getNome());
    		txtCognome.setText(s.getCognome());
    	}
    }

    @FXML
    void doIscrivi(ActionEvent event) {
    	txtResult.appendText("\n");
    	if(model.iscriviStudente(Integer.parseInt(txtMatricola.getText()), boxCorsi.getValue()))
    		txtResult.appendText("Studente iscritto con successo");
    	else txtResult.appendText("Iscrizione dello studente al corso selezionato fallita!");
    }

    @FXML
    void doReset(ActionEvent event) {
    	boxCorsi.setPromptText("");
    	txtMatricola.clear();
    	txtNome.clear();
    	txtCognome.clear();
    	txtResult.clear();

    }
    
    private boolean controlloCorso(){
    	if(boxCorsi.getValue().getCod()==null)
    		return false;
    	return true ;
    }

    @FXML
    void initialize() {
        assert boxCorsi != null : "fx:id=\"boxCorsi\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtMatricola != null : "fx:id=\"txtMatricola\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtCognome != null : "fx:id=\"txtCognome\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        
        boxCorsi.getItems().add(new Corso(null,0,"",0));
       
    }

}

