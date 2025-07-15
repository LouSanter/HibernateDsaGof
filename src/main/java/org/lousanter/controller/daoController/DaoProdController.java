package org.lousanter.controller.daoController;

import org.lousanter.model.dto.ProductoDTO;
import org.lousanter.model.entities.Categoria;
import org.lousanter.model.entities.Proveedor;
import org.lousanter.model.entities.Ubicacion;
import org.lousanter.model.observer.Observable;
import org.lousanter.service.ProductoService;



public class DaoProdController extends ProductoService {

    @Override
    public void registrarProducto(ProductoDTO dto, Ubicacion ubicacion, Proveedor proveedor, Categoria categoria) {
        super.registrarProducto(dto, ubicacion, proveedor, categoria);

        Observable.notifyObservers();
    }

    @Override
    public void actualizarProducto(ProductoDTO dto, Ubicacion ubicacion, Proveedor proveedor, Categoria categoria) {
        super.actualizarProducto(dto, ubicacion, proveedor, categoria);
        Observable.notifyObservers();
    }

    @Override
    public void eliminarProducto(Long id) {
        super.eliminarProducto(id);
        Observable.notifyObservers();
    }

}
