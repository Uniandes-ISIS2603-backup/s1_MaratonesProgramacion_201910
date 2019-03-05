/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.maratones.resources;

import co.edu.uniandes.csw.maratones.dtos.EjercicioDTO;
import co.edu.uniandes.csw.maratones.ejb.EjercicioLogic;
import co.edu.uniandes.csw.maratones.exceptions.BusinessLogicException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author estudiante
 */

@Path("ejercicios")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class EjercicioResource {
    
    private static final Logger LOGGER = Logger.getLogger(EjercicioResource.class.getName());
    
    @Inject
    EjercicioLogic ejercicioLogic;
    
    @POST
    public EjercicioDTO createEjercicio(EjercicioDTO ejercicio) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "EjercicioResource createEjercicio: input: {0}", ejercicio);
        EjercicioDTO nuevoEjercicio = new EjercicioDTO(ejercicioLogic.createEjercicio(ejercicio.toEntity()));
        LOGGER.log(Level.INFO, "EjercicioResource createEjercicio: output: {0}", nuevoEjercicio);
        
        return ejercicio;
    }
    
    
    
}
