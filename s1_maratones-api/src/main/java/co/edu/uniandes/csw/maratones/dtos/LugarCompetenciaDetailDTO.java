/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.maratones.dtos;

import co.edu.uniandes.csw.maratones.entities.LugarCompetenciaEntity;
import java.io.Serializable;

/**
 *
 * @author Julian David Mendoza Ruiz <jd.mendozar@uniandes.edu.co>
 */
public class LugarCompetenciaDetailDTO extends LugarCompetenciaDTO implements Serializable{
    
    private CompetenciaDTO competencia;

    public LugarCompetenciaDetailDTO(LugarCompetenciaEntity entity) {
        super(entity);
        
        if(entity!= null)
        {
            if(entity.getCompetencia()!= null)
            {
                competencia= new CompetenciaDTO(entity.getCompetencia());
            }
        }
    }
    
    public LugarCompetenciaEntity toEntity(){
        LugarCompetenciaEntity lugarCompetenciaEntity = super.toEntity();
        
        if(competencia!=null)
        {
            lugarCompetenciaEntity.setCompetencia(competencia.toEntity());
        }
        
        return  lugarCompetenciaEntity;
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
