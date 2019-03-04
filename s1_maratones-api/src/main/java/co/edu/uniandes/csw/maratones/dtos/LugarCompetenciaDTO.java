/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.maratones.dtos;

import co.edu.uniandes.csw.maratones.entities.LugarCompetenciaEntity;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 *
 * @author Julian David Mendoza Ruiz
 */
public class LugarCompetenciaDTO implements Serializable{
 
    private LocalDateTime fecha;
    
    private String ubicacion;

    
    public LugarCompetenciaDTO () {
        
    }
    
    public LugarCompetenciaDTO(LugarCompetenciaEntity entity)
    {
        if(entity!= null)
        {
           this.fecha = entity.getFecha();
           this.ubicacion = entity.getUbicaciones();
        }
    }
    
    public LugarCompetenciaEntity toEntity()
    {
        LugarCompetenciaEntity entity = new LugarCompetenciaEntity();
        
        entity.setFecha(this.fecha);
        entity.setUbicaciones(this.ubicacion);
        return entity;
    }

    /**
     * @return the fecha
     */
    public LocalDateTime getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(LocalDateTime fecha) {
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

}
