package org.lousanter.util.categoriaUtil;

import javafx.scene.control.Alert;
import org.hibernate.Session;
import org.lousanter.dao.HibernateUtil;
import org.lousanter.model.entities.Categoria;
import org.lousanter.model.dto.CategoriaDTO;
import org.lousanter.model.mapper.CategoriaMapper;
import org.lousanter.model.observer.Observer;

import java.util.ArrayList;
import java.util.List;

public class CategoriaList {


    //itemsBD obtendra la cantidad de items que deberia tener la base de datos; es decir:
    //si al conectarme con la BD tengo 15 filas, 15 deberia de ser el numero aqui, osea
    //siempre sera el numero de la base de datos, pero como sabria siempre esto? y en que momento
    //actualizaria esta variable para no perjudicar el sistema?, teoricamente al añadir un nuveo item
    //primero deberia de agregarse al itemsTB, posteriormente al la tableView, y finalmente a la itemsBD para
    //actualizar la BD

    //el flujograma seria

    //1.- CREACION DEL PRODUCTO CATEGORIA
    //2.- SE AGREGA A LA LISTA CATEGORIALIST
    //3.- AUMENTA EL CONTADOR ITEMSTB
    //4.- SE REFLAJA EL PRODUCTO EL LA TABLA
    //5.- SE AGREGA EL PRODUCTO EN LA BD
    //6.- AUMENTA EL CONTADOR ITEMSBD


    //para no llamar la BD siempre que requiramos de las categorias, todas estaran en la lista, pero
    //como se que tengo todo actualizado?
    //1.- al iniciar el sistema itemsBD contara las filas de Categoria y se actualizara a ese contador
    //2.- luego itemsTB tambien igualara ese numero
    //3.- si el numero de itemsTB es diferente al de itemsBD entonces se ejectura una actualizacion
    //----ahi es donde entra el flujograma principal


    //esta clase sera publica porlo que todos pueden acceder a categorias, es nesesario pq tampoco
    //quiero instancias nuevas para cada vez que la use, Una sola instancia para todo el proyecto

    //tambien se actualizara cuando inicie el sistema y ambos items esten en 0 o null

    private static Long itemsBD = 0L;
    private static Long itemsFF = 0L;

    private static List<CategoriaDTO> cateList = new ArrayList<>();






    public static void addCategoria(CategoriaDTO categoria){
        cateList.add(categoria);
        itemsFF++;

    }

    public static void deleteCategoria(CategoriaDTO categoria){
        try{
            cateList.remove(categoria);
            itemsFF--;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static void updateCategoria(CategoriaDTO categoria){
        Long id = categoria.getId();
        int index = 0;

        for (int i = 0; i < cateList.size(); i++){
            if (cateList.get(i).getId().equals(id)){
                index = i;
                break;
            }
        }

        if (index != -1){
            cateList.set(index, categoria);
        }else{
            System.out.println("CATEGORIA NO ENCONTRADA, NO SE ACTUALIZO");
        }
    }


    public static CategoriaDTO getCategoria(Long id){
        CategoriaDTO cate;
        for (int i = 0; i< cateList.size(); i++ ){
            if (cateList.get(i).getId().equals(id)){
                return cateList.get(i);
            }
        }
        return null;
    }


    public static void sincro() {
        Session se = HibernateUtil.getSessionFactory().openSession();
        try {
            se.beginTransaction();
            List<Categoria> categorias = se.createQuery("from Categoria", Categoria.class).list();
            cateList.clear();

            categorias.forEach(e -> CategoriaList.addCategoria(CategoriaMapper.toDTO(e)));


            itemsBD = (long) cateList.size();
            itemsFF = (long) cateList.size();


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

    public static List<CategoriaDTO> buscarCate(String value){

        List<CategoriaDTO> cates = new ArrayList<>();

        for (int i = 0; i<cateList.size();i++){
            if (cateList.get(i).getNombre().toLowerCase().contains(value.toLowerCase())){
                cates.add(cateList.get(i));
            }
        }

    return cates;
    }
    public static List<CategoriaDTO> getCateList(){
        return cateList;
    }



}
