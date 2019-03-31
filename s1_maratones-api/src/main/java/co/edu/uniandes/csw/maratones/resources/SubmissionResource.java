/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.maratones.resources;

import co.edu.uniandes.csw.maratones.dtos.LenguajeDTO;
import co.edu.uniandes.csw.maratones.dtos.SubmissionDTO;
import co.edu.uniandes.csw.maratones.ejb.SubmissionLogic;
import co.edu.uniandes.csw.maratones.entities.SubmissionEntity;
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


@Path("submissions")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class SubmissionResource {
    
    
    private static final Logger LOGGER = Logger.getLogger(SubmissionResource.class.getName());
    
    
    @Inject
    private SubmissionLogic submissionLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.
    
    @POST
    public SubmissionDTO createSubmission(SubmissionDTO submission) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "EditorialResource createLenguaje: input: {0}", submission);
        // Convierte el DTO (json) en un objeto Entity para ser manejado por la lógica.
        SubmissionEntity submissionEntity = submission.toEntity();
        // Invoca la lógica para crear el lenguaje nuevo
        SubmissionEntity nuevoSubEntity = submissionLogic.createSubmission(submissionEntity);
        // Como debe retornar un DTO (json) se invoca el constructor del DTO con argumento el entity nuevo
        SubmissionDTO nuevoSubDTO = new SubmissionDTO(nuevoSubEntity);
        LOGGER.log(Level.INFO, "EditorialResource createEditorial: output: {0}", nuevoSubDTO);
        return nuevoSubDTO;
    }
    
    @GET
    @Path("{submissionsId: \\d+}")
    public SubmissionDTO getSubmission(@PathParam("submissionsId") Long submissionsId) {
        LOGGER.log(Level.INFO, "SubmissionResource getSubmission: input: {0}", submissionsId);
        SubmissionEntity submissionEntity = submissionLogic.getSubmission(submissionsId);
        if (submissionEntity == null) {
            throw new WebApplicationException("El recurso /submissions/" + submissionsId + " no existe.", 404);
        }
        SubmissionDTO submissionDTO = new SubmissionDTO(submissionEntity);
        LOGGER.log(Level.INFO, "SubmissionResource getSubmission: output: {0}", submissionDTO);
        return submissionDTO;
    }
    
    
    /**
     * Busca y devuelve todos las submissions que existen en la aplicacion.
     *
     * @return JSONArray {@link LenguajeDetailDTO} - Los libros encontrados en la
     * aplicación. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List<SubmissionDTO> getSubmissions() {
        LOGGER.info("SubmissionResource getSubmissions: input: void");
        List<SubmissionDTO> listaSubmission = listEntity2DTO(submissionLogic.getSubmissions());
        LOGGER.log(Level.INFO, "SubmissionResource getSubmissions: output: {0}", listaSubmission);
        return listaSubmission;
    }
    
    /**
     * Convierte una lista de entidades a DTO.
     *
     * Este método convierte una lista de objetos SubmissionEntity a una lista de
     * objetos SubmissionDTO (json)
     *
     * @param entityList corresponde a la lista de libros de tipo Entity que
     * vamos a convertir a DTO.
     * @return la lista de submissions en forma DTO (json)
     */
    private List<SubmissionDTO> listEntity2DTO(List<SubmissionEntity> entityList) {
        List<SubmissionDTO> list = new ArrayList<>();
        for(SubmissionEntity entity : entityList) {
            list.add(new SubmissionDTO(entity));
        }
        return list;
    }
    
    /**
     * Actualiza la submission con el id recibido en la URL con la información que se
     * recibe en el cuerpo de la petición.
     *
     * @param submissionsId Identificador de submission que se desea actualizar. Este debe
     * ser una cadena de dígitos.
     * @param submission {@link SubmissionDTO} submission que se desea guardar.
     * @return JSON {@link SubmissionDTO} - la submission guardada.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la submission a
     * actualizar.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando no se puede actualizar la submission.
     */
    @PUT
    @Path("{submissionsId: \\d+}")
    public SubmissionDTO updateSubmission(@PathParam("submissionsId") Long submissionsId, SubmissionDTO submission) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "SubmissionResource updateSubmission: input: id: {0} , submission: {1}", new Object[]{submissionsId, submission});
        
        if (submissionLogic.getSubmission(submissionsId) == null) {
            throw new WebApplicationException("El recurso /submissions/" + submissionsId + " no existe.", 404);
        }
        submission.setId(submissionsId);
        SubmissionDTO detailDTO = new SubmissionDTO(submissionLogic.updateSubmission(submissionsId, submission.toEntity()));
        LOGGER.log(Level.INFO, "SubmissionResource updateSubmission: output: {0}", detailDTO);
        return detailDTO;
    }
    
    /**
     * Borra el libro con el id asociado recibido en la URL.
     *
     * @param submissionsId Identificador del libro que se desea borrar. Este debe ser
     * una cadena de dígitos.
     * @throws BusinessLogicException
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el libro.
     */
    @DELETE
    @Path("{submissionsId: \\d+}")
    public void deletSubmission(@PathParam("submissionsId") Long submissionsId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "SubmissionResource deleteSubmission: input: {0}", submissionsId);
        SubmissionEntity entity = submissionLogic.getSubmission(submissionsId);
        if (entity == null) {
            throw new WebApplicationException("El recurso /submissions/" + submissionsId + " no existe.", 404);
        }
        
        submissionLogic.deleteSubmission(submissionsId);
        LOGGER.info("SubmissionResource deleteSubmission: output: void");
    }
    
}
