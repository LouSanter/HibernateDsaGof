package org.lousanter.model.mapper;

import org.lousanter.model.entities.Ubicacion;
import org.lousanter.model.dto.UbicacionDTO;

public class UbicacionMapper {

    public static UbicacionDTO toDTO(Ubicacion ubicacion) {
        UbicacionDTO ubicacionDTO = new UbicacionDTO();
        ubicacionDTO.setIdUbicacion(ubicacion.getIdUbicacion());
        ubicacionDTO.setNombre(ubicacion.getNombre());
        ubicacionDTO.setDescripcion(ubicacion.getDescripcion());
        return ubicacionDTO;
    }
    public static Ubicacion toEntity(UbicacionDTO ubicacionDTO) {
        Ubicacion ubicacion = new Ubicacion();
        ubicacion.setIdUbicacion(ubicacionDTO.getIdUbicacion());
        ubicacion.setNombre(ubicacionDTO.getNombre());
        ubicacion.setDescripcion(ubicacionDTO.getDescripcion());
        return ubicacion;
    }
}
