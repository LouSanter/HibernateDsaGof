package org.lousanter.util.productoUtil;

import org.lousanter.model.dto.ProductoDTO;

import java.util.ArrayList;
import java.util.List;

public class ProductoQueue {

    private static Nodo frente = null;
    private static Nodo fin = null;

    private static class Nodo {
        ProductoDTO producto;
        Nodo siguiente;

        Nodo(ProductoDTO producto) {
            this.producto = producto;
        }
    }

    public static void enqueue(ProductoDTO producto) {
        if (!contains(producto.getIdProducto())) {
            Nodo nuevo = new Nodo(producto);
            if (fin != null) {
                fin.siguiente = nuevo;
            } else {
                frente = nuevo;
            }
            fin = nuevo;
        }
    }

    public static ProductoDTO dequeue() {
        if (frente == null) return null;
        ProductoDTO producto = frente.producto;
        frente = frente.siguiente;
        if (frente == null) fin = null;
        return producto;
    }

    public static boolean contains(Long idProducto) {
        Nodo actual = frente;
        while (actual != null) {
            if (actual.producto.getIdProducto().equals(idProducto)) {
                return true;
            }
            actual = actual.siguiente;
        }
        return false;
    }

    public static void removeById(Long idProducto) {
        Nodo actual = frente;
        Nodo anterior = null;

        while (actual != null) {
            if (actual.producto.getIdProducto().equals(idProducto)) {
                if (anterior == null) {
                    frente = actual.siguiente;
                    if (frente == null) fin = null;
                } else {
                    anterior.siguiente = actual.siguiente;
                    if (actual == fin) fin = anterior;
                }
                break;
            }
            anterior = actual;
            actual = actual.siguiente;
        }
    }

    public static List<ProductoDTO> getAll() {
        List<ProductoDTO> lista = new ArrayList<>();
        Nodo actual = frente;
        while (actual != null) {
            lista.add(actual.producto);
            actual = actual.siguiente;
        }
        return lista;
    }

    public static void clear() {
        frente = null;
        fin = null;
    }

    public static void cargarDesdeLista(List<ProductoDTO> productos) {
        clear();
        for (ProductoDTO p : productos) {
            if (p.getStock() <= p.getStockMin()) {
                enqueue(p);
            }
        }
    }
}
