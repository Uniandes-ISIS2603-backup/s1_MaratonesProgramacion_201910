/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.maratones.dtos;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Julian David Mendoza Ruiz
 */
public class LugarCompetenciaDTO implements Serializable{
 
    private Date fecha;
    
    private String ubicacion;
    
    private CompetenciaDTO competencia;
    
    public LugarCompetenciaDTO () {
        
    }

    /**
     * @return the fecha
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the ubicacion
     */
    public String getUbicacion() {
        return ubicacion;
    }

    /**
     * @param ubicacion the ubicacion to set
     */
    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    /**
     * @return the competencia
     */
    public CompetenciaDTO getCompetencia() {
        return competencia;
    }

    /**
     * @param competencia the competencia to set
     */
    public void setCompetencia(CompetenciaDTO competencia) {
        this.competencia = competencia;
    }
}
