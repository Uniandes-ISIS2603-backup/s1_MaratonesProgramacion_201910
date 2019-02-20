/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.maratones.ejb;

import co.edu.uniandes.csw.maratones.entities.SubmissionEntity;
import co.edu.uniandes.csw.maratones.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.maratones.persistence.SubmissionPersistence;
import javax.ejb.Stateless;
import javax.inject.Inject;
/**
 *
 * @author Angel Rodriguez aa.rodriguezv
 */
@Stateless
public class SubmissionLogic {
    
 
  @Inject  
  private SubmissionPersistence persistence;
  
    
    public SubmissionEntity createSubmission(SubmissionEntity submission) throws BusinessLogicException
    {
        if(persistence.findByName(submission.getCodigo()) != null)
        {
            throw new BusinessLogicException("Ya existe una submission enviada con el codigo: " + submission.getCodigo());
        }
        
        
        submission = persistence.create(submission);
        
        return submission;
    }
}
