package com.uncuyo.dbapp.control;

import com.uncuyo.dbapp.dao.DietaDAOImp;
import com.uncuyo.dbapp.model.Dieta;
import java.util.List;
import com.uncuyo.dbapp.dao.DietaDAO;


public class DietaController {
private DietaDAO dietadao;        

    public void insertarDieta(Dieta dieta){               
        dietadao.insertarDieta(dieta);
    }
    
    public void modificarDieta(Integer id, String obj){
        dietadao.modificarDieta(id, obj);
    }
    
    public void eliminarDieta(Dieta dieta){
        dietadao.eliminarDieta(dieta);
    }
    
    public List<Dieta> getDietas(){
        return dietadao.getDietas();
    }

    public DietaController() {
        dietadao = new DietaDAOImp();
    }
    
    public Dieta getDieta(Integer id){
        Dieta dieta = dietadao.getDieta(id);
        return dieta;
    }


}