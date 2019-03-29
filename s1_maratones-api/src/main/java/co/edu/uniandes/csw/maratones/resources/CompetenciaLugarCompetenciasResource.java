/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.maratones.resources;

import co.edu.uniandes.csw.maratones.dtos.LugarCompetenciaDTO;
import co.edu.uniandes.csw.maratones.ejb.CompetenciaLugarCompetenciasLogic;
import co.edu.uniandes.csw.maratones.ejb.LugarCompetenciaLogic;
import co.edu.uniandes.csw.maratones.entities.LugarCompetenciaEntity;
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

/**
 *
 * @author Julian David Mendoza Ruiz <jd.mendozar@uniandes.edu.co>
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CompetenciaLugarCompetenciasResource {
     private static final Logger LOGGER = Logger.getLogger(CompetenciaLugarCompetenciasResource.class.getName());

    @Inject
    private CompetenciaLugarCompetenciasLogic competenciaLugarCompetenciasLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    @Inject
    private LugarCompetenciaLogic lugarCompetenciaLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.
    
    /**
     * Guarda un lugarCompetencia dentro de una comptencia con la informacion que recibe el
     * la URL. Se devuelve el lugarCompetencia que se guarda en la competencia.
     *
     * @param competenciasId Identificador de la competencia que se esta
     * actualizando. Este debe ser una cadena de dígitos.
     * @param lugarCompetenciasId Identificador del lugarCompetencia que se desea guardar. Este debe
     * ser una cadena de dígitos.
     * @return JSON {@link LugarCompetenciaDTO} - El libro guardado en la competencia.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el lugarCompetencia.
     */
    @POST
    @Path("{lugarCompetenciasId: \\d+}")
    public LugarCompetenciaDTO addLugarCompetencia(@PathParam("competenciasId") Long competenciasId, @PathParam("lugarCompetenciasId") Long lugarCompetenciasId) {
        LOGGER.log(Level.INFO, "CompetenciaLugarCompetenciaResource addLugarComptencia: input: competenciasID: {0} , lugarCompetenciasId: {1}", new Object[]{competenciasId, lugarCompetenciasId});
        if (lugarCompetenciaLogic.getLugarCompetencia(lugarCompetenciasId) == null) {
            throw new WebApplicationException("El recurso /lugarCompetencias/" + lugarCompetenciasId + " no existe.", 404);
        }
        LugarCompetenciaDTO lugarCompetenciaDTO = new LugarCompetenciaDTO(competenciaLugarCompetenciasLogic.addLugarCompetencia(lugarCompetenciasId, competenciasId));
        LOGGER.log(Level.INFO, "CompetenciaLugarCompetenciaResource addLugarCompetencia: output: {0}", lugarCompetenciaDTO);
        return lugarCompetenciaDTO;
    }
    
     /**
     * Busca y devuelve todos los lugarCompetencias que existen en la competencia.
     *
     * @param competenciasId Identificador de la competencia que se esta buscando.
     * Este debe ser una cadena de dígitos.
     * @return JSONArray {@link LugarCompetenciaDetailDTO} - Los lugarCompetencia encontrados en la
     * competencia. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List<LugarCompetenciaDTO> getLugarCompetencias(@PathParam("competenciasId") Long competenciasId) {
        LOGGER.log(Level.INFO, "CompetenciaLugarCompetenciaResource getLugarCompetencias: input: {0}", competenciasId);
        List<LugarCompetenciaDTO> listaDetailDTOs = lugarCompetenciasListEntity2DTO(competenciaLugarCompetenciasLogic.getLugarCompetencias(competenciasId));
        LOGGER.log(Level.INFO, "CompetenciaLugarCompetenciaResource getLugarCompetencias: output: {0}", listaDetailDTOs);
        return listaDetailDTOs;
    }
    
    /**
     * Busca el lugarCompetencia con el id asociado dentro de la competencia con id asociado.
     *
     * @param competenciasId Identificador de la competencia que se esta buscando.
     * Este debe ser una cadena de dígitos.
     * @param lugarCompetenciasId Identificador del lugarCompetencia que se esta buscando. Este debe
     * ser una cadena de dígitos.
     * @return JSON {@link LugarCompetenciaDetailDTO} - El lugarCompetencia buscado
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el lugarCompetencia.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el lugarCompetencia en la
     * competencia.
     */
    @GET
    @Path("{lugarCompetenciasId: \\d+}")
    public LugarCompetenciaDTO getLugarCompetencia(@PathParam("competenciasId") Long competenciasId, @PathParam("lugarCompetenciasId") Long lugarCompetenciasId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "CompetenciaLugarCompetenciasResource getLugarCompetencia: input: competenciasID: {0} , lugarCompetenciasId: {1}", new Object[]{competenciasId, lugarCompetenciasId});
        if (lugarCompetenciaLogic.getLugarCompetencia(lugarCompetenciasId) == null) {
            throw new WebApplicationException("El recurso /competencias/" + competenciasId + "/lugarCompetencias/" + lugarCompetenciasId + " no existe.", 404);
        }
        LugarCompetenciaDTO lugarCompetenciaDetailDTO = new LugarCompetenciaDTO(competenciaLugarCompetenciasLogic.getLugarCompetencia(competenciasId, lugarCompetenciasId));
        LOGGER.log(Level.INFO, "CompetenciaLugarCompetenciaResource getLugarCompetencia: output: {0}", lugarCompetenciaDetailDTO);
        return lugarCompetenciaDetailDTO;
    }
    
     /**
     * Convierte una lista de LugarCompetenciaEntity a una lista de LugarCompetenciaDetailDTO.
     *
     * @param entityList Lista de LugarCompetenciaEntity a convertir.
     * @return Lista de LugarCompetenciaDTO convertida.
     */
    private List<LugarCompetenciaDTO> lugarCompetenciasListEntity2DTO(List<LugarCompetenciaEntity> entityList) {
        List<LugarCompetenciaDTO> list = new ArrayList();
        for (LugarCompetenciaEntity entity : entityList) {
            list.add(new LugarCompetenciaDTO(entity));
        }
        return list;
    }
    
     /**
     * Remplaza las instancias de LugarCompetencia asociadas a una instancia de Competencia
     *
     * @param competenciasId Identificador de la competencia que se esta
     * remplazando. Este debe ser una cadena de dígitos.
     * @param lugarCompetencias JSONArray {@link LugarCompetenciaDTO} El arreglo de lugarCompetencias nuevo para la
     * competencia.
     * @return JSON {@link LugarCompetenciaDTO} - El arreglo de lugarCompetencia guardado en la
     * competencia.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el lugarCompetencia.
     */
    @PUT
    public List<LugarCompetenciaDTO> replaceLugarCompetencias(@PathParam("competenciasId") Long competenciasId, List<LugarCompetenciaDTO> lugarCompetencias) {
        LOGGER.log(Level.INFO, "CompetenciaLugarCompetenciasResource replaceLugarCompetencias: input: competenciasId: {0} , lugarCompetencias: {1}", new Object[]{competenciasId, lugarCompetencias});
        for (LugarCompetenciaDTO lugarCompetencia : lugarCompetencias) {
            if (lugarCompetenciaLogic.getLugarCompetencia(lugarCompetencia.getId()) == null) {
                throw new WebApplicationException("El recurso /lugarCompetencias/" + lugarCompetencia.getId() + " no existe.", 404);
            }
        }
        List<LugarCompetenciaDTO> listaDetailDTOs = lugarCompetenciasListEntity2DTO(competenciaLugarCompetenciasLogic.replaceLugarCompetencias(competenciasId, lugarCompetenciasListDTO2Entity(lugarCompetencias)));
        LOGGER.log(Level.INFO, "CompetenciaLugarCompetenciasResource replaceLugarCompetencias: output: {0}", listaDetailDTOs);
        return listaDetailDTOs;
    }
    
     /**
     * Convierte una lista de LugarCompetenciaDetailDTO a una lista de LugarCompetenciaEntity.
     *
     * @param dtos Lista de LugarCompetenciaDetailDTO a convertir.
     * @return Lista de LugarCompetenciaEntity convertida.
     */
    private List<LugarCompetenciaEntity> lugarCompetenciasListDTO2Entity(List<LugarCompetenciaDTO> dtos) {
        List<LugarCompetenciaEntity> list = new ArrayList<>();
        for (LugarCompetenciaDTO dto : dtos) {
            list.add(dto.toEntity());
        }
        return list;
    }

}
