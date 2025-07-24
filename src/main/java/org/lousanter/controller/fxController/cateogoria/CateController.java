package org.lousanter.controller.fxController.cateogoria;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import org.lousanter.controller.daoController.DaoCateController;
import org.lousanter.model.dto.CategoriaDTO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CateController implements Initializable {
    @FXML
    private Button btnNuevaCate;
    @FXML
    private TableView<CategoriaDTO> cateTable;
    @FXML
    private TextField fldBuscar;

    @FXML
    private TableColumn<CategoriaDTO, String> colCate;
    @FXML
    private TableColumn<CategoriaDTO, String> colDesc;
    @FXML
    private Button btnCate;
    @FXML
    private Button btnDeleteCate;

    DaoCateController daoCateController = new DaoCateController();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colCate.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colDesc.setCellValueFactory(new PropertyValueFactory<>("descripcion"));


        cargarTabla();

        fldBuscar.textProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println(newValue);
            buscarCateString(newValue);
        });

    }

    private void buscarCateString(String newValue) {

        if (newValue == null || newValue.isEmpty()) {
            cargarTabla();
        }else{
            List<CategoriaDTO> cats = daoCateController.listarCategoriaByName(newValue);
            ObservableList<CategoriaDTO> categorias = FXCollections.observableList(cats);
            for (CategoriaDTO cat : cats) {
                System.out.println(cat.getNombre());
            }
            cateTable.setItems(categorias);
        }


    }


    public void newCat(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/FXML/Categoria/newCate.fxml"));
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Nueva Categoria");
        stage.setScene(new Scene(parent));
        stage.showAndWait();
        cargarTabla();

    }


    public static CategoriaDTO sendCate;


    public void editCate(ActionEvent actionEvent) throws IOException {


        this.sendCate = cateTable.getSelectionModel().getSelectedItem();

        if (sendCate != null) {
            Parent parent = FXMLLoader.load(getClass().getResource("/FXML/Categoria/editCate.fxml"));
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Editar Categoria");
            stage.setScene(new Scene(parent));
            System.out.println(sendCate.toString());
            stage.showAndWait();
            cargarTabla();
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Seleccione una categoria");
            alert.showAndWait();
        }

    }

    public void deleteCate(ActionEvent actionEvent) throws IOException {
        CategoriaDTO cate = cateTable.getSelectionModel().getSelectedItem();
        if (cate != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmacion");
            alert.setHeaderText("Esta accion requiere de tu aprobacion");
            alert.setContentText("Estas seguro de eliminar esta Categoria?");

            Optional<ButtonType> result = alert.showAndWait();

            if (ButtonType.OK == result.get()) {

                System.out.println("eliminando categoria");
                daoCateController.eliminarCategoria(cate);
                cargarTabla();
            } else {
                System.out.println("Eliminacion cancelada.");
            }
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Seleccione una categoria");
            alert.showAndWait();
        }
    }

    private void cargarTabla() {

        List<CategoriaDTO> lista = daoCateController.listarCategoria();
        ObservableList<CategoriaDTO> observableList = FXCollections.observableList(lista);
        cateTable.setItems(observableList);


    }

}
