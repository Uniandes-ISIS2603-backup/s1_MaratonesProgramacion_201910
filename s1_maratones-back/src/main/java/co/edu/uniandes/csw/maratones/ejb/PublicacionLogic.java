/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.maratones.ejb;

import co.edu.uniandes.csw.maratones.entities.PublicacionEntity;
import co.edu.uniandes.csw.maratones.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.maratones.persistence.PublicacionPersistence;
import java.util.Date;
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
public class PublicacionLogic {
    private static final Logger LOGGER = Logger.getLogger(BlogLogic.class.getName());
    @Inject
    private PublicacionPersistence persistence;
    
    public PublicacionEntity createPublicacion(PublicacionEntity publicacionEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de creación de una Publicacion");
        //Reglas para fecha
        if(publicacionEntity.getFecha()==null)
        {
            throw new BusinessLogicException("La fecha de la Publicacion invalida");
        }
        Date instante =new Date();
       
        if(!evaluarLimite(instante, publicacionEntity.getFecha())){
            throw new BusinessLogicException("La fecha de la Publicacion invalida");
        }
        persistence.create(publicacionEntity);
        LOGGER.log(Level.INFO, "Termina proceso de creación de una Publicacion");
        return publicacionEntity;
    }
    public static boolean evaluarLimite(Date date1, Date date2) {
    boolean correcto = false;
    long diferencia = (Math.abs(date1.getTime() - date2.getTime())) / 1000;
    long limit = (60 * 1000) / 100L;//limite de tiempo

    if (diferencia <= limit) {
        correcto= true;
    }
    return correcto;
}
    public PublicacionEntity getPublicacion(Long publicacionId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar una Publicacion con id = {0}", publicacionId);
        PublicacionEntity publicacionEntity = persistence.find(publicacionId);
        if (publicacionEntity == null) {
            LOGGER.log(Level.SEVERE, "El blog con el id = {0} no existe", publicacionId);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar la Publicacion con id = {0}", publicacionId);
        return publicacionEntity;
    }
    
     public List<PublicacionEntity> getPublicaciones() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todas las Publicaciones");
        List<PublicacionEntity> publicaciones = persistence.findall();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todas las Publicaciones");
        return publicaciones;
     }
     
      public PublicacionEntity updatePublicacion(Long publicacionId, PublicacionEntity publicacionEntity)  {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar la Publicacion con id = {0}", publicacionId);
       
        PublicacionEntity newEntity = persistence.update(publicacionEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar la Publicacion con id = {0}", publicacionEntity.getId());
        return newEntity;
    }
      
      public void deletePublicacion(Long publicacionId)  {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar la Publicacion con id = {0}", publicacionId);
        persistence.delete(publicacionId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar la Publicacion con id = {0}", publicacionId);
    }
      
}
