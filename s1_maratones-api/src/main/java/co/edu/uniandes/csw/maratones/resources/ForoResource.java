/*
MIT License

Copyright (c) 2017 Universidad de los Andes - ISIS2603

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 */
package co.edu.uniandes.csw.maratones.resources;

import co.edu.uniandes.csw.maratones.dtos.ForoDTO;
import co.edu.uniandes.csw.maratones.dtos.ForoDetailDTO;
import co.edu.uniandes.csw.maratones.ejb.ForoLogic;
import co.edu.uniandes.csw.maratones.entities.ForoEntity;
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
 * @author Juan Felipe Peña
 */

@Path("foros")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class ForoResource {
    
     private static final Logger LOGGER = Logger.getLogger(ForoResource.class.getName());
     
     @Inject
    private ForoLogic foroLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.
     
     @POST
    public ForoDTO createForo(ForoDTO foro) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "ForoResource createForo: input: {0}", foro.toString());
         // Convierte el DTO (json) en un objeto Entity para ser manejado por la lógica.
        ForoEntity foroEntity = foro.toEntity();
        // Invoca la lógica para crear la editorial nueva
        ForoEntity nuevoForoEntity = foroLogic.createForo(foroEntity);
        // Como debe retornar un DTO (json) se invoca el constructor del DTO con argumento el entity nuevo
        ForoDTO nuevoForoDTO = new ForoDTO(nuevoForoEntity);
        LOGGER.log(Level.INFO, "EditorialResource createEditorial: output: {0}", nuevoForoDTO.toString());
        return foro;
    }
    
    @GET
    public List<ForoDetailDTO> getForos() {
        LOGGER.info("ForoResource getForos: input: void");
        List<ForoDetailDTO> listaForos = listEntity2DetailDTO(foroLogic.getForos());
        LOGGER.log(Level.INFO, "ForoResource getForo: output: {0}", listaForos.toString());
        return listaForos;
    }
    
    @DELETE
    @Path("{foroId: \\d+}")
    public void deleteForo(@PathParam("foroId") Long forosId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "ForoResource deleteForo: input: {0}", forosId);
        if (foroLogic.getForo(forosId) == null) {
            throw new WebApplicationException("El recurso /foros/" + forosId + " no existe.", 404);
        }
        foroLogic.deleteForo(forosId);
        LOGGER.info("EditorialResource deleteForo: output: void");
    }
    
    @PUT
    @Path("{forosId: \\d+}")
    public ForoDetailDTO updateEditorial(@PathParam("forosId") Long forosId, ForoDTO foro) throws WebApplicationException {
        LOGGER.log(Level.INFO, "ForoResource updateForo: input: id:{0} , foro: {1}", new Object[]{forosId, foro.toString()});
        foro.setId(forosId);
        if (foroLogic.getForo(forosId) == null) {
            throw new WebApplicationException("El recurso /foros/" + forosId + " no existe.", 404);
        }
        ForoDetailDTO detailDTO = new ForoDetailDTO(foroLogic.updateForo(forosId, foro.toEntity()));
        LOGGER.log(Level.INFO, "EditorialResource updateEditorial: output: {0}", detailDTO.toString());
        return detailDTO;
    }
    /**
     * Convierte una lista de entidades a DTO.
     *
     * Este método convierte una lista de objetos EditorialEntity a una lista de
     * objetos EditorialDetailDTO (json)
     *
     * @param entityList corresponde a la lista de editoriales de tipo Entity
     * que vamos a convertir a DTO.
     * @return la lista de editoriales en forma DTO (json)
     */
    private List<ForoDetailDTO> listEntity2DetailDTO(List<ForoEntity> entityList) {
        List<ForoDetailDTO> list = new ArrayList<>();
        for (ForoEntity entity : entityList) {
            list.add(new ForoDetailDTO(entity));
        }
        return list;
    }
}
