package org.lousanter.model.repo;

import org.lousanter.dao.GenericDaoImpl;
import org.lousanter.model.entities.Proveedor;

public class ProveedorRepo extends GenericDaoImpl<Proveedor, Long> {

    public ProveedorRepo(Class<Proveedor> entityClass) {
        super(entityClass);
    }
}
