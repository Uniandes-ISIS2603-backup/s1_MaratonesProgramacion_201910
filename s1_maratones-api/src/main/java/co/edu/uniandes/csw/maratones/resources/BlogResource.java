/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.maratones.resources;

import co.edu.uniandes.csw.maratones.dtos.BlogDTO;
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
@Path ( "blogs" )
@Produces ( "application/json" )
@Consumes ( "application/json" )
@RequestScoped   
public class BlogResource {
     private static final Logger LOGGER = Logger.getLogger(BlogResource.class.getName()); 
     
   @POST
   public BlogDTO crearBlog(BlogDTO blog){
       return blog;
   }
}
