package org.lousanter.controller.fxController.proveedor;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.lousanter.controller.daoController.DaoProvController;
import org.lousanter.model.dto.ProveedorDTO;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class ProvController implements Initializable {
    @FXML
    private Button btnNuevoProv;
    @FXML
    private TableView<ProveedorDTO> provTable;
    @FXML
    private TextField fldBuscar;

    @FXML
    private TableColumn<ProveedorDTO, String> colProv;
    @FXML
    private TableColumn<ProveedorDTO, String> colRuc;
    @FXML
    private TableColumn<ProveedorDTO, String> colTel;
    @FXML
    private TableColumn<ProveedorDTO, String> colDir;


    @FXML
    private Button btnProv;
    @FXML
    private Button btnDeleteProv;


    DaoProvController daoProvController = new DaoProvController();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colProv.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colRuc.setCellValueFactory(new PropertyValueFactory<>("ruc"));
        colDir.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        colTel.setCellValueFactory(new PropertyValueFactory<>("telefono"));

        cargarTabla();

        fldBuscar.textProperty().addListener((observable, oldValue, newValue) -> {
            buscarProvString(newValue);
        });

    }

    private void buscarProvString(String newValue) {

        if (newValue == null || newValue.isEmpty()) {
            cargarTabla();
        }else{
            List<ProveedorDTO> provs = daoProvController.findByName(newValue);
            ObservableList<ProveedorDTO> provslis = FXCollections.observableList(provs);
            provTable.setItems(provslis);
        }


    }


    public void newProv(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/FXML/Proveedor/newProv.fxml"));
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Nuevo Proveedor");
        stage.setScene(new Scene(parent));
        stage.showAndWait();
        cargarTabla();

    }


    public static ProveedorDTO sendProv;


    public void editProv(ActionEvent actionEvent) throws IOException {


        this.sendProv = provTable.getSelectionModel().getSelectedItem();

        if (sendProv != null) {
            Parent parent = FXMLLoader.load(getClass().getResource("/FXML/Proveedor/editProv.fxml"));
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Editar Proveedor");
            stage.setScene(new Scene(parent));
            stage.showAndWait();
            cargarTabla();
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Seleccione un Proveedor");
            alert.showAndWait();
        }

    }

    public void deleteProv(ActionEvent actionEvent) throws IOException {
        ProveedorDTO prov = provTable.getSelectionModel().getSelectedItem();
        if (prov != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmacion");
            alert.setHeaderText("Esta accion requiere de tu aprobacion");
            alert.setContentText("Estas seguro de eliminar este Proveedor?");

            Optional<ButtonType> result = alert.showAndWait();

            if (ButtonType.OK == result.get()) {

                System.out.println("eliminando proveedor");
                daoProvController.delete(prov);
                cargarTabla();
            } else {
                System.out.println("Eliminacion cancelada.");
            }
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Seleccione un proveedor");
            alert.showAndWait();
        }
    }

    private void cargarTabla() {

        List<ProveedorDTO> lista = daoProvController.findAll();
        ObservableList<ProveedorDTO> observableList = FXCollections.observableList(lista);
        provTable.setItems(observableList);

    }



}
