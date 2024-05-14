package com.uncuyo.dbapp.control;

import com.uncuyo.dbapp.dao.UsuarioDAOImp;
import com.uncuyo.dbapp.model.Usuario;
import java.util.List;
import com.uncuyo.dbapp.dao.UsuarioDAO;


public class UsuarioController {
private UsuarioDAO usuariodao;        

    public void insertarUsuario(Usuario usuario){               
        usuariodao.insertarUsuario(usuario);
    }
    
    public void modificarUsuario(Usuario usuario){
        usuariodao.modificarUsuario(usuario);
    }
    
    public void eliminarUsuario(Usuario usuario){
        usuariodao.eliminarUsuario(usuario);
    }
    
    public List<Usuario> getUsuarios(){
        return usuariodao.getUsuarios();
    }

    public UsuarioController() {
        usuariodao = new UsuarioDAOImp();
    }
    
    public Usuario getUsuario(Long cuil){
        Usuario usuario = usuariodao.getUsuario(cuil);
        return usuario;
    }


}
