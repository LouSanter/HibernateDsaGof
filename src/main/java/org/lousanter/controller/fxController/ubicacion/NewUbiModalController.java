package org.lousanter.controller.fxController.ubicacion;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.lousanter.controller.daoController.DaoUbiController;
import org.lousanter.model.dto.UbicacionDTO;
import org.lousanter.model.factory.UbicacionFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class NewUbiModalController implements Initializable {

    @FXML private Button btnGuardar;
    @FXML private TextField lbl1;
    @FXML private TextField lbl2;
    @FXML private TextField lbl3;
    @FXML private TextField lbl4;
    @FXML private TextArea areaDes;
    @FXML private VBox vbox;

    private final DaoUbiController daoUbi = new DaoUbiController();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void save(ActionEvent event) {
        UbicacionDTO dto = UbicacionFactory.crearNueva(lbl1, lbl2, lbl3, lbl4, areaDes);
        daoUbi.insertarUbicacion(dto);
        ((Stage) vbox.getScene().getWindow()).close();
    }
}
