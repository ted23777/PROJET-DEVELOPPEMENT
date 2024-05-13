package application;

import java.nio.file.Path;

import java.nio.file.Paths;
import java.util.HashMap;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

//erreur obtenu le changement d'image qui ne marche pas 
//base crée mais ajout dans la base de données non effectué
public class GestionController {
	
	@FXML
	TextField  tNom;
	@FXML
	TextField  tPrenom;
	@FXML
	TextField  tAge;
	@FXML
	Button Modifier;
	@FXML
	Button Ajouter;
	@FXML
	Button Supprimer;
	@FXML
	ComboBox<String> situation;
	@FXML
	TableView<Personne> tableView;
	@FXML
	TableColumn<Personne,String> ColumnNom;
	@FXML
	TableColumn<Personne,String> ColumnPrenom;
	@FXML
	TableColumn<Personne,Integer> ColumnAge;
	@FXML
	ImageView imageview;
	@FXML
	PieChart pieChart;
	@FXML 
	ChoiceBox<String> cbSF ;
	@FXML 
	ChoiceBox<Character> cbSex;
	
	ObservableList<Personne> personnes = FXCollections.observableArrayList();
	
	Image [] MesImages = new Image[9];
	
	HashMap<String , Integer> map = new HashMap<String, Integer>(); 
	
	int celib= 0;
	int marie= 0;
	int div= 0;
	int veuf = 0;
	int pac  = 0;
	
   @FXML
	public void initialize() {
		initialiserImages();
		initialiserListe();
		initaliserTableView();
		initialiserComboBox();
		pieChart.setData(getChartData());
		
		// j'ai désactivé ces 02 boutons
		//pour régler un pb d'exception au démarrage
	    Modifier.setDisable(true);
	    Supprimer.setDisable(true);
    }
   
   @FXML
	private void initialiserImages() {
	//	initialiserLise();
	   Path p1 = Paths.get(System.getProperty("user.dir"));
	   
		for(int i=0;i<MesImages.length-1;i++) {
			Path p2 = Paths.get(p1.toString(),"src/images","Pendu-"+i+".png");
			System.out.println(p2.toString());
			MesImages[i] = new Image(p2.toString());
			//MesImages[i] = new Image(getClass().getResource("../images/Pendu-"+i+".png").toString());
		}
		if(MesImages.length>0) {
			
			imageview.setImage(MesImages[0]);
		}
	}
   
   @FXML
	private void initialiserListe() {
		personnes.add(new Personne("TEDDY","Rinner",37,"Marié",0));
		personnes.add(new Personne("MARIE","Antoinette",21,"Celibataire",1));
		personnes.add(new Personne("WERR","Hamza",20,"Celibataire",2));
		personnes.add(new Personne("BALDE","Adel",32,"Celibataire",3));
		personnes.add(new Personne("VON","Cain",41,"Celibataire",4));
		personnes.add(new Personne("LITCH","Joy",19,"Celibataire",5));
		}
   
   	@FXML
	 private void initaliserTableView() {
   		
		 ColumnNom.setCellValueFactory((CellDataFeatures<Personne,String> pers) -> {
			 Personne pers1 = pers.getValue();
			 return new SimpleStringProperty(pers1.getNom());
			});
		 ColumnPrenom.setCellValueFactory((CellDataFeatures<Personne,String> pers) -> {
			 Personne pers1 = pers.getValue();
			 return new SimpleStringProperty(pers1.getPrenom());
			});
		 ColumnAge.setCellValueFactory((CellDataFeatures<Personne,Integer> pers) -> {
			 Personne pers1 = pers.getValue();
			// return new SimpleObjectProperty(pers1.getAge());
			 return new SimpleObjectProperty<Integer>(pers1.getAge());
			});	 
		 tableView.setItems(personnes);
		 tableView.getSelectionModel().selectedItemProperty().addListener(
				 observable ->{
				 Personne persPos = tableView.getSelectionModel().getSelectedItem();
				 System.out.printf("Valeur sélectionnée: %d", persPos.getAge());
				 Path pos = Paths.get(persPos.toString(),"src/images","Pendu-"+persPos.getPhoto()+".png");
				 Image im = new Image((pos.toUri().toString()));
				 imageview.setImage(im);
				 tNom.setText(persPos.getNom());
		         tPrenom.setText(persPos.getPrenom());
		         tAge.setText(""+persPos.getAge());
		         if (persPos.getSituation() != null) {
		                cbSF.getSelectionModel().select(persPos.getSituation());
		            }
		         	Modifier.setDisable(false);
		            Supprimer.setDisable(false);
				 });
	}
   
     @FXML
	 private void initialiserPieChart() {
		 situation.getItems().setAll("Céliba", "Marié","Divorcé", "Veuf","Pacsé");
	 }
   	
     @FXML
	 private void initialiserComboBox() {
		 situation.getItems().setAll("Céliba", "Marié","Divorcé", "Veuf","Pacsé");
	 }
     
   	
   	private ObservableList<Data> getChartData() {
   		obtenirStatSF();
   		 ObservableList<Data> answer = FXCollections.observableArrayList();
   		for(Personne per : personnes)
   	    {
   	      if(per.getSituation().equals("Celiba"))
   	        celib++;
   	      if(per.getSituation().equals("Marie"))
   	        marie++;
   	      if(per.getSituation().equals("Divorce"))
   	        div++;
   	      if(per.getSituation().equals("Veuf"))
   	        veuf++;
   	      if(per.getSituation().equals("Pacse"))
   	        pac++;
   	    }
   		 for(String s:map.keySet())
   		 {
   			 answer.add(new	PieChart.Data(s +" "+map.get(s),((int)(100*(double)map.get(s)/(double)personnes.size()))));
   		 //answer.add(new	PieChart.Data(s,100*((double)map.get(s)/(double)map.size())));
   		 }
   		 return answer;
   		}

	private void obtenirStatSF() {
		if(!map.isEmpty())
			map.clear();;
		
		for(Personne p : personnes) {
			if(map.containsKey(p.getSituation())) {
				int nb = map.get(p.getSituation());
				nb++;
				map.put(p.getSituation(),nb);			
				}else {
					map.put(p.getSituation(),1);
				}
		}
		}
		
	@FXML
	  public void ajouter()
	  {
	    Alert alert = new Alert(AlertType.INFORMATION);
	        alert.setTitle("For Your Information");
	        alert.setHeaderText(null);
	    try {
	      if(tNom.getText().equals(""))
	        throw new IllegalArgumentException("Ne laisser pas de champ vide svp");
	      if(tPrenom.getText().equals(""))
	        throw new IllegalArgumentException("Ne laisser pas de champ vide svp");
	      if(tAge.getText().equals(""))
	        throw new IllegalArgumentException("Ne laisser pas de champ vide svp");
	      if(cbSF.getSelectionModel().getSelectedItem().isEmpty())
	        throw new IllegalArgumentException("Ne laisser pas de champ vide svp");
	      if(cbSex.getSelectionModel().getSelectedItem()==' ')
	        throw new IllegalArgumentException("Ne laisser pas de champ vide svp");
	      
	      char sexe = cbSex.getSelectionModel().getSelectedItem();
	      String nom = tNom.getText();
	      String prenom = tPrenom.getText();
	      String situa = cbSF.getSelectionModel().getSelectedItem();
	      Image image = MesImages[0];
	      if(sexe == 'H')
	        image = MesImages[0];
	      if(sexe == 'F')
	        image = MesImages[1];
	      if(sexe == 'N')
	        image = MesImages[2];
	      int age = Integer.parseInt(tAge.getText());
	      
	      Personne p = new Personne(nom,prenom,0,situa,age);
	      personnes.add(p);
	      tableView.refresh();
	      pieChart.setData(getChartData());
	    }catch(NumberFormatException e) {
	      alert.setContentText("For The Age, Nothing But Numbers Please !");
	      alert.showAndWait();
	    }catch(IllegalArgumentException e) {
	          alert.setContentText(e.getMessage());
	      alert.showAndWait();
	    }
	  }
	  
	  @FXML
	  public void supprimer()
	  {
	    int i = tableView.getSelectionModel().getSelectedIndex();
	    if(i>=0)
	    {
	      personnes.remove(i);
	      tableView.refresh();
	      pieChart.setData(getChartData());
	    }
	    else
	    {
	      Alert alert = new Alert(AlertType.INFORMATION);
	          alert.setTitle("For Your Information");
	          alert.setHeaderText(null);
	          alert.setContentText("You Shall Select Something To Delete");
	      alert.showAndWait();
	    }
	  }
	  
	  @FXML
	  public void modifier()
	  {
	    Alert alert = new Alert(AlertType.INFORMATION);
	        alert.setTitle("For Your Information");
	        alert.setHeaderText(null);
	    int i = tableView.getSelectionModel().getSelectedIndex();
	    if(i>=0)
	    {
	      try {
	        if(tNom.getText().equals(""))
	          throw new IllegalArgumentException("Ne laisser pas de champ vide svp");

	if(tPrenom.getText().equals(""))
	          throw new IllegalArgumentException("Ne laisser pas de champ vide svp");
	        if(tAge.getText().equals(""))
	          throw new IllegalArgumentException("Ne laisser pas de champ vide svp");
	        if(cbSF.getSelectionModel().getSelectedItem().isEmpty())
	          throw new IllegalArgumentException("Ne laisser pas de champ vide svp");
	        if(cbSex.getSelectionModel().getSelectedItem()==' ')
	          throw new IllegalArgumentException("Ne laisser pas de champ vide svp");
	        
	        char sexe = cbSex.getSelectionModel().getSelectedItem();
	        String nom = tNom.getText();
	        String prenom = tPrenom.getText();
	        String situa = cbSF.getSelectionModel().getSelectedItem();
	        Image image = MesImages[0];
	        if(sexe == 'H')
	          image = MesImages[0];
	        if(sexe == 'F')
	          image = MesImages[1];
	        if(sexe == 'N')
	          image = MesImages[2];
	        int age = Integer.parseInt(tAge.getText());
	        
	        Personne p = new Personne(nom,prenom,0,situa,age);
	        personnes.remove(i);
	        personnes.add(i, p);
	        tableView.refresh();
	        tableView.getSelectionModel().select(p);
	        pieChart.setData(getChartData());
	      }catch(NumberFormatException e) {
	        alert.setContentText("For The Age, Nothing But Numbers Please !");
	        alert.showAndWait();
	      }catch(IllegalArgumentException e) {
	            alert.setContentText(e.getMessage());
	        alert.showAndWait();
	      }catch(Exception e) {
	            alert.setContentText("Nothing Must Be Empty");
	        alert.showAndWait();
	      }
	    }
	    else
	    {
	          alert.setContentText("You Shall Select Something To Delete");
	      alert.showAndWait();
	    }
	  }
	}
