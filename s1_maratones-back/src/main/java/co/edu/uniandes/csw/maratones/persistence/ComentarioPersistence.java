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
import javax.persistence.Query;
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
    
    /**
     * Devuelve todos loslibros de la base de datos.
     *
     * @return una lista con todos los libros que encuentre en la base de datos,
     * "select u from BookEntity u" es como un "select * from BookEntity;" -
     * "SELECT * FROM table_name" en SQL.
     */
    public List<ComentarioEntity> findAll(){

        
        TypedQuery<ComentarioEntity> query = em.createQuery("select u from ComentarioEntity u", ComentarioEntity.class);
        return query.getResultList();
    }
    /**
     * Método para persisitir la entidad en la base de datos.
     *
     * @param comentarioEntity objeto Comentario que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public ComentarioEntity create(ComentarioEntity comentarioEntity) {
        LOGGER.log(Level.INFO, "Creando un Comentario nuevo");
        em.persist(comentarioEntity);
        LOGGER.log(Level.INFO, "Comentario creado");
        return comentarioEntity;
    }

    /**
     * Busca si hay algun lubro con el id que se envía de argumento
     *
     * @param comentariosId: id correspondiente al libro buscado.
     * @return un libro.
     */
    public ComentarioEntity find(Long comentariosId) {
        LOGGER.log(Level.INFO, "Consultando el Comentario con id={0}", comentariosId);
        return em.find(ComentarioEntity.class, comentariosId);
    }

    /**
     * Actualiza un Comentario
     *
     * @param comentarioEntity: el Comentario que viene con los nuevos cambios. Por ejemplo
     * el nombre pudo cambiar. En ese caso, se haria uso del método update.
     * @return un libro con los cambios aplicados.
     */
    public ComentarioEntity update(ComentarioEntity comentarioEntity) {
        LOGGER.log(Level.INFO, "Actualizando el Comentario con id={0}", comentarioEntity.getId());
        return em.merge(comentarioEntity);
    }

    /**
     *
     * Borra un Comentario de la base de datos recibiendo como argumento el id del
     * lComentario
     *
     * @param comentariosId: id correspondiente al Comentario a borrar.
     */
    public void delete(Long comentariosId) {
        LOGGER.log(Level.INFO, "Borrando el Comentario con id={0}", comentariosId);
        ComentarioEntity comentarioEntity = em.find(ComentarioEntity.class, comentariosId);
        em.remove(comentarioEntity);
    }
}
