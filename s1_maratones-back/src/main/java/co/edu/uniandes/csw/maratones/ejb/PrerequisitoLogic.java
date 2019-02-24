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
    
    public PrerequisitoEntity createPrerequisito (PrerequisitoEntity prerequisitoEntity) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "Iniciar proceso de creacion del prerequisito");
        
        //Se verifica que el prerequisito creado tenga un nivel positivo.
        if(prerequisitoEntity.getNivel()<0 || prerequisitoEntity.getNivel()>10)
        {
            throw new BusinessLogicException("El nivel del prerequisito no puede ser negativo"+prerequisitoEntity.getId());
        }
        
        prerequisitoEntity = prerequisitosPersistence.create(prerequisitoEntity);
        
        return prerequisitoEntity;
    }
 
}
