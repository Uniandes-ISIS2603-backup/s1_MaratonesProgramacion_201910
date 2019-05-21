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
 * Clase que representa el recurso de un ejercicio, este contiene los servicios HTTP que ofrece la capa del front
 * @author Angel Rodriguez aa.rodriguezv
 */
@Path("ejercicios")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class EjercicioResource {
    
    
    private static final Logger LOGGER = Logger.getLogger(EjercicioResource.class.getName());
    
    @Inject
    EjercicioLogic ejercicioLogic;
    
    /**
     * Crea un nuevo ejercicio con la informacion que se recibe en el cuerpo de la
     * petición y se regresa un objeto identico con un id auto-generado por la
     * base de datos.
     *
     * @param ejercicio {@link EjercicioDTO} - EL ejercicio que se desea guardar.
     * @return JSON {@link EjercicioDTO} - El ejercicio guardado con el atributo id generado 
     * @throws BusinessLogicException retorna excepcion en caso de que se incumpla una de las reglas de negocio definidas en la logica de ejercicio
     */
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
    
    /**
     * Busca y devuelve todos los ejercicios que existen en la aplicacion.
     *
     * @return JSONArray {@link EjercicioDetailDTO} - Los ejercicios encontrados en la
     * aplicación. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List<EjercicioDetailDTO> getEjercicios() {
        LOGGER.info("EjercicioResource getEjercicios: input: void");
        List<EjercicioDetailDTO> listaBooks = listEntity2DetailDTO(ejercicioLogic.getEjercicios());
        LOGGER.log(Level.INFO, "EjercicioResource getEjercicios: output: {0}", listaBooks);
        return listaBooks;
    }
    
    
     /**
     * Busca el ejercicio con el id asociado recibido en la URL y lo devuelve.
     *
     * @param ejerciciosId
     * @return JSON {@link EjercicioDetailDTO} - El ejercicio buscado
     * @throws BusinessLogicException retorna excepcion en caso de que alguna regla de negocio establecida en la logica haya sido violada
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} - retorna excepcion en caso de que el ejercicio que se solicito buscar no exista 
     * Error de lógica que se genera cuando no se encuentra el ejercicio.
     */
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
    
    /**
     * Actualiza el ejercicio con el id recibido en la URL con la información que se
     * recibe en el cuerpo de la petición.
     *
     * @param ejerciciosId el id del ejercicio que se quiere modificar
     * @param ejercicio el ejercicio nuevo que ha sido recibido por el front para ser modificado 
     * @return JSON {@link EjercicioDetailDTO} - El ejercicio guardado.
     * @throws BusinessLogicException retorna excepcion en caso de que sea violada alguna de las reglas de negocio establecidas en la logica 
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} - 
     * Error de lógica que se genera cuando no se encuentra el ejercicio
     * actualizar.
     */
    @PUT
    @Path("{ejerciciosId: \\d+}")
    public EjercicioDetailDTO updateEjercicio(@PathParam("ejerciciosId") Long ejerciciosId, EjercicioDTO ejercicio) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "EjercicioResource updateEjercicio: input: id: {0} , ejercicio: {1}", new Object[]{ejerciciosId, ejercicio});
        
        if (ejercicioLogic.getEjercicio(ejerciciosId) == null) {
            throw new WebApplicationException("El recurso /ejercicios/" + ejerciciosId + " no existe.", 404);
        }
        ejercicio.setId(ejerciciosId);
        EjercicioDetailDTO detailDTO = new EjercicioDetailDTO(ejercicioLogic.updateEjercicio(ejerciciosId, ejercicio.toEntity()));
        LOGGER.log(Level.INFO, "EjercicioResource updateEjercicio: output: {0}", detailDTO);
        return detailDTO;
    }
    
    /**
     * Borra el ejercicio con el id asociado recibido en la URL.
     *
     * @param ejerciciosId el id del ejercicio que se quiere borrar
     * @throws BusinessLogicException retorna excepcion cuando se viola alguna de las reglas de negocio establecidas en la logica
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} 
     * Error de lógica que se genera cuando no se encuentra el ejercicio a borrar.
     */
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
     * Metodo que permite cambiar una lista de entidades a un detalle de Ejercicio
     * @param entityList la lista de entidades de ejercicios
     * @return la lista de detalles de ejercicio
     */
    private List<EjercicioDetailDTO> listEntity2DetailDTO(List<EjercicioEntity> entityList) {
        List<EjercicioDetailDTO> list = new ArrayList<>();
        for (EjercicioEntity entity : entityList) {
            list.add(new EjercicioDetailDTO(entity));
        }
        return list;
    }
    
     /**
     * Conexión con el servicio de libros para un autor.
     * {@link AuthorBooksResource}
     *
     * Este método conecta la ruta de /authors con las rutas de /books que
     * dependen del autor, es una redirección al servicio que maneja el segmento
     * de la URL que se encarga de los libros.
     *
     * @param authorsId El ID del autor con respecto al cual se accede al
     * servicio.
     * @return El servicio de Libros para ese autor en paricular.
     */
    @Path("{ejerciciosId: \\d+}/submissions")
    public Class<EjercicioSubmissionsResource> getEjercicioSubmissionsResource(@PathParam("ejerciciosId") Long ejerciciosId) {
        if (ejercicioLogic.getEjercicio(ejerciciosId) == null) {
            throw new WebApplicationException("El recurso /ejercicios/" + ejerciciosId + " no existe.", 404);
        }
        return EjercicioSubmissionsResource.class;
    }
    
}
