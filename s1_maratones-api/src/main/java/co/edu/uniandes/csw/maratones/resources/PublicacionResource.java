/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.maratones.resources;

import co.edu.uniandes.csw.maratones.dtos.PublicacionDTO;
import co.edu.uniandes.csw.maratones.ejb.PublicacionLogic;
import co.edu.uniandes.csw.maratones.entities.PublicacionEntity;
import co.edu.uniandes.csw.maratones.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.Date;
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
 * @author c.mendez11
 */
@Path ( "publicaciones" )
@Produces ( " application/json " )
@Consumes ( " application/json " )
@RequestScoped
public class PublicacionResource {
      private static final Logger LOGGER = Logger.getLogger(PublicacionResource.class.getName()); 
     @Inject
    private PublicacionLogic publicacionLogic;
   @POST
   public PublicacionDTO crearPublicacion(@PathParam("blogsId") Long blogsId,PublicacionDTO publicacion) throws BusinessLogicException{
       LOGGER.log(Level.INFO, "PublicacionResource createPublicacion: input: {0}", publicacion);
      
        Date f = new Date();
        publicacion.setFecha(f);
        PublicacionDTO bpublicacion = new PublicacionDTO(publicacionLogic.createPublicacion(blogsId,publicacion.toEntity()));
        LOGGER.log(Level.INFO, "PublicacionResource createPublicacion: output: {0}", bpublicacion);
        return bpublicacion;
       
   }
   @GET
    public List<PublicacionDTO> getPublicaciones() {
        LOGGER.info("PublicacionesResource getPublicaciones: input: void");
        List<PublicacionDTO> listaPublicaciones = listEntity2DetailDTO(publicacionLogic.getPublicaciones());
        LOGGER.log(Level.INFO, "PublicacionResource getPublicaciones: output: {0}", listaPublicaciones);
        return listaPublicaciones;
    }
     private List<PublicacionDTO> listEntity2DetailDTO(List<PublicacionEntity> entityList) {
        List<PublicacionDTO> list = new ArrayList<>();
        for (PublicacionEntity entity : entityList) {
            list.add(new PublicacionDTO(entity));
        }
        return list;
    }
     @GET
    @Path("{publicacionesId: \\d+}")
    public PublicacionDTO getPublicacion(@PathParam("publicacionesId") Long publicacionesId) {
        LOGGER.log(Level.INFO, "PublicacionResource getPublicacion: input: {0}", publicacionesId);
        PublicacionEntity publicacionEntity = publicacionLogic.getPublicacion(publicacionesId);
        if (publicacionEntity == null) {
            throw new WebApplicationException("El recurso /Publicacion/" + publicacionesId + " no existe.", 404);
        }
        PublicacionDTO publicacionDTO = new PublicacionDTO(publicacionEntity);
        LOGGER.log(Level.INFO, "PublicaciongResource getPublicacion: output: {0}", publicacionDTO);
        return publicacionDTO;
    }
    
    @PUT
    @Path("{publicacionesId: \\d+}")
    public PublicacionDTO updatePublicacion(@PathParam("publicacionesId") Long publicacionesId, PublicacionDTO publicacion) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "PublicacionResource updatePublicacion: input: id: {0} , Publicacion: {1}", new Object[]{publicacionesId, publicacion});
        publicacion.setId(publicacionesId);
        if (publicacionLogic.getPublicacion(publicacionesId) == null) {
            throw new WebApplicationException("El recurso /Publicacion/" + publicacionesId + " no existe.", 404);
        }
        PublicacionDTO detailDTO = new PublicacionDTO(publicacionLogic.updatePublicacion(publicacionesId, publicacion.toEntity()));
        LOGGER.log(Level.INFO, "PublicacionResource updatePublicacion: output: {0}", detailDTO);
        return detailDTO;
    }
    @DELETE
    @Path("{publicacionesId: \\d+}")
    public void deletePublicacion(@PathParam("publicacionesId") Long publicacionesId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "PublicacionResource deletePublicacion: input: {0}", publicacionesId);
        PublicacionEntity entity = publicacionLogic.getPublicacion(publicacionesId);
        if (entity == null) {
            throw new WebApplicationException("El recurso /Publicacion/" + publicacionesId + " no existe.", 404);
        }
        
        publicacionLogic.deletePublicacion(publicacionesId);
        LOGGER.info("PublicacionResource deletePublicacion: output: void");
    }
}
