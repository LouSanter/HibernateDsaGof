package org.lousanter.controller.fxController.reposicion;


import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.lousanter.model.dto.ProductoDTO;
import org.lousanter.util.productoUtil.ProductoQueue;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class RepoController implements Initializable{
	
	


    @FXML
    private TableColumn<ProductoDTO, String> colProd;

    @FXML
    private TableColumn<ProductoDTO, Integer> colMin;

    @FXML
    private TableColumn<ProductoDTO, Integer> colStock;

    @FXML
    private TextField fldBuscar;

    @FXML
    private TableView<ProductoDTO> provTable;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		colProd.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("nombre"));
		colMin.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("stockMin"));
		colStock.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("stock"));
		
		cargarTabla();
	}

	private void cargarTabla() {
	    provTable.getItems().clear();
	    List<ProductoDTO> lista = ProductoQueue.getAll();
	    ObservableList<ProductoDTO> productos = FXCollections.observableArrayList(lista);
	    provTable.setItems(productos);
	}

	
	

}
