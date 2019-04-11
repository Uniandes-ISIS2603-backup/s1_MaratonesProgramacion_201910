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
import co.edu.uniandes.csw.maratones.dtos.SubmissionDTO;
import co.edu.uniandes.csw.maratones.ejb.EquipoSubmissionLogic;
import co.edu.uniandes.csw.maratones.ejb.SubmissionLogic;
//import co.edu.uniandes.csw.maratones.ejb.SubmissionEquipoLogic;
import co.edu.uniandes.csw.maratones.entities.SubmissionEntity;
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
public class SubmissionEquipoResource {

    private static final Logger LOGGER = Logger.getLogger(SubmissionEquipoResource.class.getName());

    @Inject
    private EquipoSubmissionLogic submissionEquipoLogic;

    @Inject
    private SubmissionLogic submissionLogic;

    /**
     * Asocia un equipo existente con un submission existente
     *
     * @param equipoId El ID del equipo que se va a asociar
     * @param submissionId El ID del submission al cual se le va a asociar el equipo
     * @return JSON {@link SubmissionDTO} - El equipo asociado.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el equipo.
     */
    @POST
    @Path("{equipoId: \\d+}")
    public SubmissionDTO addParticipante(@PathParam("submissionId") Long submissionId, @PathParam("equipoId") Long equipoId) {
        LOGGER.log(Level.INFO, "SubmissionEquipoResource addParticipante: input: submissionId {0} , equipoId {1}", new Object[]{submissionId, equipoId});
        if (submissionLogic.getSubmission(equipoId) == null) {
            throw new WebApplicationException("El recurso /participantes/" + equipoId + " no existe.", 404);
        }
        SubmissionDTO detailDTO = new SubmissionDTO(submissionEquipoLogic.addEquipo(submissionId, equipoId));
        LOGGER.log(Level.INFO, "SubmissionEquipoResource addParticipante: output: {0}", detailDTO);
        return detailDTO;
    }

    /**
     * Busca y devuelve todos los autores que existen en un submission.
     *
     * @param submissionId El ID del submission del cual se buscan los autores
     * @return JSONArray {@link SubmissionDTO} - Los autores encontrados en el
     * submission. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List<SubmissionDTO> getParticipantes(@PathParam("submissionId") Long equipoId) {
        LOGGER.log(Level.INFO, "SubmissionEquipoResource getParticipantes: input: {0}", equipoId);
        List<SubmissionDTO> lista = participantesListEntity2DTO(submissionEquipoLogic.getEquipos(equipoId));
        LOGGER.log(Level.INFO, "SubmissionEquipoResource getParticipantes: output: {0}", lista);
        return lista;
    }

    /**
     * Busca y devuelve el equipo con el ID recibido en la URL, relativo a un
     * submission.
     *
     * @param equipoId El ID del equipo que se busca
     * @param submissionId El ID del submission del cual se busca el equipo
     * @return {@link SubmissionDTO} - El equipo encontrado en el submission.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper}
     * Error de lógica que se genera cuando no se encuentra el equipo.
     */
    @GET
    @Path("{equipoId: \\d+}")
    public SubmissionDTO getParticipante(@PathParam("submissionId") Long submissionId, @PathParam("equipoId") Long equipoId) {
        LOGGER.log(Level.INFO, "SubmissionEquipoResource getParticipante: input: submissionId {0} , equipoId {1}", new Object[]{submissionId, equipoId});
        if (submissionLogic.getSubmission(submissionId) == null) {
            throw new WebApplicationException("El recurso /participantes/" + submissionId + " no existe.", 404);
        }
        SubmissionDTO detailDTO = new SubmissionDTO(submissionEquipoLogic.getSubmission(submissionId, equipoId));
        LOGGER.log(Level.INFO, "SubmissionEquipoResource getParticipante: output: {0}", detailDTO);
        return detailDTO;
    }

    /**
     * Actualiza la lista de autores de un submission con la lista que se recibe en
     * el cuerpo.
     *
     * @param submissionId El ID del submission al cual se le va a asociar la lista de
     * autores
     * @param participantes JSONArray {@link SubmissionDTO} - La lista de autores
     * que se desea guardar.
     * @return JSONArray {@link SubmissionDTO} - La lista actualizada.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper}
     * Error de lógica que se genera cuando no se encuentra el equipo.
     */
    @PUT
    public List<SubmissionDTO> replaceParticipantes(@PathParam("submissionId") Long submissionId, List<SubmissionDTO> participantes) {
        LOGGER.log(Level.INFO, "SubmissionEquipoResource replaceParticipantes: input: submissionId {0} , participantes {1}", new Object[]{submissionId, participantes});
        for (SubmissionDTO participante : participantes) {
            if (submissionLogic.getSubmission(participante.getId()) == null) {
                throw new WebApplicationException("El recurso /participantes/" + participante.getId() + " no existe.", 404);
            }
        }
        List<SubmissionDTO> lista = participantesListEntity2DTO(submissionEquipoLogic.replaceParticipantes(submissionId, participantesListDTO2Entity(participantes)));
        LOGGER.log(Level.INFO, "SubmissionEquipoResource replaceParticipantes: output:{0}", lista);
        return lista;
    }

    /**
     * Elimina la conexión entre el equipo y el submission recibidos en la URL.
     *
     * @param submissionId El ID del submission al cual se le va a desasociar el equipo
     * @param equipoId El ID del equipo que se desasocia
     * @throws WebApplicationException {@link WebApplicationExceptionMapper}
     * Error de lógica que se genera cuando no se encuentra el equipo.
     */
    @DELETE
    @Path("{equipoId: \\d+}")
    public void remove(@PathParam("submissionId") Long submissionId, @PathParam("equipoId") Long equipoId) {
        LOGGER.log(Level.INFO, "SubmissionEquipoResource remove: input: submissionId {0} , equipoId {1}", new Object[]{submissionId, equipoId});
        if (submissionLogic.getSubmission(submissionId) == null) {
            throw new WebApplicationException("El recurso /participantes/" + equipoId + " no existe.", 404);
        }
        submissionEquipoLogic.removeParticipante(submissionId, equipoId);
        LOGGER.info("SubmissionEquipoResource remove: output: void");
    }

    /**
     * Convierte una lista de SubmissionEntity a una lista de SubmissionDTO.
     *
     * @param entityList Lista de SubmissionEntity a convertir.
     * @return Lista de SubmissionDTO convertida.
     */
    private List<SubmissionDTO> participantesListEntity2DTO(List<SubmissionEntity> entityList) {
        List<SubmissionDTO> list = new ArrayList<>();
        for (SubmissionEntity entity : entityList) {
            list.add(new SubmissionDTO(entity));
        }
        return list;
    }

    /**
     * Convierte una lista de SubmissionDTO a una lista de SubmissionEntity.
     *
     * @param dtos Lista de SubmissionDTO a convertir.
     * @return Lista de SubmissionEntity convertida.
     */
    private List<SubmissionEntity> participantesListDTO2Entity(List<SubmissionDTO> dtos) {
        List<SubmissionEntity> list = new ArrayList<>();
        for (SubmissionDTO dto : dtos) {
            list.add(dto.toEntity());
        }
        return list;
    }
}

