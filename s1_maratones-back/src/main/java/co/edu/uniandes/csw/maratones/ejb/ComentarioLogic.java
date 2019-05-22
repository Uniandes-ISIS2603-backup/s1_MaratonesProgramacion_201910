/*
MIT License

Copyright (c) 2017 Universidad de los Andes - ISIS2603

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 */
package co.edu.uniandes.csw.maratones.ejb;

import co.edu.uniandes.csw.maratones.entities.ComentarioEntity;
import co.edu.uniandes.csw.maratones.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.maratones.persistence.ComentarioPersistence;
import co.edu.uniandes.csw.maratones.persistence.ForoPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Juan Felipe Peña
 */
@Stateless
public class ComentarioLogic {
    
    private static final Logger LOGGER = Logger.getLogger(ComentarioLogic.class.getName());

    @Inject
    private ComentarioPersistence persistence;

    @Inject
    private ForoPersistence foroPersistence;
    

    public ComentarioEntity createComentario(ComentarioEntity comentarioEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de creación del comentario");
       /* if (comentarioEntity.getForo() == null || foroPersistence.find(comentarioEntity.getForo().getId()) == null) {
            throw new BusinessLogicException("La editorial es inválida");
        }*/
        //se agregan más condiciones si son necesarias
        
        persistence.create(comentarioEntity);
        LOGGER.log(Level.INFO, "Termina proceso de creación del comentario");
        return comentarioEntity;
    }
    
    /**
     * Busca un lComentario por ID
     *
     * @param comentariosId El id del Comentarioo a buscar
     * @return El Comentario encontrado, null si no lo encuentra.
     */
    public ComentarioEntity getComentario(Long comentariosId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el Comentarioo con id = {0}", comentariosId);
        ComentarioEntity comentarioEntity = persistence.find(comentariosId);
        if (comentarioEntity == null) {
            LOGGER.log(Level.SEVERE, "El Comentario con el id = {0} no existe", comentariosId);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar el Comentario con id = {0}", comentariosId);
        return comentarioEntity;
    }

    /**
     * Actualizar un Comentario por ID
     *
     * @param comentariosId El ID del Comentario a actualizar
     * @param comentarioEntity La entidad del Comentario con los cambios deseados
     * @return La entidad del Comentario luego de actualizarla
     * @throws BusinessLogicException Si el IBN de la actualización es inválido
     */
    public ComentarioEntity updateComentario(Long comentariosId, ComentarioEntity comentarioEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el Comentario con id = {0}", comentariosId);

        
        ComentarioEntity newEntity = persistence.update(comentarioEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar el Comentario con id = {0}", comentarioEntity.getId());
        return newEntity;
    }

    /**
     * Eliminar un Comentario por ID
     *
     * @param comentariosId El ID del Comentario a eliminar
     * @throws BusinessLogicException si el Comentario tiene autores asociados
     */
    public void deleteComentario(Long comentariosId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar el Comentarioo con id = {0}", comentariosId);
   
        //tener en cuenta
        persistence.delete(comentariosId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar el Comentario con id = {0}", comentariosId);
    }

    /**
     * Devuelve todos los comentarios que hay en la base de datos.
     *
     * @return Lista de entidades de tipo comentario.
     */
    public List<ComentarioEntity> getComentarios() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los comentarios");
        List<ComentarioEntity> comentarios = persistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todos los comentarios");
        return comentarios;
    }
}
