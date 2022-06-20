package generator;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;
import java.util.Stack;

import javafx.application.Platform;
import javafx.event.ActionEvent;
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
	@FXML private Button oeffnen;
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
			  wortHinzufuegen(tempString);
			  }
	      
	      protected void wortHinzufuegen(String tempString) {
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
				return;
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
	      
	  	//die Methode zum Laden
	  	@FXML protected void ladenKlick(ActionEvent event) {
	  		//eine neue Instanz der Klasse FileChooser erzeugen
	  		FileChooser oeffnenDialog = new FileChooser();
	  		//den Titel für den Dialog setzen
	  		oeffnenDialog.setTitle("Datei öffnen");
	  		//die Filter setzen
	  		oeffnenDialog.getExtensionFilters().add(new ExtensionFilter("Text", "*.txt"));
	  		oeffnenDialog.getExtensionFilters().add(new ExtensionFilter("MarkDown", "*.md"));
	  		oeffnenDialog.getExtensionFilters().add(new ExtensionFilter("RichTextFormat", "*.rtf"));
	  		//den Startordner auf den Benutzerordner setzen
	  		oeffnenDialog.setInitialDirectory(new File(System.getProperty("user.home")));
	  		//den Öffnendialog anzeigen und das Ergebnis beschaffen
	  		File datei = oeffnenDialog.showOpenDialog(meineStage);
	  		if (datei != null)
	  			//dann über eine eigene Methode laden
	  			dateiLaden(datei);
	  	}
	  	
	  //die Methode lädt eine Datei
		public void dateiLaden(File dateix) {
			//das Medium, den Mediaplayer und die MediaView erzeugen
			try {	
				File datei = new File(dateix.toURI().toString());
				
				// existiert bisher ein Eintrag
				if(dateix != null) {
					datei = dateix;
					try (Scanner sc = new Scanner(datei)) {
						while(sc.hasNext()==true) {
							wortHinzufuegen(sc.next());
						}
					}
				}
				//und die Titelleiste anpassen
				meineStage.setTitle("WordlistGenFX " + datei.toString());	
				
			}
			catch(Exception ex) {
				//den Dialog erzeugen und anzeigen
				Alert meinDialog = new Alert(AlertType.INFORMATION, "Beim Laden hat es ein Problem gegeben.\n" + ex.getMessage());
				//den Text setzen
				meinDialog.setHeaderText("Bitte beachten");
				meinDialog.initOwner(meineStage);
				//den Dialog anzeigen
				meinDialog.showAndWait();
			}
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
					//Sämtliche String-Snippet`s aus dem Stack auslesen
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
