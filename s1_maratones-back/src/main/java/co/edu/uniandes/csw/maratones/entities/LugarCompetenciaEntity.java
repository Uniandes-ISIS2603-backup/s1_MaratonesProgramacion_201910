/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.maratones.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;

/**
 *
 * @author Julian David Mendoza Ruiz <jd.mendozar@uniandes.edu.co>
 */
@Entity
public class LugarCompetenciaEntity extends BaseEntity implements Serializable {
    private Date fecha;
    
    private String ubicaciones;

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
     * @return the ubicaciones
     */
    public String getUbicaciones() {
        return ubicaciones;
    }

    /**
     * @param ubicaciones the ubicaciones to set
     */
    public void setUbicaciones(String ubicaciones) {
        this.ubicaciones = ubicaciones;
    }
}
