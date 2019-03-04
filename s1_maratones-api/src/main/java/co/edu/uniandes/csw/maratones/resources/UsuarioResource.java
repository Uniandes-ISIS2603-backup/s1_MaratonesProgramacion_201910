/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.edu.uniandes.csw.maratones.resources;
import co.edu.uniandes.csw.maratones.dtos.UsuarioDTO;
import co.edu.uniandes.csw.maratones.dtos.UsuarioDetailDTO;
import co.edu.uniandes.csw.maratones.ejb.UsuarioLogic;
import co.edu.uniandes.csw.maratones.entities.UsuarioEntity;
import co.edu.uniandes.csw.maratones.exceptions.BusinessLogicException;


import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;

import javax.ws.rs.WebApplicationException;
@Path("/usuarios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
/**
 *
 * @author camilalonart
 */
public class UsuarioResource {
    private static final Logger LOGGER = Logger.getLogger(UsuarioResource.class.getName());
    
    @Inject
    private UsuarioLogic usuarioLogic;
    @POST
    public UsuarioDTO crearUsuario(UsuarioDTO pusuario) {
        return pusuario;
    }
    
    

   @POST
    public UsuarioDTO create(UsuarioDTO usuario) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "UsuarioResource create: input: {0}", usuario);
        UsuarioDTO nuevoDTO = new UsuarioDTO(usuarioLogic.create(usuario.toEntity()));
        LOGGER.log(Level.INFO, "UsuarioResource create: output: {0}", nuevoDTO);
        return nuevoDTO;
    }

    @GET
    @Path("{usuariosId: \\d+}")
    public UsuarioDetailDTO get(@PathParam("usuariosId") Long usuariosId) {
        LOGGER.log(Level.INFO, "UsuarioResource get: input: {0}", usuariosId);
        UsuarioEntity entity = usuarioLogic.getUsuarioPorId(usuariosId);
        if (entity == null) {
            throw new WebApplicationException("El recurso /usuarios/" + usuariosId + " no existe.", 404);
        }
        UsuarioDetailDTO elDetailDTO = new UsuarioDetailDTO(entity);
        LOGGER.log(Level.INFO, "UsuarioResource get: output: {0}", elDetailDTO);
        return elDetailDTO;
    }
    
    /**
     * Actualiza el usuario con el id recibido en la URL con la información que
     * se recibe en el cuerpo de la petición.
     *
     * @param usuariosId Identificador del usuario que se desea actualizar. Este
     * debe ser una cadena de dígitos.
     * @param usuario {@link UsuarioDTO} El usuario que se desea guardar.
     * @return JSON {@link UsuarioDetailDTO} - El usuario guardada.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el premio a
     * actualizar.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando no se puede actualizar el usuario.
     */
    @PUT
    @Path("{usuariosId: \\d+}")
    public UsuarioDetailDTO update(@PathParam("usuariosId") Long equipoId, UsuarioDTO usuario) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "UsuarioResource update: input: usuariosId: {0} , usuario: {1}", new Object[]{equipoId, usuario});
        usuario.setId(equipoId);
        if (usuarioLogic.getUsuarioPorId(equipoId) == null) {
            throw new WebApplicationException("El recurso /usuarios/" + equipoId + " no existe.", 404);
        }
        UsuarioDetailDTO detailDTO = new UsuarioDetailDTO(usuarioLogic.update(equipoId, usuario.toEntity()));
        LOGGER.log(Level.INFO, "PrizeResource updatePrize: output: {0}", detailDTO);
        return detailDTO;
    }
    /**
     * Borra el usuario con el id asociado recibido en la URL.
     *
     * @param equipoId Identificador del premio que se desea borrar. Este debe
     * ser una cadena de dígitos.
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper}
     * Error de lógica que se genera cuando no se encuentra el usuario.
     */
    @DELETE
    @Path("{usuariosId: \\d+}")
    public void delete(@PathParam("usuariosId") Long equipoId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "UsuarioResource delete: input: {0}", equipoId);
        if (usuarioLogic.getUsuarioPorId(equipoId) == null) {
            throw new WebApplicationException("El recurso /usuarios/" + equipoId + " no existe.", 404);
        }
        usuarioLogic.delete(equipoId);
        LOGGER.info("UsuarioResource delete: output: void");
    }
    
    
    private List<UsuarioDetailDTO> listEntity2DetailDTO(List<UsuarioEntity> entityList) {
        List<UsuarioDetailDTO> list = new ArrayList<UsuarioDetailDTO>();
        for (UsuarioEntity entity : entityList) {
            list.add(new UsuarioDetailDTO(entity));
        }
        return list;
    }
  }  