/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.maratones.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author Angel Rodriguez aa.rodriguezv
 */
@Entity
public class LenguajeEntity extends BaseEntity implements Serializable{
    
    
    /*
    
    */
    private String nombre;
    
    /*
    
    */
    private int experiencia;
    
    /**
     * 
     */
    @PodamExclude
    @ManyToOne 
    private UsuarioEntity programadorEntity;
    
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
     * @return the experiencia
     */
    public int getExperiencia() {
        return experiencia;
    }

    /**
     * @param experiencia the experiencia to set
     */
    public void setExperiencia(int experiencia) {
        this.experiencia = experiencia;
    }

    /**
     * @return the programador
     */
    public UsuarioEntity getProgramador() {
        return programadorEntity;
    }

    /**
     * @param programador the programador to set
     */
    public void setProgramador(UsuarioEntity programador) {
        this.programadorEntity = programador;
    }
    
    
    
    
    
  
}
