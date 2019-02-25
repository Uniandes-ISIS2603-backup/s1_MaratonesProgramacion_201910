/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.maratones.persistence;

import co.edu.uniandes.csw.maratones.entities.InstitucionEntity;
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
public class InstitucionPersistence {
    
    private static final Logger LOGGER = Logger.getLogger(InstitucionPersistence.class.getName());
    
     @PersistenceContext(unitName = "maratonesPU")
    protected EntityManager em;
     
     public InstitucionEntity create(InstitucionEntity institucionEntity){
        LOGGER.log(Level.INFO, "Creando una institucion ");
        em.persist(institucionEntity);
        LOGGER.log(Level.INFO, "Institucion creada");
        return  institucionEntity;
    }
      public InstitucionEntity find(Long institucionId)
     {
         LOGGER.log(Level.INFO, "Consultando la institucion con id={x}", institucionId);
         return em.find(InstitucionEntity.class,institucionId);
     }
     public List<InstitucionEntity>  findall(){
         LOGGER.log(Level.INFO, "Consultando todas las institucions");
         TypedQuery<InstitucionEntity> query=em.createQuery("select u from InstitucionEntity u",InstitucionEntity.class);
         return query.getResultList();
     }
     public InstitucionEntity update(InstitucionEntity institucionEntity) {
        LOGGER.log(Level.INFO, "Actualizando la institucion con id={x}", institucionEntity.getId());
        return em.merge(institucionEntity);
    }
      public void delete(Long institucionId) {
        LOGGER.log(Level.INFO, "Borrando la institucion con id={x}", institucionId);
        InstitucionEntity institucionEntity = em.find(InstitucionEntity.class, institucionId);
        em.remove(institucionEntity);
    }
}
