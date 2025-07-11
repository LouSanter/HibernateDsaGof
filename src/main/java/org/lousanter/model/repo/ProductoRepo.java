package org.lousanter.model.repo;

import org.lousanter.dao.GenericDaoImpl;
import org.lousanter.model.entities.Producto;

public class ProductoRepo extends GenericDaoImpl<Producto, Long> {
    public ProductoRepo(Class<Producto> entityClass) {
        super(entityClass);
    }
}
