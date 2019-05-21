/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.maratones.resources;

import co.edu.uniandes.csw.maratones.dtos.LenguajeDTO;

import co.edu.uniandes.csw.maratones.ejb.LenguajeLogic;
import co.edu.uniandes.csw.maratones.entities.LenguajeEntity;
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

@Path("lenguajes")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class LenguajeResource {
    
    
    private static final Logger LOGGER = Logger.getLogger(LenguajeResource.class.getName());
    
    @Inject
    private LenguajeLogic lenguajeLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    /**
     * Crea un nuevo lenguaje con la informacion que se recibe en el cuerpo de la
     * petición y se regresa un objeto identico con un id auto-generado por la
     * base de datos.
     *
     * @param lenguaje {@link LenguajeDTO} - EL lenguaje que se desea guardar.
     * @return JSON {@link LenguajeDTO} - El lenguaje guardado con el atributo id
     * autogenerado.
     * @throws BusinessLogicException en caso de que se viole alguna de las reglas definidas en la capa de logica del lenguaje 
     */
    @POST
    public LenguajeDTO createLenguaje(LenguajeDTO lenguaje) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "EditorialResource createLenguaje: input: {0}", lenguaje);
        // Convierte el DTO (json) en un objeto Entity para ser manejado por la lógica.
        LenguajeEntity lenguajeEntity = lenguaje.toEntity();
        // Invoca la lógica para crear el lenguaje nuevo
        LenguajeEntity nuevoEditorialEntity = lenguajeLogic.createLenguaje(lenguajeEntity);
        // Como debe retornar un DTO (json) se invoca el constructor del DTO con argumento el entity nuevo
        LenguajeDTO nuevoLenguajeDTO = new LenguajeDTO(nuevoEditorialEntity);
        LOGGER.log(Level.INFO, "EditorialResource createEditorial: output: {0}", nuevoLenguajeDTO);
        return nuevoLenguajeDTO;
    }
    
    
    /**
     * Busca el lenguaje con el id asociado recibido en la URL y lo devuelve.
     *
     * @param lenguajesId Identificador del lenguajes que se esta buscando. Este debe
     * ser una cadena de dígitos.
     * @return JSON {@link LenguajeDTO} - El lenguaje buscado
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el autor.
     */
    @GET
    @Path("{lenguajesId: \\d+}")
    public LenguajeDTO getLenguaje(@PathParam("lenguajesId") Long lenguajesId) {
        LOGGER.log(Level.INFO, "LenguajeResource getLenguaje: input: {0}", lenguajesId);
        LenguajeEntity lenguajeEntity = lenguajeLogic.getLenguaje(lenguajesId);
        if (lenguajeEntity == null) {
            throw new WebApplicationException("El recurso /lenguajes/" + lenguajesId + " no existe.", 404);
        }
        LenguajeDTO lenguajeDTO = new LenguajeDTO(lenguajeEntity);
        LOGGER.log(Level.INFO, "LenguajeResource getLenguaje: output: {0}", lenguajeDTO);
        return lenguajeDTO;
    }
    
    
    /**
     * Busca y devuelve todos los lenguajes que existen en la aplicacion.
     *
     * @return JSONArray {@link LenguajeDTO} - Los lenguajes encontrados en la
     * aplicación. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List<LenguajeDTO> getLenguajes() {
        LOGGER.info("LenguajeResource getLenguajes: input: void");
        List<LenguajeDTO> listaLenguajes = listEntity2DTO(lenguajeLogic.getLenguajes());
        LOGGER.log(Level.INFO, "LenguajeResource getLenguajes: output: {0}", listaLenguajes);
        return listaLenguajes;
    }
    
    /**
     * Convierte una lista de entidades a DTO.
     *
     * Este método convierte una lista de objetos BookEntity a una lista de
     * objetos BookDetailDTO (json)
     *
     * @param entityList corresponde a la lista de libros de tipo Entity que
     * vamos a convertir a DTO.
     * @return la lista de libros en forma DTO (json)
     */
    private List<LenguajeDTO> listEntity2DTO(List<LenguajeEntity> entityList) {
        List<LenguajeDTO> list = new ArrayList<>();
        for (LenguajeEntity entity : entityList) {
            list.add(new LenguajeDTO(entity));
        }
        return list;
    }
    
    
    /**
     * Actualiza el lenguaje con el id recibido en la URL con la información que se
     * recibe en el cuerpo de la petición.
     *
     * @param lenguajesId
     * @param lenguaje
     * @return JSON {@link LenguajeDTO} - El lenguaje guardado.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el lenguaje a
     * actualizar.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando no se puede actualizar el lenguaje.
     */
    @PUT
    @Path("{lenguajesId: \\d+}")
    public LenguajeDTO updateLenguaje(@PathParam("lenguajesId") Long lenguajesId, LenguajeDTO lenguaje) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "LenguajeResource updateLenguaje: input: id: {0} , lenguaje: {1}", new Object[]{lenguajesId, lenguaje});
        
        if (lenguajeLogic.getLenguaje(lenguajesId) == null) {
            throw new WebApplicationException("El recurso /lenguajes/" + lenguajesId + " no existe.", 404);
        }
        lenguaje.setId(lenguajesId);
        LenguajeDTO delDTO = new LenguajeDTO(lenguajeLogic.updateLenguaje(lenguaje.toEntity()));
        LOGGER.log(Level.INFO, "LenguajeResource updateLenguaje: output: {0}", delDTO);
        return delDTO;
    }
    
    /**
     * Borra el lenguaje con el id asociado recibido en la URL.
     *
     * @param lenguajesId Identificador del lenguaje que se desea borrar. Este debe
     * ser una cadena de dígitos.
     * @throws BusinessLogicException
     * se retorna excepcion cuando se  viola alguna regla definida en la logica de lenguaje
     * @throws WebApplicationException {@link WebApplicationExceptionMapper}
     * Error de lógica que se genera cuando no se encuentra el lenguaje a borrar.
     */
    @DELETE
    @Path("{lenguajesId: \\d+}")
    public void deletLenguaje(@PathParam("lenguajesId") Long lenguajesId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "LenguajeResource deleteLenguaje: input: {0}", lenguajesId);
        LenguajeEntity entity = lenguajeLogic.getLenguaje(lenguajesId);
        if (entity == null) {
            throw new WebApplicationException("El recurso /lenguajes/" + lenguajesId + " no existe.", 404);
        }
        
        lenguajeLogic.deleteLenguaje(lenguajesId);
        LOGGER.info("LenguajeResource deleteLenguaje: output: void");
    }
}
