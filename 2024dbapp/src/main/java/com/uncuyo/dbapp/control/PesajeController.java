/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uncuyo.dbapp.control;

import com.uncuyo.dbapp.dao.PesajeDAO;
import com.uncuyo.dbapp.dao.PesajeDAOImp;
import com.uncuyo.dbapp.model.Pesaje;

/**
 *
 * @author tomas
 */
public class PesajeController {
    private PesajeDAO pesajedao;        

    public void insertarPesaje(Pesaje pesaje){               
        pesajedao.insertarPesaje(pesaje);
    }
    
    public PesajeController() {
        pesajedao = new PesajeDAOImp();
    }
}
