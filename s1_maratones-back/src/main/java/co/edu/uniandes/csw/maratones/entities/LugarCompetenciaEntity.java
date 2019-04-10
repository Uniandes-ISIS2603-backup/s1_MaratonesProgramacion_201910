/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.maratones.entities;

import java.io.Serializable;
import java.util.Date;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author Julian David Mendoza Ruiz <jd.mendozar@uniandes.edu.co>
 */
@Entity
public class LugarCompetenciaEntity extends BaseEntity implements Serializable {
    private Date fecha;
    
    private String ubicaciones;

    @PodamExclude
    @ManyToOne(fetch = FetchType.LAZY)
    private CompetenciaEntity competencia;

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
     * @return the competencia
     */
    public CompetenciaEntity getCompetencia() {
        return competencia;
    }

    /**
     * @param competencia the competencia to set
     */
    public void setCompetencia(CompetenciaEntity competencia) {
        this.competencia = competencia;
    }
}
