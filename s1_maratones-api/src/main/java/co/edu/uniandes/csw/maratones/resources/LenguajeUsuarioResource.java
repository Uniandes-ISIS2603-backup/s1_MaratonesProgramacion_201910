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

import co.edu.uniandes.csw.maratones.dtos.LenguajeDTO;
import co.edu.uniandes.csw.maratones.dtos.UsuarioDetailDTO;
import co.edu.uniandes.csw.maratones.ejb.LenguajeLogic;
import co.edu.uniandes.csw.maratones.ejb.UsuarioLenguajesLogic;
import co.edu.uniandes.csw.maratones.entities.LenguajeEntity;
import co.edu.uniandes.csw.maratones.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.maratones.mappers.WebApplicationExceptionMapper;
import java.util.logging.Level;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.logging.Logger;
import javax.ws.rs.DELETE;
import javax.ws.rs.PUT;
import javax.ws.rs.WebApplicationException;

/**
 * Clase que implementa el recurso "prize/{id}/lenguaje".
 *
 * @lenguaje ISIS2603
 * @version 1.0
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class LenguajeUsuarioResource {
    
    private static final Logger LOGGER = Logger.getLogger(LenguajeUsuarioResource.class.getName());

    @Inject
   private UsuarioLenguajesLogic lenguajeUsuarioLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    @Inject
    private LenguajeLogic lenguajeLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    /**
     * Guarda un lenguaje dentro de un usuario con la informacion que recibe el la
     * URL.
     *
     * @param usuarioid Identificador de el usuario que se esta actualizando. Este
     * debe ser una cadena de dígitos.
     * @param lenguaje
     * @return JSON {@link LenguajeDTO} - El lenguaje guardado en el usuario.
     * @throws co.edu.uniandes.csw.maratones.exceptions.BusinessLogicException
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el lenguaje.
     */
    @POST
    public UsuarioDetailDTO addLenguaje(@PathParam("usuarioid") Long usuarioid, LenguajeDTO lenguaje) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "LenguajeUsuarioResource addLenguaje: input: usuarioID: {0} , lenguajeId: {1}", new Object[]{usuarioid, lenguaje.getId()});
        lenguajeLogic.createLenguaje(lenguaje.toEntity());
        UsuarioDetailDTO authorDTO = new UsuarioDetailDTO(lenguajeUsuarioLogic.addLenguaje(usuarioid, lenguaje.toEntity())); 
        LOGGER.log(Level.INFO, "LenguajeUsuarioResource addLenguaje: output: {0}", authorDTO);
        return authorDTO;
    }

    /**
     * Busca el lenguaje dentro de el usuario con id asociado.
     *
     * @param usuarioid Identificador de el usuario que se esta buscando. Este
     * debe ser una cadena de dígitos.
     * @param lenguajeid
     * @return JSON {@link AuthorDetailDTO} - El lenguaje buscado
     * @throws co.edu.uniandes.csw.maratones.exceptions.BusinessLogicException
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando el usuario no tiene lenguaje.
     */
    @GET
    @Path("{lenguajeid: \\d+}")
    public LenguajeDTO getLenguaje(@PathParam("usuarioid") Long usuarioid, @PathParam("lenguajeid") Long lenguajeid) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "LenguajeUsuarioResource getLenguaje: input: {0}", usuarioid);
        LenguajeEntity lenguajeEntity = lenguajeUsuarioLogic.getLenguaje(usuarioid, lenguajeid);
        if (lenguajeEntity == null) {
            throw new WebApplicationException("El recurso /usuarios/" + usuarioid + "/lenguaje/" + lenguajeid + "no existe.", 404);
        }
        LenguajeDTO lenguajeDTO = new LenguajeDTO(lenguajeEntity);
        LOGGER.log(Level.INFO, "LenguajeUsuarioResource getLenguaje: output: {0}", lenguajeDTO);
        return lenguajeDTO;
    }

    

    /**
     * Elimina la conexión entre el lenguaje y el usuario recibido en la URL.
     *
     * @param usuarioid El ID del usuario al cual se le va a desasociar el lenguaje
     * @param lenguajeId
     * @throws co.edu.uniandes.csw.maratones.exceptions.BusinessLogicException
     * Error de lógica que se genera cuando el usuario no tiene lenguaje.
     */
    @DELETE
    @Path("{lenguajeId: \\d+}")
    public void removeLenguaje(@PathParam("usuarioid") Long usuarioid, @PathParam("lenguajeId") Long lenguajeId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "LenguajeUsuarioResource removeLenguaje: input: {0}", usuarioid);
        lenguajeUsuarioLogic.removeLenguaje(usuarioid, lenguajeId);
        lenguajeLogic.deleteLenguaje(lenguajeId);
        LOGGER.info("LenguajeUsuarioResource removeLenguaje: output: void");
    }
    
    
    @PUT
    @Path("{lenguajesId: \\+d}")
    public LenguajeDTO updateLenguaje(@PathParam("usuarioid") Long usuarioid, @PathParam("lenguajesId") Long lenguajesId, LenguajeDTO lenguajeNuevo) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "LenguajeUsuarioResource updateLenguaje: input: usuarioid {0} , lenguajesId {1}", new Object[]{usuarioid, lenguajesId});
        LenguajeEntity lenguajeEntity = lenguajeUsuarioLogic.getLenguaje(usuarioid, lenguajesId);
        if (lenguajeEntity == null) {
            throw new WebApplicationException("El recurso /usuarios/" + usuarioid + "/lenguajes/" + lenguajesId + "no existe.", 404);
        }
        LenguajeDTO detailDTO = new LenguajeDTO(lenguajeLogic.updateLenguaje(lenguajeNuevo.toEntity()));
        LOGGER.log(Level.INFO, "LenguajeUsuarioResource updateLenguaje: output: {0}", lenguajesId);
        return detailDTO;
    }
    
    
}