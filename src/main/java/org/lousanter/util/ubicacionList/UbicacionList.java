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


    private static Long itemsBD = 0L;
    private static Long itemsFF = 0L;

    private static List<UbicacionDTO> ubiList = new ArrayList<>();



    public static void addUbi(UbicacionDTO ubicacion){
        ubiList.add(ubicacion);
        itemsFF++;
    }

    public static void deleteUbi(UbicacionDTO ubicacion){
        try{
            ubiList.remove(ubicacion);
            itemsFF--;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static void updateUbi(UbicacionDTO ubicacion){
        Long id = ubicacion.getIdUbicacion();
        int index = 0;

        for (int i = 0; i < ubiList.size(); i++){
            if (ubiList.get(i).getIdUbicacion().equals(id)){
                index = i;
                break;
            }
        }

        if (index != -1){
            ubiList.set(index, ubicacion);
        }else{
            System.out.println("UBICACION NO ENCONTRADO, NO SE ACTUALIZO");
        }
    }


    public static UbicacionDTO getUbi(Long id){

        for (int i = 0; i< ubiList.size(); i++ ){
            if (ubiList.get(i).getIdUbicacion().equals(id)){
                return ubiList.get(i);
            }
        }
        return null;
    }


    public static void sincro() {
        Session se = HibernateUtil.getSessionFactory().openSession();
        try {
            se.beginTransaction();
            List<Ubicacion> ubs = se.createQuery("from Ubicacion ", Ubicacion.class).list();
            ubiList.clear();
            ubs.forEach(ub -> ubiList.add(UbicacionMapper.toDTO(ub)));


            itemsBD = (long) ubiList.size();
            itemsFF = (long) ubiList.size();


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

    public static List<UbicacionDTO> buscarUbi(String value){

        List<UbicacionDTO> ubs = new ArrayList<>();

        for (int i = 0; i<ubiList.size();i++){
            if (ubiList.get(i).getNombre().toLowerCase().contains(value.toLowerCase())){
                ubs.add(ubiList.get(i));
            }
        }

        return ubs;
    }
    public static List<UbicacionDTO> getUbicacionList(){
        return ubiList;
    }


    public static void deleteById(Long id){
        deleteUbi(getUbi(id));
    }



}
