/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.maratones.ejb;

import co.edu.uniandes.csw.maratones.entities.EjercicioEntity;
import co.edu.uniandes.csw.maratones.entities.SubmissionEntity;
import co.edu.uniandes.csw.maratones.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.maratones.persistence.EjercicioPersistence;
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
public class EjercicioSubmissionsLogic {
    
    private static final Logger LOGGER = Logger.getLogger(EjercicioSubmissionsLogic.class.getName());

    @Inject
    private EjercicioPersistence ejercicioPersistence;

    @Inject
    private SubmissionPersistence submissionPersistence;
    
    /**
     * Asocia una Submission existente a un Ejercicio
     *
     * @param ejerciciosId
     * @param submissionsId
     * @return Instancia de BookEntity que fue asociada a Author
     */ public EjercicioEntity addSubmission(Long ejerciciosId, Long submissionsId) {
        LOGGER.log(Level.INFO, "Inicia proceso de asociarle una submission al ejercicio con id = {0}", ejerciciosId);
        EjercicioEntity ejerEntity = ejercicioPersistence.find(ejerciciosId);
        SubmissionEntity subEntity = submissionPersistence.find(submissionsId);
        subEntity.setEjercicioEntity(ejerEntity);
        ejerEntity.getSubmissions().add(subEntity);
        LOGGER.log(Level.INFO, "Termina proceso de asociarle una submission al ejercicio con id = {0}", ejerciciosId);
        return ejercicioPersistence.find(ejerciciosId);
    }
     
     /**
     * Obtiene una colección de instancias de SubmissionEntity asociadas a una
     * instancia de Ejercicio
     *
     * @param ejerciciosId
     * @return Colección de instancias de SubmissionEntity asociadas a la instancia de
     * Ejercicio
     */
    public List<SubmissionEntity> getSubmissions(Long ejerciciosId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos las submissions del ejercicio con id = {0}", ejerciciosId);
        return ejercicioPersistence.find(ejerciciosId).getSubmissions();
    }

    
    public SubmissionEntity getSubmission(Long ejerciciosId, Long submissionsId) throws BusinessLogicException {
        
        LOGGER.log(Level.INFO, "Inicia proceso de consultar la submission con id = {0} del ejercicio con id = {1}", new Object[]{submissionsId, ejerciciosId});
        List<SubmissionEntity> submissions = ejercicioPersistence.find(ejerciciosId).getSubmissions();
        SubmissionEntity submissionEntity = submissionPersistence.find(submissionsId);
        int index = submissions.indexOf(submissionEntity);
        LOGGER.log(Level.INFO, "Termina proceso de consultar la submission con id = {0} del ejercicio con id = {1}", new Object[]{submissionsId, ejerciciosId});
        if (index >= 0) {
            return submissions.get(index);
        }
        throw new BusinessLogicException("La submission no esta asociada al ejercicio");
    }
       
}
