/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.maratones.persistence;

import co.edu.uniandes.csw.maratones.entities.ForoEntity;
import java.util.List;
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
public class ForoPersistence {
    
    //private static final Logger LOGGER = Logger.getLogger(ForoPersistence.class.getNombre());
    
    @PersistenceContext(unitName = "maratonesPU")
    protected EntityManager em;
    
    public ForoEntity create(ForoEntity foroEntity){
        
        em.persist(foroEntity);
        return foroEntity;
    }
    
    public ForoEntity find(Long forosId){
        
        return em.find(ForoEntity.class, forosId);
    }
    
    public List<ForoEntity> findAll(){

        
        TypedQuery<ForoEntity> query = em.createQuery("select u from ForoEntity u", ForoEntity.class);
        return query.getResultList();
    }
}
