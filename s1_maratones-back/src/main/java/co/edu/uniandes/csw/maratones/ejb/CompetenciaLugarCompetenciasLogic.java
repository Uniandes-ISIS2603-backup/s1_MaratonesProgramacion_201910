/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.maratones.ejb;

import co.edu.uniandes.csw.maratones.entities.CompetenciaEntity;
import co.edu.uniandes.csw.maratones.entities.LugarCompetenciaEntity;
import co.edu.uniandes.csw.maratones.persistence.CompetenciaPersistence;
import co.edu.uniandes.csw.maratones.persistence.LugarCompetenciaPersistence;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Julian David Mendoza Ruiz <jd.mendozar@uniandes.edu.co>
 */
@Stateless
public class CompetenciaLugarCompetenciasLogic {
    private static final Logger LOGGER = Logger.getLogger(CompetenciaLugarCompetenciasLogic.class.getName());

    @Inject
    private LugarCompetenciaPersistence lugarCompetenciaPersistence;

    @Inject
    private CompetenciaPersistence competenciaPersistence;
    
    /**
     * Agregar un lugarCompetencia a la competencia
     *
     * @param lugarCompetenciasId El id lugarCompetencia a guardar
     * @param competenciasId El id de la competencia en la cual se va a guardar el
     * lugarCompetencia.
     * @return El libro creado.
     */
    public LugarCompetenciaEntity addLugarCompetencia(Long lugarCompetenciasId, Long competenciasId) {
        LOGGER.log(Level.INFO, "Inicia proceso de agregarle un lugarCompetencia a la competencia con id = {0}", competenciasId);
        CompetenciaEntity competenciaEntity = competenciaPersistence.find(competenciasId);
        LugarCompetenciaEntity lugarCompetenciaEntity = lugarCompetenciaPersistence.find(lugarCompetenciasId);
        lugarCompetenciaEntity.setCompetencia(competenciaEntity);
        LOGGER.log(Level.INFO, "Termina proceso de agregarle un lugarCompetencia a la competencia con id = {0}", competenciasId);
        return lugarCompetenciaEntity;
    }
}
