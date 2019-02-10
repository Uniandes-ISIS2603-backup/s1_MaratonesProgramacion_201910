/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.maratones.dtos;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author estudiante
 */
public class EjercicioDetailDTO extends EjercicioDTO implements Serializable{
    
    /*
    
    
    private List<CompetenciaDTO> competencias;
    */
    /*
    
    */
    private List<SubmissionDTO> submissions;

    /**
     * @return the competencias
     
    public List<CompetenciaDTO> getCompetencias() {
        return competencias;
    }
    */
    
    public EjercicioDetailDTO()
    {
        
    }

    /**
     * @param competencias the competencias to set
     
    public void setCompetencias(List<CompetenciaDTO> competencias) {
        this.competencias = competencias;
    }
    * */
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
