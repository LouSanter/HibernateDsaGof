package org.lousanter.model.dto;

public class CategoriaDTO {

    private Long idCategoria;
    private String nombre;
    private String descripcion;



    public CategoriaDTO() {

    }
    public CategoriaDTO(Long id, String nombre) {
        this.idCategoria = id;
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }


    public void setIdCategoria(Long idCategoria) {
        this.idCategoria = idCategoria;
    }

    public Long getId() {
        return idCategoria;
    }

    public void setId(Long id) {
        this.idCategoria = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
