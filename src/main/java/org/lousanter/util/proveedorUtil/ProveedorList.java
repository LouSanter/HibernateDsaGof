package org.lousanter.util.proveedorUtil;

import javafx.scene.control.Alert;
import org.hibernate.Session;
import org.lousanter.dao.HibernateUtil;
import org.lousanter.model.entities.Proveedor;
import org.lousanter.model.dto.ProveedorDTO;
import org.lousanter.model.mapper.ProveedorMapper;

import java.util.ArrayList;
import java.util.List;

public class ProveedorList {

    private static Nodo head = null;

    private static class Nodo {
        ProveedorDTO dato;
        Nodo siguiente;

        Nodo(ProveedorDTO dato) {
            this.dato = dato;
        }
    }

    public static void addProveedor(ProveedorDTO proveedor) {
        Nodo nuevo = new Nodo(proveedor);
        if (head == null) {
            head = nuevo;
        } else {
            Nodo actual = head;
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = nuevo;
        }
    }

    public static void deleteProveedor(ProveedorDTO proveedor) {
        if (head == null) return;

        if (head.dato.equals(proveedor)) {
            head = head.siguiente;
            return;
        }

        Nodo actual = head;
        while (actual.siguiente != null && !actual.siguiente.dato.equals(proveedor)) {
            actual = actual.siguiente;
        }

        if (actual.siguiente != null) {
            actual.siguiente = actual.siguiente.siguiente;
        }
    }

    public static void updateProveedor(ProveedorDTO proveedor) {
        Long id = proveedor.getIdProveedor();
        Nodo actual = head;

        while (actual != null) {
            if (actual.dato.getIdProveedor().equals(id)) {
                actual.dato = proveedor;
                return;
            }
            actual = actual.siguiente;
        }

        System.out.println("PROVEEDOR NO ENCONTRADO, NO SE ACTUALIZO");
    }

    public static ProveedorDTO getProveedor(Long id) {
        Nodo actual = head;

        while (actual != null) {
            if (actual.dato.getIdProveedor().equals(id)) {
                return actual.dato;
            }
            actual = actual.siguiente;
        }

        return null;
    }

    public static void sincro() {
        Session se = HibernateUtil.getSessionFactory().openSession();
        try {
            se.beginTransaction();
            List<Proveedor> provs = se.createQuery("from Proveedor", Proveedor.class).list();
            head = null;

            for (Proveedor p : provs) {
                addProveedor(ProveedorMapper.toDTO(p));
            }

            se.getTransaction().commit();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Error al cargar proveedores");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        } finally {
            se.close();
        }
    }

    public static List<ProveedorDTO> buscarProv(String value) {
        List<ProveedorDTO> resultado = new ArrayList<>();
        Nodo actual = head;

        while (actual != null) {
            if (actual.dato.getNombre().toLowerCase().contains(value.toLowerCase())) {
                resultado.add(actual.dato);
            }
            actual = actual.siguiente;
        }

        return resultado;
    }

    public static List<ProveedorDTO> getProvList() {
        List<ProveedorDTO> lista = new ArrayList<>();
        Nodo actual = head;

        while (actual != null) {
            lista.add(actual.dato);
            actual = actual.siguiente;
        }

        return lista;
    }

    public static void deleteById(Long id) {
        deleteProveedor(getProveedor(id));
    }
}
