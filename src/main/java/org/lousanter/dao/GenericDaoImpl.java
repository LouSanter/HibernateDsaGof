package org.lousanter.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.Serializable;
import java.util.List;

public class GenericDaoImpl<T, ID extends Serializable> implements GenericDao<T, ID> {

    private final Class<T> entityClass;


    public GenericDaoImpl(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected Session getSession() {
        return HibernateUtil
                .getSessionFactory()
                .openSession();
    }

    //METODOS JPAREPOSITORY BASICOOOO

    public T findById(ID id) {
        Session session = getSession();
        T entidad = session.getReference(entityClass, id);
        session.close();
        return entidad;
    }



    public List<T> findAll() {
        Session session = getSession();
        List<T> list = session.createQuery("from "+entityClass.getName()).list();
        session.close();
        return list;
    }

    public List<T> findByString(String valor, String nameColum) {
        String patron = "%" + valor.toLowerCase() + "%";
        Session session = getSession();
        String hql = "from "+entityClass.getName()+" where LOWER("+nameColum+") LIKE :valor";
        List<T> list = session
                .createQuery(hql)
                .setParameter("valor", patron)
                .list();

        session.close();
        return list;
    }


    public void save(T entity) {
        System.out.println("ENTRANDO EN METODO SAVE");
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        session.persist(entity);
        tx.commit();
        session.close();

    }

    public void update(T entity) {
        System.out.println("ENTRANDO EN METODO UPDATE");
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        session.merge(entity);
        tx.commit();
        session.close();

    }

    public void delete(T entity) {
        Transaction tx = null;
        Session session = getSession();
        try {
            tx = session.beginTransaction();
            session.remove(entity);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw new RuntimeException("No se pudo eliminar el registro. Verifica si esta relacionado a otros datos.", e);
        } finally {
            session.close();
        }
    }


    public void deleteById(ID id) {

        T entidad = findById(id);
        if (entidad != null) {
            delete(entidad);
        }else{
            System.out.println("ENTIDAD NO ENCONTRADA PARA ELIMINAR");
        }

    }
}
