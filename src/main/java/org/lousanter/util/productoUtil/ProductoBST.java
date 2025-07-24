package org.lousanter.util.productoUtil;


import org.hibernate.Session;
import org.lousanter.dao.HibernateUtil;
import org.lousanter.model.dto.ProductoDTO;
import org.lousanter.model.entities.Producto;
import org.lousanter.model.mapper.ProductoMapper;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductoBST {

    private static class Nodo {
        ProductoDTO producto;
        Nodo izq, der;

        Nodo(ProductoDTO producto) {
            this.producto = producto;
        }
    }



    private static Nodo raiz;


    private static Nodo insertarRec(Nodo actual, ProductoDTO producto) {
        if (actual == null) return new Nodo(producto);

        int cmp = producto.getNombre().compareToIgnoreCase(actual.producto.getNombre());
        if (cmp < 0) actual.izq = insertarRec(actual.izq, producto);
        else if (cmp > 0) actual.der = insertarRec(actual.der, producto);
        return actual;
    }

    public List<ProductoDTO> buscar(String nombre) {
        List<ProductoDTO> resultados = new ArrayList<>();
        buscarRecursivo(raiz, nombre.toLowerCase(), resultados);
        return resultados;
    }

    private void buscarRecursivo(Nodo nodo, String nombreBuscado, List<ProductoDTO> resultados) {
        if (nodo == null) return;

        if (nodo.producto.getNombre().toLowerCase().contains(nombreBuscado)) {
            resultados.add(nodo.producto);
        }

        buscarRecursivo(nodo.izq, nombreBuscado, resultados);
        buscarRecursivo(nodo.der, nombreBuscado, resultados);
    }



    private Nodo eliminarRec(Nodo nodo, Long id) {
        if (nodo == null) return null;

        int cmp = id.compareTo(nodo.producto.getIdProducto());
        if (cmp < 0) nodo.izq = eliminarRec(nodo.izq, id);
        else if (cmp > 0) nodo.der = eliminarRec(nodo.der, id);
        else {
            if (nodo.izq == null) return nodo.der;
            if (nodo.der == null) return nodo.izq;

            Nodo reemplazo = encontrarMin(nodo.der);
            nodo.producto = reemplazo.producto;
            nodo.der = eliminarRec(nodo.der, reemplazo.producto.getIdProducto());
        }
        return nodo;
    }


    private Nodo encontrarMin(Nodo nodo) {
        while (nodo.izq != null) nodo = nodo.izq;
        return nodo;
    }

    public static List<ProductoDTO> getAll() {
        List<ProductoDTO> lista = new ArrayList<>();
        inOrden(raiz, lista);
        return lista;
    }

    private static void inOrden(Nodo nodo, List<ProductoDTO> lista) {
        if (nodo != null) {
            inOrden(nodo.izq, lista);
            lista.add(nodo.producto);
            inOrden(nodo.der, lista);
        }
    }
    
    public static void sincro() {
        Session se = HibernateUtil.getSessionFactory().openSession();
        try {
            se.beginTransaction();
            List<Producto> productos = se.createQuery("from Producto", Producto.class).list();
            clear();
            List<ProductoDTO> dtos = productos.stream()
                    .map(ProductoMapper::toDTO)
                    .collect(Collectors.toList());

            dtos.forEach(ProductoBST::insertar);
            ProductoQueue.cargarDesdeLista(dtos);

            se.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            se.close();
        }
    }




    public void reemplazar(ProductoDTO nuevoProducto) {
        eliminar(nuevoProducto.getIdProducto());
        insertar(nuevoProducto);

    }

    public void eliminar(Long id) {
        raiz = eliminarRec(raiz, id);
    }


    public static void insertar(ProductoDTO producto) {

        raiz = insertarRec(raiz, producto);

    }

    public static void clear() {
        raiz = null;

    }



}
