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
import co.edu.uniandes.csw.maratones.persistence.LugarCompetenciaPersistence;
import java.util.List;
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
    
    
    /**
     * Retorna todos los books asociados a una competencia
     *
     * @param competenciasId El ID de la competencia buscada
     * @return La lista de lugarCompetencia de la competencia.
     */
    public List<LugarCompetenciaEntity> getLugarCompetencias(Long competenciasId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar los lugarCompetencia asociados a la competencia con id = {0}", competenciasId);
        return competenciaPersistence.find(competenciasId).getlugarCompetencias();
    }

    
    /**
     * Retorna un lugarCompetencia asociado a una competencia
     *
     * @param competenciasId El id de la competencia a buscar.
     * @param lugarCompetenciasId El id del lugarCompetencia a buscar
     * @return El lugarCompetencia encontrado dentro de la competencia.
     * @throws BusinessLogicException Si el lugarCompetencia no se encuentra en la
     * competencia
     */
    public LugarCompetenciaEntity getLugarCompetencia(Long competenciasId, Long lugarCompetenciasId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el lugarCompetencia con id = {0} de la competencia con id = " + competenciasId, lugarCompetenciasId);
        List<LugarCompetenciaEntity> lugarCompetencias = competenciaPersistence.find(competenciasId).getlugarCompetencias();
        LugarCompetenciaEntity lugarCompetenciaEntity = lugarCompetenciaPersistence.find(lugarCompetenciasId);
        int index = lugarCompetencias.indexOf(lugarCompetenciaEntity);
        LOGGER.log(Level.INFO, "Termina proceso de consultar el lugarCompetencia con id = {0} de la competencia con id = " + competenciasId, lugarCompetenciasId);
        if (index >= 0) {
            return lugarCompetencias.get(index);
        }
        throw new BusinessLogicException("El lugarCompetencia no está asociado a la competencia");
    }
    
    /**
     * Remplazar lugarCompetencia de una competencia
     *
     * @param lugarCompetencias Lista de lugarCompetencias que serán los de la competencia.
     * @param competenciasId El id de la competencia que se quiere actualizar.
     * @return La lista de lugarCompetencias actualizada.
     */
    public List<LugarCompetenciaEntity> replaceLugarCompetencias(Long competenciasId, List<LugarCompetenciaEntity> lugarCompetencias) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar la competencia con id = {0}", competenciasId);
        CompetenciaEntity competenciaEntity = competenciaPersistence.find(competenciasId);
        List<LugarCompetenciaEntity> lugarCompetenciaList = lugarCompetenciaPersistence.findAll();
        for (LugarCompetenciaEntity lugarCompetencia : lugarCompetenciaList) {
            if (lugarCompetencias.contains(lugarCompetencia)) {
                lugarCompetencia.setCompetencia(competenciaEntity);
            } else if (lugarCompetencia.getCompetencia() != null && lugarCompetencia.getCompetencia().equals(competenciaEntity)) {
                lugarCompetencia.setCompetencia(null);
            }
        }
        LOGGER.log(Level.INFO, "Termina proceso de actualizar la competencia con id = {0}", competenciasId);
        return lugarCompetencias;
    }
    

}
