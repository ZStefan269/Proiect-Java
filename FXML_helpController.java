package proiect;

import com.sun.corba.se.pept.transport.Connection;
import java.net.URL;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Student
 */
public class FXML_helpController implements Initializable {

    @FXML
    private TableView<Flowers> tvflowers;
    @FXML
    private TableColumn<Flowers, Integer> colid;
    @FXML
    private TableColumn<Flowers, String> coldenumire;
    @FXML
    private TableColumn<Flowers, Integer> colvarsta;
    @FXML
    private TableColumn<Flowers, Integer> colcantitate;
    @FXML
    private TableColumn<Flowers, String> coltipvanzare;
    @FXML
    private TableColumn<Flowers, String> colactiune;
    @FXML
    private TextField tfactiune;
    @FXML
    private TextField tftipvanzare;
    @FXML
    private TextField tfcantitate;
    @FXML
    private TextField tfvarsta;
    @FXML
    private TextField tfdenumire;
    @FXML
    private TextField tfid;
    @FXML
    private Button btnnew;
    @FXML
    private Button btnupdate;
    @FXML
    private Button btndelete;
    @FXML
    private TextField tfsearch;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {        

        if(event.getSource() == btnnew){
            insertRecord();
        }else if (event.getSource() == btnupdate){
            updateRecord();
        }else if(event.getSource() == btndelete){
            deleteButton();
        }
            
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showFlowers("");
    }    
    
    public java.sql.Connection getConnection(){
        java.sql.Connection conn;
        try{
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/florarie", "root","");
            return conn;
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return null;
        }
    }
    
    public ObservableList<Flowers> getFlowersList(String q){
        ObservableList<Flowers> flowerList = FXCollections.observableArrayList();
        java.sql.Connection conn = getConnection();
        String query = "SELECT * FROM flowers " + q;
        Statement st;
        ResultSet rs;
        
        try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Flowers flowers;
            while(rs.next()){
                flowers = new Flowers(rs.getInt("id"), rs.getString("Denumire"), rs.getInt("Varsta"), rs.getInt("Cantitate"),rs.getString("tipvanzare"),rs.getString("Actiune"));
                flowerList.add(flowers);
            }
                
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return flowerList;
    }
    
    public void showFlowers(String q){
        ObservableList<Flowers> list = getFlowersList(q);

        colid.setCellValueFactory(new PropertyValueFactory<Flowers, Integer>("id"));
        coldenumire.setCellValueFactory(new PropertyValueFactory<Flowers, String>("denumire"));
        colvarsta.setCellValueFactory(new PropertyValueFactory<Flowers, Integer>("varsta"));
        colcantitate.setCellValueFactory(new PropertyValueFactory<Flowers, Integer>("cantitate"));
        coltipvanzare.setCellValueFactory(new PropertyValueFactory<Flowers, String>("tipvanzare"));
        colactiune.setCellValueFactory(new PropertyValueFactory<Flowers, String>("actiune"));
        
        tvflowers.setItems(list);
    }
    
    @FXML
    private void insertRecord(){
        String query="INSERT INTO flowers(`denumire`, `varsta`, `cantitate`, `tipvanzare`, `actiune`) "
                + "VALUES ('"+ tfdenumire.getText() + "','" +  tfvarsta.getText() + "','" + tfcantitate.getText() + "','"  + tftipvanzare.getText() + "','" + tfactiune.getText()+ "')";
        executeQuery(query);
        showFlowers("");
    }
    
    @FXML
    private void search()   
    {
          showFlowers("WHERE denumire = '" + tfsearch.getText() + "'" );
    }
    
    private void updateRecord(){
        String query = "UPDATE `flowers` SET `denumire` = '" + tfdenumire.getText() + "', `varsta` = '" + tfvarsta.getText() + "' , `cantitate` = '" +
                tfcantitate.getText() + "' , `tipvanzare` = '" + tftipvanzare.getText() + "', `actiune` = '" + tfactiune.getText()+ "' WHERE id = '" + tfid.getText() + "'";
        executeQuery(query);
        showFlowers("");
    }
    private void deleteButton(){
        String query = "DELETE FROM flowers WHERE id =" + tfid.getText() + "";
        executeQuery(query);
        showFlowers("");
    }

    private void executeQuery(String query) {
        java.sql.Connection conn = getConnection();
        Statement st;
        try{
            st = conn.createStatement();
            st.executeUpdate(query);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
