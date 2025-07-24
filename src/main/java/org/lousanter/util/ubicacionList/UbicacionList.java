package org.lousanter.util.ubicacionList;

import javafx.scene.control.Alert;
import org.hibernate.Session;
import org.lousanter.dao.HibernateUtil;
import org.lousanter.model.entities.Ubicacion;
import org.lousanter.model.dto.UbicacionDTO;
import org.lousanter.model.mapper.UbicacionMapper;

import java.util.ArrayList;
import java.util.List;

public class UbicacionList {


    private static Nodo head = null;

    private static class Nodo {
        UbicacionDTO dato;
        Nodo siguiente;

        Nodo(UbicacionDTO dato) {
            this.dato = dato;
            this.siguiente = null;
        }
    }

    public static void addUbi(UbicacionDTO ubicacion) {
        Nodo nuevo = new Nodo(ubicacion);
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

    public static void deleteUbi(UbicacionDTO ubicacion) {
        if (head == null) return;

        if (head.dato.equals(ubicacion)) {
            head = head.siguiente;
            return;
        }

        Nodo actual = head;
        while (actual.siguiente != null && !actual.siguiente.dato.equals(ubicacion)) {
            actual = actual.siguiente;
        }

        if (actual.siguiente != null) {
            actual.siguiente = actual.siguiente.siguiente;
        }
    }

    public static void updateUbi(UbicacionDTO ubicacion) {
        Long id = ubicacion.getIdUbicacion();
        Nodo actual = head;
        while (actual != null) {
            if (actual.dato.getIdUbicacion().equals(id)) {
                actual.dato = ubicacion;
                return;
            }
            actual = actual.siguiente;
        }
        System.out.println("UBICACION NO ENCONTRADA, NO SE ACTUALIZO");
    }

    public static UbicacionDTO getUbi(Long id) {
        Nodo actual = head;
        while (actual != null) {
            if (actual.dato.getIdUbicacion().equals(id)) {
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
            List<Ubicacion> ubs = se.createQuery("from Ubicacion", Ubicacion.class).list();
            head = null;

            for (Ubicacion ub : ubs) {
                addUbi(UbicacionMapper.toDTO(ub));
            }


            se.getTransaction().commit();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Error al cargar ubicaciones");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        } finally {
            se.close();
        }
    }

    public static List<UbicacionDTO> buscarUbi(String value) {
        List<UbicacionDTO> resultado = new ArrayList<>();
        Nodo actual = head;

        while (actual != null) {
            if (actual.dato.getNombre().toLowerCase().contains(value.toLowerCase())) {
                resultado.add(actual.dato);
            }
            actual = actual.siguiente;
        }
        return resultado;
    }

    public static List<UbicacionDTO> getUbicacionList() {
        List<UbicacionDTO> lista = new ArrayList<>();
        Nodo actual = head;

        while (actual != null) {
            lista.add(actual.dato);
            actual = actual.siguiente;
        }
        return lista;
    }

    public static void deleteById(Long id) {
        deleteUbi(getUbi(id));
    }
}

