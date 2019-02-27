/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.maratones.dtos;

import co.edu.uniandes.csw.maratones.entities.EjercicioEntity;
import java.io.Serializable;

/**
 *
 * @author aa.rodriguezv
 */
public class EjercicioDTO implements Serializable{
    
    /*
    
    */
    private String nombre;
    
    /*
    
    */
    private String descripcion;
    
    /*
    
    */
    private String inputt;
    
    /*
    
    */
    private String outputt;
    
    /*
    
    */
    private int puntaje;
    
    /*
    
    */
    private int nivel;

    
    
    public EjercicioDTO()
    {
        
    }
    
    public EjercicioDTO(EjercicioEntity entity)
    {
        if(entity != null)
        {
            this.nombre = entity.getNombre();
            this.inputt = entity.getInputt();
            this.outputt = entity.getOutputt();
            this.nivel = entity.getNivel();
            this.puntaje = entity.getPuntaje();
            this.descripcion = entity.getDescripcion();
        }
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
     * @return the input
     */
    public String getInputt() {
        return inputt;
    }

    /**
     * @param input the input to set
     */
    public void setInputt(String input) {
        this.inputt = input;
    }

    /**
     * @return the output
     */
    public String getOutputt() {
        return outputt;
    }

    /**
     * @param output the output to set
     */
    public void setOutputt(String output) {
        this.outputt = output;
    }

    /**
     * @return the puntaje
     */
    public int getPuntaje() {
        return puntaje;
    }

    /**
     * @param puntaje the puntaje to set
     */
    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }

    /**
     * @return the nivel
     */
    public int getNivel() {
        return nivel;
    }

    /**
     * @param nivel the nivel to set
     */
    public void setNivel(int nivel) {
        this.nivel = nivel;
    }
    
    
    
    
}
