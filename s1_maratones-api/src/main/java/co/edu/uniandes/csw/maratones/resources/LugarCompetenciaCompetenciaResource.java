/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.maratones.resources;

import co.edu.uniandes.csw.maratones.dtos.CompetenciaDTO;
import co.edu.uniandes.csw.maratones.dtos.LugarCompetenciaDTO;
import co.edu.uniandes.csw.maratones.ejb.CompetenciaLogic;
import co.edu.uniandes.csw.maratones.ejb.LugarCompetenciaCompetenciaLogic;
import co.edu.uniandes.csw.maratones.ejb.LugarCompetenciaLogic;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

/**
 *
 *
 * Clase que implementa el recurso "Competencia/{id}/lugarCompetencias".
 * @author Julian David Mendoza Ruiz <jd.mendozar@uniandes.edu.co>
 */
@Path("lugarCompetencias/{lugarCompetenciasId: \\d+}/competencia")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class LugarCompetenciaCompetenciaResource {
     private static final Logger LOGGER = Logger.getLogger(LugarCompetenciaCompetenciaResource.class.getName());
     
    @Inject
    private LugarCompetenciaLogic lugarCompetenciaLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    @Inject
    private LugarCompetenciaCompetenciaLogic lugarCompetenciaCompetenciaLogic ;// Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    @Inject
    private CompetenciaLogic competenciaLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.
    
    /**
     * Remplaza la instancia de Competencia asociada a un LugarCompetencia.
     *
     * @param lugarCompetenciasId Identificador del lugarCompetencia que se esta actualizando. Este
     * debe ser una cadena de dígitos.
     * @param competencia La competencia que se será del lugarCompetencia.
     * @return JSON {@link LugarCompetenciaDetailDTO} - El arreglo de lugarCompetencias guardado en la
     * competencia.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la competencia o el
     * lugarCompetencia.
     */
    @PUT
    public LugarCompetenciaDTO replaceCompetencia(@PathParam("lugarCompetenciasId") Long lugarCompetenciasId, CompetenciaDTO competencia) {
        LOGGER.log(Level.INFO, "LugarCompetenciaCompetenciaResource replaceCompetencia: input: lugarCompetenciasId{0} , Competencia:{1}", new Object[]{lugarCompetenciasId, competencia});
        if (lugarCompetenciaLogic.getLugarCompetencia(lugarCompetenciasId) == null) {
            throw new WebApplicationException("El recurso /lugarCompetencias/" + lugarCompetenciasId + " no existe.", 404);
        }
        if (competenciaLogic.getCompetencia(competencia.getId()) == null) {
            throw new WebApplicationException("El recurso /competencias/" + competencia.getId() + " no existe.", 404);
        }
        LugarCompetenciaDTO lugarCompetenciaDTO = new LugarCompetenciaDTO(lugarCompetenciaCompetenciaLogic.replaceCompetencia(lugarCompetenciasId, competencia.getId()));
        LOGGER.log(Level.INFO, "LugarCompetenciaCompetenciaResource replaceCompetencia: output: {0}", lugarCompetenciaDTO);
        return lugarCompetenciaDTO;
    }
}
