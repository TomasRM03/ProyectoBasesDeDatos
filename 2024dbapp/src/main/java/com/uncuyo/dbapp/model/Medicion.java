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
@Table(name="medicion")
public class Medicion implements Serializable {
    @Id
    @Column(name="cuil")
    private Long cuil;
    @Id
    @Column(name="fechamedicion")
    private Date fechamedicion;
    @Column(name="altura")
    private Float altura;

    public Medicion() {
    }

    public Medicion(Long cuil, Date fechamedicion, Float altura) {
        this.cuil = cuil;
        this.fechamedicion = fechamedicion;
        this.altura = altura;
    }

    public Long getCuil() {
        return cuil;
    }

    public Date getFechamedicion() {
        return fechamedicion;
    }

    public Float getAltura() {
        return altura;
    }

    public void setCuil(Long cuil) {
        this.cuil = cuil;
    }

    public void setFechamedicion(Date fechamedicion) {
        this.fechamedicion = fechamedicion;
    }

    public void setAltura(Float altura) {
        this.altura = altura;
    }

    @Override
    public String toString() {
        return "Medicion{" + "cuil=" + cuil + ", fechamedicion=" + fechamedicion + ", altura=" + altura + '}';
    }
    
    
}
