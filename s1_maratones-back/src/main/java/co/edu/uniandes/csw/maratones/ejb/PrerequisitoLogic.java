/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.maratones.ejb;

import co.edu.uniandes.csw.maratones.entities.PrerequisitoEntity;
import co.edu.uniandes.csw.maratones.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.maratones.persistence.PrerequisitoPersistence;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Julian David Mendoza Ruiz <jd.mendozar@uniandes.edu.co>
 */
@Stateless
public class PrerequisitoLogic {
 
    private static final Logger LOGGER = Logger.getLogger(PrerequisitoLogic.class.getName());
    
    @Inject
    private PrerequisitoPersistence prerequisitosPersistence;
    
    public PrerequisitoEntity create (PrerequisitoEntity prerequisitoEntity) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "Iniciar proceso de creacion del prerequisito");
        
        //Se verifica que el prerequisito creado tenga un nivel positivo.
        if(prerequisitoEntity.getNivel()<0)
        {
            throw new BusinessLogicException("El nivel del prerequisito no puede ser negativo"+prerequisitoEntity.getNivel());
        }
        else if(prerequisitoEntity.getNivel()>11)
        {
            throw new BusinessLogicException("El nivel del prerequisito no puede ser mayor a 10"+prerequisitoEntity.getNivel());
        }
        
        prerequisitoEntity = prerequisitosPersistence.create(prerequisitoEntity);
        
        return prerequisitoEntity;
    }
    
    public PrerequisitoEntity delete (Long prerequisitoId)
    {
        LOGGER.log(Level.INFO, "Borrando prerequisito con id = {0}", prerequisitoId);
        // Se hace uso de mismo método que esta explicado en public PrerequisitoEntity find(Long id) para obtener el prerequisito a borrar.
        PrerequisitoEntity entity = prerequisitosPersistence.find(prerequisitoId);
        /* Note que una vez obtenido el objeto desde la base de datos llamado "entity", volvemos hacer uso de un método propio del
         EntityManager para eliminar de la base de datos el objeto que encontramos y queremos borrar.
         Es similar a "delete from PrerequisitoEntity where id=id;" - "DELETE FROM table_name WHERE condition;" en SQL.*/
        prerequisitosPersistence.delete(entity.getId());
        LOGGER.log(Level.INFO, "Saliendo de borrar el prerequisito con id = {0}", prerequisitoId);
        return entity;
    }
 
}
