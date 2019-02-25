/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.maratones.ejb;

import co.edu.uniandes.csw.maratones.entities.LugarCompetenciaEntity;
import co.edu.uniandes.csw.maratones.entities.PrerequisitoEntity;
import co.edu.uniandes.csw.maratones.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.maratones.persistence.LugarCompetenciaPersistence;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Julian David Mendoza Ruiz <jd.mendozar@uniandes.edu.co>
 */
@Stateless
public class LugarCompetenciaLogic {
    
    private static final Logger LOGGER = Logger.getLogger(LugarCompetenciaLogic.class.getName());
    
    @Inject
    private LugarCompetenciaPersistence lugarCompetenciaPersistence;
    
    public LugarCompetenciaEntity create (LugarCompetenciaEntity lugarCompetenciaEntity) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "Iniciar proceso de creación del LugarCompetencia");
        
        if(lugarCompetenciaEntity.getUbicaciones().equals(""))
        {
            throw new BusinessLogicException("Ubicaciones no puede ser un String vacio");
        }
        
        lugarCompetenciaPersistence.create(lugarCompetenciaEntity);
        
        return lugarCompetenciaEntity;
    }
     public LugarCompetenciaEntity delete (Long lugarCompetenciaId)
    {
        LOGGER.log(Level.INFO, "Borrando prerequisito con id = {0}", lugarCompetenciaId);
        // Se hace uso de mismo método que esta explicado en public PrerequisitoEntity find(Long id) para obtener el prerequisito a borrar.
        LugarCompetenciaEntity entity = lugarCompetenciaPersistence.find(lugarCompetenciaId);
        /* Note que una vez obtenido el objeto desde la base de datos llamado "entity", volvemos hacer uso de un método propio del
         EntityManager para eliminar de la base de datos el objeto que encontramos y queremos borrar.
         Es similar a "delete from PrerequisitoEntity where id=id;" - "DELETE FROM table_name WHERE condition;" en SQL.*/
        lugarCompetenciaPersistence.delete(entity.getId());
        LOGGER.log(Level.INFO, "Saliendo de borrar el prerequisito con id = {0}", lugarCompetenciaId);
        return entity;
    }
}
