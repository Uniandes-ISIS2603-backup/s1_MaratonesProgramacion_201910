/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.maratones.resources;

import co.edu.uniandes.csw.maratones.dtos.EjercicioDetailDTO;
import co.edu.uniandes.csw.maratones.dtos.SubmissionDTO;
import co.edu.uniandes.csw.maratones.ejb.CompetenciaEjercicioLogic;
import co.edu.uniandes.csw.maratones.ejb.CompetenciaLogic;
import co.edu.uniandes.csw.maratones.ejb.CompetenciaLugarCompetenciasLogic;
import co.edu.uniandes.csw.maratones.ejb.EjercicioLogic;
import co.edu.uniandes.csw.maratones.ejb.EjercicioSubmissionsLogic;
import co.edu.uniandes.csw.maratones.ejb.SubmissionLogic;
import co.edu.uniandes.csw.maratones.entities.EjercicioEntity;
import co.edu.uniandes.csw.maratones.entities.SubmissionEntity;
import co.edu.uniandes.csw.maratones.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author Julian David Mendoza Ruiz <jd.mendozar@uniandes.edu.co>
 */
public class CompetenciaEjercicioResource {
       
    private static final Logger LOGGER = Logger.getLogger(CompetenciaEjercicioResource.class.getName());

    @Inject
    private CompetenciaEjercicioLogic ejercicioSubmissionLogic;

    @Inject
    private CompetenciaLogic competenciaLogic;
    
    @Inject
    private EjercicioLogic ejercicioLogic;

    
    
    /**
     * Asocia un libro existente con un autor existente
     *
     * @param competenciasId
     * @param ejerciciosId
     * @return JSON {@link SubmissionDTO} - El libro asociado.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el libro.
     */
    @POST
    @Path("{competenciassId: \\d+}")
    public EjercicioDetailDTO addEjercicio(@PathParam("competenciasId") Long competenciasId, @PathParam("ejerciciosId") Long ejerciciosId) {
        LOGGER.log(Level.INFO, "EjercicioSubmissionsResource addSubmission: input: ejerciciosId {0} , submissionsId {1}", new Object[]{competenciasId, ejerciciosId});
        if (ejercicioLogic.getEjercicio(ejerciciosId) == null) {
            throw new WebApplicationException("El recurso /competencias/" + ejerciciosId + " no existe.", 404);
        }
        EjercicioDetailDTO detailDTO = new EjercicioDetailDTO(ejercicioSubmissionLogic.addEjercicio(competenciasId, ejerciciosId));
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
    public List<EjercicioDetailDTO> getEjercicios(@PathParam("EjerciciosId") Long ejerciciosId) {
        LOGGER.log(Level.INFO, "EjercicioSubmissionsResource getSubmissions: input: {0}", ejerciciosId);
        List<EjercicioDetailDTO> lista = ejerciciosListEntity2DTO(ejercicioSubmissionLogic.getEjercicios(ejerciciosId));
        LOGGER.log(Level.INFO, "EjercicioSubmissionsResource getSubmissions: output: {0}", lista);
        return lista;
    }
    
    
        /**
     * Elimina la conexión entre el libro y e autor recibidos en la URL.
     ** Error de lógica que se genera cuando no se encuentra el libro.
     * @param competenciasId
     * @param ejerciciosId
     */
    @DELETE
    @Path("{competenciasId: \\d+}")
    public void removeSubmission(@PathParam("competenciasId") Long competenciasId, @PathParam("ejerciciosId") Long ejerciciosId) {
        LOGGER.log(Level.INFO, "EjercicioSubmissionsResource removeSubmission: input: ejerciciosId {0} , submissionsId {1}", new Object[]{competenciasId, ejerciciosId});
        if (ejercicioLogic.getEjercicio(ejerciciosId) == null) {
            throw new WebApplicationException("El recurso /submissions/" + ejerciciosId + " no existe.", 404);
        }
        ejercicioSubmissionLogic.removeEjercicio(competenciasId, ejerciciosId);
        LOGGER.info("EjercicioSubmissionsResource removeSubmission: output: void");
    }
    
    
    @GET
    @Path("{competenciasId: \\d+}")
    public EjercicioDetailDTO getSubmission(@PathParam("competenciasId") Long competenciasId, @PathParam("ejerciciosId") Long ejerciciosId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "AuthorBooksResource getBook: input: authorsId {0} , booksId {1}", new Object[]{competenciasId, ejerciciosId});
        if (ejercicioLogic.getEjercicio(ejerciciosId) == null) {
            throw new WebApplicationException("El recurso /submissions/" + ejerciciosId + " no existe.", 404);
        }
        EjercicioDetailDTO detailDTO = new EjercicioDetailDTO(ejercicioSubmissionLogic.getEjercicio(competenciasId, ejerciciosId));
        LOGGER.log(Level.INFO, "AuthorBooksResource getBook: output: {0}", detailDTO);
        return detailDTO;
    }
    
        /**
     * Convierte una lista de BookDetailDTO a una lista de BookEntity.
     *
     * @param dtos Lista de BookDetailDTO a convertir.
     * @return Lista de BookEntity convertida.
     */
    private List<EjercicioEntity> ejerciciosListDTO2Entity(List<EjercicioDetailDTO> dtos) {
        List<EjercicioEntity> list = new ArrayList<>();
        for (EjercicioDetailDTO dto : dtos) {
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
    private List<EjercicioDetailDTO> ejerciciosListEntity2DTO(List<EjercicioEntity> entityList) {
        List<EjercicioDetailDTO> list = new ArrayList<>();
        for (EjercicioEntity entity : entityList) {
            list.add(new EjercicioDetailDTO(entity));
        }
        return list;
    }
    
      

}
