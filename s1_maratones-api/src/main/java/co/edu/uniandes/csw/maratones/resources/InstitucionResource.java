/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.maratones.resources;

import co.edu.uniandes.csw.maratones.dtos.InstitucionDTO;
import co.edu.uniandes.csw.maratones.dtos.InstitucionDetailDTO;
import co.edu.uniandes.csw.maratones.ejb.InstitucionLogic;
import co.edu.uniandes.csw.maratones.entities.InstitucionEntity;
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
 * @author c.mendez11
 */
@Path ( "instituciones" )
@Produces ( "application/json" )
@Consumes ( "application/json" )
@RequestScoped
public class InstitucionResource {
      private static final Logger LOGGER = Logger.getLogger(InstitucionResource.class.getName()); 
     @Inject
    private InstitucionLogic institucionLogic;
   @POST
   public InstitucionDTO crearInstitucion(InstitucionDTO institucion)throws BusinessLogicException{
       
      LOGGER.log(Level.INFO, "InstitucionResource createInstitucion: input: {0}", institucion);
        InstitucionDTO ninstitucion = new InstitucionDTO(institucionLogic.createInstitucion(institucion.toEntity()));
        LOGGER.log(Level.INFO, "bInstitucionResource createInstitucion: output: {0}", ninstitucion);
        return ninstitucion;
       
   }
   @GET
    public List<InstitucionDetailDTO> getInstituciones() {
        LOGGER.info("InstitucionResource getInstitucioness: input: void");
        List<InstitucionDetailDTO> listaInstituciones = listEntity2DetailDTO(institucionLogic.getInstituciones());
        LOGGER.log(Level.INFO, "BlogResource getBlogs: output: {0}", listaInstituciones);
        return listaInstituciones;
    }
     private List<InstitucionDetailDTO> listEntity2DetailDTO(List<InstitucionEntity> entityList) {
        List<InstitucionDetailDTO> list = new ArrayList<>();
        for (InstitucionEntity entity : entityList) {
            list.add(new InstitucionDetailDTO(entity));
        }
        return list;
    }
     @GET
    @Path("{institucionesId: \\d+}")
    public InstitucionDetailDTO getInstitucion(@PathParam("institucionesId") Long institucionesId) {
        LOGGER.log(Level.INFO, "InstitucionResource getInstitucion: input: {0}", institucionesId);
        InstitucionEntity institucionEntity = institucionLogic.getInstitucion(institucionesId);
        if (institucionEntity == null) {
            throw new WebApplicationException("El recurso /institucion/" + institucionesId + " no existe.", 404);
        }
        InstitucionDetailDTO institucionDetailDTO = new InstitucionDetailDTO(institucionEntity);
        LOGGER.log(Level.INFO, "InstitucionResource getInstitucion: output: {0}", institucionDetailDTO);
        return institucionDetailDTO;
    }
    
    @PUT
    @Path("{institucionesId: \\d+}")
    public InstitucionDetailDTO updateInstitucion(@PathParam("institucionesId") Long institucionesId, InstitucionDetailDTO institucion) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "InstitucionResource updateInstitucion: input: id: {0} , institucion: {1}", new Object[]{institucionesId, institucion});
        institucion.setId(institucionesId);
        
        if (institucionLogic.getInstitucion(institucionesId) == null) {
            throw new WebApplicationException("El recurso /institucion/" + institucionesId+ " no existe.", 404);
        }
       
        
        InstitucionDetailDTO detailDTO = new InstitucionDetailDTO(institucionLogic.updateInstitucion(institucionesId, institucion.toEntity()));
        
        LOGGER.log(Level.INFO, "InstitucionResource updateInstitucion: output: {0}", detailDTO);
        return detailDTO;
    }
    @DELETE
    @Path("{institucionesId: \\d+}")
    public void deleteInstitucion(@PathParam("institucionesId") Long institucionesId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "InstitucionResource deleteInstitucion: input: {0}", institucionesId);
        InstitucionEntity entity = institucionLogic.getInstitucion(institucionesId);
        if (entity == null) {
            throw new WebApplicationException("El recurso /institucion/" + institucionesId + " no existe.", 404);
        }
        
        institucionLogic.deleteInstitucion(institucionesId);
        LOGGER.info("InstitucionResource deleteInstitucion: output: void");
    }

}
