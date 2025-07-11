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
import org.lousanter.model.factory.ProductoFactory;
import org.lousanter.model.mapper.CategoriaMapper;
import org.lousanter.model.mapper.ProveedorMapper;
import org.lousanter.model.mapper.UbicacionMapper;
import org.lousanter.util.categoriaUtil.CategoriaList;
import org.lousanter.util.proveedorUtil.ProveedorList;
import org.lousanter.util.ubicacionList.UbicacionList;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class EditProdModalController implements Initializable {

    @FXML
    private ComboBox<CategoriaDTO> boxCate;

    @FXML
    private ComboBox<ProveedorDTO> boxProv;

    @FXML
    private Button btnUbi;

    @FXML
    private TextField lblCodigo;

    @FXML
    private TextField lblNombre;

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

    @FXML
    private TextField lblDes;


    DaoProdController daoProd = new DaoProdController();
    ProductoDTO prodRec = ProdController.sendProd;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<CategoriaDTO> categorias = FXCollections.observableList(CategoriaList.getCateList());
        boxCate.setItems(categorias);

        ObservableList<ProveedorDTO> proveedores = FXCollections.observableList(ProveedorList.getProvList());
        boxProv.setItems(proveedores);

        ObservableList<UbicacionDTO> ubis = FXCollections.observableList(UbicacionList.getUbicacionList());
        boxUbi.setItems(ubis);

        cargarIndex();

        lblNombre.setText(prodRec.getNombre());
        lblPrecioCompra.setText(String.valueOf(prodRec.getPrecioCompra()));
        lblPrecioVenta.setText(String.valueOf(prodRec.getPrecioVenta()));
        lblStock.setText(String.valueOf(prodRec.getStock()));
        lblStockMin.setText(String.valueOf(prodRec.getStockMin()));
        lblCodigo.setText(prodRec.getCodigo());
        lblDes.setText(prodRec.getDescripcion());


    }

    private void cargarIndex() {


        CategoriaDTO cdto = CategoriaList.getCateList().stream()
                .filter(cat -> cat.getId().equals(prodRec.getIdCategoria()))
                .findFirst()
                .orElse(null);
        ProveedorDTO pdto = ProveedorList.getProvList().stream()
                .filter(p -> p.getIdProveedor().equals(prodRec.getIdProveedor()))
                .findFirst()
                .orElse(null);
        UbicacionDTO udto = UbicacionList.getUbicacionList().stream()
                .filter(ubi -> ubi.getIdUbicacion().equals(prodRec.getIdUbicacion()))
                .findFirst()
                .orElse(null);


        if (cdto != null && pdto != null && udto != null) {
            boxCate.getSelectionModel().select(cdto);
            boxProv.getSelectionModel().select(pdto);
            boxUbi.getSelectionModel().select(udto);
        }


    }

    @FXML
    public void save(ActionEvent event) {
        /*ProductoDTO producto = ProductoFactory.f(
                lblNombre,
                lblCodigo,
                lblStock,
                lblStockMin,
                lblPrecioCompra,
                lblPrecioVenta,
                boxCate,
                boxProv,
                boxUbi

        );
        daoProd.actualizarProducto(producto,
                UbicacionMapper.toEntity(boxUbi.getSelectionModel().getSelectedItem()),
                ProveedorMapper.toEntity(boxProv.getSelectionModel().getSelectedItem()),
                CategoriaMapper.toEntity(boxCate.getSelectionModel().getSelectedItem())
        );
        ((Stage) pane.getScene().getWindow()).close();

    }
    */





    }
}






