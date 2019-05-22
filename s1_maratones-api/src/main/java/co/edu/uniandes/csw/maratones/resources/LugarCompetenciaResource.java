/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.maratones.resources;

import co.edu.uniandes.csw.maratones.dtos.CompetenciaDetailDTO;
import co.edu.uniandes.csw.maratones.dtos.LugarCompetenciaDTO;
import co.edu.uniandes.csw.maratones.dtos.UsuarioDTO;
import co.edu.uniandes.csw.maratones.ejb.LugarCompetenciaLogic;
import co.edu.uniandes.csw.maratones.entities.CompetenciaEntity;
import co.edu.uniandes.csw.maratones.entities.LugarCompetenciaEntity;
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
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Julian David Mendoza Ruiz
 */
@Path("/lugarCompetencias")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class LugarCompetenciaResource {
    private static final Logger LOGGER = Logger.getLogger(LugarCompetenciaResource.class.getName());
    
    @Inject
    private LugarCompetenciaLogic lugarCompetenciaLogic;
    
    /**
     * Crea una nueva ubicacion con la informacion que se recibe en el cuerpo de
     * la petición y se regresa un objeto identico con un id auto-generado por
     * la base de datos.
     *
     * @param ubicacion {@link LugarCompetenciaDTO} - La ubicacion que se desea
     * guardar.
     * @return JSON {@link LugarCompetenciaDTO} - La ubicacion guardada con el atributo
     * id autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando ya existe la ubicacion.
     */
    @POST
    public LugarCompetenciaDTO createLugarCompetencia(LugarCompetenciaDTO ubicacion) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "LugarCompetenciaResource create: input: {0}", ubicacion);
        // Convierte el DTO (json) en un objeto Entity para ser manejado por la lógica.
        LugarCompetenciaEntity lugarCompetenciaEntity = ubicacion.toEntity();
        // Invoca la lógica para crear la editorial nueva
        LugarCompetenciaEntity nuevoLugarCompetenciaEntity = lugarCompetenciaLogic.createLugarCompetencia(lugarCompetenciaEntity);
        // Como debe retornar un DTO (json) se invoca el constructor del DTO con argumento el entity nuevo
        LugarCompetenciaDTO nuevoLugarCompetenciaDTO = new LugarCompetenciaDTO(nuevoLugarCompetenciaEntity);
        LOGGER.log(Level.INFO, "LugarCompetenciaResource create: output: {0}", nuevoLugarCompetenciaDTO);
        return nuevoLugarCompetenciaDTO;
    }
    
    /**
     * Busca y devuelve todos los LugarCompetencia que existen en la aplicacion.
     *
     * @return JSONArray {@link EditorialDetailDTO} - Los lugarCompetencias
     * encontradas en la aplicación. Si no hay ninguna retorna una lista vacía.
     */
    @GET
    public List<LugarCompetenciaDTO> getLugarCompetencias() {
        LOGGER.info("LugarCompetenciaResource getLugarCompetencias: input: void");
        List<LugarCompetenciaDTO> listaLugarCompetencias = listEntity2DTO(lugarCompetenciaLogic.getLugarCompetencias());
        LOGGER.log(Level.INFO, "LugarCompetenciaResource getLugarCompetencias: output: {0}", listaLugarCompetencias);
        return listaLugarCompetencias;
    }
    
    /**
     * Busca el lugarCompetencia con el id asociado recibido en la URL y la devuelve.
     *
     * @param lugarCompetenciasId Identificador de el lugarCompetencia que se esta buscando.
     * Este debe ser una cadena de dígitos.
     * @return JSON {@link LugarCompetenciaDetailDTO} - El lugarCompetencia buscado
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el LugarCompetencia.
     */
    @GET
    @Path("{lugarCompetenciasId: \\d+}")
    public LugarCompetenciaDTO getLugarCompetencia(@PathParam("lugarCompetenciasId") Long lugarCompetenciasId) throws WebApplicationException {
        LOGGER.log(Level.INFO, "LugarCompetenciaResource getLugarCompetencia: input: {0}", lugarCompetenciasId);
        LugarCompetenciaEntity lugarCompetenciaEntity = lugarCompetenciaLogic.getLugarCompetencia(lugarCompetenciasId);
        if (lugarCompetenciaEntity == null) {
            throw new WebApplicationException("El recurso /lugarCompetencias/" + lugarCompetenciasId + " no existe.", 404);
        }
        LugarCompetenciaDTO detailDTO = new LugarCompetenciaDTO(lugarCompetenciaEntity);
        LOGGER.log(Level.INFO, "LugarCompetenciaResource getLugarCompetencia: output: {0}", detailDTO);
        return detailDTO;
    }
    
    /**
     * Actualiza el lugarCompetencia con el id recibido en la URL con la informacion
     * que se recibe en el cuerpo de la petición.
     *
     * @param lugarCompetenciasId Identificador del lugarCompetencia que se desea
     * actualizar. Este debe ser una cadena de dígitos.
     * @param lugarCompetencia {@link LugarCompetenciaDTO} El lugarCompetencia que se desea
     * guardar.
     * @return JSON {@link EditorialDetailDTO} - El lugarCompetencia guardado.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el lugarCompetencia a
     * actualizar.
     */
    @PUT
    @Path("{lugarCompetenciasId: \\d+}")
    public LugarCompetenciaDTO updateLugarCompetencia(@PathParam("lugarCompetenciasId") Long lugarCompetenciasId, LugarCompetenciaDTO lugarCompetencia) throws WebApplicationException, BusinessLogicException {
        LOGGER.log(Level.INFO, "LugarCompetenciaResource updateLugarCompetencia: input: id:{0} , lugarCompetencia: {1}", new Object[]{lugarCompetenciasId, lugarCompetencia});
        lugarCompetencia.setId(lugarCompetenciasId);
        if (lugarCompetenciaLogic.getLugarCompetencia(lugarCompetenciasId) == null) {
            throw new WebApplicationException("El recurso /lugarCompetencias/" + lugarCompetenciasId + " no existe.", 404);
        }
        LugarCompetenciaDTO detailDTO = new LugarCompetenciaDTO(lugarCompetenciaLogic.updateLugarCompetencia(lugarCompetenciasId, lugarCompetencia.toEntity()));
        LOGGER.log(Level.INFO, ":LugarCompetenciaResource updateLugarCompetencia: output: {0}", detailDTO);
        return detailDTO;

    }
    
      /**
     * Borra el lugarCompetencia con el id asociado recibido en la URL.
     *
     * @param lugarCompetenciasId Identificador del lugarCompetencia que se desea borrar.
     * Este debe ser una cadena de dígitos.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando no se puede eliminar el lugarCompetencia.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el lugarCompetencia.
     */
    @DELETE
    @Path("{lugarCompetenciasId: \\d+}")
    public void deleteLugarCompetencia(@PathParam("lugarCompetenciasId") Long lugarCompetenciasId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "LugarCompetenciaResource deleteLugarCompetencia: input: {0}", lugarCompetenciasId);
        LugarCompetenciaEntity entity = lugarCompetenciaLogic.getLugarCompetencia(lugarCompetenciasId);
        
        if (entity == null) {
            throw new WebApplicationException("El recurso /lugarCompetencias/" + lugarCompetenciasId + " no existe.", 404);
        }
        lugarCompetenciaLogic.deleteLugarCompetencia(lugarCompetenciasId);
        LOGGER.info("LugarCompetenciaResource deleteLugarCompetencia: output: void");
    }
    
     
    
    /**
     * Convierte una lista de entidades a DTO.
     *
     * Este método convierte una lista de objetos LugarCompetenciaEntity a una lista de
     * objetos LugarCompetenciaDetailDTO (json)
     *
     * @param entityList corresponde a la lista de lugarCompetencias de tipo Entity
     * que vamos a convertir a DTO.
     * @return la lista de lugarCompetencias en forma DTO (json)
     */
    private List<LugarCompetenciaDTO> listEntity2DTO(List<LugarCompetenciaEntity> entityList) {
        List<LugarCompetenciaDTO> list = new ArrayList<>();
        for (LugarCompetenciaEntity entity : entityList) {
            list.add(new LugarCompetenciaDTO(entity));
        }
        return list;
    }
    
    
        
}
