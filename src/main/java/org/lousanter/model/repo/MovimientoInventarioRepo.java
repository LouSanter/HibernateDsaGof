package org.lousanter.model.repo;

import org.lousanter.dao.GenericDaoImpl;
import org.lousanter.model.entities.MovimientoInventario;

public class MovimientoInventarioRepo extends GenericDaoImpl<MovimientoInventario, Long> {

    public MovimientoInventarioRepo(Class<MovimientoInventario> entityClass) {
        super(entityClass);
    }
}
