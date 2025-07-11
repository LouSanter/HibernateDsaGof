package org.lousanter.dao;


import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.lousanter.model.entities.*;

public class HibernateUtil {


    private static SessionFactory sessionFactory = buildSessionFactory();


    private static SessionFactory buildSessionFactory() {
        try {
            StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                    .applySetting("hibernate.connection.driver_class", "org.postgresql.Driver")
                    .applySetting("hibernate.connection.url", "jdbc:postgresql://localhost:5432/HibernateOne")
                    .applySetting("hibernate.connection.username", "santer")
                    .applySetting("hibernate.connection.password", "1234")
                    .applySetting("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect")
                    .applySetting("hibernate.show_sql", "true")
                    .applySetting("hibernate.hbm2ddl.auto", "update")
                    .applySetting("hibernate.format_sql", "true")
                    .build();

            return new MetadataSources(registry)
                    .addAnnotatedClass(Categoria.class)
                    .addAnnotatedClass(Producto.class)
                    .addAnnotatedClass(Proveedor.class)
                    .addAnnotatedClass(Ubicacion.class)
                    .addAnnotatedClass(MovimientoInventario.class)
                    .buildMetadata()
                    .buildSessionFactory();

        } catch (Exception e) {
            System.out.println("ERROR EN CREAR SESSIONFACTORY" + e.getMessage());
            return null;
        }

    }




    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }




}
