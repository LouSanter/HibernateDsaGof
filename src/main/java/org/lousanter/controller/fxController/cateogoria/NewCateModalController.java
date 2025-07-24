package org.lousanter.controller.fxController.cateogoria;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.lousanter.IA.ChatClientConstructor;
import org.lousanter.controller.daoController.DaoCateController;
import org.lousanter.model.dto.CategoriaDTO;
import org.lousanter.model.factory.CategoriaFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class NewCateModalController implements Initializable {

    @FXML
    private Button btnGuardar;
    @FXML
    private TextField lblCate;
    @FXML
    private TextArea lblCateDescripcion;

    @FXML
    private VBox vbox;

    DaoCateController daoCateController = new DaoCateController();



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    public void guardarCategoria(ActionEvent actionEvent) {
        CategoriaDTO categoria = CategoriaFactory.crearDesdeFormulario(lblCate, lblCateDescripcion);
        daoCateController.registrarCategoria(categoria);
        Stage stage =(Stage) vbox.getScene().getWindow();
        stage.close();

    }

    ChatClientConstructor cc;

    public void generarIA(ActionEvent actionEvent) {

        if (lblCate.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Debe ingresar una categoria valida");
            alert.showAndWait();
        }else {


            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/load.fxml"));
                Parent root = loader.load();
                Stage loadingStage = new Stage(StageStyle.UNDECORATED);
                loadingStage.setScene(new Scene(root));
                loadingStage.initModality(Modality.APPLICATION_MODAL);
                loadingStage.show();

                Task<String> task = new Task<String>() {
                    @Override
                    protected String call() throws Exception {
                        cc = new ChatClientConstructor();
                        cc.construirConsultaCategoria(lblCate.getText());
                        return cc.construirRespuesta();
                    }
                };

                task.setOnSucceeded(e -> {
                    lblCateDescripcion.setText(task.getValue());
                    loadingStage.close();
                });

                task.setOnFailed(e -> {
                    loadingStage.close();
                    lblCateDescripcion.setText("Error al generar la respuesta");
                    task.getException().printStackTrace();
                });

                new Thread(task).start();

            } catch (IOException e) {
                e.printStackTrace();
            }

        }



    }



}
