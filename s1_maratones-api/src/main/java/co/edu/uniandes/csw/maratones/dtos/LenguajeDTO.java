/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.maratones.dtos;

import co.edu.uniandes.csw.maratones.entities.LenguajeEntity;
import java.io.Serializable;

/**
 *
 * @author aa.rodriguezv
 */
public class LenguajeDTO implements Serializable{
   
    /*
    
    */
    private String nombre;
    
    /*
    
    */
    private Integer experiencia;
    
    /*
    
    */
    private UsuarioDTO programador;
    
    public LenguajeDTO(LenguajeEntity entity)
    {
        if(entity != null)
        {
            this.nombre = entity.getNombre();
            this.experiencia = entity.getExperiencia();
        
        }
    }

    public LenguajeDTO() {
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
    public UsuarioDTO getProgramador() {
        return programador;
    }

    /**
     * @param programador the programador to set
     */
    public void setProgramador(UsuarioDTO programador) {
        this.programador = programador;
    }
    
    public LenguajeEntity toEntity()
    {
        LenguajeEntity entity = new LenguajeEntity();
        
        entity.setNombre(this.nombre);
        entity.setExperiencia(this.experiencia);
        if(this.programador != null)
        {
            
        }
        
        return entity;
    }
    
}
