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


public class EditProvModalController implements Initializable {

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

    ProveedorDTO p = ProvController.sendProv;

    @Override
    public void initialize(URL location, ResourceBundle resources) {



            lblProv.setText(p.getNombre());
            lblRucProv.setText(p.getRuc());
            lblDirProv.setText(p.getDireccion());
            lblTelProv.setText(p.getTelefono());


    }

    public void guardarProv(){
        ProveedorDTO dto = ProveedorFactory.actualizarDesdeFormulario(lblProv, lblRucProv, lblDirProv, lblTelProv, p.getIdProveedor());
        daoProvController.update(dto);
        Stage st = (Stage) vbox.getScene().getWindow();
        st.close();


    }


}
