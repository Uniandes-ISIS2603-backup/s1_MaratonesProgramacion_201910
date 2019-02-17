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
    
    /*@POST
    public UsuarioDTO crearUsuario(UsuarioDTO pusuario) {
        return pusuario;
    }

    */    
    private UsuarioLogic elUsuariolLogic;
    @Inject
    
    
    /**
     * Crea un nuevo usuario con la informacion que se recibe en el cuerpo de la
     * petición y se regresa un objeto identico con un id auto-generado por la
     * base de datos.
     *
     * @param pusuario {@link UsuarioDTO} - EL usuario que se desea guardar.
     * @return JSON {@link UsuarioDTO} - El usuario guardado con el atributo id
     * autogenerado.
     */
    @POST
    public UsuarioDTO crearUsuario(UsuarioDTO pusuario) {
        LOGGER.log(Level.INFO, "UserResource createUser: input: {0}", pusuario);
        UsuarioDTO elUsuarioDTO = new UsuarioDTO(elUsuariolLogic.crearUsuario(pusuario.toEntity()));
        LOGGER.log(Level.INFO, "UserResource createUser: output: {0}", elUsuarioDTO);
        return elUsuarioDTO;
    }
    
    /**
     * Busca y devuelve todos los usuarios que existen en la aplicacion.
     *
     * @return JSONArray {@link UsuarioDetailDTO} - Los usuarios encontrados en la
     * aplicación. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List<UsuarioDetailDTO> getUsuarios() {
        LOGGER.info("UserResource Users: input: void");
        List<UsuarioDetailDTO> listaUsuarios = listEntity2DTO(elUsuariolLogic.getUsuarios());
        LOGGER.log(Level.INFO, "UserResource getUsuarios: output: {0}", listaUsuarios);
        return listaUsuarios;
    }
    
    /**
     * Busca el usuario con el nombreUsuario asociado recibido en la URL y lo devuelve.
     *
     * @param nombreUsuario Identificador del autor que se esta buscando. Este debe
     * ser una cadena de dígitos.
     * @return JSON {@link UsuarioDetailDTO} - El autor buscado
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el autor.
     */
    @GET
    @Path("{nombresUsuario: \\d+}")
    public UsuarioDetailDTO getUsuario(@PathParam("nombreUsuario") String elNombreUsuario) {
        
       UsuarioDetailDTO u = new UsuarioDetailDTO();
       return u;
    }
    
    /**
     * Actualiza el usuario con el nombreUsuario recibido en la URL con la información que se
     * recibe en el cuerpo de la petición.
     *
     * @param nombreUsuario Identificador del autor que se desea actualizar. Este
     * debe ser una cadena de dígitos.
     * @param elUsuario {@link UsuarioDetailDTO} El usuario que se desea guardar.
     * @return JSON {@link UsuarioDetailDTO} - El usuario guardado.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el usuario a
     * actualizar.
     */
    @PUT
    @Path("{nombresUsuario: \\d+}")
    public UsuarioDetailDTO updateUsuario(@PathParam("nombreUsuario") String elNombreUsuario, UsuarioDetailDTO elUsuario) {
       UsuarioDetailDTO u = new UsuarioDetailDTO();
       return u;
    }
    
    
    /**
         * Borra el autor con el id asociado recibido en la URL.
         *
         * @param elNombreUsuario Identificador del autor que se desea borrar. Este debe
         * ser una cadena de dígitos.
         * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
         * si el autor tiene libros asociados
         * @throws WebApplicationException {@link WebApplicationExceptionMapper}
         * Error de lógica que se genera cuando no se encuentra el autor a borrar.
         */
    @DELETE
    @Path("{nombresUsuario: \\d+}")
    public void deleteUsuario(@PathParam("elNombreUsuario") String elNombreUsuario) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "UserResource deleteUsuario: input: {0}", elNombreUsuario);
        if (elUsuariolLogic.getUsuario(elNombreUsuario) == null) {
            throw new WebApplicationException("El recurso /usuarios/" + elNombreUsuario + " no existe.", 404);
        }
                elUsuariolLogic.deleteUsuario(elNombreUsuario);
        LOGGER.info("UserResource deleteUsuario: output: void");
    }
    
    /**
         * Convierte una lista de UsuarioEntity a una lista de UsuarioDetailDTO.
         *
         * @param entityList Lista de UsuarioEntity a convertir.
         * @return Lista de UsuarioDetailDTO convertida.
         */
    private List<UsuarioDetailDTO> listEntity2DTO(List<UsuarioEntity> entityList) {
        List<UsuarioDetailDTO> list = new ArrayList<>();
        for (UsuarioEntity entity : entityList) {
            list.add(new UsuarioDetailDTO(entity));
        }
        return list;
    }
    
}


