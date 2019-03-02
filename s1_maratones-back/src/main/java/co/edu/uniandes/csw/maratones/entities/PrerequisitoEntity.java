/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.maratones.entities;

import java.io.Serializable;
import javax.persistence.Entity;

/**
 *
 * @author Julian David Mendoza Ruiz <jd.mendozar@uniandes.edu.co>
 */
@Entity
public class PrerequisitoEntity extends BaseEntity implements Serializable{
    
    private int nivel;
    
    
//    private LenguajeEntity lenguaje;

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

//    /**
//     * @return the lenguaje
//     */
//    public LenguajeEntity getLenguaje() {
//        return lenguaje;
//    }
//
//    /**
//     * @param lenguaje the lenguaje to set
//     */
//    public void setLenguaje(LenguajeEntity lenguaje) {
//        this.lenguaje = lenguaje;
//    }
}
