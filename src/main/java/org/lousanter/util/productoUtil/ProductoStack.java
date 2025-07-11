package org.lousanter.util.productoUtil;

import org.lousanter.model.entities.Producto;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


//Pila para el historial de los productos
public class ProductoStack {
    private Stack<Producto> historial = new Stack<>();

    public void push(Producto producto) {
        historial.push(producto);
    }
    public Producto pop() {
        return historial.pop();
    }
    public List<Producto> getAll() {
        return new ArrayList<Producto>(historial);
    }
}
