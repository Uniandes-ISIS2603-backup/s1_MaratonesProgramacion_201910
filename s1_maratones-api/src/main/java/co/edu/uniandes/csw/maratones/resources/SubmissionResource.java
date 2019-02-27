/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.maratones.resources;

import co.edu.uniandes.csw.maratones.dtos.SubmissionDTO;
import co.edu.uniandes.csw.maratones.ejb.SubmissionLogic;
import co.edu.uniandes.csw.maratones.entities.SubmissionEntity;
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


@Path("submissions")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class SubmissionResource {
    
    
    private static final Logger LOGGER = Logger.getLogger(SubmissionResource.class.getName());
    
    
    @Inject
    private SubmissionLogic submissionLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    @POST
    public SubmissionDTO createSubmission(SubmissionDTO submission)
    {
        return submission;
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
        LOGGER.log(Level.INFO, "LenguajeResource getLenguaje: output: {0}", submissionDTO);
        return submissionDTO;
    }
    
    
    /**
     * Busca y devuelve todos los libros que existen en la aplicacion.
     *
     * @return JSONArray {@link LenguajeDetailDTO} - Los libros encontrados en la
     * aplicación. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List<SubmissionDTO> getLenguajes() {
        LOGGER.info("LenguajeResource getLenguajes: input: void");
        List<SubmissionDTO> listaSubmission = listEntity2DTO(submissionLogic.getSubmissions());
        LOGGER.log(Level.INFO, "LenguajeResource getLenguajes: output: {0}", listaSubmission);
        return listaSubmission;
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
    private List<SubmissionDTO> listEntity2DTO(List<SubmissionEntity> entityList) {
        List<SubmissionDTO> list = new ArrayList<>();
        for (SubmissionEntity entity : entityList) {
            list.add(new SubmissionDTO(entity));
        }
        return list;
    }
    
}
