package org.lousanter.model.repo;


import org.lousanter.dao.GenericDaoImpl;
import org.lousanter.model.entities.Categoria;

public class CategoriaRepo extends GenericDaoImpl<Categoria, Long> {
    public CategoriaRepo(Class<Categoria> entityClass) {
        super(entityClass);
    }
}
