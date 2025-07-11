package org.lousanter;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.lousanter.IA.ChatClientConstructor;
import org.lousanter.service.ProductoService;
import org.lousanter.util.categoriaUtil.CategoriaList;
import org.lousanter.util.productoUtil.ProductoBST;
import org.lousanter.util.proveedorUtil.ProveedorList;
import org.lousanter.util.ubicacionList.UbicacionList;

public class Main extends Application {


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("/FXML/main.fxml"));
        Scene scene = new Scene(root);

        stage.setScene(scene);

        stage.setTitle("INVENTARIO CON DSA");
        stage.setFullScreen(true);

        cargarCache();
        stage.show();

    }


    private void cargarCache() {

        CategoriaList.sincro();
        ProveedorList.sincro();
        UbicacionList.sincro();
        ProductoBST.sincro();

    }
}


