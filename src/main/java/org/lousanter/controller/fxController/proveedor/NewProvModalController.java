package org.lousanter.controller.fxController.proveedor;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.lousanter.controller.daoController.DaoProvController;
import org.lousanter.model.dto.ProveedorDTO;
import org.lousanter.model.factory.ProveedorFactory;


import java.net.URL;
import java.util.ResourceBundle;

public class NewProvModalController implements Initializable {

    @FXML
    private TextField lblProv;
    @FXML
    private TextField lblRucProv;
    @FXML
    private TextField lblDirProv;
    @FXML
    private TextField lblTelProv;


    @FXML
    private VBox vbox;

    DaoProvController daoProvController = new DaoProvController();


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void guardarProv() {
        ProveedorDTO dto = ProveedorFactory.crearDesdeFormulario( lblProv, lblRucProv, lblDirProv, lblTelProv);
        daoProvController.save(dto);
        Stage stage = (Stage) vbox.getScene().getWindow();
        stage.close();
    }
}
