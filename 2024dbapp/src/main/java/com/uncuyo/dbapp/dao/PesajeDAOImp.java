
package com.uncuyo.dbapp.dao;

import com.uncuyo.dbapp.model.Pesaje;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author tomas
 */
public class PesajeDAOImp implements PesajeDAO{
private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("Persistencia");

    @Override
    public void insertarPesaje(Pesaje pesaje) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(pesaje);
        em.getTransaction().commit();
        em.close();
    }
}