/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.maratones.resources;

import co.edu.uniandes.csw.maratones.dtos.SubmissionDTO;
import co.edu.uniandes.csw.maratones.ejb.EjercicioLogic;
import co.edu.uniandes.csw.maratones.ejb.EjercicioSubmissionsLogic;
import co.edu.uniandes.csw.maratones.ejb.SubmissionLogic;
import co.edu.uniandes.csw.maratones.entities.SubmissionEntity;
import co.edu.uniandes.csw.maratones.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
 * @author Angel Rodriguez aa.rodriguezv
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class EjercicioSubmissionsResource {

    private static final Logger LOGGER = Logger.getLogger(EjercicioSubmissionsResource.class.getName());

    @Inject
    private EjercicioSubmissionsLogic ejercicioSubmissionLogic;

    @Inject
    private EjercicioLogic ejercicioLogic;

    @Inject
    private SubmissionLogic submissionLogic;

    /**
     * Asocia un libro existente con un autor existente
     *
     * @param ejerciciosId
     * @param submission
     * @return JSON {@link SubmissionDTO} - El libro asociado.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el libro.
     */
    @POST
    public SubmissionDTO addSubmission(@PathParam("ejerciciosId") Long ejerciciosId, SubmissionDTO submission) {
        LOGGER.log(Level.INFO, "EjercicioSubmissionsResource addSubmission: input: ejerciciosId {0} , submissionsId {1}", new Object[]{ejerciciosId, submission.getId()});

        SubmissionDTO detailDTO = new SubmissionDTO(ejercicioSubmissionLogic.addSubmission(ejerciciosId, submission.toEntity()));
        LOGGER.log(Level.INFO, "EjercicioSubmissionsResource adSubmission: output: {0}", detailDTO);
        return detailDTO;
    }

    /**
     * Busca y devuelve todos los libros que existen en un autor.
     *
     * @param authorsId El ID del autor del cual se buscan los libros
     * @return JSONArray {@link BookDetailDTO} - Los libros encontrados en el
     * autor. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List<SubmissionDTO> getSubmissions(@PathParam("ejercicioId") Long ejercicioId) {
        LOGGER.log(Level.INFO, "EjercicioSubmissionsResource getSubmissions: input: {0}", ejercicioId);
        List<SubmissionDTO> lista = submissionsListEntity2DTO(ejercicioSubmissionLogic.getSubmissions(ejercicioId));
        LOGGER.log(Level.INFO, "EjercicioSubmissionsResource getSubmissions: output: {0}", lista);
        return lista;
    }

    /**
     * Elimina la conexión entre el libro y e autor recibidos en la URL. * Error
     * de lógica que se genera cuando no se encuentra el libro.
     *
     * @param ejerciciosId
     * @param submissionsId
     */
    @DELETE
    @Path("{submissionsId: \\d+}")
    public void removeSubmission(@PathParam("ejerciciosId") Long ejerciciosId, @PathParam("submissionsId") Long submissionsId) {
        LOGGER.log(Level.INFO, "EjercicioSubmissionsResource removeSubmission: input: ejerciciosId {0} , submissionsId {1}", new Object[]{ejerciciosId, submissionsId});
        if (submissionLogic.getSubmission(submissionsId) == null) {
            throw new WebApplicationException("El recurso /submissions/" + submissionsId + " no existe.", 404);
        }
        ejercicioSubmissionLogic.removeSubmission(ejerciciosId, submissionsId);
        LOGGER.info("EjercicioSubmissionsResource removeSubmission: output: void");
    }

    @GET
    @Path("{submissionsId: \\d+}")
    public SubmissionDTO getSubmission(@PathParam("ejerciciosId") Long ejerciciosId, @PathParam("submissionsId") Long submissionsId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "EjercicioSubmissionsResource getSubmission: input: authorsId {0} , booksId {1}", new Object[]{ejerciciosId, submissionsId});
        if (submissionLogic.getSubmission(submissionsId) == null) {
            throw new WebApplicationException("El recurso /submissions/" + submissionsId + " no existe.", 404);
        }
        SubmissionDTO detailDTO = new SubmissionDTO(ejercicioSubmissionLogic.getSubmission(ejerciciosId, submissionsId));
        LOGGER.log(Level.INFO, "EjercicioSubmissionsResource getSubmission: output: {0}", detailDTO);
        return detailDTO;
    }

    /**
     * Convierte una lista de BookDetailDTO a una lista de BookEntity.
     *
     * @param dtos Lista de BookDetailDTO a convertir.
     * @return Lista de BookEntity convertida.
     */
    private List<SubmissionEntity> submissionsListDTO2Entity(List<SubmissionDTO> dtos) {
        List<SubmissionEntity> list = new ArrayList<>();
        for (SubmissionDTO dto : dtos) {
            list.add(dto.toEntity());
        }
        return list;
    }

    /**
     * Convierte una lista de BookEntity a una lista de BookDetailDTO.
     *
     * @param entityList Lista de BookEntity a convertir.
     * @return Lista de BookDetailDTO convertida.
     */
    private List<SubmissionDTO> submissionsListEntity2DTO(List<SubmissionEntity> entityList) {
        List<SubmissionDTO> list = new ArrayList<>();
        for (SubmissionEntity entity : entityList) {
            list.add(new SubmissionDTO(entity));
        }
        return list;
    }

    /**
     * Remplaza las instancias de Submission asociadas a una instancia de
     * Ejercicio
     *
     * @param ejerciciosId
     * @param submissions
     * @return JSON {@link BookDTO} - El arreglo de submissions guardado en el
     * ejercicio.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la submission.
     */
    @PUT
    @Path("{submissionsId: \\d+}")
    public List<SubmissionDTO> replaceSubmissions(@PathParam("ejerciciosId") Long ejerciciosId, List<SubmissionDTO> submissions) {
        LOGGER.log(Level.INFO, "EjercicioSubmissionsResource replaceSubmissions: input: ejerciciosId: {0} , submissions: {1}", new Object[]{ejerciciosId, submissions});
        for (SubmissionDTO submission : submissions) {
            if (submissionLogic.getSubmission(submission.getId()) == null) {
                throw new WebApplicationException("El recurso /submissions/" + submission.getId() + " no existe.", 404);
            }
        }
        List<SubmissionDTO> listaDTOs = submissionsListEntity2DTO(ejercicioSubmissionLogic.replaceSubmissions(ejerciciosId, submissionsListDTO2Entity(submissions)));
        LOGGER.log(Level.INFO, "EjercicioSubmissionsResource replaceSubmissions: output: {0}", listaDTOs);
        return listaDTOs;
    }

}
