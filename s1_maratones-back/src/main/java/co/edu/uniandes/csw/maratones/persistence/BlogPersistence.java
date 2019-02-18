/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.maratones.persistence;

import co.edu.uniandes.csw.maratones.entities.BlogEntity;
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
public class BlogPersistence {
     @PersistenceContext(unitName = "maratonesPU")
    protected EntityManager em;
     
     public BlogEntity create(BlogEntity blogEntity){
        em.persist(blogEntity);
        return  blogEntity;
    }
     public BlogEntity find(Long blogId)
     {
         return em.find(BlogEntity.class,blogId);
     }
     public List<BlogEntity>  findall(){
         TypedQuery<BlogEntity> query=em.createQuery("select u from BlogEntity u",BlogEntity.class);
         return query.getResultList();
     }

}
