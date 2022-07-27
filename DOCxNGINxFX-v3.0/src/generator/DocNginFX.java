package generator;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DocNginFX extends Application{
	@Override
	public void start(Stage meineStage) throws Exception {
		//eine Instanz von FXMLLoader erzeugen
		FXMLLoader meinLoader = new FXMLLoader(getClass().getResource("Generatorv3.fxml"));
		//die Datei laden
		Parent root = meinLoader.load();
		//den Controller beschaffen
		FXMLController meinController = meinLoader.getController();
		//und die Bühne übergeben
		meinController.setMeineStage(meineStage);
		//die Szene erzeugen
		//an den Konstruktor werden der oberste Knoten und die Größe übergeben
		Scene meineScene = new Scene(root, 1400,848);
		//den Titel über stage setzen
		meineStage.setTitle("DOC:NGIN:FX \t by HellGateCorp");
		meineStage.setResizable(true);
		//die Szene setzen
		meineStage.setScene(meineScene);
		//und anzeigen
		meineStage.show();
		meinController.start();
		meinController.playAudio();
	}
	public void zeichneNeuDark(Stage meineStage) throws IOException {
				
				//eine Instanz von FXMLLoader erzeugen
				FXMLLoader meinLoader = new FXMLLoader(getClass().getResource("Generatorv3-dark.fxml"));
				//die Datei laden
				Parent root = meinLoader.load();
				//den Controller beschaffen
				FXMLController meinController = meinLoader.getController();
				//und die Bühne übergeben
				meinController.setMeineStage(meineStage);
				//die Szene erzeugen
				//an den Konstruktor werden der oberste Knoten und die Größe übergeben
				Scene meineScene = new Scene(root, 1400,848);
				//den Titel über stage setzen
				meineStage.setTitle("DOC:NGIN:FX \t by HellGateCorp");
				meineStage.setResizable(true);
				//die Szene setzen
				meineStage.setScene(meineScene);
				//und anzeigen
				meineStage.show();
				meinController.start();
				
	}
public void zeichneNeuGirlie(Stage meineStage) throws IOException {
		
		//eine Instanz von FXMLLoader erzeugen
		FXMLLoader meinLoader = new FXMLLoader(getClass().getResource("Generatorv3-girlie.fxml"));
		//die Datei laden
		Parent root = meinLoader.load();
		//den Controller beschaffen
		FXMLController meinController = meinLoader.getController();
		//und die Bühne übergeben
		meinController.setMeineStage(meineStage);
		//die Szene erzeugen
		//an den Konstruktor werden der oberste Knoten und die Größe übergeben
		Scene meineScene = new Scene(root, 1400,848);
		//den Titel über stage setzen
		meineStage.setTitle("DOC:NGIN:FX \t by HellGateCorp");
		meineStage.setResizable(true);
		//die Szene setzen
		meineStage.setScene(meineScene);
		//und anzeigen
		meineStage.show();
		meinController.start();
	}
}
