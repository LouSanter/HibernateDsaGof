package org.lousanter.service;

import org.lousanter.model.entities.Ubicacion;
import org.lousanter.model.repo.UbicacionRepo;
import org.lousanter.model.dto.UbicacionDTO;
import org.lousanter.model.mapper.UbicacionMapper;
import org.lousanter.util.ubicacionList.UbicacionList;

import java.util.List;

public class UbicacionService {

    private final UbicacionRepo ubRepo = new UbicacionRepo(Ubicacion.class);

    public List<UbicacionDTO> listarUbicaciones() {
        return UbicacionList.getUbicacionList();
    }

    public UbicacionDTO buscarUbicacion(Long id) {
        UbicacionDTO ub = UbicacionList.getUbi(id);
        return (ub != null) ? ub : null;
    }

    public void insertarUbicacion(UbicacionDTO dto) {
        Ubicacion ubicacion = UbicacionMapper.toEntity(dto);
        ubRepo.save(ubicacion);
        dto.setIdUbicacion(ubicacion.getIdUbicacion());
        UbicacionList.addUbi(dto);
    }

    public void updateUbicacion(UbicacionDTO dto) {
        Ubicacion ubicacion = UbicacionMapper.toEntity(dto);
        ubRepo.update(ubicacion);
        dto.setIdUbicacion(ubicacion.getIdUbicacion());
        UbicacionList.updateUbi(dto);

    }


    public void deleteUbicacion(UbicacionDTO dto) {
        Ubicacion ubicacion = UbicacionMapper.toEntity(dto);
        ubRepo.delete(ubicacion);
        dto.setIdUbicacion(ubicacion.getIdUbicacion());
        UbicacionList.deleteUbi(dto);

    }

    public List<UbicacionDTO> searchUbicacion(String nombre) {
        return UbicacionList.buscarUbi(nombre);
    }
}

