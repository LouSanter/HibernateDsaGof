package org.lousanter.controller.fxController.ubicacion;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.lousanter.controller.daoController.DaoUbiController;
import org.lousanter.model.dto.UbicacionDTO;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class UbiController implements Initializable {


    @FXML
    VBox vBox;

    @FXML
    private Button btnSaveUbi;
    @FXML
    private Button btnUpdateUbi;
    @FXML
    private Button btnDeleteUbi;

    @FXML
    private TableView ubiTable;
    @FXML
    private TextField fldBuscar;


    @FXML
    private TableColumn<UbicacionDTO, String>  colName;
    @FXML
    private TableColumn<UbicacionDTO, String> colDes;



    DaoUbiController daoUbi = new DaoUbiController();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colDes.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        colName.setCellValueFactory(new PropertyValueFactory<>("nombre"));

        cargarTabla();
        fldBuscar.textProperty().addListener((observable, oldValue, newValue) -> {
            buscarUbi(newValue);
        });

    }

    private void buscarUbi(String newValue) {
        daoUbi.searchUbicacion(newValue);
    }


    public void guardarUbi(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Ubicacion/newUbi.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
        cargarTabla();

    }


    public static UbicacionDTO sendUbi;

    public void updateUbi(ActionEvent event) throws IOException {

        this.sendUbi = (UbicacionDTO) ubiTable.getSelectionModel().getSelectedItem();
        System.out.println("Se seleciono el item: " + this.sendUbi.getNombre());


        if (sendUbi != null){
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/Ubicacion/editUbi.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
            cargarTabla();

        }


    }

    public void deleteUbi(ActionEvent event){

        UbicacionDTO ubiDel = (UbicacionDTO) ubiTable.getSelectionModel().getSelectedItem();
        daoUbi.deleteUbicacion(ubiDel);

        cargarTabla();
    }


    public void cargarTabla(){

        List<UbicacionDTO> ubis = daoUbi.listarUbicaciones();
        ObservableList<UbicacionDTO> observableList = FXCollections.observableList(ubis);
        ubiTable.setItems(observableList);
    }


}
