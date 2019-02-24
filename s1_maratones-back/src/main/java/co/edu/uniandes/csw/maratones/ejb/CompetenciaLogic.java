/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.maratones.ejb;

import co.edu.uniandes.csw.maratones.entities.CompetenciaEntity;
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
    
    public CompetenciaEntity createCompetenncia(CompetenciaEntity competenciaEntity)throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "Iniciar proceso de creacion de la competencia");
        
        if(competenciaPersistence.findByName(competenciaEntity.getNombre())!= null)
        {
            
        }
        
        competenciaEntity = competenciaPersistence.create(competenciaEntity);
        return competenciaEntity;
    }
}
