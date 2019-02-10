/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.maratones.resources;

import co.edu.uniandes.csw.maratones.dtos.EquipoDTO;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author camilalonart
 */
@Path("equipos")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class EquipoResources {
    private static final Logger LOGGER = Logger.getLogger(EquipoResources.class.getName());
    
    @POST
    public EquipoDTO createEjercicio(EquipoDTO equipo)
    {
        return equipo;
    }
}
