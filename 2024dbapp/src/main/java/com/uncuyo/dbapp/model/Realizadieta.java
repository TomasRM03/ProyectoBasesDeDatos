
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
@Table(name="realizadieta")
public class Realizadieta implements Serializable {
    @Id
    @Column(name="cuil")
    private Long cuil;
    @Id
    @Column(name="fechainicio")
    private Date fechainicio;
    @Column(name="nrodieta")
    private Integer nrodieta;

    public Realizadieta() {
    }

    public Realizadieta(Long cuil, Date fechainicio, Integer nrodieta) {
        this.cuil = cuil;
        this.fechainicio = fechainicio;
        this.nrodieta = nrodieta;
    }

    public Long getCuil() {
        return cuil;
    }

    public Date getFechainicio() {
        return fechainicio;
    }

    public Integer getNrodieta() {
        return nrodieta;
    }

    public void setCuil(Long cuil) {
        this.cuil = cuil;
    }

    public void setFechainicio(Date fechainicio) {
        this.fechainicio = fechainicio;
    }

    public void setNrodieta(Integer nrodieta) {
        this.nrodieta = nrodieta;
    }

    @Override
    public String toString() {
        return "realizadieta{" + "cuil=" + cuil + ", fechainicio=" + fechainicio + ", nrodieta=" + nrodieta + '}';
    }
    
    
    
}
