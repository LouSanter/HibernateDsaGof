package org.lousanter.dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDao<T , ID extends Serializable> {

    T findById(ID id);
    List<T> findAll();
    T save(T entity);
    void update(T entity);
    void delete(T entity);
    void deleteById(ID id);

}
