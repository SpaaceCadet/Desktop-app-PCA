package sample;

import com.github.sh0nk.matplotlib4j.PythonExecutionException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.io.FileNotFoundException;
import java.io.IOException;


public class Controller {
    @FXML
    private TextField cols;
    @FXML
    private TextField rows;
    @FXML
    private Button Load;
    @FXML
    private CheckBox Normalize;
    @FXML
    private Button Scree;
    @FXML
    private Button Scatter1;
    @FXML
    private Button Scatter2;
    @FXML
    private Label exception1;
    @FXML
    private Label exception2;
    int roww,coll;
    boolean state=false;

    double [][] mydata;
    Normalize_mat normalisation;
    Projection my_projection;
    public void Change(){
        if(Normalize.isSelected()){
            state=true;
        }
        else {
            state = false;
        }}

    public void Dragdata() throws FileNotFoundException {
        try {
            coll = Integer.parseInt(cols.getText());
            roww = Integer.parseInt(rows.getText());
            //mydata = new double[roww][coll];
            CSVREAD reader = new CSVREAD(roww, coll);

            normalisation=new Normalize_mat(reader.readFile());
            //mydata=normalisation.Center();
            normalisation.Center();
            //mydata=normalisation.get_mat();
            if(state){
                normalisation.Norm();
              //  mydata=normalisation.get_mat();
                                      }

            COV_Corr_MAT my_cov=new COV_Corr_MAT(normalisation);
            my_cov.My_eig();

           my_projection=new Projection(normalisation.get_mat(),my_cov);
        }

        catch (Exception e ){
           // System.out.println("Data size or input size are in wrong format  enter valid data !");
            exception1.setText("Enter valid Data size/input please !");

        }
    }

    public void carte1() throws IOException, PythonExecutionException {
        try {

            my_projection.plot_carte1();
        }
        catch(Exception d){
            exception2.setText("Please insert some data !");
        } }




    public void carte2() throws IOException, PythonExecutionException {
        try {

            my_projection.plot_carte2();
        }
        catch(Exception d){
            exception2.setText("Please insert some data !");
        } }

    public void eboulis() throws IOException, PythonExecutionException{

        try {
            my_projection.plot_eboulis();
        }
        catch(Exception m){
            exception2.setText("Please insert some data !");

        } }}




