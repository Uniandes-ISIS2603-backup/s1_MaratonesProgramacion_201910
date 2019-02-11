/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.maratones.dtos;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Julian David Mendoza Ruiz
 */
public class CompetenciaDTO implements Serializable{
    private boolean esVirtual;
    
    private Date fechaInicio;
    
    private String nombre;
    
    private String descripcion;
    
    private UsuarioDTO patrocinadores;
    
    private int puntos;
    
    private String condiciones;
    
    private Date fechaFin;
    
    public CompetenciaDTO () {
        
    }

    /**
     * @return the esVirtual
     */
    public boolean isEsVirtual() {
        return esVirtual;
    }

    /**
     * @param esVirtual the esVirtual to set
     */
    public void setEsVirtual(boolean esVirtual) {
        this.esVirtual = esVirtual;
    }

    /**
     * @return the fechaInicio
     */
    public Date getFechaInicio() {
        return fechaInicio;
    }

    /**
     * @param fechaInicio the fechaInicio to set
     */
    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the patrocinadores
     */
    public UsuarioDTO getPatrocinadores() {
        return patrocinadores;
    }

    /**
     * @param patrocinadores the patrocinadores to set
     */
    public void setPatrocinadores(UsuarioDTO patrocinadores) {
        this.patrocinadores = patrocinadores;
    }

    /**
     * @return the puntos
     */
    public int getPuntos() {
        return puntos;
    }

    /**
     * @param puntos the puntos to set
     */
    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    /**
     * @return the condiciones
     */
    public String getCondiciones() {
        return condiciones;
    }

    /**
     * @param condiciones the condiciones to set
     */
    public void setCondiciones(String condiciones) {
        this.condiciones = condiciones;
    }

    /**
     * @return the fechaFin
     */
    public Date getFechaFin() {
        return fechaFin;
    }

    /**
     * @param fechaFin the fechaFin to set
     */
    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }
}
