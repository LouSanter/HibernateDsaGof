package org.lousanter.util.categoriaUtil;

import javafx.scene.control.Alert;
import org.hibernate.Session;
import org.lousanter.dao.HibernateUtil;
import org.lousanter.model.entities.Categoria;
import org.lousanter.model.dto.CategoriaDTO;
import org.lousanter.model.mapper.CategoriaMapper;

import java.util.ArrayList;
import java.util.List;

public class CategoriaList {

    private static Nodo head = null;

    private static class Nodo {
        CategoriaDTO dato;
        Nodo siguiente;

        Nodo(CategoriaDTO dato) {
            this.dato = dato;
        }
    }

    public static void addCategoria(CategoriaDTO categoria) {
        Nodo nuevo = new Nodo(categoria);
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

    public static void deleteCategoria(CategoriaDTO categoria) {
        if (head == null) return;

        if (head.dato.equals(categoria)) {
            head = head.siguiente;
            return;
        }

        Nodo actual = head;
        while (actual.siguiente != null && !actual.siguiente.dato.equals(categoria)) {
            actual = actual.siguiente;
        }

        if (actual.siguiente != null) {
            actual.siguiente = actual.siguiente.siguiente;
        }
    }

    public static void updateCategoria(CategoriaDTO categoria) {
        Long id = categoria.getId();
        Nodo actual = head;

        while (actual != null) {
            if (actual.dato.getId().equals(id)) {
                actual.dato = categoria;
                return;
            }
            actual = actual.siguiente;
        }

        System.out.println("CATEGORIA NO ENCONTRADA, NO SE ACTUALIZO");
    }

    public static CategoriaDTO getCategoria(Long id) {
        Nodo actual = head;

        while (actual != null) {
            if (actual.dato.getId().equals(id)) {
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
            List<Categoria> categorias = se.createQuery("from Categoria", Categoria.class).list();
            head = null;

            for (Categoria c : categorias) {
                addCategoria(CategoriaMapper.toDTO(c));
            }

            se.getTransaction().commit();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Error al cargar categorías");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        } finally {
            se.close();
        }
    }

    public static List<CategoriaDTO> buscarCate(String value) {
        List<CategoriaDTO> resultado = new ArrayList<>();
        Nodo actual = head;

        while (actual != null) {
            if (actual.dato.getNombre().toLowerCase().contains(value.toLowerCase())) {
                resultado.add(actual.dato);
            }
            actual = actual.siguiente;
        }

        return resultado;
    }

    public static List<CategoriaDTO> getCateList() {
        List<CategoriaDTO> lista = new ArrayList<>();
        Nodo actual = head;

        while (actual != null) {
            lista.add(actual.dato);
            actual = actual.siguiente;
        }

        return lista;
    }
}
