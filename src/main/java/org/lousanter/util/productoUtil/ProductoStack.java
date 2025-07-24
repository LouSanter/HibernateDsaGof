package org.lousanter.util.productoUtil;

import org.lousanter.model.dto.ProductoDTO;

public class ProductoStack {

    private static Nodo top = null;

    private static class Nodo {
        ProductoHistorialEntry entry;
        Nodo siguiente;

        Nodo(ProductoHistorialEntry entry) {
            this.entry = entry;
        }
    }

    public static void push(ProductoDTO producto, ProductoHistorialEntry.Accion accion) {
        ProductoHistorialEntry nuevoEntry = new ProductoHistorialEntry(producto, accion);
        Nodo nuevoNodo = new Nodo(nuevoEntry);
        nuevoNodo.siguiente = top;
        top = nuevoNodo;
    }

    public static ProductoHistorialEntry undoPop() {
        if (top == null) return null;
        ProductoHistorialEntry entry = top.entry;
        top = top.siguiente;
        return entry;
    }

    public static boolean canUndo() {
        return top != null;
    }
}
