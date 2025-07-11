package org.lousanter.model.factory;

import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.lousanter.model.dto.UbicacionDTO;

public class UbicacionFactory {

    public static UbicacionDTO crearNueva(TextField lbl1, TextField lbl2, TextField lbl3, TextField lbl4, TextArea descripcionArea) {
        UbicacionDTO dto = new UbicacionDTO();
        dto.setNombre(formatearNombre(lbl1, lbl2, lbl3, lbl4));
        dto.setDescripcion(descripcionArea.getText()!=null?descripcionArea.getText():"Sin descripcion adicional");
        return dto;
    }

    public static UbicacionDTO editarExistente(Long id, TextField lbl1, TextField lbl2, TextField lbl3, TextField lbl4, TextArea descripcionArea) {
        UbicacionDTO dto = crearNueva(lbl1, lbl2, lbl3, lbl4, descripcionArea);
        dto.setIdUbicacion(id);
        return dto;
    }


    private static String formatearNombre(TextField lbl1, TextField lbl2, TextField lbl3, TextField lbl4) {
        return String.join(" - ",
                lbl1.getText().trim(),
                lbl2.getText().trim(),
                lbl3.getText().trim(),
                lbl4.getText().trim());
    }
}
