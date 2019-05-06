/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.maratones.resources;

import co.edu.uniandes.csw.maratones.dtos.BlogDTO;
import co.edu.uniandes.csw.maratones.dtos.BlogDetailDTO;
import co.edu.uniandes.csw.maratones.ejb.BlogLogic;
import co.edu.uniandes.csw.maratones.entities.BlogEntity;
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
@Path ( "blogs" )
@Produces ( "application/json" )
@Consumes ( "application/json" )
@RequestScoped   
public class BlogResource {
    private static final Logger LOGGER = Logger.getLogger(BlogResource.class.getName()); 
    
    @Inject
    private BlogLogic blogLogic;
    
   @POST
   public BlogDTO crearBlog(BlogDTO blog)throws BusinessLogicException{
       
      LOGGER.log(Level.INFO, "BlogResource createBlog: input: {0}", blog);
        BlogDTO nblog = new BlogDTO(blogLogic.createBlog(blog.toEntity()));
        LOGGER.log(Level.INFO, "blogResource createBlog: output: {0}", nblog);
        return nblog;
       
   }
   @GET
    public List<BlogDetailDTO> getBlogs() {
        LOGGER.info("BlogResource getBlogs: input: void");
        List<BlogDetailDTO> listaBlogs = listEntity2DetailDTO(blogLogic.getBlogs());
        LOGGER.log(Level.INFO, "BlogResource getBlogs: output: {0}", listaBlogs);
        return listaBlogs;
    }
     private List<BlogDetailDTO> listEntity2DetailDTO(List<BlogEntity> entityList) {
        List<BlogDetailDTO> list = new ArrayList<>();
        for (BlogEntity entity : entityList) {
            list.add(new BlogDetailDTO(entity));
        }
        return list;
    }
     @GET
    @Path("{blogsId: \\d+}")
    public BlogDetailDTO getBlog(@PathParam("blogsId") Long blogsId) {
        LOGGER.log(Level.INFO, "BlogResource getblog: input: {0}", blogsId);
        BlogEntity blogEntity = blogLogic.getBlog(blogsId);
        if (blogEntity == null) {
            throw new WebApplicationException("El recurso /blog/" + blogsId + " no existe.", 404);
        }
        BlogDetailDTO blogDetailDTO = new BlogDetailDTO(blogEntity);
        LOGGER.log(Level.INFO, "BlogResource getBlog: output: {0}", blogDetailDTO);
        return blogDetailDTO;
    }
    
    @PUT
    @Path("{blogsId: \\d+}")
    public BlogDetailDTO updateBlog(@PathParam("blogsId") Long blogsId, BlogDetailDTO blog) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "BlogResource updateBlog: input: id: {0} , blog: {1}", new Object[]{blogsId, blog});
        blog.setId(blogsId);
        if (blogLogic.getBlog(blogsId) == null) {
            throw new WebApplicationException("El recurso /blogs/" + blogsId + " no existe.", 404);
        }
        BlogDetailDTO detailDTO = new BlogDetailDTO(blogLogic.updateBlog(blogsId, blog.toEntity()));
        LOGGER.log(Level.INFO, "BlogResource updateBlog: output: {0}", detailDTO);
        return detailDTO;
    }
    @DELETE
    @Path("{blogsId: \\d+}")
    public void deleteBlog(@PathParam("blogsId") Long blogsId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "BlogResource deleteBlog: input: {0}", blogsId);
        BlogEntity entity = blogLogic.getBlog(blogsId);
        if (entity == null) {
            throw new WebApplicationException("El recurso /blog/" + blogsId + " no existe.", 404);
        }
        
        blogLogic.deleteBlog(blogsId);
        LOGGER.info("BlogResource deleteBlog: output: void");
    }
    
     @Path("{blogsId: \\d+}/publicaciones")
    public Class<PublicacionResource> getPublicacionResource(@PathParam("blogsId") Long blogsId   ) {
        if (blogLogic.getBlog(blogsId) == null) {
            throw new WebApplicationException("El recurso /blogs/" + blogsId + "/públicaciones no existe.", 404);
        }
        return PublicacionResource.class;
    }


}

