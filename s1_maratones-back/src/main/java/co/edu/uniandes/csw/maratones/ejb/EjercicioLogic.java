/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.maratones.ejb;

import co.edu.uniandes.csw.maratones.entities.CompetenciaEntity;
import co.edu.uniandes.csw.maratones.entities.EjercicioEntity;
import co.edu.uniandes.csw.maratones.entities.SubmissionEntity;
import co.edu.uniandes.csw.maratones.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.maratones.persistence.EjercicioPersistence;
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
public class EjercicioLogic {
    
   private static final Logger LOGGER = Logger.getLogger(LenguajeLogic.class.getName());

    
  @Inject  
  private EjercicioPersistence persistence;
  
    
    public EjercicioEntity createEjercicio(EjercicioEntity ejercicio) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "Inicia proceso de creación del ejercicio");
        
        if(persistence.findByName(ejercicio.getNombre()) != null)
        {
            throw new BusinessLogicException("Ya existe una ejercicio con el nombre: " + ejercicio.getNombre());
        }
        
        if(ejercicio.getDescripcion().equals(""))
        {
            throw new BusinessLogicException("La descripcion de un ejercicio no puede ir vacia");
        }
        
        if(ejercicio.getNombre().equals(""))
        {
            throw new BusinessLogicException("El nombre de un ejercicio no puede ir vacio");
        }
        
        if(ejercicio.getNivel() <= 0)
        {
            throw new BusinessLogicException("El nivel de un ejercicio no puede ser ni cero nu negativo");
        }
        
        if(ejercicio.getPuntaje() <= 0)
        {
            throw new BusinessLogicException("El puntaje que ofrece un ejercicio no puede ser cero ni negativo");
        }
        
        ejercicio = persistence.create(ejercicio);
         LOGGER.log(Level.INFO, "Termina proceso de creación del ejercicio");
       
        return ejercicio;
    }
    
    public EjercicioEntity getEjercicio(Long ejerID)
    {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar elejercicio con id = {0}", ejerID);
        EjercicioEntity ejercicio = persistence.find(ejerID);
        LOGGER.log(Level.INFO, "Culmina proceso de consultar el ejercicio con id = {0}", ejerID);
        return ejercicio;
    }
    
    
    public List<EjercicioEntity> getEjercicios()
    {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los ejercicios");
        List<EjercicioEntity> languages = persistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todos los ejercicios");
        return languages;
    }
    
    public EjercicioEntity updateEjercicio(Long ejerID, EjercicioEntity ejercicioEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el ejercicio con id = {0}", ejerID);
        if (persistence.find(ejerID) == null) {
            throw new BusinessLogicException("El ejercicio que se desea actualizar no existe");
        }
        if(ejercicioEntity.getDescripcion().equals(""))
        {
            throw new BusinessLogicException("La descripcion de un ejercicio no puede ir vacia");
        }
        
        if(ejercicioEntity.getNombre().equals(""))
        {
            throw new BusinessLogicException("El nombre de un ejercicio no puede ir vacio");
        }
        
        if(ejercicioEntity.getNivel() <= 0)
        {
            throw new BusinessLogicException("El nivel de un ejercicio no puede ser ni cero nu negativo");
        }
        
        if(ejercicioEntity.getPuntaje() <= 0)
        {
            throw new BusinessLogicException("El puntaje que ofrece un ejercicio no puede ser cero ni negativo");
        }
        
        EjercicioEntity newEntity = persistence.update(ejercicioEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar el ejercicio con id = {0}", ejercicioEntity.getId());
        return newEntity;
    }
    
    public void deleteEjercicio(Long ejerID) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar el ejercicio con id = {0}", ejerID);
        
        if (persistence.find(ejerID)== null) {
            throw new BusinessLogicException("No se puede borrar el ejercicio porque no existe");
        }
        
        List<SubmissionEntity> submissionsAsociadasA = getEjercicio(ejerID).getSubmissions();
        if( submissionsAsociadasA!= null  || !submissionsAsociadasA.isEmpty() )
        {
            throw new BusinessLogicException("No se puede borrar el ejercicio porque tiene submissions asociadas");
        }
        
        
        persistence.delete(ejerID);
        LOGGER.log(Level.INFO, "Termina proceso de borrar el ejercicio con id = {0}", ejerID);
    }
    
    
}
