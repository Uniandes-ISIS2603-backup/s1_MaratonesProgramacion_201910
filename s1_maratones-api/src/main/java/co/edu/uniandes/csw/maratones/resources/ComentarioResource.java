/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.maratones.resources;

import co.edu.uniandes.csw.maratones.dtos.ComentarioDTO;
import co.edu.uniandes.csw.maratones.ejb.ComentarioLogic;
import co.edu.uniandes.csw.maratones.entities.ComentarioEntity;
import co.edu.uniandes.csw.maratones.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author estudiante
 */

@Path("comentarios")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class ComentarioResource {
    
    private static final Logger LOGGER = Logger.getLogger(ComentarioResource.class.getName());

    @Inject
    private ComentarioLogic comentarioLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    /**
     * Busca el Comentario con el id asociado recibido en la URL y lo devuelve.
     *
     * @param comentariosId Identificador del Comentario que se esta buscando. Este debe
     * ser una cadena de dígitos.
     * @return JSON {@link BookDetailDTO} - El Comentario buscado
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el Comentario.
     */
    @GET
    @Path("{comentariosId: \\d+}")
    public ComentarioDTO getBook(@PathParam("comentariosId") Long comentariosId) {
        LOGGER.log(Level.INFO, "ComentarioResource getComentario: input: {0}", comentariosId);
        ComentarioEntity comentarioEntity = comentarioLogic.getComentario(comentariosId);
        if (comentarioEntity == null) {
            throw new WebApplicationException("El recurso /books/" + comentariosId + " no existe.", 404);
        }
        ComentarioDTO comentarioDTO = new ComentarioDTO(comentarioEntity);
        LOGGER.log(Level.INFO, "ComentarioResource getBook: output: {0}", comentarioDTO);
        return comentarioDTO;
    }

    /**
     * Actualiza el libro con el id recibido en la URL con la información que se
     * recibe en el cuerpo de la petición.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando no se puede actualizar el Comentario
     */
    @PUT
    @Path("{comentariosId: \\d+}")
    public ComentarioDTO updateBook(@PathParam("comentariosId") Long comentariosId, ComentarioDTO comentario) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "ComentarioResource updateComentario: input: id: {0} , book: {1}", new Object[]{comentariosId, comentario});
        comentario.setId(comentariosId);
        if (comentarioLogic.getComentario(comentariosId) == null) {
            throw new WebApplicationException("El recurso /books/" + comentariosId + " no existe.", 404);
        }
        ComentarioDTO detailDTO = new ComentarioDTO(comentarioLogic.updateComentario(comentariosId, comentario.toEntity()));
        LOGGER.log(Level.INFO, "BookResource updateBook: output: {0}", detailDTO);
        return detailDTO;
    }

    /**
     * Borra el Comentario con el id asociado recibido en la URL.
     *
     * @param comentariosId Identificador del Comentario que se desea borrar. Este debe ser
     * una cadena de dígitos.
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     * cuando el Comentario tiene asociados.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el Comentario.
     */
    @DELETE
    @Path("{comentariosId: \\d+}")
    public void deleteComentario(@PathParam("comentariosId") Long comentariosId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "ComentarioResource deleteComentario: input: {0}", comentariosId);
        ComentarioEntity entity = comentarioLogic.getComentario(comentariosId);
        if (entity == null) {
            throw new WebApplicationException("El recurso /comentarios/" + comentariosId + " no existe.", 404);
        }
        comentarioLogic.deleteComentario(comentariosId);
        LOGGER.info("ComentarioResource deleteComentario: output: void");
    }
    @POST
    public ComentarioDTO createComentario(ComentarioDTO comentario) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "ComentarioResource createComentario: input: {0}", comentario.toString());
        ComentarioDTO nuevoComentarioDTO = new ComentarioDTO(comentarioLogic.createComentario(comentario.toEntity()));
        LOGGER.log(Level.INFO, "ComentarioResource createComentario: output: {0}", nuevoComentarioDTO.toString());
        return nuevoComentarioDTO;
    }
}
