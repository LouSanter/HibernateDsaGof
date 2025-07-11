package org.lousanter.model.factory;

import javafx.scene.control.TextField;
import org.lousanter.model.dto.ProveedorDTO;

public class ProveedorFactory {

    public static ProveedorDTO crearDesdeFormulario(TextField nombreField, TextField ruc, TextField direccion, TextField telefono) {
        ProveedorDTO dto = new ProveedorDTO();
        dto.setNombre(nombreField.getText());
        dto.setTelefono(telefono.getText());
        dto.setDireccion(direccion.getText());
        dto.setRuc(ruc.getText());
        return dto;
    }

    public static ProveedorDTO actualizarDesdeFormulario(TextField nombreField, TextField ruc, TextField direccion, TextField telefono, Long idExistente) {
        ProveedorDTO dto = new ProveedorDTO();
        dto.setIdProveedor(idExistente);
        dto.setNombre(nombreField.getText());
        dto.setTelefono(telefono.getText());
        dto.setDireccion(direccion.getText());
        dto.setRuc(ruc.getText());
        return dto;
    }




}
