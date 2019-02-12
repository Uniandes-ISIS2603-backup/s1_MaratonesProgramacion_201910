/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.maratones.entities;

import java.util.Objects;

/**
 *
 * @author Angel Rodriguez aa.rodriguezv
 */
public class LenguajeEntity {
    
    
    /*
    
    */
    private String nombre;
    
    /*
    
    */
    private int experiencia;
    
    

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
    
    
    
    
    
    
    
     @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final LenguajeEntity other = (LenguajeEntity) obj;
        return Objects.equals(this.getNombre(), other.getNombre());
    }

    @Override
    public int hashCode() {
        if (this.getNombre() != null) {
            return this.getNombre().hashCode();
        }
        return super.hashCode();
    }
}
