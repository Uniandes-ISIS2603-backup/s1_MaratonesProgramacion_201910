/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.maratones.ejb;

import co.edu.uniandes.csw.maratones.entities.BlogEntity;
import co.edu.uniandes.csw.maratones.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.maratones.persistence.BlogPersistence;
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
        LOGGER.log(Level.INFO, "Termina proceso de creación deun blog");
        return blogEntity;
    }
    
}
