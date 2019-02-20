/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.maratones.persistence;

import co.edu.uniandes.csw.maratones.entities.PublicacionEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author c.mendez11
 */
@Stateless
public class PublicacionPersistence {
    
    private static final Logger LOGGER = Logger.getLogger(PublicacionPersistence.class.getName());
    
     @PersistenceContext(unitName = "maratonesPU")
    protected EntityManager em;
     
     public PublicacionEntity create(PublicacionEntity publicacionEntity){
        LOGGER.log(Level.INFO, "Creando una publicacion");
        em.persist(publicacionEntity);
        LOGGER.log(Level.INFO, "Publicacion creado");
        return  publicacionEntity;
    }
      public PublicacionEntity find(Long publicacionId)
     {
         LOGGER.log(Level.INFO, "Consultando la publicaion con id={x}", publicacionId);
         return em.find(PublicacionEntity.class,publicacionId);
     }
     public List<PublicacionEntity>  findall(){
         LOGGER.log(Level.INFO, "Consultando todas las publicaciones");
         TypedQuery<PublicacionEntity> query=em.createQuery("select u from PublicacionEntity u",PublicacionEntity.class);
         return query.getResultList();
     }
       public PublicacionEntity update(PublicacionEntity publicacionEntity) {
        LOGGER.log(Level.INFO, "Actualizando la publicacion con id={x}", publicacionEntity.getId());
        return em.merge(publicacionEntity);
    }
      public void delete(Long publicacionId) {
        LOGGER.log(Level.INFO, "Borrando la publicacion con id={x}", publicacionId);
        PublicacionEntity publicacionEntity = em.find(PublicacionEntity.class, publicacionId);
        em.remove(publicacionEntity);
    }
}
