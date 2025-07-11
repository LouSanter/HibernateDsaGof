package org.lousanter.controller.fxController.cateogoria;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.lousanter.controller.daoController.DaoCateController;
import org.lousanter.model.dto.CategoriaDTO;
import org.lousanter.model.factory.CategoriaFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class EditCateModalController implements Initializable {

    @FXML
    private Button btnGuardar;
    @FXML
    private TextField lblCate;
    @FXML
    private TextArea lblCateDescripcion;

    @FXML
    private VBox vbox;

    DaoCateController daoCateController = new DaoCateController();

    private CategoriaDTO categoriaRec = CateController.sendCate;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        lblCate.setText(categoriaRec.getNombre());
        lblCateDescripcion.setText(categoriaRec.getDescripcion());

    }


    public void guardarCategoria(ActionEvent actionEvent) {
        CategoriaDTO nuevaCategoriaDTO = CategoriaFactory.ActualizarDesdeFormulario(lblCate, lblCateDescripcion, categoriaRec.getId());
        daoCateController.editarCategoria(nuevaCategoriaDTO);
        Stage stage =(Stage) vbox.getScene().getWindow();
        stage.close();

    }


}
