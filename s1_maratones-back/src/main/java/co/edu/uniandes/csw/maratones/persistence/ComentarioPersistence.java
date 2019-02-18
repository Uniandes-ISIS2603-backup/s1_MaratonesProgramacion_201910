/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.maratones.persistence;

import co.edu.uniandes.csw.maratones.entities.ComentarioEntity;
import java.util.List;
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
    
    @PersistenceContext(unitName = "maratonesPU")
    protected EntityManager em;
    
    public ComentarioEntity create(ComentarioEntity comentarioEntity){
        
        em.persist(comentarioEntity);
        return comentarioEntity;
    }
    
    public ComentarioEntity find(Long comentariosId){
        
        return em.find(ComentarioEntity.class, comentariosId);
    }
    
    public List<ComentarioEntity> findAll(){

        
        TypedQuery<ComentarioEntity> query = em.createQuery("select u from ComentarioEntity u", ComentarioEntity.class);
        return query.getResultList();
    }
}
