package org.lousanter.util.productoUtil;

import org.lousanter.model.dto.ProductoDTO;

public class ProductoHistorialEntry {

    public enum Accion {
        NUEVO,
        ACTUALIZADO,
        ELIMINADO
    }

    private final ProductoDTO producto;
    private final Accion accion;

    public ProductoHistorialEntry(ProductoDTO producto, Accion accion) {
        this.producto = producto;
        this.accion = accion;
    }

    public ProductoDTO getProducto() {
        return producto;
    }

    public Accion getAccion() {
        return accion;
    }
}
