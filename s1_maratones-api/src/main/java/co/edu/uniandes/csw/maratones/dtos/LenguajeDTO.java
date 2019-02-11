/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.maratones.dtos;

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
    private int experiencia;
    
    public LenguajeDTO()
    {
        
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
    
    
    
}
