/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.maratones.ejb;

import co.edu.uniandes.csw.maratones.entities.CompetenciaEntity;
import co.edu.uniandes.csw.maratones.entities.EjercicioEntity;
import co.edu.uniandes.csw.maratones.entities.LenguajeEntity;
import co.edu.uniandes.csw.maratones.entities.SubmissionEntity;
import co.edu.uniandes.csw.maratones.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.maratones.persistence.CompetenciaPersistence;
import co.edu.uniandes.csw.maratones.persistence.EjercicioPersistence;
import co.edu.uniandes.csw.maratones.persistence.LenguajePersistence;
import co.edu.uniandes.csw.maratones.persistence.SubmissionPersistence;
import co.edu.uniandes.csw.maratones.persistence.UsuarioPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;

/**
 *
 * @author Julian David Mendoza Ruiz <jd.mendozar@uniandes.edu.co>
 */
public class CompetenciaEjercicioLogic {
      private static final Logger LOGGER = Logger.getLogger(UsuarioLenguajesLogic.class.getName());

    @Inject
    private CompetenciaPersistence competenciaPersistence;

    @Inject
    private EjercicioPersistence ejercicioPersistence;
    
    /**
     * Asocia una Submission existente a un Ejercicio
     *
     * @param competenciasId
     * @param ejerciciosId
     * @return Instancia de BookEntity que fue asociada a Author
     */    public EjercicioEntity addEjercicio(Long competenciasId, Long ejerciciosId) {
        LOGGER.log(Level.INFO, "Inicia proceso de asociarle una submission al ejercicio con id = {0}", competenciasId);
        CompetenciaEntity comEntity = competenciaPersistence.find(competenciasId);
        EjercicioEntity ejerEntity = ejercicioPersistence.find(ejerciciosId);
        ejerEntity.setCompetencia(comEntity);
        comEntity.getEjercicioEntitys().add(ejerEntity);
        LOGGER.log(Level.INFO, "Termina proceso de asociarle una submission al ejercicio con id = {0}", competenciasId);
        return ejercicioPersistence.find(ejerciciosId);
    }
     
     /**
     * Obtiene una colección de instancias de SubmissionEntity asociadas a una
     * instancia de Ejercicio
     *
     * @param competenciasId
     * @return Colección de instancias de SubmissionEntity asociadas a la instancia de
     * Ejercicio
     */
    public List<EjercicioEntity> getEjercicios(Long competenciasId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos las submissions del ejercicio con id = {0}", competenciasId);
        return competenciaPersistence.find(competenciasId).getEjercicioEntitys();
    }

    
    public EjercicioEntity getEjercicio(Long competenciasId, Long ejerciciosId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar la submission con id = {0} del ejercicio con id = " + competenciasId, ejerciciosId);
        List<EjercicioEntity> ejercicios = competenciaPersistence.find(competenciasId).getEjercicioEntitys();
        EjercicioEntity ejercicioEntity = ejercicioPersistence.find(ejerciciosId);
        int index = ejercicios.indexOf(ejercicioEntity);
        LOGGER.log(Level.INFO, "Termina proceso de consultar la submission con id = {0} del ejercicio con id = " + competenciasId, ejerciciosId);
        if (index >= 0) {
            return ejercicios.get(index);
        }
        throw new BusinessLogicException("La submission no esta asociada al ejercicio");
    }
    
    
    /**
     * Desasocia una Submission existente de un Ejercicio existente
     *
     * @param competenciasId
     * @param ejerciciosId
     */
    public void removeEjercicio(Long competenciasId, Long ejerciciosId) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar una submission del ejercicio con id = {0}", competenciasId);
        CompetenciaEntity competenciaEntity = competenciaPersistence.find(competenciasId);
        EjercicioEntity ejercicioEntity = ejercicioPersistence.find(ejerciciosId);
        competenciaEntity.getEjercicioEntitys().remove(ejercicioEntity);
        LOGGER.log(Level.INFO, "Termina proceso de borrar una submission del ejercicio con id = {0}", competenciasId);
    }
    
    
     
}
