package org.lousanter.service;

import org.lousanter.model.dto.ProveedorDTO;
import org.lousanter.model.entities.Categoria;
import org.lousanter.model.repo.CategoriaRepo;
import org.lousanter.model.dto.CategoriaDTO;
import org.lousanter.model.mapper.CategoriaMapper;
import org.lousanter.util.categoriaUtil.CategoriaList;
import org.lousanter.util.proveedorUtil.ProveedorList;

import java.util.List;


public class CategoriaService{



    CategoriaRepo cRepo = new CategoriaRepo(Categoria.class);


    public List<CategoriaDTO> listarCategoria(){
        return CategoriaList.getCateList();
    }

    public List<CategoriaDTO> listarCategoriaByName(String nome){
        return CategoriaList.buscarCate(nome);
    }


    public void registrarCategoria(CategoriaDTO dto){
        Categoria categoria = CategoriaMapper.toEntity(dto);
        cRepo.save(categoria);
        dto.setId(categoria.getIdCategoria());
        CategoriaList.addCategoria(dto);

    }

    public void eliminarCategoria(CategoriaDTO dto){
        Categoria categoria = CategoriaMapper.toEntity(dto);
        cRepo.delete(categoria);
        dto.setId(categoria.getIdCategoria());
        CategoriaList.deleteCategoria(dto);

    }

    public void editarCategoria(CategoriaDTO dto){

        if (dto.getId() != null)   {
            Categoria categoria = CategoriaMapper.toEntity(dto);
            cRepo.update(categoria);
            dto.setId(categoria.getIdCategoria());
            CategoriaList.updateCategoria(dto);

        }else {
            System.out.println("NO ID ENCONTRADO");
        }

    }


    public CategoriaDTO findById(Long idCategoria) {
        return CategoriaList.getCategoria(idCategoria);

    }
}
