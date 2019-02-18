/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.maratones.persistence;

import co.edu.uniandes.csw.maratones.entities.PublicacionEntity;
import java.util.List;
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
     @PersistenceContext(unitName = "maratonesPU")
    protected EntityManager em;
     
     public PublicacionEntity create(PublicacionEntity publicacionEntity){
        em.persist(publicacionEntity);
        return  publicacionEntity;
    }
      public PublicacionEntity find(Long publicacionId)
     {
         return em.find(PublicacionEntity.class,publicacionId);
     }
     public List<PublicacionEntity>  findall(){
         TypedQuery<PublicacionEntity> query=em.createQuery("select u from PublicacionEntity u",PublicacionEntity.class);
         return query.getResultList();
     }
}
