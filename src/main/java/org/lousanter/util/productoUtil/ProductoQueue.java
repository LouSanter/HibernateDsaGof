package org.lousanter.util.productoUtil;

import org.lousanter.model.entities.Producto;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ProductoQueue {

    //MANDAR PRODUCTOS A REPOSICION

    private Queue<Producto> cola = new LinkedList<>();

    public void enqueue (Producto producto) {
        cola.offer(producto);
    }

    public Producto dequeue () {
        return cola.poll();
    }

    public List<Producto> getAll() {
        return new ArrayList<Producto>(cola);
    }
}
