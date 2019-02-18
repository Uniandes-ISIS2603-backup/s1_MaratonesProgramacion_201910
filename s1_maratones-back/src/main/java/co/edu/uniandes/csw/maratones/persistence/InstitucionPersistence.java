/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.maratones.persistence;

import co.edu.uniandes.csw.maratones.entities.InstitucionEntity;
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
public class InstitucionPersistence {
     @PersistenceContext(unitName = "maratonesPU")
    protected EntityManager em;
     
     public InstitucionEntity create(InstitucionEntity institucionEntity){
        em.persist(institucionEntity);
        return  institucionEntity;
    }
      public InstitucionEntity find(Long institucionId)
     {
         return em.find(InstitucionEntity.class,institucionId);
     }
     public List<InstitucionEntity>  findall(){
         TypedQuery<InstitucionEntity> query=em.createQuery("select u from InstitucionEntity u",InstitucionEntity.class);
         return query.getResultList();
     }
}
