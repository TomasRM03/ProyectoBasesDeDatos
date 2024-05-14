package com.uncuyo.dbapp.dao;

import com.uncuyo.dbapp.model.Usuario;
import java.util.List;


public interface UsuarioDAO {
    public void insertarUsuario(Usuario usuario);
    public void modificarUsuario(Usuario usuario);
    public void eliminarUsuario(Usuario usuario);
    public List<Usuario> getUsuarios();
    public Usuario getUsuario(Long cuil);
}
