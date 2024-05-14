package com.uncuyo.dbapp.dao;
import com.uncuyo.dbapp.model.Dieta;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class DietaDAOImp implements DietaDAO{
private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("Persistencia");    
    
    @Override
    public void insertarDieta(Dieta dieta) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(dieta);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void modificarDieta(Integer id, String obj) {
        EntityManager em = emf.createEntityManager();        
        Dieta a = em.find(Dieta.class, id);
        em.getTransaction().begin();
        a.setObjetivodieta(obj);   
        em.persist(a);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void eliminarDieta(Dieta dieta) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Dieta a = em.find(Dieta.class,dieta.getId());
        em.remove(a);
        em.getTransaction().commit();
        em.close();
    }
    
    @Override
    public Dieta getDieta(Integer id){
        EntityManager em = emf.createEntityManager();
        List<Dieta> dietas = (List<Dieta>) em.createQuery("From Dieta Where id = " + id.toString()).getResultList();        
        return dietas.getFirst();
    }

    public List<Dieta> getDietas() {
        EntityManager em = emf.createEntityManager();
        List<Dieta> dietas = null;
        try{
        dietas = (List<Dieta>) em.createQuery("From Dieta").getResultList();        
        }catch(Exception e){
            e.printStackTrace();
        }
        em.close();
        return dietas;
    }
}
    