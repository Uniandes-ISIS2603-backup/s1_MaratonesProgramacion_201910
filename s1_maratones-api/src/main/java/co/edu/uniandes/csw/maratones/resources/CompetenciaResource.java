/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.maratones.resources;

import co.edu.uniandes.csw.maratones.dtos.CompetenciaDTO;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Julian David Mendoza Ruiz
 */
@Path("/competencias")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class CompetenciaResource {
     private static final Logger LOGGER = Logger.getLogger(CompetenciaResource.class.getName());
    
    @POST
    public CompetenciaDTO createCompetencia (CompetenciaDTO competencia)
    {
        return competencia;
    }
}
