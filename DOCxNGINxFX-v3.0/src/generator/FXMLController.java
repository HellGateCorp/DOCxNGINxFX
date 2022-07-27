package generator;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.util.Duration;

public class FXMLController extends DocNginFX{
	
	@FXML private PieChart pie=new PieChart();
	@FXML private PieChart pie2=new PieChart();
	@FXML private Button switcher=new Button();
	@FXML private Button switcherBlack=new Button();
	@FXML private Button switcherGIRLIE=new Button();
	@FXML private Button hinzufuegen=new Button();
	@FXML private Button speichern=new Button();
	@FXML private Button oeffnen=new Button();
	@FXML private Button beenden=new Button();
	@FXML private Button transform=new Button();
	@FXML private Button search=new Button();
	@FXML private RadioButton woDE=new RadioButton();
	@FXML private RadioButton woNR=new RadioButton();	
	@FXML private RadioButton toU=new RadioButton(); 	
	@FXML private RadioButton toL=new RadioButton();;
	@FXML private CheckBox chABC=new CheckBox();
	@FXML private CheckBox chAbc=new CheckBox();
	@FXML private CheckBox chabc=new CheckBox();
	@FXML private CheckBox chnr=new CheckBox();
	@FXML private CheckBox chcombo=new CheckBox();
	@FXML private CheckBox chkey=new CheckBox();
	@FXML private CheckBox chendings=new CheckBox();
	@FXML private TextArea txtABC=new TextArea();
	@FXML private TextArea txtAbc=new TextArea();
	@FXML private TextArea txtabc=new TextArea();
	@FXML private TextArea txtn=new TextArea();
	@FXML private TextArea txtcombo=new TextArea();
	@FXML private TextArea txtKey=new TextArea();
	@FXML private TextArea txtEndings=new TextArea();
	@FXML private TextField eingabe=new TextField();
	@FXML private TextField eingabe2=new TextField();
	@FXML private TextField eingabe3=new TextField();
	@FXML private TextField eingabe4=new TextField();
	@FXML private TextField eingabe5=new TextField();
	@FXML private TextField eingabe6=new TextField();
	@FXML private TextField txABC=new TextField();
	@FXML private TextField txAbc=new TextField();
	@FXML private TextField txabc=new TextField();
	@FXML private TextField txnr=new TextField();
	@FXML private TextField txCombo=new TextField();
	@FXML private TextField txCount=new TextField();
	@FXML private TextField txKey=new TextField();
	@FXML private TextField txCountDE=new TextField();
	@FXML private TextField txEndings=new TextField();
	@FXML private TextField txtimer=new TextField();
	@FXML private Label abc=new Label();
	@FXML private Label Abc=new Label();
	@FXML private Label etc=new Label();
	@FXML private MediaView mediaView = new MediaView();
	@FXML private MediaView mediaView2 = new MediaView();
	@FXML private MediaView mediaView3 = new MediaView();
	private Media media,media2,media3;
	private MediaPlayer player,player2,player3;
		 
		  public long startTime,stopTime,time;
		  // der (Wort-)Zähler
		  private int flagLoad,minute,second,counterEndings = 0,counter = 0,counterDE= 0,counterABC= 0,counterAbc= 0,counterabc= 0,counternr= 0,countercombo= 0,counterkey= 0;
		  public final int faktor=1000;
		  public boolean boDE=true,boU,boL,doppelt,boThemeDark,boThemeMild=true,boThemeGirlie,boTransform;
		  //BasisString für die Anzeige
		  private String baseEndings, baseABC,baseAbc,baseabc,baseNr,baseCombo, baseKey;
		  //Stack zum Puffern der String -Snippets
		  private ArrayList<String> eintrag, eintragTransform;
		  //für die Bühne
	      private Stage meineStage;
	      
	      @FXML //die Methode beendet das Programm
		  protected void beendenKlick(ActionEvent event) throws Exception {
	    	  System.exit(0);
	      }
	      
	      @FXML public void start() {
	  		audioAnimation();
	  	}
	      
	      @FXML public void audioAnimation() {
	  		
	  		try {	
	  			media = new Media(getClass().getResource("/sound/buffershootout.wav").toExternalForm());
	  			player = new MediaPlayer(media);
	  			mediaView.setMediaPlayer(player);
	  			player.setVolume(100);

	  			
	  			media2 = new Media(getClass().getResource("/sound/Bell.wav").toExternalForm());
	  			player2 = new MediaPlayer(media2);
	  			mediaView2.setMediaPlayer(player2);
	  			player2.setVolume(50);

	  			
	  			media3 = new Media(getClass().getResource("/sound/wheel.mp3").toExternalForm());
	  			player3 = new MediaPlayer(media3);
	  			mediaView3.setMediaPlayer(player3);
	  			player3.setVolume(50);
	  			
	  			
	  		}
	  		catch(Exception e) {
	  			System.out.println(e);}
	  		}
	    
	      protected void playAudio() {
	    	  player.play();
	      }

	      @FXML //die Methode beendet das Programm
		  protected void neuKlick(ActionEvent event) throws Exception {
	    	  if(boThemeMild==true)
	    		  switcherKlick(event);
	    	  else if(boThemeDark==true)
	    		  switcherKlickBlack(event);
	    	  else if(boThemeGirlie==true)
	    		  switcherKlickGirlie(event);
	      }
	      
	      @FXML //die Methode beendet das Programm
		  protected void clearAll() {
	    	  counter=0;
	    	  txtimer.clear();
	    	  txCount.clear();
	    	  txCountDE.clear();
	    	  txtABC.clear();
	    	  txABC.clear();
	    	  baseABC=null;
		  	  counterABC=0;
	    	  txtAbc.clear();
	    	  txAbc.clear();
	    	  baseAbc=null;
		  	  counterAbc=0;
	    	  txtabc.clear();
	    	  txabc.clear();
	    	  baseabc=null;
		  	  counterabc=0;
	    	  txtEndings.clear();
	    	  txEndings.clear();
	    	  baseEndings=null;
		  	  counterEndings=0;
	    	  txtn.setText("");
	    	  txnr.clear();
	    	  baseNr=null;
		  	  counternr=0;
	    	  txtKey.clear();
	    	  txKey.clear();
	    	  baseKey=null;
		  	  counterkey=0;
	    	  txtcombo.clear();
	    	  txCombo.clear();
	    	  baseCombo=null;
		  	  countercombo=0;
		  	eingabe.clear();
		  	eingabe2.clear();
		  	eingabe3.clear();
		  	eingabe4.clear();
		  	eingabe5.clear();
		  	Abc.setVisible(false);
		  	abc.setVisible(false);
		  	etc.setVisible(false);
	      }
	      
	      @FXML//Methode für die Eingabe der Wörter
	      protected void switcherKlick(ActionEvent event) throws Exception {
		    	  try {
		    		  super.start(meineStage);
		    		  boThemeMild=true;
		    		  boThemeDark=false;
		    		  boThemeGirlie=false;
		    	  } 	
		    	  catch (IOException e) {
		    		  // TODO Auto-generated catch block
		    		  e.printStackTrace();
				}
	      }
	      
	      @FXML//Methode für die Eingabe der Wörter
	      protected void switcherKlickBlack(ActionEvent event) throws Exception {
		    	 try {
		    		super.zeichneNeuDark(meineStage);
		    		 boThemeMild=false;
		    		  boThemeDark=true;
		    		  boThemeGirlie=false;
		    	 } 	
		    	 catch (IOException e) {
	    		  // TODO Auto-generated catch block
	    		  e.printStackTrace();
				}
	      }
	      
	      @FXML//Methode für die Eingabe der Wörter
	      protected void switcherKlickGirlie(ActionEvent event) throws Exception {
		    	 try {
		    		super.zeichneNeuGirlie(meineStage);
		    		 boThemeMild=false;
		    		  boThemeDark=false;
		    		  boThemeGirlie=true;
		    	 } 	
		    	 catch (IOException e) {
	    		  // TODO Auto-generated catch block
	    		  e.printStackTrace();
				}
	      }
	      
	      @FXML protected void transformKlick(ActionEvent event) {
	    	  ArrayList<String> eintragTransform=new ArrayList<String>();
	    	  String tempString;
	    	  clearAll();
	    	  timerStart();
	    	  for(int i=0;i<=eintrag.size()-1;i++) {
	    		  tempString=eintrag.get(i);
	    		  eintragTransform.add(tempString);
	    		  }
	    	  for(int i=0;i<=eintragTransform.size()-1;i++) {
    			  tempString=eintragTransform.get(i);
    			  eintrag.add(tempString);
    			  wortHinzufuegen(tempString);
    			  if(i==eintrag.size()-1) {
    				  timerStop();
    			  }
    			}
	    	  }
	      
	      @FXML//Methode für die Eingabe der Wörter
	      protected void searchKlick(ActionEvent event) {
	    	  boolean doubled = false;
	    	  String tempString;
	    	  Timeline timer = new Timeline(new KeyFrame(Duration.seconds(2), e -> eingabe5.clear()));
	    	  if(eingabe.getText() !=null) {
    			  tempString =  eingabe5.getText();
    			  if(eintrag.size()>=1) {
    			  for(int i=0;i<=eintrag.size()-1;i++) {
    				  if(tempString.compareTo(eintrag.get(i))==0) {
    					  doubled=true;
    					  break;
    				  }
    				  else
    					  doubled=false;
    			  		}
    			  	}
    			  else if(eintragTransform.size()>=1) {
        			  for(int i=0;i<=eintragTransform.size()-1;i++) {
        				  if(tempString.compareTo(eintragTransform.get(i))==0) {
        					  System.out.println(tempString + " 5");
        					  doubled=true;
        					  break;
        				  }
        				  else
        					  doubled=false;
        			  }}
    			  if(doubled==true)
    				  eingabe5.setText("TRUE !");
    			  else
    				  eingabe5.setText("FALSE !");
    			  timer.play();
    			  }
    		  }
	      

	      public ObservableList<Data>  getChartData() {  
	    	  Abc.setVisible(true);
			  	abc.setVisible(true);
			  	etc.setVisible(true);
	    	  pie.setLabelLineLength(50);
	    	    ObservableList<Data> list = FXCollections.observableArrayList();  
	    	    list.addAll(new PieChart.Data("[Abc]-Lines", counterAbc),  
	    	            new PieChart.Data("[abc]-Lines", counterabc),  
	    	            new PieChart.Data("[ABC]-lines", counterABC),  
	    	            new PieChart.Data("[0-9]-Lines", counternr),  
	    	            new PieChart.Data("[Time:Stamps]", counterkey),  
	    	            new PieChart.Data("[@WebLinks]", counterEndings),  
	    	            new PieChart.Data("[e@Mail]", countercombo));  
	    	    
	    	    return list;
	    	}  
	      
	      public ObservableList<Data>  getChartData2() {  
	    	  Abc.setVisible(true);
			  	abc.setVisible(true);
			  	etc.setVisible(true);
	    	  pie.setLabelLineLength(40);
	    	    ObservableList<Data> list = FXCollections.observableArrayList();  
	    	    list.addAll(new PieChart.Data("[Abc]-Lines", counterAbc),  
	    	            new PieChart.Data("[abc]-Lines", counterabc),  
	    	            new PieChart.Data("[ABC]-lines", counterABC),  
	    	            new PieChart.Data("[0-9]-Lines", counternr),  
	    	            new PieChart.Data("[Time:Stamps]", counterkey),  
	    	            new PieChart.Data("[@WebLinks]", counterEndings),  
	    	            new PieChart.Data("[e@Mail]", countercombo));  
	    	    
	    	    return list;
	    	}  
	      
	      @FXML//Methode für die Eingabe der Wörter
	      protected void hinzufuegenKlick(ActionEvent event) {
	    	  try {
	    		  String tempString,tempString2,tempString3,tempString4;
	    		//String für das Eingabefeld
	    		  if(eingabe.getText() !=null) {
	    			  tempString =  eingabe.getText();
	    			  wortHinzufuegen(tempString);
	    			  eingabe.clear();
	    		  }
	    		  if(eingabe2.getText() !=null) {
	    			  tempString2 =  eingabe2.getText();
	    			  wortHinzufuegen(tempString2);
	    			  eingabe2.clear();
	    		  }
	    		  if(eingabe3.getText() !=null) {
	    			  tempString3 =  eingabe3.getText();
	    			  wortHinzufuegen(tempString3);
	    			  eingabe3.clear();
	    		  }
	    		  if(eingabe4.getText() !=null) {
	    			  tempString4 =  eingabe4.getText();
	    			  wortHinzufuegen(tempString4);
	    			  eingabe4.clear();
	    		  }
	    		  	  
	    	  }
	    	  catch(Exception e) {
	    	  }
		  } 	
	      
	      protected void wortHinzufuegen(String tempString) {
	    	  	
	    	  String sign="@",doti=".",doubleDoti=":",slashi="/";
	    	  
	    	  if(toL.isSelected()==true && boL==true)
	    		  tempString=tempString.toLowerCase();
	    	  if(toU.isSelected()==true && boU==true)
	    		  tempString=tempString.toUpperCase();
	    	  
	    	  if(tempString.length() > 2 && tempString.length() <= 63 
					  && tempString.matches("[A-ZÄÜÖ,.:?!]+")&& chABC.isSelected() == true) {
					  //und über Methode visuell ausgeben
					  eintragSetzenABC(tempString + "\n");
			  }
			  else if(tempString.length() > 2 && tempString.length() <= 63 
					  && tempString.matches("[a-zäüö,.:?!]+") && chabc.isSelected() == true) {
					  //und über Methode visuell ausgeben
					  eintragSetzenabc(tempString + "\n");
			  }			  
	    	  //wenn Wort zwischen 1-63 Buchstaben enthält(längstes dt. Word)
			  //und ausschliesslich aus Buchstaben enthält, dann ..
			  else if(chAbc.isSelected() == true  && tempString.length() > 2 && tempString.length() <= 63 
					  && tempString.matches("^([A-ZÄÖÜ])[a-zaöü,.:?!]+")) {
					  eintragSetzenAbc(tempString + "\n");
			  }
			  else if(tempString.length() >= 1 && tempString.length() <= 63 
					  && tempString.matches("[0-9.,?!]+")&& chnr.isSelected() == true) {
					  //und über Methode visuell ausgeben
					  eintragSetzenNR(tempString + "\n");
			  }
			  else if(tempString.length() >= 1 && tempString.length() <= 63 && tempString.matches("^(https://)[a-zA-Z:/.]+") || tempString.matches("^(http://[a-zA-Z0-9:/.]+)")
					   && chendings.isSelected() == true) {
					  //und über Methode visuell ausgeben
					  eintragSetzenEndings(tempString + "\n");
			  }
			  
			  else if(chkey.isSelected() == true && tempString.length() >= 5 && tempString.length() <= 10 && tempString.matches("[[0-9:][0-9:][0-9]]+") || 
					  tempString.matches("[[0-9.][0-9.][0-9]]+") || tempString.matches("[[0-9/][0-9/][0-9]]+")) {
				  		//dann das Wort im Stack puffern
				  		if(tempString.length() == 5) {
					  		if(tempString.charAt(2) == doti.charAt(0))
					  			eintragSetzenKey(tempString + "\n");
					  		else if(tempString.charAt(2) == doubleDoti.charAt(0))
					  			eintragSetzenKey(tempString + "\n");
					  		else if(tempString.charAt(2) == slashi.charAt(0))
					  			eintragSetzenKey(tempString + "\n");
					  		}
				  		
				  		//und über Methode visuell ausgeben
				  		else if(tempString.length() == 8 || tempString.length() == 10) {
					  		if(tempString.charAt(2) == doti.charAt(0) && tempString.charAt(5) == doti.charAt(0))
					  			eintragSetzenKey(tempString + "\n");
					  		else if(tempString.charAt(2) == doubleDoti.charAt(0) && tempString.charAt(5) == doubleDoti.charAt(0))
					  			eintragSetzenKey(tempString + "\n");
					  		else if(tempString.charAt(2) == slashi.charAt(0) && tempString.charAt(5) == slashi.charAt(0))
					  			eintragSetzenKey(tempString + "\n");
					  		}
			  }
	    	  
	    	  else if(tempString.length() >= 1 && tempString.length() <= 100 && tempString.matches("[a-zA-Z0-9@.]+")
	    			  && chcombo.isSelected() == true) {
	    		  	  int flagAtSign=0,flagAtDot=0;
					  for(int i=0;i<tempString.length()-1;i++) {
						  if(tempString.charAt(i) == sign.charAt(0))
							  flagAtSign++;
						  if(tempString.charAt(i) == doti.charAt(0))
							  flagAtDot++;
					  }
					  	  if(flagAtSign==1 && flagAtDot!=0 && flagAtDot<=2)
					  		eintragSetzenCombo(tempString + "\n");
					  	  else
					  		return;
			  }
			  //und wenn nicht
			  else 
				  return; 	  
	      }
	      
	   // Methode zum visuellen Setzen des Eintrags
	      protected void eintragSetzenABC(String eintrag) {
	    	  if(boTransform==true) {
	    		  eintragTransform.add(eintrag);
	    	  }
	    	   //ist Basiseintrag leer
	    	   if (baseABC == null) 
	    		   //tempString als Basisstring setzen
	    		   baseABC = eintrag;
	    	   else 
	    		   //andernfalls TempString der Basis
	    		   //mit Absatz setzen
	    		   baseABC = (eintrag+baseABC); 
	    	  
	    	   counter++;
	    	   counterABC++;
	    	   //Zähler und BasisString setzen
	    	   txtABC.setText(baseABC);
	    	   txCount.setText(counter+"");
	    	   txABC.setText(counterABC+"");
	      }
	      
	      // Methode zum visuellen Setzen des Eintrags
	      protected void eintragSetzenAbc(String eintrag) {
	    	  if(boTransform==true) {
	    		  eintragTransform.add(eintrag);
	    	  }
	    	    //ist Basiseintrag leer
	    	   if (baseAbc == null) 
	    		   //tempString als Basisstring setzen
	    		   baseAbc = eintrag;
	    	   else 
	    		   //andernfalls TempString der Basis
	    		   //mit Absatz setzen
	    		   baseAbc = (eintrag+baseAbc); 
	    	 
	    	   counter++;
	    	   counterAbc++;
	    	   //Zähler und BasisString setzen
	    	   txtAbc.setText(baseAbc);
	    	   txCount.setText(counter+"");
	    	   txAbc.setText(counterAbc+"");
	      }
	    
	      // Methode zum visuellen Setzen des Eintrags
	      protected void eintragSetzenabc(String eintrag) {
	    	    //ist Basiseintrag leer
	    	   if (baseabc == null) 
	    		   //tempString als Basisstring setzen
	    		   baseabc = eintrag;
	    	   else 
	    		   //andernfalls TempString der Basis
	    		   //mit Absatz setzen
	    		   baseabc = (eintrag+baseabc); 
	    	  
	    	   counter++;
	    	   counterabc++;
	    	   //Zähler und BasisString setzen
	    	   txtabc.setText(baseabc);
	    	   txCount.setText(counter+"");
	    	   txabc.setText(counterabc+"");
	      }
	      
	      // Methode zum visuellen Setzen des Eintrags
	      protected void eintragSetzenNR(String eintrag) {
	    	    //ist Basiseintrag leer
	    	   if (baseNr == null) 
	    		   //tempString als Basisstring setzen
	    		   baseNr = eintrag;
	    	   else 
	    		   //andernfalls TempString der Basis
	    		   //mit Absatz setzen
	    		   baseNr = (eintrag+baseNr); 
	    	   
	    	 //Zähler und BasisString setzen
	    	   txtn.setText(baseNr);
	    	   counter++;
	    	   counternr++;
	    	   txCount.setText(counter+"");
	    	   txnr.setText(counternr+"");
	    	   }
	      
	      // Methode zum visuellen Setzen des Eintrags
	      protected void eintragSetzenEndings(String eintrag) {
	    	    //ist Basiseintrag leer
	    	   if (baseEndings == null) 
	    		   //tempString als Basisstring setzen
	    		   baseEndings = eintrag;
	    	   else 
	    		   //andernfalls TempString der Basis
	    		   //mit Absatz setzen
	    		   baseEndings = (eintrag+baseEndings); 
	    	
	    	   counter++;
	    	   counterEndings++;
	    	   //Zähler und BasisString setzen
	    	   txtEndings.setText(baseEndings);
	    	   txCount.setText(counter+"");
	    	   txEndings.setText(counterEndings+"");
	      }
	      
	      // Methode zum visuellen Setzen des Eintrags
	      protected void eintragSetzenCombo(String eintrag) {
	    	    //ist Basiseintrag leer
	    	   if (baseCombo == null) 
	    		   //tempString als Basisstring setzen
	    		   baseCombo = eintrag;
	    	   else 
	    		   //andernfalls TempString der Basis
	    		   //mit Absatz setzen
	    		   baseCombo = (eintrag+baseCombo); 
	    	
	    	   counter++;
	    	   countercombo++;
	    	   //Zähler und BasisString setzen
	    	   txtcombo.setText(baseCombo);
	    	   txCount.setText(counter+"");
	    	   txCombo.setText(countercombo+"");
	      }
	      
	   // Methode zum visuellen Setzen des Eintrags
	      protected void eintragSetzenKey(String eintrag) {
	    	    //ist Basiseintrag leer
	    	   if (baseKey == null) 
	    		   //tempString als Basisstring setzen
	    		   baseKey = eintrag;
	    	   else 
	    		   //andernfalls TempString der Basis
	    		   //mit Absatz setzen
	    		   baseKey = (eintrag+baseKey); 
	    	
	    	   counter++;
	    	   counterkey++;
	    	   //Zähler und BasisString setzen
	    	   txtKey.setText(baseKey);
	    	   txCount.setText(counter+"");
	    	   txKey.setText(counterkey+"");
	      }
	      
	    @FXML protected void radioDE(ActionEvent event) {  
	    	if(woDE.isSelected() == false) 
	    		boDE=false;
	    	if(woDE.isSelected() == true) 
	    		boDE=true;
	    }
	    
	    @FXML protected void checkToNr(ActionEvent event) {  
	    	if(chnr.isSelected() == false) {
	    		woNR.setSelected(true);
	    	}
	    	if(chnr.isSelected() == true) {
	    		woNR.setSelected(false);
	    	}
	    }
	    
	    @FXML protected void radioNR(ActionEvent event) {  
	    	if(woNR.isSelected() == false) {
	    		chnr.setSelected(true);
	    		chnr.setDisable(false);
	    	}
	    	if(woNR.isSelected() == true) {
	    		chnr.setSelected(false);
	    		chnr.setDisable(true);
	    	}
	    }
	    
	    @FXML protected void checkToU(ActionEvent event) {  
	    	if(chABC.isSelected() == false) {
	    		boU=true;
	    		toU.setDisable(true);
	    		chABC.setSelected(false);
	    	}
	    	if(chABC.isSelected() == true) {
	    		boU=false;
	    		toU.setDisable(false);
	    		chABC.setSelected(true);
	    	}
	    }
	    
	    @FXML protected void radioToU(ActionEvent event) {  
	    	if(toU.isSelected() == true) {
	    		boU=true;
	    		toL.setDisable(true);
	    		chAbc.setSelected(false);
	    		chAbc.setDisable(true);
	    		chabc.setSelected(false);
	    		chabc.setDisable(true);
	    	}
	    	if(toU.isSelected() == false) {
	    		boU=false;
	    		toL.setDisable(false);
	    		chAbc.setSelected(true);
	    		chAbc.setDisable(false);
	    		chabc.setSelected(true);
	    		chabc.setDisable(false);
	    	}
	    }
	    
	    @FXML protected void checkToL(ActionEvent event) {  
	    	if(chabc.isSelected() == false) {
	    		boU=true;
	    		toL.setDisable(true);
	    		chabc.setSelected(false);
	    	}
	    	if(chabc.isSelected() == true) {
	    		boU=false;
	    		toL.setDisable(false);
	    		chabc.setSelected(true);
	    	}
	    }
	    
	    @FXML protected void radioToL(ActionEvent event) {  
	    	if(toL.isSelected() == true) {
	    		boL=true;
	    		toU.setDisable(true);
	    		chAbc.setSelected(false);
	    		chAbc.setDisable(true);
	    		chABC.setSelected(false);
	    		chABC.setDisable(true);
	    	}
	    	if(toL.isSelected() == false) {
	    		boL=false;
	    		toU.setDisable(false);
	    		chAbc.setSelected(true);
	    		chAbc.setDisable(false);
	    		chABC.setSelected(true);
	    		chABC.setDisable(false);
	    	}
	    }
	    
	  	//die Methode zum Laden
	  	@FXML protected void ladenKlick(ActionEvent event) {
	  		if(flagLoad==1) {
				clearAll();
				flagLoad=0;
			}
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
	  		if (datei != null) {
	  			//dann über eine eigene Methode laden
	  			dateiLaden(datei);
	  			flagLoad++;
	  		}
	  	}
	  	
	  //die Methode lädt eine Datei
		public void dateiLaden(File dateix) {

			try {	
				File datei = new File(dateix.toURI().toString());
				// existiert bisher ein Eintrag
				if(dateix != null) 
					datei = dateix;
					String tempString=null;
					try (Scanner sc = new Scanner(datei)) {
						
						timerStart();
						
						while(sc.hasNext()==true) {
							tempString=sc.next();
							if(boDE == true && eintrag == null) {
								eintrag = new ArrayList<String>();
								eintrag.add(tempString);
								System.out.println("FIRST ENTRIE: \t" + tempString + "\t SETTED !");
							}
							else if(boDE == true && eintrag != null) {
								System.out.println("CHECK FOR DOUBLE ENTRIE: \t" + tempString +"\t FORWARDED TO NEXT METHODE !");
								deOuttake(tempString);
							}
							else if(boDE == false && eintrag == null) {
								wortHinzufuegen(tempString);
								//System.out.println("PUSH ENTRIE: \t" + tempString + "\t WITHOUT CHECK FOR DOUBLES");
							}
							
						}
						if(sc.hasNext()!=true) {
							sc.close();
							System.out.println("SCANNER CLOSED !");
							
							if(boDE==true) 
								cleanList();
							else
								timerStop();
						}
					}
				//und die Titelleiste anpassen
				meineStage.setTitle("DOC.NGIN-FX " + datei.toString());	
				
			}
			catch(Exception ex) {
				ex.printStackTrace();
				//den Dialog erzeugen und anzeigen
				Alert meinDialog = new Alert(AlertType.INFORMATION, "WHILE LOADING OCCURS AN ERROR, PLEASE CHECK YOUR TERMINAL-LOG!");
				//den Text setzen
				meinDialog.setHeaderText("ATTENTION PLEASE!");
				meinDialog.initOwner(meineStage);
				//den Dialog anzeigen
				meinDialog.showAndWait();
			}
		}
		
			private void deOuttake(String tempString) {
				doppelt=false;
				if(eintrag!=null) {
					for(int i=0;i<=eintrag.size()-1;i++) {
						if(tempString.compareTo(eintrag.get(i).toString()) == 0) {
							this.doppelt=true;
							break;
						}
					}
					if(doppelt==true) {
						System.out.println("DOUBLE ENTRIE FOUND: \t"  + tempString + "\t AND KICKED !");
						counterDE++;
						txCountDE.setText(counterDE+"");
					}
					else if(doppelt==false) {
						System.out.println("NEXT ENTRIE:\t"  + tempString + "\tSETTED !");
						eintrag.add(tempString);
						}
				}
			}
		  
		  protected void cleanList() {
			  
			  System.out.println("SUMMARY OF KICKED DOUBLES: \t" + counterDE);
			  
			  for(int i=0;i<=eintrag.size()-1;i++) {
				  wortHinzufuegen(eintrag.get(i));
				  if(i==eintrag.size()-1) {
					  timerStop();
					  txCountDE.setText(""+(counterDE-counter));
				  }
			  }
		  }
		  

		  protected void timerStart() {
			  System.out.println("TIMER START BY TIME: \t 0 ms ");
			  startTime = System.nanoTime();
			  
		  }
		  
	
		  protected void timerStop() {
				stopTime = System.nanoTime();
				time = (stopTime - startTime);  //divide by 1000000 to get milliseconds.
				System.out.println("TIMER STOPPED AT TIME: \t" + time/1000000 + " ms \t" + time/1000000000 +" sec");
				player2.play();
				calculateMessedTime();
				pie.setData(getChartData());
				pie2.setData(getChartData());
				setMeineStage(meineStage);
			}
		  	
		  	
	      
		  protected void calculateMessedTime() {
			int tempTime,rest;
			if(time/1000000>1000) {
				tempTime=(int) (time/1000000000);
				if(tempTime>=60) {
					System.out.println(minute=tempTime/60);
					rest=minute*60;
					second=tempTime-rest;
				}
				else {
					second=tempTime;
				}
				if(second<10 && minute<10)
					txtimer.setText("0"+minute+"min  :  0"+second+"sec");
				else if(second>=10 && minute<10)
					txtimer.setText("0"+minute+"min  :  "+second+"sec");
				else if(second>=10 && minute>=10)
					txtimer.setText(minute+"min  :  "+second+"sec");
			}
			else if(time/1000000<=1000) {
				txtimer.setText(time/1000000 + "\t ms");
			}
				System.out.println("TIMER SETTED BY TIMEVALUE: \t" +minute+":"+second);
		}
		  
	      @FXML //die Methode zum Anzeigen des Speicherndialogs
	      protected void speichernKlick(ActionEvent event) {
			//eine neue Instanz der Klasse FileChooser erzeugen
			FileChooser speichernDialog = new FileChooser();
			//den Titel für den Dialog setzen
			speichernDialog.setTitle("File Save");
			//den Ordner setzen
			speichernDialog.setInitialDirectory(new File(System.getProperty("user.home")));
			//den Filter setzen
			speichernDialog.getExtensionFilters().add(new ExtensionFilter("Text-File", "*.txt"));
			speichernDialog.getExtensionFilters().add(new ExtensionFilter("MarkDown-File", "*.md"));
			speichernDialog.getExtensionFilters().add(new ExtensionFilter("Binary-File", "*.bin"));
			//den Speicherndialog anzeigen und das Ergebnis beschaffen
			File datei = speichernDialog.showSaveDialog(meineStage);
			//wurde ein Dateityp ausgewählt
			if (datei != null)
			    //dann den Inhalt über eine eigene Methode speichern
				datenSchreiben(datei);
			}   
	      
		  //die Methode zum Schreiben
		  //Sie erhält die Datei, die geschrieben werden soll
		  protected void datenSchreiben(File dateiSchreiben) {
			  String tempString;
		  
			//eine Instanz der Klasse FileWriter mit der Datei erzeugen 
			try (RandomAccessFile tempDatei = new RandomAccessFile(dateiSchreiben, "rw")) {
					//Sämtliche String-Snippet`s aus dem Stack auslesen
					//und in die Datei schreiben
					for(int i = 0; i < eintrag.size()-1; i++) {
						tempString="\n"+eintrag.get(i);
						tempDatei.writeUTF(tempString);
					}
					
					//und abschliend den Zähler(int= 4byte) in Datei schreiben
					// tempDatei.writeInt(counter);
			}	
			catch (IOException e) {
				//bei Problemen einen Dialog erzeugen
				//bei Problemen einen Dialog erzeugen
				Alert meinDialog = new Alert(AlertType.INFORMATION, "WHILE WRITTING THE FILE OCCURS FOLLOWING ERROR: " + e.getMessage());
				meinDialog.setHeaderText("ATTENTION PLEASE");
				meinDialog.showAndWait();
				}
		  	}
		   
		  //die Methode setzt die Bühne auf den übergebenen Wert
			public void setMeineStage(Stage meineStage) {
				this.meineStage = meineStage;
			}	
}
