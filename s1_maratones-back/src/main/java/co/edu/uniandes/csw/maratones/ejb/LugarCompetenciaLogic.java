/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.maratones.ejb;

import co.edu.uniandes.csw.maratones.entities.LugarCompetenciaEntity;
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
    private LugarCompetenciaPersistence lugarCompetenciaPersistencia;
    
    public LugarCompetenciaEntity createLugarCompetencia (LugarCompetenciaEntity lugarCompetenciaEntity) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "Iniciar proceso de creación del LugarCompetencia");
        
        if(lugarCompetenciaEntity.getUbicaciones().equals(""))
        {
            throw new BusinessLogicException("Ubicaciones no puede ser un String vacio");
        }
        
        Date currentDate = new Date();
        
      
        return lugarCompetenciaEntity;
    }
}
