package com.uncuyo.dbapp.dao;

import com.uncuyo.dbapp.model.Dieta;
import java.util.List;


public interface DietaDAO {
    public void insertarDieta(Dieta dieta);
    public void modificarDieta(Integer id, String obj);
    public void eliminarDieta(Dieta dieta);
    public List<Dieta> getDietas();
    public Dieta getDieta(Integer id);
}
