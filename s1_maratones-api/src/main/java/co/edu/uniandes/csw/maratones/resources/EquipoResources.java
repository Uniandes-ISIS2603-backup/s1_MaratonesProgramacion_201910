/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.maratones.resources;

import co.edu.uniandes.csw.maratones.dtos.EquipoDTO;
import co.edu.uniandes.csw.maratones.dtos.EquipoDetailDTO;
import co.edu.uniandes.csw.maratones.ejb.EquipoLogic;
import co.edu.uniandes.csw.maratones.ejb.UsuarioLogic;
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
 * Clase que implementa el recurso "equipos/{id}/usuarios".
 *
 * @author camila
 */
@Path("equipos")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class EquipoResources {
    private static final Logger LOGGER = Logger.getLogger(EquipoResources.class.getName());

    @Inject
    private UsuarioLogic usuarioLogic;
    
    @Inject
    private EquipoLogic equipoLogic;
    
    @POST
    public EquipoDTO createEjercicio(EquipoDTO equipo)
    {
        return equipo;
    }
    
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
        LOGGER.log(Level.INFO, "EquipoResource create: input: {0}", equipo);
        EquipoDTO nuevoDTO = new EquipoDTO(equipoLogic.create(equipo.toEntity()));
        LOGGER.log(Level.INFO, "EquipoResource create: output: {0}", nuevoDTO);
        return nuevoDTO;
    }

    @GET
    @Path("{equiposId: \\d+}")
    public EquipoDetailDTO get(@PathParam("equiposId") Long equiposId) {
        LOGGER.log(Level.INFO, "EquipoResource get: input: {0}", equiposId);
        EquipoEntity entity = equipoLogic.getEquipo(equiposId);
        if (entity == null) {
            throw new WebApplicationException("El recurso /equipos/" + equiposId + " no existe.", 404);
        }
        EquipoDetailDTO elDetailDTO = new EquipoDetailDTO(entity);
        LOGGER.log(Level.INFO, "EquipoResource get: output: {0}", elDetailDTO);
        return elDetailDTO;
    }
    
    /**
     * Actualiza el equipo con el id recibido en la URL con la información que
     * se recibe en el cuerpo de la petición.
     *
     * @param equiposId Identificador del equipo que se desea actualizar. Este
     * debe ser una cadena de dígitos.
     * @param equipo {@link EquipoDTO} El equipo que se desea guardar.
     * @return JSON {@link EquipoDetailDTO} - El equipo guardada.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el premio a
     * actualizar.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando no se puede actualizar el equipo.
     */
    @PUT
    @Path("{equiposId: \\d+}")
    public EquipoDetailDTO updatePrize(@PathParam("equiposId") Long equipoId, EquipoDTO equipo) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "EquipoResource update: input: equiposId: {0} , equipo: {1}", new Object[]{equipoId, equipo});
        equipo.setId(equipoId);
        if (equipoLogic.getEquipo(equipoId) == null) {
            throw new WebApplicationException("El recurso /equipos/" + equipoId + " no existe.", 404);
        }
        EquipoDetailDTO detailDTO = new EquipoDetailDTO(equipoLogic.update(equipoId, equipo.toEntity()));
        LOGGER.log(Level.INFO, "PrizeResource updatePrize: output: {0}", detailDTO);
        return detailDTO;
    }
    /**
     * Borra el equipo con el id asociado recibido en la URL.
     *
     * @param equipoId Identificador del premio que se desea borrar. Este debe
     * ser una cadena de dígitos.
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper}
     * Error de lógica que se genera cuando no se encuentra el equipo.
     */
    @DELETE
    @Path("{equiposId: \\d+}")
    public void delete(@PathParam("equiposId") Long equipoId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "EquipoResource delete: input: {0}", equipoId);
        if (equipoLogic.getEquipo(equipoId) == null) {
            throw new WebApplicationException("El recurso /equipos/" + equipoId + " no existe.", 404);
        }
        equipoLogic.delete(equipoId);
        LOGGER.info("EquipoResource delete: output: void");
    }
    
    
    private List<EquipoDetailDTO> listEntity2DetailDTO(List<EquipoEntity> entityList) {
        List<EquipoDetailDTO> list = new ArrayList<EquipoDetailDTO>();
        for (EquipoEntity entity : entityList) {
            list.add(new EquipoDetailDTO(entity));
        }
        return list;
    }
}
