/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.maratones.resources;

import co.edu.uniandes.csw.maratones.dtos.CompetenciaDTO;
import co.edu.uniandes.csw.maratones.dtos.CompetenciaDetailDTO;
import co.edu.uniandes.csw.maratones.ejb.CompetenciaLogic;
import co.edu.uniandes.csw.maratones.entities.CompetenciaEntity;
import co.edu.uniandes.csw.maratones.exceptions.BusinessLogicException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
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
    
     @Inject
     private CompetenciaLogic logic;
    
      /**
     * Crea una nueva editorial con la informacion que se recibe en el cuerpo de
     * la petición y se regresa un objeto identico con un id auto-generado por
     * la base de datos.
     *
     * @param competencia {@link EditorialDTO} - La editorial que se desea
     * guardar.
     * @return JSON {@link EditorialDTO} - La editorial guardada con el atributo
     * id autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando ya existe la editorial.
     */
    @POST
    public CompetenciaDTO createCompetencia(CompetenciaDTO competencia )throws BusinessLogicException {
        LOGGER.log(Level.INFO, "CompetenciaResource createCompetencia: input: {0}", competencia);
        // Convierte el DTO (json) en un objeto Entity para ser manejado por la lógica.
        CompetenciaEntity competenciaEntity = competencia.toEntity();
        // Invoca la lógica para crear la editorial nueva
        CompetenciaEntity nuevoCompetenciaEntity = logic.create(competenciaEntity);
        // Como debe retornar un DTO (json) se invoca el constructor del DTO con argumento el entity nuevo
        CompetenciaDTO nuevoCompetenciaDTO=  new CompetenciaDTO(nuevoCompetenciaEntity);
        LOGGER.log(Level.INFO, "EditorialResource createCompetencia: output: {0}", nuevoCompetenciaDTO);
        return nuevoCompetenciaDTO;
    }
  
}
