package org.lousanter.model.factory;

import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.lousanter.model.dto.CategoriaDTO;

public class CategoriaFactory {

    public static CategoriaDTO crearDesdeFormulario(TextField nombreField, TextArea descripcionArea) {
        CategoriaDTO dto = new CategoriaDTO();
        dto.setNombre(nombreField.getText());
        dto.setDescripcion(descripcionArea.getText());
        return dto;
    }

    public static CategoriaDTO ActualizarDesdeFormulario(TextField nombreField, TextArea descripcionArea, Long id) {
        CategoriaDTO dto = new CategoriaDTO();
        dto.setId(id);
        dto.setNombre(nombreField.getText());
        dto.setDescripcion(descripcionArea.getText());
        return dto;
    }
}
