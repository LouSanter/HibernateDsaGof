package org.lousanter.model.repo;

import org.lousanter.dao.GenericDaoImpl;
import org.lousanter.model.entities.Ubicacion;

public class UbicacionRepo extends GenericDaoImpl<Ubicacion, Long> {

    public UbicacionRepo(Class<Ubicacion> entityClass) {
        super(entityClass);
    }
}
