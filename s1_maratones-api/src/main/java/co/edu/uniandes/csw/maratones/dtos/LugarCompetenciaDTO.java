/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.maratones.dtos;

import co.edu.uniandes.csw.maratones.adapters.DateAdapter;
import co.edu.uniandes.csw.maratones.entities.LugarCompetenciaEntity;
import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 *
 * @author Julian David Mendoza Ruiz
 */
public class LugarCompetenciaDTO implements Serializable{
 
    private Long id;
    
    @XmlJavaTypeAdapter(DateAdapter.class)
    private Date fecha;
    
    private String ubicacion;

    /*
    * Relación a una editorial  
    * dado que esta tiene cardinalidad 1.
     */
    private CompetenciaDTO competencia;
    
    public LugarCompetenciaDTO () {
        
    }
    
    public LugarCompetenciaDTO(LugarCompetenciaEntity entity)
    {
        if(entity!= null)
        {
           this.id = entity.getId();
           this.fecha = entity.getFecha();
           this.ubicacion = entity.getUbicaciones();
           
           if(entity.getCompetencia()!= null)
           {
               this.competencia= new CompetenciaDTO(entity.getCompetencia());
           }else
           {
               this.competencia= null;
           }
        }
    }
    
    public LugarCompetenciaEntity toEntity()
    {
        LugarCompetenciaEntity entity = new LugarCompetenciaEntity();
        
        entity.setFecha(this.fecha);
        entity.setUbicaciones(this.ubicacion);
        entity.setCompetencia(this.competencia.toEntity());
        return entity;
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
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

}
