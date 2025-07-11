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

public class eDITUbiModalController implements Initializable {

    @FXML
    private Button btnGuardar;

    @FXML
            private TextField lbl1;
    @FXML
            private TextField lbl2;
    @FXML
            private TextField lbl3;
    @FXML
            private TextField lbl4;
    @FXML
            private TextArea areaDes;
    @FXML
            private VBox vbox;

    DaoUbiController daoUbi = new DaoUbiController();


    public void save(ActionEvent event) {


        UbicacionDTO actualizada = UbicacionFactory.editarExistente(
                ubiRecived.getIdUbicacion(), lbl1, lbl2, lbl3, lbl4, areaDes
        );
        daoUbi.updateUbicacion(actualizada);
        ((Stage) vbox.getScene().getWindow()).close();

    }



    UbicacionDTO ubiRecived = UbiController.sendUbi;



    @Override
    public void initialize(URL location, ResourceBundle resources) {


        if (ubiRecived != null) {
            String[] partes = ubiRecived.getNombre().split(" - ");
            if (partes.length >= 4) {
                lbl1.setText(partes[0]);
                lbl2.setText(partes[1]);
                lbl3.setText(partes[2]);
                lbl4.setText(partes[3]);
            }
            areaDes.setText(ubiRecived.getDescripcion());
        }


    }
}
