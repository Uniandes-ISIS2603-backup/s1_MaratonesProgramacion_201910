/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.maratones.ejb;

import co.edu.uniandes.csw.maratones.entities.SubmissionEntity;
import co.edu.uniandes.csw.maratones.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.maratones.persistence.SubmissionPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
/**
 *
 * @author Angel Rodriguez aa.rodriguezv
 */
@Stateless
public class SubmissionLogic {
    
   private static final Logger LOGGER = Logger.getLogger(SubmissionLogic.class.getName());
 
    
  @Inject  
  private SubmissionPersistence persistence;
  
    
    public SubmissionEntity createSubmission(SubmissionEntity submission) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "Inicia proceso de agregar una submission");
        if(persistence.findByName(submission.getCodigo()) != null)
        {
            throw new BusinessLogicException("Ya existe una submission enviada con el codigo: " + submission.getCodigo());
        }
        
        if(submission.getTiempo() <= 0)
        {
            throw new BusinessLogicException("El tiempo que tomo una submission no puede ser menor a 0");
        }
        
        if(submission.getMemoria() <= 0)
        {
            throw new BusinessLogicException("El peso de una submission no puede ser negativo");
        }
        
        if(!submission.getVeredicto().equals(SubmissionEntity.EN_REVISION))
        {
            throw new BusinessLogicException("El estado inicial de una submission debe ser 'En Revision'");
        }
        
        submission = persistence.create(submission);
        
        LOGGER.log(Level.INFO, "Inicia proceso de agregar una submission");
        return submission;
    }
    
    
    
    
    
    
    public SubmissionEntity getSubmission(Long submissionId) 
    {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar la submission con id = {0}", submissionId);
        if(persistence.find(submissionId) == null)
        {
            LOGGER.log(Level.SEVERE, "La submission con el id = {0} no existe", submissionId);
        }
        
        SubmissionEntity submission = persistence.find(submissionId);
        LOGGER.log(Level.INFO, "Culmina proceso de consultar la submission con id = {0}", submissionId);
        return submission;
    }
    
    /**
     * 
     * @return 
     */
    public List<SubmissionEntity> getSubmissions()
    {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todas las submissions");
        List<SubmissionEntity> submissions = persistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todas las submissions");
        return submissions;
    }
    
    /**
     * 
     * @param submissionID
     * @throws BusinessLogicException 
     */
    public void deleteSubmission(Long submissionID)
    {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar una submission con id = {0}", submissionID);
        SubmissionEntity subEntity = persistence.find(submissionID);
        if(subEntity.getEjercicioEntity() != null)
        {
            subEntity.getEjercicioEntity().getSubmissions().remove(subEntity);
        }
        LOGGER.log(Level.INFO, "Termina proceso de borrar la submission con id = {0}", submissionID);
        persistence.delete(submissionID);
    }
    
    
    /**
     *  
     * @param subID
     * @param sub
     * @return 
     * @throws co.edu.uniandes.csw.maratones.exceptions.BusinessLogicException
     */
    public SubmissionEntity updateSubmission(Long subID, SubmissionEntity sub) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar la submission con id = {0}", subID);
        
        
        if(sub.getTiempo() <= 0)
        {
            throw new BusinessLogicException("No se puede configurar una submission  con un tiempo menor o igual a 0");
        }
        if(sub.getMemoria() <= 0)
        {
            throw new BusinessLogicException("No se puede configurar una submission  con un peso menor o igual a 0");
        }
        
        if(!sub.getVeredicto().equals(SubmissionEntity.EN_REVISION) && !sub.getVeredicto().equals(SubmissionEntity.APROBADA) && !sub.getVeredicto().equals(SubmissionEntity.ERROR_COMPILACION) && !sub.getVeredicto().equals(SubmissionEntity.ERROR_TIEMPO))
        {
            throw new BusinessLogicException("El estado de una submission no puede ser distinto a los predeterminados; En revision, Error de Compilacion, Error de Tiempo o Aprobada");
        }
        
        sub = persistence.update(sub);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar el lenguaje con id = {0}", subID);
        return sub;
    }
}
