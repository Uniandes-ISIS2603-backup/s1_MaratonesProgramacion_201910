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
     */ public SubmissionEntity addSubmission(Long ejerciciosId, Long submissionsId) {
        LOGGER.log(Level.INFO, "Inicia proceso de asociarle una submission al ejercicio con id = {0}", ejerciciosId);
        EjercicioEntity ejerEntity = ejercicioPersistence.find(ejerciciosId);
        SubmissionEntity subEntity = submissionPersistence.find(submissionsId);
        ejerEntity.getSubmissions().add(subEntity);
        LOGGER.log(Level.INFO, "Termina proceso de asociarle una submission al ejercicio con id = {0}", ejerciciosId);
        return submissionPersistence.find(submissionsId);
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
        LOGGER.log(Level.INFO, "Inicia proceso de consultar la submission con id = {0} del ejercicio con id = " + ejerciciosId, submissionsId);
        List<SubmissionEntity> submissions = ejercicioPersistence.find(ejerciciosId).getSubmissions();
        SubmissionEntity submissionEntity = submissionPersistence.find(submissionsId);
        int index = submissions.indexOf(submissionEntity);
        LOGGER.log(Level.INFO, "Termina proceso de consultar la submission con id = {0} del ejercicio con id = " + ejerciciosId, submissionsId);
        if (index >= 0) {
            return submissions.get(index);
        }
        throw new BusinessLogicException("La submission no esta asociada al ejercicio");
    }
    
    
    /**
     * Desasocia una Submission existente de un Ejercicio existente
     *
     * @param ejerciciosId
     * @param submissionsId
     */
    public void removeSubmission(Long ejerciciosId, Long submissionsId) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar una submission del ejercicio con id = {0}", ejerciciosId);
        EjercicioEntity ejercicioEntity = ejercicioPersistence.find(ejerciciosId);
        SubmissionEntity submissionEntity = submissionPersistence.find(submissionsId);
        ejercicioEntity.getSubmissions().remove(submissionEntity);
        LOGGER.log(Level.INFO, "Termina proceso de borrar una submission del ejercicio con id = {0}", ejerciciosId);
    }
    
    
        /**
     * Remplazar submissions de un ejercicio
     *
     * @param submissions Lista de submissions que serán los de el ejercicio.
     * @param ejerciciosId El id del ejercicio que se quiere actualizar.
     * @return La lista de submissions actualizada.
     */
    public List<SubmissionEntity> replaceSubmissions(Long ejerciciosId, List<SubmissionEntity> submissions) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el ejercicio con id = {0}", ejerciciosId);
        EjercicioEntity ejercicioEntity = ejercicioPersistence.find(ejerciciosId);
        List<SubmissionEntity> submissionList = submissionPersistence.findAll();
        for (SubmissionEntity submission : submissionList) {
            if (submissions.contains(submission)) {
                submission.setEjercicioEntity(ejercicioEntity);
            } else if (submission.getEjercicioEntity() != null && submission.getEjercicioEntity().equals(ejercicioEntity)) {
                submission.setEjercicioEntity(null);
            }
        }
        LOGGER.log(Level.INFO, "Termina proceso de actualizar la editorial con id = {0}", ejerciciosId);
        return submissions;
    }
}
