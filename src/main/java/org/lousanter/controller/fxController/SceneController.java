package org.lousanter.controller.fxController;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;



import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class SceneController implements Initializable {
    @FXML
    private Button btnProd;
    @FXML
    private Button btnRepo;
    @FXML
    private Button btnCate;
    @FXML
    private GridPane grid;
    @FXML
    private Button btnUbi;

    @FXML
    private Button btnProv;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)  {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/FXML/Producto/producto.fxml"));
            grid.getChildren().remove(1,1);
            grid.add(root,1,1);
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    public void goToProv(ActionEvent event) throws IOException {
        goTo("/Proveedor/proveedor");
    }

    public void goToCate(ActionEvent event) throws IOException {
        goTo("/Categoria/categorias");
    }

    public void goToProd(ActionEvent event) throws IOException {
        goTo("/Producto/producto");
    }

    public void goToRepo(ActionEvent event) throws IOException {
        goTo("/Reposicion/repo");
    }

    public void goToUbi(ActionEvent event) throws IOException {
        goTo("/Ubicacion/ubicacion");
    }


    public void goTo(String var) throws IOException {
        Parent newContent = FXMLLoader.load(getClass().getResource("/FXML/" + var + ".fxml"));
        clearAndAdd(newContent);

    }


    private void clearAndAdd(Parent newContent) {

        grid.getChildren().removeIf(node ->
                GridPane.getColumnIndex(node) != null && GridPane.getColumnIndex(node) == 1 &&
                        GridPane.getRowIndex(node) != null && GridPane.getRowIndex(node) == 1
        );

        grid.add(newContent, 1, 1);
    }



}
