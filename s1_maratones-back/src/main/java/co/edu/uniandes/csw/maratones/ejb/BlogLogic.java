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
        if(blogEntity.getNombre().equals(""))
        {
            throw new BusinessLogicException("El nombre del blog no puede estar vacio");
        }
        if(blogEntity.getDescripcion().equals(""))
        {
            throw new BusinessLogicException("La descripcion del blog no puede estar vacia");
        }
        persistence.create(blogEntity);
        LOGGER.log(Level.INFO, "Termina proceso de creación deun blog");
        return blogEntity;
    }
}
