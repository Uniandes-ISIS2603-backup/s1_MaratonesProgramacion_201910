/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.maratones.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author Julian David Mendoza Ruiz <jd.mendozar@uniandes.edu.co>
 */
@Entity
public class CompetenciaEntity extends BaseEntity implements Serializable{

    @PodamExclude
    @ManyToMany(mappedBy = "competencias", cascade = {
        CascadeType.PERSIST,
        CascadeType.MERGE
    })
    private List<EjercicioEntity> ejercicioEntitys;
    
    @PodamExclude
    @ManyToMany (mappedBy = "competencias", cascade = {
        CascadeType.PERSIST,
        CascadeType.MERGE
    })
    private List<EquipoEntity> equipos;
    
    private boolean esVirtual;
    
    private LocalDateTime fechaInicio;
    
    private String nombre;
    
    private String descripcion;
    
    private int puntos;
    
    private String condiciones;
    
    private LocalDateTime fechaFin;

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
    public LocalDateTime getFechaFin() {
        return fechaFin;
    }
    /**
     * @return the fechaInicio
     */
    public LocalDateTime getFechaInicio() {
        return fechaInicio;
    }

    /**
     * @param fechaInicio the fechaInicio to set
     */
    public void setFechaInicio(LocalDateTime fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    /**
     * @param fechaFin the fechaFin to set
     */
    public void setFechaFin(LocalDateTime fechaFin) {
        this.fechaFin = fechaFin;
    }
    
}
