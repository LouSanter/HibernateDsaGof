package org.lousanter.service;

import org.lousanter.model.entities.Proveedor;
import org.lousanter.model.repo.ProveedorRepo;
import org.lousanter.model.dto.ProveedorDTO;
import org.lousanter.model.mapper.ProveedorMapper;
import org.lousanter.util.proveedorUtil.ProveedorList;

import java.util.List;

public class ProveedorService {

    private final ProveedorRepo proRepo = new ProveedorRepo(Proveedor.class);

    public List<ProveedorDTO> findAll() {
        return ProveedorList.getProvList();
    }

    public ProveedorDTO findById(Long id) {
        return ProveedorList.getProveedor(id);
    }

    public void save(ProveedorDTO dto) {
        Proveedor proveedor = ProveedorMapper.toEntity(dto);
        proRepo.save(proveedor);
        dto.setIdProveedor(proveedor.getIdProveedor());
        ProveedorList.addProveedor(dto);

    }

    public void delete(ProveedorDTO dto) {
        Proveedor proveedor = ProveedorMapper.toEntity(dto);

        ProveedorList.deleteProveedor(dto);
        proRepo.delete(proveedor);
    }

    public void update(ProveedorDTO dto) {
        Proveedor proveedor = ProveedorMapper.toEntity(dto);
        ProveedorList.updateProveedor(dto);
        proRepo.update(proveedor);
    }

    public List<ProveedorDTO> findByName(String name) {
        return ProveedorList.buscarProv(name);
    }

    public void deleteById(Long id) {
        ProveedorList.deleteById(id);
        proRepo.deleteById(id);
    }
}
