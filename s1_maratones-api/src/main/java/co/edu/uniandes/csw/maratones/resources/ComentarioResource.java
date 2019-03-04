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
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

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
    
    @POST
    public ComentarioDTO createComentario(ComentarioDTO comentario) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "ComentarioResource createComentario: input: {0}", comentario.toString());
        ComentarioDTO nuevoComentarioDTO = new ComentarioDTO(comentarioLogic.createComentario(comentario.toEntity()));
        LOGGER.log(Level.INFO, "ComentarioResource createComentario: output: {0}", nuevoComentarioDTO.toString());
        return nuevoComentarioDTO;
    }
}
