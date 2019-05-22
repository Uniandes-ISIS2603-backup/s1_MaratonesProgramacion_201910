/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.maratones.resources;

import co.edu.uniandes.csw.maratones.dtos.UsuarioDTO;
import co.edu.uniandes.csw.maratones.dtos.UsuarioDetailDTO;
import co.edu.uniandes.csw.maratones.ejb.CompetenciaUsuarioLogic;
import co.edu.uniandes.csw.maratones.ejb.UsuarioLogic;
import co.edu.uniandes.csw.maratones.entities.UsuarioEntity;
import co.edu.uniandes.csw.maratones.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import java.util.Arrays;
/**
 *
 * @author Julian David Mendoza Ruiz <jd.mendozar@uniandes.edu.co>
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CompetenciaUsuarioResource 
{
 private static final Logger LOGGER = Logger.getLogger(CompetenciaUsuarioResource.class.getName()); 
 
 @Inject
 private CompetenciaUsuarioLogic competenciaUsuarioLogic;
 
 @Inject
 private UsuarioLogic usuarioLogic;
 
 /**
     * Guarda un usuario dentro de una comptencia con la informacion que recibe el
     * la URL. Se devuelve el usuario que se guarda en la competencia.
     *
     * @param competenciasId Identificador de la competencia que se esta
     * actualizando. Este debe ser una cadena de dígitos.
     * @param usuariosId Identificador del usuario que se desea guardar. Este debe
     * ser una cadena de dígitos.
     * @return JSON {@link usuarioDTO} - El libro guardado en la competencia.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el usuario.
     */
    @POST
    @Path("{usuariosId: \\d+}")
    public UsuarioDetailDTO addJuez(@PathParam("competenciasId") Long competenciasId, @PathParam("usuariosId") Long usuariosId) {
        LOGGER.log(Level.INFO, "CompetenciaUsuarioResource addJuez: input: competenciasID: {0} , usuariosId: {1}", new Object[]{competenciasId, usuariosId});
        if (usuarioLogic.getUsuarioPorId(usuariosId) == null) {
            throw new WebApplicationException("El recurso /usuarios/" + usuariosId + " no existe.", 404);
        }
        UsuarioDetailDTO usuarioDTO = new UsuarioDetailDTO(competenciaUsuarioLogic.addJuez(usuariosId, competenciasId));
        LOGGER.log(Level.INFO, "CompetenciausuarioResource addusuario: output: {0}", usuarioDTO);
        return usuarioDTO;
    }
 
    /**
     * Busca y devuelve todos los usuarios que existen en la competencia.
     *
     * @param competenciasId Identificador de la competencia que se esta buscando.
     * Este debe ser una cadena de dígitos.
     * @return JSONArray {@link usuarioDetailDTO} - Los usuario encontrados en la
     * competencia. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List<UsuarioDetailDTO> getJueces(@PathParam("competenciasId") Long competenciasId) {
        LOGGER.log(Level.INFO, "CompetenciausuarioResource getusuarios: input: {0}", competenciasId);
        List<UsuarioDetailDTO> listaDetailDTOs = usuariosListEntity2DTO(competenciaUsuarioLogic.getJueces(competenciasId));
        LOGGER.log(Level.INFO, "CompetenciausuarioResource getusuarios: output: {0}", listaDetailDTOs);
        return listaDetailDTOs;
    }
    
    /**
     * Convierte una lista de UsuarioEntity a una lista de UsuarioDetailDTO.
     *
     * @param entityList Lista de UsuarioEntity a convertir.
     * @return Lista de UsuarioDTO convertida.
     */
    private List<UsuarioDetailDTO> usuariosListEntity2DTO(List<UsuarioEntity> entityList) {
        List<UsuarioDetailDTO> list = new ArrayList();
        for (UsuarioEntity entity : entityList) {
            list.add(new UsuarioDetailDTO(entity));
        }
        return list;
    }
}
