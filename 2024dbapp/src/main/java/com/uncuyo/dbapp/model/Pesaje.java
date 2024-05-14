package com.uncuyo.dbapp.model;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author tomas
 */
@Entity
@Table(name="pesaje")
public class Pesaje implements Serializable {
    @Id
    @Column(name="cuil")
    private Long cuil;
    @Id
    @Column(name="fechapesaje")
    private Date fechapesaje;
    @Column(name="peso")
    private Float peso;

    public Pesaje() {
    }

    public Pesaje(Long cuil, Date fechapesaje, Float peso) {
        this.cuil = cuil;
        this.fechapesaje = fechapesaje;
        this.peso = peso;
    }

    public Long getCuil() {
        return cuil;
    }

    public Date getFechapesaje() {
        return fechapesaje;
    }

    public Float getPeso() {
        return peso;
    }

    public void setCuil(Long cuil) {
        this.cuil = cuil;
    }

    public void setFechapesaje(Date fechapesaje) {
        this.fechapesaje = fechapesaje;
    }

    public void setPeso(Float peso) {
        this.peso = peso;
    }

    @Override
    public String toString() {
        return "Pesaje{" + "cuil=" + cuil + ", fechapesaje=" + fechapesaje + ", peso=" + peso + '}';
    }
    
    
}
