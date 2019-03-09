/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.maratones.resources;

import co.edu.uniandes.csw.maratones.dtos.CompetenciaDTO;
import co.edu.uniandes.csw.maratones.dtos.CompetenciaDetailDTO;
import co.edu.uniandes.csw.maratones.ejb.CompetenciaLogic;
import co.edu.uniandes.csw.maratones.entities.CompetenciaEntity;
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
@Path("/competencias")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class CompetenciaResource {
     private static final Logger LOGGER = Logger.getLogger(CompetenciaResource.class.getName());
    
     @Inject
     private CompetenciaLogic logic;
    
      /**
     * Crea una nueva editorial con la informacion que se recibe en el cuerpo de
     * la petición y se regresa un objeto identico con un id auto-generado por
     * la base de datos.
     *
     * @param competencia {@link EditorialDTO} - La editorial que se desea
     * guardar.
     * @return JSON {@link EditorialDTO} - La editorial guardada con el atributo
     * id autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando ya existe la editorial.
     */
    @POST
    public CompetenciaDTO createCompetencia(CompetenciaDTO competencia )throws BusinessLogicException {
        LOGGER.log(Level.INFO, "CompetenciaResource createCompetencia: input: {0}", competencia);
        // Convierte el DTO (json) en un objeto Entity para ser manejado por la lógica.
        CompetenciaEntity competenciaEntity = competencia.toEntity();
        // Invoca la lógica para crear la editorial nueva
        CompetenciaEntity nuevoCompetenciaEntity = logic.create(competenciaEntity);
        // Como debe retornar un DTO (json) se invoca el constructor del DTO con argumento el entity nuevo
        CompetenciaDTO nuevoCompetenciaDTO=  new CompetenciaDTO(nuevoCompetenciaEntity);
        LOGGER.log(Level.INFO, "EditorialResource createCompetencia: output: {0}", nuevoCompetenciaDTO);
        return nuevoCompetenciaDTO;
    }
    
    /**
     * Busca y devuelve todas las competencias que existen en la aplicacion.
     *
     * @return JSONArray {@link CompetenciaDetailDTO} - Las competencias
     * encontradas en la aplicación. Si no hay ninguna retorna una lista vacía.
     */
    @GET
    public List<CompetenciaDetailDTO> getCompetencias() {
        LOGGER.info("CompetenciaResource getCompetencias: input: void");
        List<CompetenciaDetailDTO> listaCompetencias = listEntity2DetailDTO(logic.getCompetencias());
        LOGGER.log(Level.INFO, "CompetenciaResource getCompetencias: output: {0}", listaCompetencias);
        return listaCompetencias;
    }
    
    /**
     * Busca la competencia con el id asociado recibido en la URL y la devuelve.
     *
     * @param competenciasId Identificador de la editorial que se esta buscando.
     * Este debe ser una cadena de dígitos.
     * @return JSON {@link EditorialDetailDTO} - La editorial buscada
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la editorial.
     */
    @GET
    @Path("{competenciasId: \\d+}")
    public CompetenciaDetailDTO getCompetencia(@PathParam("competenciasId") Long competenciasId) throws WebApplicationException {
        LOGGER.log(Level.INFO, "CompetenciaResource getCompetencia: input: {0}", competenciasId);
        CompetenciaEntity competenciaEntity = logic.getCompetencia(competenciasId);
        if (competenciaEntity == null) {
            throw new WebApplicationException("El recurso /competencias/" + competenciasId + " no existe.", 404);
        }
        CompetenciaDetailDTO detailDTO = new CompetenciaDetailDTO(competenciaEntity);
        LOGGER.log(Level.INFO, "CompetenciaResource getCompetencia: output: {0}", detailDTO);
        return detailDTO;
    }
  
    /**
     * Actualiza la competencia con el id recibido en la URL con la informacion
     * que se recibe en el cuerpo de la petición.
     *
     * @param competenciasId Identificador de la editorial que se desea
     * actualizar. Este debe ser una cadena de dígitos.
     * @param competencia {@link CompetenciaDetailDTO} La competencia que se desea
     * guardar.
     * @return JSON {@link CompetenciaDetailDTO} - La competencia guardada.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la competencia a
     * actualizar.
     */
    @PUT
    @Path("{competenciasId: \\d+}")
    public CompetenciaDetailDTO updateCompetencia(@PathParam("competenciasId") Long competenciasId, CompetenciaDetailDTO competencia) throws WebApplicationException {
        LOGGER.log(Level.INFO, "CompetenciaResource updateCompetencia: input: id:{0} , competencia: {1}", new Object[]{competenciasId, competencia});
        competencia.setId(competenciasId);
        if (logic.getCompetencia(competenciasId) == null) {
            throw new WebApplicationException("El recurso /competencias/" + competenciasId + " no existe.", 404);
        }
        CompetenciaDetailDTO detailDTO = new CompetenciaDetailDTO(logic.updateCompetencia(competenciasId, competencia.toEntity()));
        LOGGER.log(Level.INFO, "CompetenciaResource updateCompetencia: output: {0}", detailDTO);
        return detailDTO;
    }
         /**
     * Borra la competencia con el id asociado recibido en la URL.
     *
     * @param competenciasId Identificador de la competencia que se desea borrar.
     * Este debe ser una cadena de dígitos.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando no se puede eliminar la competencia.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la competencia.
     */
    @DELETE
    @Path("{competenciasId: \\d+}")
    public void deleteCompetencia(@PathParam("competenciasId") Long competenciasId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "CompetenciaResource deleteCompetencia: input: {0}", 0);
        if (logic.getCompetencia(competenciasId) == null) {
            throw new WebApplicationException("El recurso /competencias/" + competenciasId + " no existe.", 404);
        }
        logic.delete(competenciasId);
        LOGGER.info("CompetenciaResource deleteCompetencia: output: void");
    }

   
    
    /**
     * Convierte una lista de entidades a DTO.
     *
     * Este método convierte una lista de objetos EditorialEntity a una lista de
     * objetos EditorialDetailDTO (json)
     *
     * @param entityList corresponde a la lista de editoriales de tipo Entity
     * que vamos a convertir a DTO.
     * @return la lista de editoriales en forma DTO (json)
     */
    private List<CompetenciaDetailDTO> listEntity2DetailDTO(List<CompetenciaEntity> entityList) {
        List<CompetenciaDetailDTO> list = new ArrayList<>();
        for (CompetenciaEntity entity : entityList) {
            list.add(new CompetenciaDetailDTO(entity));
        }
        return list;
    }
}
