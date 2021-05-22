package generator;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Stack;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

public class FXMLController {

    	  // für die entgegennahme des Wortes / (manuell beschriftbar)
	@FXML private TextField eingabe;
		  // für die Ausgabe des Wortzählers / (nicht beschriftbar)
	@FXML private TextField ausgabeAnzahl;
	@FXML private Button hinzufuegen;
	@FXML private Button speichern;
		  // für die Ausgabe der Wortliste / (nicht beschriftbar)
	@FXML private TextArea ausgabeWordlist;
	@FXML private Button beenden;
		  // der (Wort-)Zähler
		  private int counter = 0;
		  //BasisString für die Anzeige
		  private String base;
		  //Stack zum Puffern der String -Snippets
		  private  Stack<String> elemente = new Stack<String>();
		  //für die Bühne
	      private Stage meineStage;
	      
	      @FXML //die Methode beendet das Programm
		  protected void beendenKlick(ActionEvent event) {
		  Platform.exit();
	      }
	      
	      @FXML//Methode für die Eingabe der Wörter
	      protected void hinzufuegenKlick(ActionEvent event) {
			  //String für das Eingabefeld
			  String tempString = eingabe.getText();
			  //wenn Wort zwischen 1-63 Buchstaben enthält(längstes dt. Wort)
			  //und ausschliesslich aus Buchstaben enthält, dann ..
			  if(tempString.length() >= 1 && tempString.length() <= 63 
					  && tempString.matches("[a-zA-Z]+")) {
				  	  //dann das Wort im Stack puffern
					  elemente.push(tempString + "\n");
					  //und über Methode visuell ausgeben
					  eintragSetzen(tempString);
			  }
			  //und wenn nicht
			  else {
				  //den Dialog erzeugen,
				  Alert meinDialog = new Alert(AlertType.INFORMATION, "Dieses Wort entspricht nicht der Länge des für das Spiel am längsten "
			 		+ "geltenden Wortes oder enthält ein Leerzeichen bzw. Sonderzeichen. Korrigieren sie Ihre Eingabe !");
				  //den Text setzen
				  meinDialog.setHeaderText("NICHT ERLAUBT!");
				  // und anzeigen
				  meinDialog.showAndWait();
				  //Fokus auf Eingabefeld setzen
				  eingabe.requestFocus();
				}
			  }
	      
	      // Methode zum visuellen Setzen des Eintrags
	      protected void eintragSetzen(String eintrag) {
	    	    //ist Basiseintrag leer
	    	   if (base == null) 
	    		   //tempString als Basisstring setzen
	    		   base = eintrag;
	    	   else 
	    		   //andernfalls TempString der Basis
	    		   //mit Absatz setzen
	    		   base = (base + "\n" + eintrag); 
	    	   //Zähler ++
	    	   counter++;
	    	   //Zähler und BasisString setzen
	    	   ausgabeWordlist.setText(base);
	    	   ausgabeAnzahl.setText(""+counter);
	    	   //eingabefeld leeren und fokusieren
	    	   eingabe.clear();
	    	   eingabe.requestFocus(); 
	      }
	      
	      @FXML //die Methode zum Anzeigen des Speicherndialogs
	      protected void speichernKlick(ActionEvent event) {
			//eine neue Instanz der Klasse FileChooser erzeugen
			FileChooser speichernDialog = new FileChooser();
			//den Titel für den Dialog setzen
			speichernDialog.setTitle("Datei speichern");
			//den Ordner setzen
			speichernDialog.setInitialDirectory(new File(System.getProperty("user.home")));
			//den Filter setzen
			speichernDialog.getExtensionFilters().add(new ExtensionFilter("Binärdatei", "*.json"));
			//den Speicherndialog anzeigen und das Ergebnis beschaffen
			File datei = speichernDialog.showSaveDialog(meineStage);
			//wurde eine Datei ausgewählt
			if (datei != null)
			    //dann den Inhalt über eine eigene Methode speichern
				datenSchreiben(datei);
			}
	      
		  //die Methode zum Schreiben
		  //Sie erhält die Datei, die geschrieben werden soll
		  protected void datenSchreiben(File dateiSchreiben) {
			//eine Instanz der Klasse FileWriter mit der Datei erzeugen 
			try (RandomAccessFile tempDatei = new RandomAccessFile(dateiSchreiben, "rw")) {
					//Sämtliche String-Snippet`s aus dem String auslesen
					//und in die Datei schreiben
					for(int i = 0; i < counter; i++) {
						tempDatei.writeUTF(elemente.pop());
					}		
					//und abschliend den Zähler(int= 4byte) in Datei schreiben
					tempDatei.writeInt(counter);
			}	
			catch (IOException e) {
				//bei Problemen einen Dialog erzeugen
				//bei Problemen einen Dialog erzeugen
				Alert meinDialog = new Alert(AlertType.INFORMATION, "Beim Schreiben ist ein Problem aufgetreten");
				meinDialog.setHeaderText("Bitte beachten");
				meinDialog.showAndWait();
				}
		  	}
		   
		  //die Methode setzt die Bühne auf den übergebenen Wert
			public void setMeineStage(Stage meineStage) {
				this.meineStage = meineStage;
				}	
}
