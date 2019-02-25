/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.maratones.persistence;

import co.edu.uniandes.csw.maratones.entities.BlogEntity;
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
public class BlogPersistence {
    
    private static final Logger LOGGER = Logger.getLogger(BlogPersistence.class.getName());
    
     @PersistenceContext(unitName = "maratonesPU")
    protected EntityManager em;
     
     public BlogEntity create(BlogEntity blogEntity){
         LOGGER.log(Level.INFO, "Creando un blog nuevo");
        em.persist(blogEntity);
        LOGGER.log(Level.INFO, "Blog creado");
        return  blogEntity;
    }
     public BlogEntity find(Long blogId)
     {
         LOGGER.log(Level.INFO, "Consultando el blog con id={x}", blogId);
         return em.find(BlogEntity.class,blogId);
     }
     public List<BlogEntity>  findall(){
         LOGGER.log(Level.INFO, "Consultando todos los blogs");
         TypedQuery<BlogEntity> query=em.createQuery("select u from BlogEntity u",BlogEntity.class);
         return query.getResultList();
     }
     public BlogEntity update(BlogEntity blogEntity) {
        LOGGER.log(Level.INFO, "Actualizando el blog con id={x}", blogEntity.getId());
        return em.merge(blogEntity);
    }
      public void delete(Long blogId) {
        LOGGER.log(Level.INFO, "Borrando el blog con id={x}", blogId);
        BlogEntity blogEntity = em.find(BlogEntity.class, blogId);
        em.remove(blogEntity);
    }

}
