package org.lousanter.controller.fxController.producto;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.lousanter.IA.ChatClientConstructor;
import org.lousanter.controller.daoController.DaoProdController;
import org.lousanter.model.dto.CategoriaDTO;
import org.lousanter.model.dto.ProductoDTO;
import org.lousanter.model.dto.ProveedorDTO;
import org.lousanter.model.dto.UbicacionDTO;
import org.lousanter.model.entities.Producto;
import org.lousanter.model.entities.Ubicacion;
import org.lousanter.model.factory.ProductoFactory;
import org.lousanter.model.factory.ProveedorFactory;
import org.lousanter.model.mapper.CategoriaMapper;
import org.lousanter.model.mapper.ProveedorMapper;
import org.lousanter.model.mapper.UbicacionMapper;
import org.lousanter.util.categoriaUtil.CategoriaList;
import org.lousanter.util.proveedorUtil.ProveedorList;
import org.lousanter.util.ubicacionList.UbicacionList;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;

public class NewProdModalController implements Initializable {

    @FXML
    private ComboBox<CategoriaDTO> boxCate;

    @FXML
    private ComboBox<ProveedorDTO> boxProv;

    @FXML
    private Button btnUbi;

    @FXML
    private TextField lblCodigo;

    @FXML
    private TextField  lblNombre;

    @FXML
    private TextField lblPrecioCompra;

    @FXML
    private TextField lblPrecioVenta;

    @FXML
    private TextField lblStock;

    @FXML
    private TextField lblStockMin;

    @FXML
    private ComboBox<UbicacionDTO> boxUbi;

    @FXML
            private AnchorPane pane;


    DaoProdController daoProd = new DaoProdController();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<CategoriaDTO> categorias = FXCollections.observableList(CategoriaList.getCateList());
        boxCate.setItems(categorias);

        ObservableList<ProveedorDTO> proveedores = FXCollections.observableList(ProveedorList.getProvList());
        boxProv.setItems(proveedores);

        ObservableList<UbicacionDTO> ubis = FXCollections.observableList(UbicacionList.getUbicacionList());
        boxUbi.setItems(ubis);
    }

    @FXML
    public void newProd(ActionEvent event) {
        ProductoDTO producto = ProductoFactory.crearDesdeFormulario(
                lblNombre,
                lblCodigo,
                lblStock,
                lblStockMin,
                lblPrecioCompra,
                lblPrecioVenta,
                boxCate,
                boxProv,
                boxUbi,
                lblDes
        );
        daoProd.registrarProducto(producto,
                UbicacionMapper.toEntity(boxUbi.getSelectionModel().getSelectedItem()),
                ProveedorMapper.toEntity(boxProv.getSelectionModel().getSelectedItem()),
                CategoriaMapper.toEntity(boxCate.getSelectionModel().getSelectedItem())
        );
        ((Stage) pane.getScene().getWindow()).close();

    }

    @FXML
    private Button genAI;
    @FXML
            private TextField lblDes;


    ChatClientConstructor cc ;


    public void genai(ActionEvent actionEvent) {

        if (lblNombre.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Debe ingresar una producto valido");
            alert.showAndWait();
        } else {


            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/load.fxml"));
                Parent root = loader.load();
                Stage loadingStage = new Stage(StageStyle.UNDECORATED);
                loadingStage.setScene(new Scene(root));
                loadingStage.initModality(Modality.APPLICATION_MODAL);
                loadingStage.show();

                Task<String> task = new Task<>() {
                    @Override
                    protected String call() throws Exception {
                        cc = new ChatClientConstructor();
                        cc.construirConsultaCategoria(lblNombre.getText());
                        return cc.construirRespuesta();
                    }
                };

                task.setOnSucceeded(e -> {
                    lblDes.setText(task.getValue());
                    loadingStage.close();
                });

                task.setOnFailed(e -> {
                    loadingStage.close();
                    lblDes.setText("Error al generar la respuesta");
                    task.getException().printStackTrace();
                });

                new Thread(task).start();

            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }



}
