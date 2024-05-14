package com.uncuyo.dbapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

@Entity
@Table(name="usuario")
public class Usuario {
    @Id
    @Column(name="cuil")
    private Long cuil;
    @Column(name="nombre")
    private String nombre;
    @Column(name="apellido")
    private String apellido;
    @Column(name="suenopromedio")
    private Float suenopromedio;
    @Column(name="aguapromedio")
    private Float aguapromedio;
    @Column(name="fechaNacimiento")
    private Date fechaNacimiento;
    @Column(name="sexo")
    private char sexo;
    @Column(name="genero")
    private String genero;
    @Column(name="imc")
    private Float imc;
    @Column(name="estadoPeso")
    private String estadoPeso;
    @Column(name="horasDeporteSemanales")
    private Float horasDeporteSemanales;

    public Usuario() {
    }

    public Usuario(Long cuil, String nombre, String apellido, Float suenopromedio, Float aguapromedio, String fechaNacimiento, char sexo, String genero, Float imc, String estadoPeso, Float horasDeporteSemanales) {
        this.cuil = cuil;
        this.nombre = nombre;
        this.apellido = apellido;
        this.suenopromedio = suenopromedio;
        this.fechaNacimiento = Date.valueOf(fechaNacimiento);
        this.sexo = sexo;
        this.genero = genero;
        this.imc = imc;
        this.estadoPeso = estadoPeso;
        this.horasDeporteSemanales = horasDeporteSemanales;
        this.aguapromedio = aguapromedio;
    }

    public Long getCuil() {
        return cuil;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public Float getSuenopromedio() {
        return suenopromedio;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento.toString();
    }

    public char getSexo() {
        return sexo;
    }

    public String getGenero() {
        return genero;
    }

    public Float getImc() {
        return imc;
    }

    public String getEstadoPeso() {
        return estadoPeso;
    }

    public Float getHorasDeporteSemanales() {
        return horasDeporteSemanales;
    }
    
    public Float getAguapromedio() {
        return aguapromedio;
    }

    @Override
    public String toString() {
        return "Usuario{" + "cuil=" + cuil + ", nombre=" + nombre + ", apellido=" + apellido + ", suenopromedio=" + suenopromedio + ", fechaNacimiento=" + fechaNacimiento + ", sexo=" + sexo + ", genero=" + genero + ", imc=" + imc + ", estadoPeso=" + estadoPeso + ", horasDeporteSemanales=" + horasDeporteSemanales + '}';
    }

    public void setCuil(Long cuil) {
        this.cuil = cuil;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setSuenopromedio(Float suenopromedio) {
        this.suenopromedio = suenopromedio;
    }
    
    public void setAguapromedio(Float aguapromedio) {
        this.aguapromedio = aguapromedio;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = Date.valueOf(fechaNacimiento);
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public void setImc(Float imc) {
        this.imc = imc;
    }

    public void setEstadoPeso(String estadoPeso) {
        this.estadoPeso = estadoPeso;
    }

    public void setHorasDeporteSemanales(Float horasDeporteSemanales) {
        this.horasDeporteSemanales = horasDeporteSemanales;
    }
    
    
}

