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
public class LugarCompetenciaCompetenciaLogic {
    
    private static final Logger LOGGER = Logger.getLogger(LugarCompetenciaCompetenciaLogic.class.getName());

    @Inject
    private LugarCompetenciaPersistence lugarCompetenciaPersistence;

    @Inject
    private CompetenciaPersistence competenciaPersistence;
    
     /**
     * Remplazar la competencia de un LugarCompetencia
     *
     * @param lugarCompetenciasId id del lugarCompetencia que se quiere actualizar.
     * @param competenciassId El id de la competencia que se será del lugarCompetencia.
     * @return el nuevo lugarCompetencia.
     */
    public LugarCompetenciaEntity replaceCompetencia(Long lugarCompetenciasId, Long competenciassId) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar libro con id = {0}", lugarCompetenciasId);
        CompetenciaEntity competenciaEntity = competenciaPersistence.find(competenciassId);
        LugarCompetenciaEntity lugarCompetenciaEntity = lugarCompetenciaPersistence.find(lugarCompetenciasId);
        lugarCompetenciaEntity.setCompetencia(competenciaEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar lugarCompetencia con id = {0}", lugarCompetenciaEntity.getId());
        return lugarCompetenciaEntity;
    }
    
     /**
     * Borrar un lugarCompetencia de una competencia. Este metodo se utiliza para borrar la
     * relacion de un lugarCompetencia.
     *
     * @param lugarCompetenciasId El lugarCompetencia que se desea borrar de la competencia.
     */
    public void removeCompetencia(Long lugarCompetenciasId) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar la Competencia del lugarCompetencia con id = {0}", lugarCompetenciasId);
        LugarCompetenciaEntity lugarCompetenciaEntity = lugarCompetenciaPersistence.find(lugarCompetenciasId);
        CompetenciaEntity competenciaEntity = competenciaPersistence.find(lugarCompetenciaEntity.getCompetencia().getId());
        lugarCompetenciaEntity.setCompetencia(null);
        competenciaEntity.getlugarCompetencias().remove(lugarCompetenciaEntity);
        LOGGER.log(Level.INFO, "Termina proceso de borrar la Competencia del lugarCompetencia con id = {0}", lugarCompetenciaEntity.getId());
    }
}
