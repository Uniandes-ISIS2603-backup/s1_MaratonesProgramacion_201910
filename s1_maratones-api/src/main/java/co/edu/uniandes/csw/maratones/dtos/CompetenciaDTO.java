/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.maratones.dtos;

import co.edu.uniandes.csw.maratones.adapters.DateAdapter;
import co.edu.uniandes.csw.maratones.entities.CompetenciaEntity;
import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 *
 * @author Julian David Mendoza Ruiz
 */
public class CompetenciaDTO implements Serializable{
    private Long id;

    private String esVirtual;
    
    @XmlJavaTypeAdapter(DateAdapter.class)
    private Date fechaInicio;
    
    private String nombre;
    
    private String descripcion;
    
    private Integer puntos;
    
    private String condiciones;
    
    @XmlJavaTypeAdapter(DateAdapter.class)
    private Date fechaFin;
    
    private Integer nivel;
   
    
    public CompetenciaDTO () {
        
    }

    public CompetenciaDTO(CompetenciaEntity entity)
    {
      if(entity!= null)
      {
          this.id= entity.getId();
          this.esVirtual= entity.isEsVirtual();
          this.fechaInicio = entity.getFechaInicio();
          this.nombre= entity.getNombre();
          this.descripcion= entity.getDescripcion();
          this.puntos = entity.getPuntos();
          this.condiciones= entity.getCondiciones();
          this.fechaFin= entity.getFechaFin();
          this.nivel= entity.getNivel();
      }
    
    }
    
    public CompetenciaEntity toEntity()
    {
        CompetenciaEntity entity = new CompetenciaEntity();
        entity.setId(this.id);
        entity.setEsVirtual(this.esVirtual);
        entity.setFechaInicio(this.fechaInicio);
        entity.setNombre(this.nombre);
        entity.setDescripcion(this.descripcion);
        entity.setPuntos(this.puntos);
        entity.setCondiciones(this.condiciones);
        entity.setFechaFin(this.fechaFin);
        entity.setNivel(this.nivel);
        
        return entity;
        
                
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
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }
    
    
}