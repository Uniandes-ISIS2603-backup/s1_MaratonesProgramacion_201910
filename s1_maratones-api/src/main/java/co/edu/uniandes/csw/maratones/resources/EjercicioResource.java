/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.maratones.resources;

import co.edu.uniandes.csw.maratones.dtos.EjercicioDTO;
import co.edu.uniandes.csw.maratones.dtos.EjercicioDetailDTO;
import co.edu.uniandes.csw.maratones.ejb.EjercicioLogic;
import co.edu.uniandes.csw.maratones.entities.EjercicioEntity;
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

@Path("ejercicios")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class EjercicioResource {
    
    private static final Logger LOGGER = Logger.getLogger(EjercicioResource.class.getName());
    
    @Inject
    EjercicioLogic ejercicioLogic;
    
    @POST
    public EjercicioDTO createEjercicio(EjercicioDTO ejercicio) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "EditorialResource createEjercicio: input: {0}", ejercicio);
        // Convierte el DTO (json) en un objeto Entity para ser manejado por la lógica.
        EjercicioEntity ejerEntity = ejercicio.toEntity();
        // Invoca la lógica para crear el lenguaje nuevo
        EjercicioEntity nuevoEjerEntity = ejercicioLogic.createEjercicio(ejerEntity);
        // Como debe retornar un DTO (json) se invoca el constructor del DTO con argumento el entity nuevo
        EjercicioDTO nuevoSubDTO = new EjercicioDTO(nuevoEjerEntity);
        LOGGER.log(Level.INFO, "EditorialResource createEjercicio: output: {0}", nuevoSubDTO);
        return nuevoSubDTO;
    }
    
    
    @GET
    public List<EjercicioDetailDTO> getEjercicios() {
        LOGGER.info("EjercicioResource getEjercicios: input: void");
        List<EjercicioDetailDTO> listaBooks = listEntity2DetailDTO(ejercicioLogic.getEjercicios());
        LOGGER.log(Level.INFO, "EjercicioResource getEjercicios: output: {0}", listaBooks);
        return listaBooks;
    }
    
    
    
    @GET
    @Path("{ejerciciosId: \\d+}")
    public EjercicioDetailDTO getEjercicio(@PathParam("ejerciciosId") Long ejerciciosId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "EjercicioResource getEjercicio: input: {0}", ejerciciosId);
        
        if (ejercicioLogic.getEjercicio(ejerciciosId) == null) {
            throw new WebApplicationException("El recurso /ejercicios/" + ejerciciosId + " no existe.", 404);
        }
        EjercicioEntity ejercicioEntity = ejercicioLogic.getEjercicio(ejerciciosId);
        EjercicioDetailDTO ejercicioDetailDTO = new EjercicioDetailDTO(ejercicioEntity);
        LOGGER.log(Level.INFO, "EjercicioResource getEjercicio: output: {0}", ejercicioDetailDTO);
        return ejercicioDetailDTO;
    }
    
    
    @PUT
    @Path("{ejerciciosId: \\d+}")
    public EjercicioDetailDTO updateEjercicio(@PathParam("ejerciciosId") Long ejerciciosId, EjercicioDTO ejercicio) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "EjercicioResource updateEjercicio: input: id: {0} , book: {1}", new Object[]{ejerciciosId, ejercicio});
        
        if (ejercicioLogic.getEjercicio(ejerciciosId) == null) {
            throw new WebApplicationException("El recurso /ejercicios/" + ejerciciosId + " no existe.", 404);
        }
        ejercicio.setId(ejerciciosId);
        EjercicioDetailDTO detailDTO = new EjercicioDetailDTO(ejercicioLogic.updateEjercicio(ejerciciosId, ejercicio.toEntity()));
        LOGGER.log(Level.INFO, "EjercicioResource updateEjercicio: output: {0}", detailDTO);
        return detailDTO;
    }
    
    @DELETE
    @Path("{ejerciciosId: \\d+}")
    public void deleteEjercicio(@PathParam("ejerciciosId") Long ejerciciosId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "EjercicioResource deleteEjercicio: input: {0}", ejerciciosId);
        EjercicioEntity entity = ejercicioLogic.getEjercicio(ejerciciosId);
        if (entity == null) {
            throw new WebApplicationException("El recurso /ejercicios/" + ejerciciosId + " no existe.", 404);
        }
        ejercicioLogic.deleteEjercicio(ejerciciosId);
        LOGGER.info("EjercicioResource deleteEjercicio: output: void");
    }
    
    
    /**
     * 
     * @param entityList
     * @return 
     */
    private List<EjercicioDetailDTO> listEntity2DetailDTO(List<EjercicioEntity> entityList) {
        List<EjercicioDetailDTO> list = new ArrayList<>();
        for (EjercicioEntity entity : entityList) {
            list.add(new EjercicioDetailDTO(entity));
        }
        return list;
    }

    
}
