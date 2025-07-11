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

    private static Long itemsBD = 0L;
    private static Long itemsFF = 0L;

    private static List<ProveedorDTO> provList = new ArrayList<>();


    public static void addProveedor(ProveedorDTO proveedor){
        provList.add(proveedor);
        itemsFF++;
    }

    public static void deleteProveedor(ProveedorDTO proveedor){
        try{
            provList.remove(proveedor);
            itemsFF--;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static void updateProveedor(ProveedorDTO proveedor){
        Long id = proveedor.getIdProveedor();
        int index = 0;

        for (int i = 0; i < provList.size(); i++){
            if (provList.get(i).getIdProveedor().equals(id)){
                index = i;
                break;
            }
        }

        if (index != -1){
            provList.set(index, proveedor);
        }else{
            System.out.println("PROVVEDOR NO ENCONTRADO, NO SE ACTUALIZO");
        }
    }


    public static ProveedorDTO getProveedor(Long id){
        Proveedor prov;
        for (int i = 0; i< provList.size(); i++ ){
            if (provList.get(i).getIdProveedor().equals(id)){
                return provList.get(i);
            }
        }
        return null;
    }


    public static void sincro() {
        Session se = HibernateUtil.getSessionFactory().openSession();
        try {
            se.beginTransaction();
            List<Proveedor> provs = se.createQuery("from Proveedor ", Proveedor.class).list();
            provList.clear();

            provs.forEach(prov -> provList.add(ProveedorMapper.toDTO(prov)));


            itemsBD = (long) provList.size();
            itemsFF = (long) provList.size();


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

    public static List<ProveedorDTO> buscarProv(String value){

        List<ProveedorDTO> provs = new ArrayList<>();

        for (int i = 0; i<provList.size();i++){
            if (provList.get(i).getNombre().toLowerCase().contains(value.toLowerCase())){
                provs.add(provList.get(i));
            }
        }

        return provs;
    }
    public static List<ProveedorDTO> getProvList(){
        return provList;
    }


    public static void deleteById(Long id){
        deleteProveedor(getProveedor(id));
    }



}
