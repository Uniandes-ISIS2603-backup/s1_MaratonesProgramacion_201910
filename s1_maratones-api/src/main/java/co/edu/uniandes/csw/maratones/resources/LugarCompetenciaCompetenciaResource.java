/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.maratones.resources;

import co.edu.uniandes.csw.maratones.ejb.CompetenciaLogic;
import co.edu.uniandes.csw.maratones.ejb.LugarCompetenciaLogic;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
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
    private LugarCompetenciaLogic lugarCompetenciaCompetenciaLogic ;// Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    @Inject
    private CompetenciaLogic competenciaLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.
}
