
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
    @Column(name="nrodieta")
    private Integer nrodieta;
    @Column(name="objetivo")
    private String objetivodieta;

    public Dieta() {
    }

    public Dieta(String objetivodieta) {
        this.objetivodieta = objetivodieta;
    }

    public Integer getnrodieta() {
        return nrodieta;
    }

    public String getObjetivodieta() {
        return objetivodieta;
    }

    public void setObjetivodieta(String objetivodieta) {
        this.objetivodieta = objetivodieta;
    }

    public void setnrodieta(Integer id) {
        this.nrodieta = id;
    }
    
    @Override
    public String toString() {
        return "Dieta{" + "nrodieta=" + nrodieta + ", objetivodieta=" + objetivodieta + '}';
    }
    
    
    
    
}
