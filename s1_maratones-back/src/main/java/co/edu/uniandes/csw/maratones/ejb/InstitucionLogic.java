/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.maratones.ejb;

import co.edu.uniandes.csw.maratones.entities.InstitucionEntity;
import co.edu.uniandes.csw.maratones.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.maratones.persistence.InstitucionPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author c.mendez11
 */
@Stateless
public class InstitucionLogic {
        private static final Logger LOGGER = Logger.getLogger(BlogLogic.class.getName());
    @Inject
    private InstitucionPersistence persistence;
    
    public InstitucionEntity createInstitucion(InstitucionEntity institucionEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de creación de una Institucion");
        //Reglas del nombre
        if((institucionEntity.getNombre()==null)||(institucionEntity.getNombre().length()>60))
        {
            throw new BusinessLogicException("El nombre de la institucion es invalido");
        }
        if(persistence.findByName(institucionEntity.getNombre())!=null)
        {
            throw new BusinessLogicException("Ya existe una institucion con ese nombre");
        }
      
        //Reglas para descripcion
        if(institucionEntity.getDescripcion()==null || institucionEntity.getDescripcion().length()>500)
        {
            throw new BusinessLogicException("La descripcion de la institucion invalida");
        }
        
        //Reglas para imagen
        if(institucionEntity.getImagen()==null)
        {
            throw new BusinessLogicException("La imagen de la institucion invalida");
        }
        //Reglas para ubicacion
        if(institucionEntity.getUbicacion()==null)
        {
            throw new BusinessLogicException("La ubicacion de la institucion invalida");
        }
        
        persistence.create(institucionEntity);
        LOGGER.log(Level.INFO, "Termina proceso de creación de una institucion");
        return institucionEntity;
    }
    
    public InstitucionEntity getInstitucion(Long institucionId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar la institucion con id = {0}", institucionId);
        InstitucionEntity institucionEntity = persistence.find(institucionId);
        if (institucionEntity == null) {
            LOGGER.log(Level.SEVERE, "La institucion institucion con el id = {0} no existe", institucionId);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar la institucioncon id = {0}", institucionId);
        return institucionEntity;
    }
    
     public List<InstitucionEntity> getInstituciones() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todas las instituciones");
        List<InstitucionEntity> instituciones = persistence.findall();
        LOGGER.log(Level.INFO, "Termina proceso de consultar tod las instituciones");
        return instituciones;
     }
     
      public InstitucionEntity updateInstitucion(Long institucionId, InstitucionEntity institucionEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar la institucion con id = {"+institucionId+"}", institucionId);
         int x=0;
            if(institucionEntity.getNombre()==null)
        {
            x=2;
        }
        if(x==2)
        {
           
            throw new BusinessLogicException("El nombre de la institucion es invalido");
        }
        
        if((institucionEntity.getNombre().equals("")))
        {
            throw new BusinessLogicException("El nombre de la institucion es invalido");
        }
        if(institucionEntity.getNombre().length()>60)
        {
            throw new BusinessLogicException("El nombre de la institucion es invalido");
        }
         if(persistence.findByName(institucionEntity.getNombre())!=null)
        {
            throw new BusinessLogicException("Ya existe una institucion con ese nombre");
        }
        InstitucionEntity newEntity = persistence.update(institucionEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar la institucion con id = {"+institucionId+"}", institucionEntity.getId());
        return newEntity;
    }
      
      public void deleteInstitucion(Long institucionId)  {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar el blog con id = {0}", institucionId);
        persistence.delete(institucionId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar el blog con id = {0}", institucionId);
    }
      
}
