/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.maratones.resources;

import co.edu.uniandes.csw.maratones.dtos.LenguajeDTO;

import co.edu.uniandes.csw.maratones.ejb.LenguajeLogic;
import co.edu.uniandes.csw.maratones.entities.LenguajeEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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

    
    @POST
    public LenguajeDTO createLenguaje(LenguajeDTO lenguaje)
    {
        return lenguaje;
    }
    
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
     * Busca y devuelve todos los libros que existen en la aplicacion.
     *
     * @return JSONArray {@link LenguajeDetailDTO} - Los libros encontrados en la
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
}
