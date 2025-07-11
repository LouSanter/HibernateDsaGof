package org.lousanter.model.mapper;

import org.lousanter.model.entities.Proveedor;
import org.lousanter.model.dto.ProveedorDTO;

public class ProveedorMapper {

    public static ProveedorDTO toDTO(Proveedor proveedor) {
        ProveedorDTO proveedorDTO = new ProveedorDTO();
        proveedorDTO.setIdProveedor(proveedor.getIdProveedor());
        proveedorDTO.setNombre(proveedor.getNombre());
        proveedorDTO.setDireccion(proveedor.getDireccion());
        proveedorDTO.setTelefono(proveedor.getTelefono());
        proveedorDTO.setRuc(proveedor.getRuc());
        return proveedorDTO;
    }
    public static Proveedor toEntity(ProveedorDTO proveedorDTO) {
        Proveedor proveedor = new Proveedor();
        proveedor.setIdProveedor(proveedorDTO.getIdProveedor());
        proveedor.setNombre(proveedorDTO.getNombre());
        proveedor.setDireccion(proveedorDTO.getDireccion());
        proveedor.setTelefono(proveedorDTO.getTelefono());
        proveedor.setRuc(proveedorDTO.getRuc());
        return proveedor;
    }


}
