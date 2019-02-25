/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.maratones.dtos;

import co.edu.uniandes.csw.maratones.entities.PrerequisitoEntity;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Julian David Mendoza Ruiz
 */
public class PrerequisitoDetailDTO extends PrerequisitoDTO implements Serializable{
    
    private List<CompetenciaDetailDTO> competencias;

    public PrerequisitoDetailDTO(PrerequisitoEntity prerequisitoEntity) {
        super(prerequisitoEntity);
    }

    /**
     * @return the competencias
     */
    public List<CompetenciaDetailDTO> getCompetencias() {
        return competencias;
    }

    /**
     * @param competencias the competencias to set
     */
    public void setCompetencias(List<CompetenciaDetailDTO> competencias) {
        this.competencias = competencias;
    }
}
