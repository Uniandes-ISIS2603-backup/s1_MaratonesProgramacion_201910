/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.maratones.dtos;

import co.edu.uniandes.csw.maratones.entities.CompetenciaEntity;
import co.edu.uniandes.csw.maratones.entities.EjercicioEntity;
import co.edu.uniandes.csw.maratones.entities.SubmissionEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author aa.rodriguezv
 */
public class EjercicioDetailDTO extends EjercicioDTO implements Serializable{
    
    /*
    
    */
    private List<CompetenciaDTO> competencias;
    
    /*
    
    */
    private List<SubmissionDTO> submissions;

    /**
     * @return the competencias
     */
    public List<CompetenciaDTO> getCompetencias() {
        return competencias;
    }
    
    
    public EjercicioDetailDTO()
    {
        super();
    }
    
    public EjercicioDetailDTO(EjercicioEntity entity)
    {
        super(entity);
        if(entity.getCompetencias() != null)
        {
            competencias = new ArrayList<>();
            for(CompetenciaEntity competencia: entity.getCompetencias())
            {
                
            }
            
        }
        if(entity.getSubmissions() != null)
        {
            submissions = new ArrayList<>();
            for(SubmissionEntity submission: entity.getSubmissions())
            {
                submissions.add(new SubmissionDTO(submission));
            }
        }
    }

    /**
     * @param competencias the competencias to set
     */
    public void setCompetencias(List<CompetenciaDTO> competencias) {
        this.competencias = competencias;
    }
    
    /**
     * @return the submissions
     */
    public List<SubmissionDTO> getSubmissions() {
        return submissions;
    }

    /**
     * @param submissions the submissions to set
     */
    public void setSubmissions(List<SubmissionDTO> submissions) {
        this.submissions = submissions;
    }
    
    
}
