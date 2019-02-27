/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.maratones.persistence;

import co.edu.uniandes.csw.maratones.entities.ComentarioEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Juan Felipe Peña
 */

@Stateless
public class ComentarioPersistence {
    
    private static final Logger LOGGER = Logger.getLogger(ComentarioPersistence.class.getName());
    
    @PersistenceContext(unitName = "maratonesPU")
    protected EntityManager em;
    
    public ComentarioEntity find(Long comentariosId){
        
        return em.find(ComentarioEntity.class, comentariosId);
    }
    
    public List<ComentarioEntity> findAll(){

        
        TypedQuery<ComentarioEntity> query = em.createQuery("select u from ComentarioEntity u", ComentarioEntity.class);
        return query.getResultList();
    }
    
    public ComentarioEntity create(ComentarioEntity comentarioEntity) {
        LOGGER.log(Level.INFO, "Creando un libro nuevo");
        em.persist(comentarioEntity);
        LOGGER.log(Level.INFO, "Libro creado");
        return comentarioEntity;
    }
}
