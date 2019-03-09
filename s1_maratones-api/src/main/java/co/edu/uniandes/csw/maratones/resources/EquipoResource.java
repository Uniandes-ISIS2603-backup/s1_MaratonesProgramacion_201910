/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.maratones.resources;

import co.edu.uniandes.csw.maratones.dtos.EquipoDTO;
import co.edu.uniandes.csw.maratones.dtos.EquipoDetailDTO;
import co.edu.uniandes.csw.maratones.ejb.EquipoLogic;
import co.edu.uniandes.csw.maratones.entities.EquipoEntity;
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
 * @author camilalonart
 */
@Path("equipos")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class EquipoResource {
    private static final Logger LOGGER = Logger.getLogger(EquipoResource.class.getName());
    @Inject
    EquipoLogic equipoLogic;
    /*        
    @POST
    public EquipoDTO createEjercicio(EquipoDTO equipo)
    {
        return equipo;
    }
    */
    /**
     * Crea un nuevo equipo con la informacion que se recibe en el cuerpo de la
     * petición y se regresa un objeto identico con un id auto-generado por la
     * base de datos.
     *
     * @param equipo {@link EquipoDTO} - EL equipo que se desea guardar.
     * @return JSON {@link EquipoDTO} - El equipo guardado con el atributo id
     * autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando ya existe el equipo 
     */
    @POST
    public EquipoDTO create(EquipoDTO equipo) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "BookResource createBook: input: {0}", equipo);
        EquipoDTO equipoNew = new EquipoDTO(equipoLogic.create(equipo.toEntity()));
        LOGGER.log(Level.INFO, "BookResource createBook: output: {0}", equipoNew);
        return equipoNew;
    }
    
    /**
     * Busca y devuelve todos los libros que existen en la aplicacion.
     *
     * @return JSONArray {@link BookDetailDTO} - Los libros encontrados en la
     * aplicación. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List<EquipoDetailDTO> getTodosEquipos() {
        LOGGER.info("EquipoResource getTodosEquipos: input: void");
        List<EquipoDetailDTO> listaBooks = listEntity2DetailDTO(equipoLogic.gets());
        LOGGER.log(Level.INFO, "BookResource getBooks: output: {0}", listaBooks);
        return listaBooks;
    }
    
    /**
     * Busca el equipo con el id asociado recibido en la URL y lo devuelve.
     *
     * @param equipoId Identificador del equipo que se esta buscando. Este debe
     * ser una cadena de dígitos.
     * @return JSON {@link EquipoDetailDTO} - El equipo buscado
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el equipo.
     */
    @GET
    @Path("{equipoId: \\d+}")
    public EquipoDetailDTO getEquipo(@PathParam("equipoId") Long equipoId) {
        LOGGER.log(Level.INFO, "EquipoResource getEquipo: input: {0}", equipoId);
        EquipoEntity equipoEntity = equipoLogic.getEquipo(equipoId);
        if (equipoEntity == null) {
            throw new WebApplicationException("El recurso /equipos/" + equipoId + " no existe.", 404);
        }
        EquipoDetailDTO equipoDetailDTO = new EquipoDetailDTO(equipoEntity);
        LOGGER.log(Level.INFO, "EquipoResource getEquipo: output: {0}", equipoDetailDTO);
        return equipoDetailDTO;
    }

    /**
     * Actualiza el equipo con el id recibido en la URL con la información que se
     * recibe en el cuerpo de la petición.
     *
     * @param equipoId Identificador del equipo que se desea actualizar. Este debe
     * ser una cadena de dígitos.
     * @param book {@link BookDTO} El equipo que se desea guardar.
     * @return JSON {@link EquipoDetailDTO} - El equipo guardada.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el equipo a
     * actualizar.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando no se puede actualizar el equipo.
     */
    @PUT
    @Path("{equipoId: \\d+}")
    public EquipoDetailDTO updateEquipo(@PathParam("equipoId") Long equipoId, EquipoDetailDTO book) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "EquipoResource updateEquipo: input: id: {0} , book: {1}", new Object[]{equipoId, book});
        book.setId(equipoId);
        if (equipoLogic.getEquipo(equipoId) == null) {
            throw new WebApplicationException("El recurso /equipos/" + equipoId + " no existe.", 404);
        }
        EquipoDetailDTO detailDTO = new EquipoDetailDTO(equipoLogic.update(equipoId, book.toEntity()));
        LOGGER.log(Level.INFO, "EquipoResource updateEquipo: output: {0}", detailDTO);
        return detailDTO;
    }

    /**
     * Borra el equipo con el id asociado recibido en la URL.
     *
     * @param equipoId Identificador del equipo que se desea borrar. Este debe ser
     * una cadena de dígitos.
     * @throws co.edu.uniandes.csw.maratones.exceptions.BusinessLogicException
     * cuando el equipo tiene autores asociados.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el equipo.
     */
    @DELETE
    @Path("{equipoId: \\d+}")
    public void deleteEquipo(@PathParam("equipoId") Long equipoId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "EquipoResource deleteEquipo: input: {0}", equipoId);
        EquipoEntity entity = equipoLogic.getEquipo(equipoId);
        if (entity == null) {
            throw new WebApplicationException("El recurso /equipos/" + equipoId + " no existe.", 404);
        }
        equipoLogic.delete(equipoId);
        LOGGER.info("EquipoResource deleteEquipo: output: void");
    }

    /**
     * Conexión con el servicio de participantes para un equipo. {@link UsuarioResource}
     *
     * Este método conecta la ruta de /equipos con las rutas de /participantes que
     * dependen del equipo, es una redirección al servicio que maneja el segmento
     * de la URL que se encarga de las participantes.
     *
     * @param equipoId El ID del equipo con respecto al cual se accede al
     * servicio.
     * @return El servicio de Reseñas para ese equipo en paricular.\
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el equipo.
     */
    /*
    @Path("{equipoId: \\d+}/participantes")
    public Class<UsuarioResource> getReviewResource(@PathParam("equipoId") Long equipoId) {
        if (equipoLogic.getEquipo(equipoId) == null) {
            throw new WebApplicationException("El recurso /equipos/" + equipoId + "/participantes no existe.", 404);
        }
        return UsuarioResource.class;
    }
*/
    /**
     * Conexión con el servicio de autores para un equipo.
     * {@link UsuarioEquipoResource}
     *
     * Este método conecta la ruta de /equipos con las rutas de /participantes que
     * dependen del equipo, es una redirección al servicio que maneja el segmento
     * de la URL que se encarga de las participantes.
     *
     * @param equipoId El ID del equipo con respecto al cual se accede al
     * servicio.
     * @return El servicio de autores para ese equipo en paricular.\
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el equipo.
     */
    /*
    @Path("{equipoId: \\d+}/participantes")
    public Class<UsuarioEquipoResource> getUsuarioEquipoResource(@PathParam("equipoId") Long equipoId) {
        if (equipoLogic.getEquipo(equipoId) == null) {
            throw new WebApplicationException("El recurso /equipos/" + equipoId + " no existe.", 404);
        }
        return UsuarioEquipoResource.class;
    }
*/
    /**
     * Convierte una lista de entidades a DTO.
     *
     * Este método convierte una lista de objetos EquipoEntity a una lista de
     * objetos EquipoDetailDTO (json)
     *
     * @param entityList corresponde a la lista de libros de tipo Entity que
     * vamos a convertir a DTO.
     * @return la lista de libros en forma DTO (json)
     */
    private List<EquipoDetailDTO> listEntity2DetailDTO(List<EquipoEntity> entityList) {
        List<EquipoDetailDTO> list = new ArrayList<>();
        for(EquipoEntity entity : entityList) {
            list.add(new EquipoDetailDTO(entity));
        }
        return list;
    }
}
