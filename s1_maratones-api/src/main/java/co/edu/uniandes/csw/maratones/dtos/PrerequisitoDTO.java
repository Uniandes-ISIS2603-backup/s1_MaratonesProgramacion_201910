/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.maratones.dtos;

import java.io.Serializable;

/**
 *
 * @author Julian David Mendoza Ruiz
 */
public class PrerequisitoDTO implements Serializable{
 
    private int nivel;

    public PrerequisitoDTO() {
        
    }
    /**
     * @return the puntaje
     */
    public int getNivel() {
        return nivel;
    }

    /**
     * @param nivel the puntaje to set
     */
    public void setNivel(int nivel) {
        this.nivel = nivel;
    }
}
