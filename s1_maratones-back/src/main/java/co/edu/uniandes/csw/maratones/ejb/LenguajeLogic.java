/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.maratones.ejb;

import co.edu.uniandes.csw.maratones.entities.LenguajeEntity;
import co.edu.uniandes.csw.maratones.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.maratones.persistence.LenguajePersistence;
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
public class LenguajeLogic {
  
   private static final Logger LOGGER = Logger.getLogger(LenguajeLogic.class.getName());

    
  @Inject  
  private LenguajePersistence persistence;
  
    /**
     * 
     * @param lenguaje
     * @return
     * @throws BusinessLogicException 
     */
    public LenguajeEntity createLenguaje(LenguajeEntity lenguaje) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "Inicia proceso de creación del lenguaje");
        if(persistence.findByName(lenguaje.getNombre()) != null)
        {
            throw new BusinessLogicException("Ya existe un lenguaje con el nombre: " + lenguaje.getNombre());
        }
        
        if(lenguaje.getExperiencia() <= 0)
        {
            throw new BusinessLogicException("No se puede configurar un lenguaje con una experiencia menor o igual a 0");
        }
        
        lenguaje = persistence.create(lenguaje);
        LOGGER.log(Level.INFO, "Termina proceso de creación del lenguaje");
        
        return lenguaje;
    }
    
    /**
     * 
     * @param lenguaje 
     */
    public LenguajeEntity updateLenguaje(LenguajeEntity lenguaje) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el lenguaje con id = {0}", lenguaje.getId());
        if(persistence.find(lenguaje.getId()) == null)
        {
             throw new BusinessLogicException("No existe un lenguaje con el nombre: " + lenguaje.getNombre());
        }
        
        if(lenguaje.getExperiencia() <= 0)
        {
            throw new BusinessLogicException("No se puede configurar un lenguaje con una experiencia menor o igual a 0");
        }
        
        lenguaje = persistence.update(lenguaje);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar el lenguaje con id = {0}", lenguaje.getId());
        return lenguaje;
    }
    
    
    public void deleteLenguaje(LenguajeEntity lenguaje) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar el lenguaje con id = {0}", lenguaje.getId());
        if(persistence.find(lenguaje.getId()) == null)
        {
            throw new BusinessLogicException("No existe el lenguaje con el id:" + lenguaje.getId());
        }
        
        LOGGER.log(Level.INFO, "Termina proceso de borrar el lenguaje con id = {0}", lenguaje.getId());
        persistence.delete(lenguaje.getId());
    }
    
    public LenguajeEntity getLenguaje(Long lenguajeId)
    {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el lenguaje con id = {0}", lenguajeId);
        if(persistence.find(lenguajeId) == null)
        {
            LOGGER.log(Level.SEVERE, "El lenguaje con el id = {0} no existe", lenguajeId);
        }
        
        LenguajeEntity lenguaje = persistence.find(lenguajeId);
        LOGGER.log(Level.INFO, "Culmina proceso de consultar el lenguaje con id = {0}", lenguaje.getId());
        return lenguaje;
    }
    
    
    public List<LenguajeEntity> getLenguajes()
    {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los lenguajes");
        List<LenguajeEntity> languages = persistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todos los lenguajes");
        return languages;
    }
}
