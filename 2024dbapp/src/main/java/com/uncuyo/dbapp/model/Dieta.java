/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uncuyo.dbapp.model;

import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author tomas
 */
@Entity
@Table(name="dieta")
public class Dieta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;
    @Column(name="objetivodieta")
    private String objetivodieta;

    public Dieta() {
    }

    public Dieta(String objetivodieta) {
        this.objetivodieta = objetivodieta;
    }

    public Integer getId() {
        return id;
    }

    public String getObjetivodieta() {
        return objetivodieta;
    }

    public void setObjetivodieta(String objetivodieta) {
        this.objetivodieta = objetivodieta;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    @Override
    public String toString() {
        return "Dieta{" + "id=" + id + ", objetivodieta=" + objetivodieta + '}';
    }
    
    
    
    
}
