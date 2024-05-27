package com.uncuyo.dbapp.dao;
import com.uncuyo.dbapp.dao.MedicionDAO;
import com.uncuyo.dbapp.model.Medicion;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class MedicionDAOImp implements MedicionDAO{
private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("Persistencia");

    @Override
    public void insertarMedicion(Medicion medicion) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(medicion);
        em.getTransaction().commit();
        em.close();
    }
}
