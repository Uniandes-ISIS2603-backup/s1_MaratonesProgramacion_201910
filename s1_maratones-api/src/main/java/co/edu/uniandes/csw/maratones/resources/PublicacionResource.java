/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.maratones.resources;

import co.edu.uniandes.csw.maratones.dtos.PublicacionDTO;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

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
     
   @POST
   public PublicacionDTO crearPublicacion(PublicacionDTO publicacion){
       return publicacion;
   }
}
