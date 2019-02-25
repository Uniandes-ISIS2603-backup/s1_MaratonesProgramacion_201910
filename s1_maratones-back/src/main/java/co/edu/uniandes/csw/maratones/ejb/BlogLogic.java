/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.maratones.ejb;

import co.edu.uniandes.csw.maratones.entities.BlogEntity;
import co.edu.uniandes.csw.maratones.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.maratones.persistence.BlogPersistence;
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
public class BlogLogic {
    
    private static final Logger LOGGER = Logger.getLogger(BlogLogic.class.getName());
    @Inject
    private BlogPersistence persistence;
    
    public BlogEntity createBlog(BlogEntity blogEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de creación deun Blog");
        //Reglas del nombre
        if(blogEntity.getNombre()==null)
        {
            throw new BusinessLogicException("El nombre del blog invalido");
        }
        if(blogEntity.getNombre().length()>60)
        {
            throw new BusinessLogicException("El numero de caracteres maximo es 60");
        }
      
        //Reglas para descripcion
        if(blogEntity.getDescripcion()==null)
        {
            throw new BusinessLogicException("La descripcion del blog invalida");
        }
        if(blogEntity.getDescripcion().length()>500)
        {
            throw new BusinessLogicException("El numero de caracteres maximo es 500");
        }
        
        persistence.create(blogEntity);
        LOGGER.log(Level.INFO, "Termina proceso de creación de un blog");
        return blogEntity;
    }
    
    public BlogEntity getBlog(Long blogId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar un blog con id = {0}", blogId);
        BlogEntity blogEntity = persistence.find(blogId);
        if (blogEntity == null) {
            LOGGER.log(Level.SEVERE, "El blog con el id = {0} no existe", blogId);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar el blog con id = {0}", blogId);
        return blogEntity;
    }
    
     public List<BlogEntity> getBlogs() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los blogs");
        List<BlogEntity> blogs = persistence.findall();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todos los blogs");
        return blogs;
     }
     
      public BlogEntity updateBlog(Long blogId, BlogEntity blogEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el blog con id = {0}", blogId);
        if(blogId==null || blogId<0){
             throw new BusinessLogicException("El Id del blog no es valido");
        }
        BlogEntity newEntity = persistence.update(blogEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar el blog con id = {0}", blogEntity.getId());
        return newEntity;
    }
      
      public void deleteBlog(Long blogId)  {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar el blog con id = {0}", blogId);
        persistence.delete(blogId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar el blog con id = {0}", blogId);
    }
      
      
}
