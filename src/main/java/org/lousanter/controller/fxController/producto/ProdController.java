package org.lousanter.controller.fxController.producto;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import org.lousanter.controller.daoController.DaoCateController;
import org.lousanter.controller.daoController.DaoProdController;
import org.lousanter.controller.daoController.DaoProvController;
import org.lousanter.controller.daoController.DaoUbiController;
import org.lousanter.model.dto.ProductoDTO;
import org.lousanter.model.entities.Categoria;
import org.lousanter.model.entities.Proveedor;
import org.lousanter.model.entities.Ubicacion;
import org.lousanter.model.mapper.CategoriaMapper;
import org.lousanter.model.mapper.ProveedorMapper;
import org.lousanter.model.mapper.UbicacionMapper;
import org.lousanter.model.observer.Observable;
import org.lousanter.model.observer.Observer;
import org.lousanter.util.productoUtil.ProductoHistorialEntry;
import org.lousanter.util.productoUtil.ProductoStack;

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
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ProdController implements Initializable, Observer {

    @FXML
    private Button btnDeleteCate;

    @FXML
    private Button btnNuevaCate;

    @FXML
    private TableView<ProductoDTO> cateTable;

    @FXML
    private TableColumn<ProductoDTO, String> colProd;

    @FXML
    private TableColumn<ProductoDTO, String> colCod;

    @FXML
    private TableColumn<ProductoDTO, BigDecimal> colPrecio;

    @FXML
    private TableColumn<ProductoDTO, Integer> colStock;

    @FXML
    private TableColumn<ProductoDTO, String> colCate;

    @FXML
    private TableColumn<ProductoDTO, Button> colVer;

    @FXML
    private TextField fldBuscar;

    @FXML
    private VBox contentPane;


    @FXML
    private Button updateCate;

    private final DaoProdController daoProdController = new DaoProdController();

    ContextMenu historial = new ContextMenu();

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        MenuItem undo = new MenuItem("Deshacer");

        undo.setOnAction(e-> clickOnUndo());

        historial.getItems().addAll(undo);

        contentPane.setOnMousePressed(this::handleMouseClick);
        colProd.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colCod.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precioVenta"));
        colStock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        colCate.setCellValueFactory(new PropertyValueFactory<>("nombreCategoria")); // campo adicional desde el mapper

        fldBuscar.textProperty().addListener((obs, oldValue, newValue) -> buscar(newValue));
        Observable.addObserver(this);
        cargarTabla();
    }
    private void handleMouseClick(MouseEvent event) {
        if (event.getButton() == MouseButton.SECONDARY) {
            historial.show(contentPane, event.getScreenX(), event.getScreenY());
        } else {
            historial.hide();
        }
    }


    DaoCateController daoCateController = new DaoCateController();
    DaoProvController daoProvController = new DaoProvController();
    DaoUbiController daoUbiController = new DaoUbiController();



    //DESHACER
    private void clickOnUndo() {
        if (ProductoStack.canUndo()) {
            ProductoHistorialEntry entrada = ProductoStack.undoPop();
            ProductoDTO producto = entrada.getProducto();

            switch (entrada.getAccion()) {
                case NUEVO:
                    daoProdController.eliminarProducto(producto.getIdProducto());
                    break;

                case ELIMINADO:
                    Ubicacion ubi = UbicacionMapper.toEntity(daoUbiController.buscarUbicacion(producto.getIdUbicacion()));
                    Proveedor prov = ProveedorMapper.toEntity(daoProvController.findById(producto.getIdProveedor()));
                    Categoria cate = CategoriaMapper.toEntity(daoCateController.findById(producto.getIdCategoria()));
                    daoProdController.registrarProducto(producto, ubi, prov, cate);
                    ProductoStack.undoPop();
                    break;

                case ACTUALIZADO:
                    Ubicacion ubiAnt = UbicacionMapper.toEntity(daoUbiController.buscarUbicacion(producto.getIdUbicacion()));
                    Proveedor provAnt = ProveedorMapper.toEntity(daoProvController.findById(producto.getIdProveedor()));
                    Categoria cateAnt = CategoriaMapper.toEntity(daoCateController.findById(producto.getIdCategoria()));
                    daoProdController.actualizarProducto(producto, ubiAnt, provAnt, cateAnt);
                    break;
            }
        }
        cargarTabla();
    }


    private void cargarTabla() {
        //ProductoBST.sincro();
        System.out.println("Cargando tabla...");
        List<ProductoDTO> lista = daoProdController.listarProductos();
        ObservableList<ProductoDTO> productos = FXCollections.observableList(lista);
        cateTable.setItems(productos);
    }

    private void buscar(String texto) {
        if (texto == null || texto.trim().isEmpty()) {
            cargarTabla();
        } else {
            List<ProductoDTO> filtrados = daoProdController.buscarPorNombre(texto.trim());
            cateTable.setItems(FXCollections.observableList(filtrados));
        }
    }

    @FXML
    void newProd(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/Producto/newProd.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.showAndWait();

    }

    @FXML
    public void deleteProd(ActionEvent actionEvent) throws IOException {
        ProductoDTO prod = cateTable.getSelectionModel().getSelectedItem();
        if (prod != null && prod.getIdProducto() != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmación");
            alert.setHeaderText("Esta acción requiere de tu aprobación");
            alert.setContentText("¿Estás seguro de eliminar este producto?");

            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {

                daoProdController.eliminarProducto(prod.getIdProducto());

            } else {
                System.out.println("Eliminación cancelada.");
            }
        } else {
            mostrarAlerta("Producto inválido. No se puede eliminar.");
        }
    }



    public static ProductoDTO sendProd;


    @FXML
    void editProd(ActionEvent event) throws IOException {


        sendProd = cateTable.getSelectionModel().getSelectedItem();

        if (sendProd != null) {

            Parent root = FXMLLoader.load(getClass().getResource("/FXML/Producto/editProd.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.showAndWait();

        }else{
            System.out.println("seleccina un producto");
        }

    }

    private void mostrarAlerta(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }



    @Override
    public void update() {
        cargarTabla();
    }


}
