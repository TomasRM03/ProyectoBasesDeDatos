package com.uncuyo.dbapp.dao;

import com.uncuyo.dbapp.model.Usuario;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class UsuarioDAOImp implements UsuarioDAO{
private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("Persistencia");    

    @Override
    public void insertarUsuario(Usuario usuario) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(usuario);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void modificarUsuario(Usuario usuario) {
        EntityManager em = emf.createEntityManager();        
        Usuario a = em.find(Usuario.class,usuario.getCuil());
        em.getTransaction().begin();
        a.setNombre(usuario.getNombre());
        a.setApellido(usuario.getApellido());
        a.setSuenopromedio(usuario.getSuenopromedio());
        a.setFechaNacimiento(usuario.getFechaNacimiento());
        a.setSexo(usuario.getSexo());
        a.setGenero(usuario.getGenero());
        a.setImc(usuario.getImc());
        a.setEstadoPeso(usuario.getEstadoPeso());
        a.setHorasDeporteSemanales(usuario.getHorasDeporteSemanales());    
        em.persist(a);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void eliminarUsuario(Usuario usuario) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Usuario a = em.find(Usuario.class,usuario.getCuil());
        em.remove(a);
        em.getTransaction().commit();
        em.close();
    }
    
    @Override
    public Usuario getUsuario(Long cuil){
        EntityManager em = emf.createEntityManager();
        List<Usuario> usuarios = (List<Usuario>) em.createQuery("From Usuario Where cuil = " + cuil.toString()).getResultList();        
        return usuarios.getFirst();
    }

    public List<Usuario> getUsuarios() {
        EntityManager em = emf.createEntityManager();
        List<Usuario> usuarios = null;
        try{
        usuarios = (List<Usuario>) em.createQuery("From Usuario").getResultList();        
        }catch(Exception e){
            e.printStackTrace();
        }
        em.close();
        return usuarios;
    }
    
}
