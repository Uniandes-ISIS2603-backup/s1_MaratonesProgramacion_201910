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

import co.edu.uniandes.csw.maratones.dtos.EquipoDetailDTO;
import co.edu.uniandes.csw.maratones.dtos.UsuarioDetailDTO;
import co.edu.uniandes.csw.maratones.ejb.UsuarioLogic;
import co.edu.uniandes.csw.maratones.ejb.UsuarioEquipoLogic;
import co.edu.uniandes.csw.maratones.entities.UsuarioEntity;
import co.edu.uniandes.csw.maratones.mappers.WebApplicationExceptionMapper;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.WebApplicationException;

/**
 * Clase que implementa el recurso "equipos/{id}/participantes".
 *
 * @author camila
 * @version 1.0
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UsuarioEquipoResource {

    private static final Logger LOGGER = Logger.getLogger(UsuarioEquipoResource.class.getName());

    @Inject
    private UsuarioEquipoLogic usuarioEquipoLogic;

    @Inject
    private UsuarioLogic usuarioLogic;

    /**
     * Asocia un equipo existente con un usuario existente
     *
     * @param equipoId El ID del equipo que se va a asociar
     * @param usuarioId El ID del usuario al cual se le va a asociar el equipo
     * @return JSON {@link UsuarioDetailDTO} - El equipo asociado.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el equipo.
     */
    @POST
    @Path("{equipoId: \\d+}")
    public UsuarioDetailDTO addParticipante(@PathParam("usuarioId") Long usuarioId, @PathParam("equipoId") Long equipoId) {
        LOGGER.log(Level.INFO, "UsuarioEquipoResource addParticipante: input: usuarioId {0} , equipoId {1}", new Object[]{usuarioId, equipoId});
        if (usuarioLogic.getUsuarioPorId(equipoId) == null) {
            throw new WebApplicationException("El recurso /participantes/" + equipoId + " no existe.", 404);
        }
        UsuarioDetailDTO detailDTO = new UsuarioDetailDTO(usuarioEquipoLogic.addUsuario(usuarioId, equipoId));
        LOGGER.log(Level.INFO, "UsuarioEquipoResource addParticipante: output: {0}", detailDTO);
        return detailDTO;
    }

    /**
     * Busca y devuelve todos los autores que existen en un usuario.
     *
     * @param usuarioId El ID del usuario del cual se buscan los autores
     * @return JSONArray {@link UsuarioDetailDTO} - Los autores encontrados en el
     * usuario. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List<UsuarioDetailDTO> getParticipantes(@PathParam("usuarioId") Long equipoId) {
        LOGGER.log(Level.INFO, "UsuarioEquipoResource getParticipantes: input: {0}", equipoId);
        List<UsuarioDetailDTO> lista = participantesListEntity2DTO(usuarioEquipoLogic.getUsuarios(equipoId));
        LOGGER.log(Level.INFO, "UsuarioEquipoResource getParticipantes: output: {0}", lista);
        return lista;
    }

    /**
     * Busca y devuelve el equipo con el ID recibido en la URL, relativo a un
     * usuario.
     *
     * @param equipoId El ID del equipo que se busca
     * @param usuarioId El ID del usuario del cual se busca el equipo
     * @return {@link UsuarioDetailDTO} - El equipo encontrado en el usuario.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper}
     * Error de lógica que se genera cuando no se encuentra el equipo.
     */
    @GET
    @Path("{equipoId: \\d+}")
    public UsuarioDetailDTO getParticipante(@PathParam("usuarioId") Long usuarioId, @PathParam("equipoId") Long equipoId) {
        LOGGER.log(Level.INFO, "UsuarioEquipoResource getParticipante: input: usuarioId {0} , equipoId {1}", new Object[]{usuarioId, equipoId});
        if (usuarioLogic.getUsuarioPorId(usuarioId) == null) {
            throw new WebApplicationException("El recurso /participantes/" + usuarioId + " no existe.", 404);
        }
        UsuarioDetailDTO detailDTO = new UsuarioDetailDTO(usuarioEquipoLogic.getParticipante(usuarioId, equipoId));
        LOGGER.log(Level.INFO, "UsuarioEquipoResource getParticipante: output: {0}", detailDTO);
        return detailDTO;
    }

    /**
     * Actualiza la lista de autores de un usuario con la lista que se recibe en
     * el cuerpo.
     *
     * @param usuarioId El ID del usuario al cual se le va a asociar la lista de
     * autores
     * @param participantes JSONArray {@link UsuarioDetailDTO} - La lista de autores
     * que se desea guardar.
     * @return JSONArray {@link UsuarioDetailDTO} - La lista actualizada.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper}
     * Error de lógica que se genera cuando no se encuentra el equipo.
     */
    @PUT
    public List<UsuarioDetailDTO> replaceParticipantes(@PathParam("usuarioId") Long usuarioId, List<UsuarioDetailDTO> participantes) {
        LOGGER.log(Level.INFO, "UsuarioEquipoResource replaceParticipantes: input: usuarioId {0} , participantes {1}", new Object[]{usuarioId, participantes});
        for (UsuarioDetailDTO participante : participantes) {
            if (usuarioLogic.getUsuarioPorId(participante.getId()) == null) {
                throw new WebApplicationException("El recurso /participantes/" + participante.getId() + " no existe.", 404);
            }
        }
        List<UsuarioDetailDTO> lista = participantesListEntity2DTO(usuarioEquipoLogic.replaceParticipantes(usuarioId, participantesListDTO2Entity(participantes)));
        LOGGER.log(Level.INFO, "UsuarioEquipoResource replaceParticipantes: output:{0}", lista);
        return lista;
    }

    /**
     * Elimina la conexión entre el equipo y el usuario recibidos en la URL.
     *
     * @param usuarioId El ID del usuario al cual se le va a desasociar el equipo
     * @param equipoId El ID del equipo que se desasocia
     * @throws WebApplicationException {@link WebApplicationExceptionMapper}
     * Error de lógica que se genera cuando no se encuentra el equipo.
     */
    @DELETE
    @Path("{equipoId: \\d+}")
    public void remove(@PathParam("usuarioId") Long usuarioId, @PathParam("equipoId") Long equipoId) {
        LOGGER.log(Level.INFO, "UsuarioEquipoResource remove: input: usuarioId {0} , equipoId {1}", new Object[]{usuarioId, equipoId});
        if (usuarioLogic.getUsuarioPorId(usuarioId) == null) {
            throw new WebApplicationException("El recurso /participantes/" + equipoId + " no existe.", 404);
        }
        usuarioEquipoLogic.removeParticipante(usuarioId, equipoId);
        LOGGER.info("UsuarioEquipoResource remove: output: void");
    }

    /**
     * Convierte una lista de UsuarioEntity a una lista de UsuarioDetailDTO.
     *
     * @param entityList Lista de UsuarioEntity a convertir.
     * @return Lista de UsuarioDetailDTO convertida.
     */
    private List<UsuarioDetailDTO> participantesListEntity2DTO(List<UsuarioEntity> entityList) {
        List<UsuarioDetailDTO> list = new ArrayList<>();
        for (UsuarioEntity entity : entityList) {
            list.add(new UsuarioDetailDTO(entity));
        }
        return list;
    }

    /**
     * Convierte una lista de UsuarioDetailDTO a una lista de UsuarioEntity.
     *
     * @param dtos Lista de UsuarioDetailDTO a convertir.
     * @return Lista de UsuarioEntity convertida.
     */
    private List<UsuarioEntity> participantesListDTO2Entity(List<UsuarioDetailDTO> dtos) {
        List<UsuarioEntity> list = new ArrayList<>();
        for (UsuarioDetailDTO dto : dtos) {
            list.add(dto.toEntity());
        }
        return list;
    }
}

