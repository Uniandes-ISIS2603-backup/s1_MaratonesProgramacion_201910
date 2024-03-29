/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.maratones.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author Julian David Mendoza Ruiz <jd.mendozar@uniandes.edu.co>
 */
@Entity
public class CompetenciaEntity extends BaseEntity implements Serializable{

    @PodamExclude
    @OneToMany(mappedBy = "competencia",orphanRemoval = true, cascade = {
        CascadeType.PERSIST
    })
    private List<EjercicioEntity> ejercicioEntitys;
    
    @PodamExclude
    @OneToMany(mappedBy = "competencia",fetch = FetchType.LAZY,orphanRemoval = true, cascade = 
            {
                CascadeType.PERSIST
            })
    private List<LugarCompetenciaEntity> lugarCompetencias;
    
    
    @PodamExclude
    @ManyToMany(mappedBy= "competencias" ,fetch = FetchType.LAZY, cascade = 
            {
                CascadeType.PERSIST
            })
    private List<UsuarioEntity> jueces;
    
    @PodamExclude
    @ManyToMany(mappedBy = "competencias")
    private List<EquipoEntity> equipoEntity;
    
    private List<String> lenguajes;
    
    private String esVirtual;
    
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInicio;
    
    private String nombre;
    
    private String descripcion;
    
    private Integer puntos;
    
    private String condiciones;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFin;
    
    private Integer nivel;


      /**
     * @return the ejercicioEntitys
     */
    public List<EjercicioEntity> getEjercicioEntitys() {
        return ejercicioEntitys;
    }

    /**
     * @param ejercicioEntitys the ejercicioEntitys to set
     */
    public void setEjercicioEntitys(List<EjercicioEntity> ejercicioEntitys) {
        this.ejercicioEntitys = ejercicioEntitys;
    }


    /**
     * @return the lugarCompetencias
     */
    public List<LugarCompetenciaEntity> getlugarCompetencias() {
        return lugarCompetencias;
    }

    /**
     * @param lugarCompetencias the lugarCompetencias to set
     */
    public void setLugarCompetencias(List<LugarCompetenciaEntity> lugarCompetencias) {
        this.lugarCompetencias = lugarCompetencias;
    }

    /**
     * @return the jueces
     */
    public List<UsuarioEntity> getJueces() {
        return jueces;
    }

    /**
     * @param jueces the jueces to set
     */
    public void setJueces(List<UsuarioEntity> jueces) {
        this.jueces = jueces;
    }

    /**
     * @return the equipoEntity
     */
    public List<EquipoEntity> getEquipoEntity() {
        return equipoEntity;
    }

    /**
     * @param equipoEntity the equipoEntity to set
     */
    public void setEquipoEntity(List<EquipoEntity> equipoEntity) {
        this.equipoEntity = equipoEntity;
    }

    /**
     * @return the esVirtual
     */
    public String isEsVirtual() {
        return esVirtual;
    }

    /**
     * @param esVirtual the esVirtual to set
     */
    public void setEsVirtual(String esVirtual) {
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
     * @return the puntos
     */
    public Integer getPuntos() {
        return puntos;
    }

    /**
     * @param puntos the puntos to set
     */
    public void setPuntos(Integer puntos) {
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

    /**
     * @return the nivel
     */
    public Integer getNivel() {
        return nivel;
    }

    /**
     * @param nivel the nivel to set
     */
    public void setNivel(Integer nivel) {
        this.nivel = nivel;
    }

    /**
     * @return the lenguajes
     */
    public List<String> getLenguajes() {
        return lenguajes;
    }

    /**
     * @param lenguajes the lenguajes to set
     */
    public void setLenguajes(List<String> lenguajes) {
        this.lenguajes = lenguajes;
    }

   
    
}
