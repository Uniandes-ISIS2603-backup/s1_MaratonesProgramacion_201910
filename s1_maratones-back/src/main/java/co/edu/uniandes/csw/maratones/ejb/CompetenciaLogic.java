/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.maratones.ejb;

import co.edu.uniandes.csw.maratones.entities.CompetenciaEntity;
import co.edu.uniandes.csw.maratones.entities.LugarCompetenciaEntity;
import co.edu.uniandes.csw.maratones.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.maratones.persistence.CompetenciaPersistence;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Julian David Mendoza Ruiz <jd.mendozar@uniandes.edu.co>
 */
@Stateless
public class CompetenciaLogic {
    private static final Logger LOGGER = Logger.getLogger(CompetenciaLogic.class.getName());
    
    @Inject
    private CompetenciaPersistence competenciaPersistence;
    
    public CompetenciaEntity create(CompetenciaEntity competenciaEntity)throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "Iniciar proceso de creacion de la competencia");
        
        if(competenciaPersistence.findByName(competenciaEntity.getNombre())!= null)
        {
            throw new BusinessLogicException("El nombre de la competencia ya existe");
        }
        if(competenciaEntity.getNombre().equals(""))
        {
            throw new BusinessLogicException("El nombre no puede ser un String vacio");
        }
        if(competenciaEntity.getDescripcion().equals(""))
        {
            throw new BusinessLogicException("La descripcion no puede ser un String vacio");
        }
        
        competenciaEntity = competenciaPersistence.create(competenciaEntity);
        return competenciaEntity;
    }
    
    public CompetenciaEntity delete (Long competenciaId)
    {
        LOGGER.log(Level.INFO, "Borrando prerequisito con id = {0}", competenciaId);
        // Se hace uso de mismo método que esta explicado en public PrerequisitoEntity find(Long id) para obtener el prerequisito a borrar.
        CompetenciaEntity entity = competenciaPersistence.find(competenciaId);
        /* Note que una vez obtenido el objeto desde la base de datos llamado "entity", volvemos hacer uso de un método propio del
         EntityManager para eliminar de la base de datos el objeto que encontramos y queremos borrar.
         Es similar a "delete from PrerequisitoEntity where id=id;" - "DELETE FROM table_name WHERE condition;" en SQL.*/
        competenciaPersistence.delete(entity.getId());
        LOGGER.log(Level.INFO, "Saliendo de borrar el prerequisito con id = {0}", competenciaId);
        return entity;
    }
}
