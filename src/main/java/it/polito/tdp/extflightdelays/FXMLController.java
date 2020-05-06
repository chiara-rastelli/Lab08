/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.extflightdelays;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import org.jgrapht.graph.DefaultWeightedEdge;

import it.polito.tdp.extflightdelays.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

	private Model model;
	
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML // fx:id="distanzaMinima"
    private TextField distanzaMinima; // Value injected by FXMLLoader

    @FXML // fx:id="btnAnalizza"
    private Button btnAnalizza; // Value injected by FXMLLoader

    @FXML
    void doAnalizzaAeroporti(ActionEvent event) {
    
    try {	
    	
    	int distanzaMinima = Integer.parseInt(this.distanzaMinima.getText());
    	
    	if (distanzaMinima <= 0) {
    		this.txtResult.setText("Devi inserire una distanza minima valida, quindi > 0");
    		return;
    		}
    	else {
    		this.model.creaGrafo(distanzaMinima);
    //		this.txtResult.setText(this.model.getGraph().edgeSet().toString());
    		
    		for (DefaultWeightedEdge d : this.model.getGraph().edgeSet()) {
    			this.txtResult.appendText(d.toString()+" peso: "+this.model.getGraph().getEdgeWeight(d)+"\n");
    		}
    		
    		this.txtResult.appendText("Grafo caricato con "+this.model.getGraph().vertexSet().size()+" vertici e "+
    									this.model.getGraph().edgeSet().size()+" archi");
    	}
    } catch (NumberFormatException e) {
    		this.txtResult.setText("Devi inserire un numero!");;
    	}
    	
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
        assert distanzaMinima != null : "fx:id=\"distanzaMinima\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnAnalizza != null : "fx:id=\"btnAnalizza\" was not injected: check your FXML file 'Scene.fxml'.";
    }
    
    public void setModel(Model model) {
    	this.model = model;
    }
}
