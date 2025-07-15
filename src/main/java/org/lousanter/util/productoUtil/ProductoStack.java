package org.lousanter.util.productoUtil;

import org.lousanter.model.dto.ProductoDTO;
import org.lousanter.model.entities.Producto;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


public class ProductoStack {
    private static final Stack<ProductoHistorialEntry> undoStack = new Stack<>();
    private static final Stack<ProductoHistorialEntry> redoStack = new Stack<>();

    public static void push(ProductoDTO producto, ProductoHistorialEntry.Accion accion) {
        undoStack.push(new ProductoHistorialEntry(producto, accion));
        redoStack.clear();
    }

    public static ProductoHistorialEntry undoPop() {
        ProductoHistorialEntry e = undoStack.pop();
        redoStack.push(e);
        return e;
    }

    public static ProductoHistorialEntry redoPop() {
        ProductoHistorialEntry e = redoStack.pop();
        undoStack.push(e);
        return e;
    }

    public static boolean canUndo() {
        return !undoStack.isEmpty();
    }

    public static boolean canRedo() {
        return !redoStack.isEmpty();
    }
}
