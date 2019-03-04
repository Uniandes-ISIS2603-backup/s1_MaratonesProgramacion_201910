/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.maratones.resources;

import co.edu.uniandes.csw.maratones.dtos.LugarCompetenciaDTO;
import co.edu.uniandes.csw.maratones.dtos.UsuarioDTO;
import co.edu.uniandes.csw.maratones.ejb.LugarCompetenciaLogic;
import co.edu.uniandes.csw.maratones.exceptions.BusinessLogicException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Julian David Mendoza Ruiz
 */
@Path("/Lugarcompetencias")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class LugarCompetenciaResource {
    private static final Logger LOGGER = Logger.getLogger(LugarCompetenciaResource.class.getName());
    
    @Inject
    private LugarCompetenciaLogic lugarCompetenciaLogic;
    
    @POST
    public LugarCompetenciaDTO createLugarCompetencia (LugarCompetenciaDTO lugarCompetencia)
    {
        return lugarCompetencia;
    }
    
    @POST
    public LugarCompetenciaDTO create(LugarCompetenciaDTO ubicacion) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "UsuarioResource create: input: {0}", ubicacion);
        LugarCompetenciaDTO nuevoDTO = new LugarCompetenciaDTO(lugarCompetenciaLogic.create(ubicacion.toEntity()));
        LOGGER.log(Level.INFO, "UsuarioResource create: output: {0}", nuevoDTO);
        return nuevoDTO;
    }
    
    
        
}
