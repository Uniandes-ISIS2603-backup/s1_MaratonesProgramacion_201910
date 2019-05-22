/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.maratones.ejb;

import co.edu.uniandes.csw.maratones.entities.CompetenciaEntity;
import co.edu.uniandes.csw.maratones.entities.UsuarioEntity;
import co.edu.uniandes.csw.maratones.entities.UsuarioEntity;
import co.edu.uniandes.csw.maratones.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.maratones.persistence.CompetenciaPersistence;
import co.edu.uniandes.csw.maratones.persistence.UsuarioPersistence;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Julian David Mendoza Ruiz <jd.mendozar@uniandes.edu.co>
 */
@Stateless
public class CompetenciaUsuarioLogic {
    private static final Logger LOGGER = Logger.getLogger(CompetenciaUsuarioLogic.class.getName());
    
    @Inject
    private CompetenciaPersistence competenciaPersistence;
    
    @Inject
    private UsuarioPersistence usuarioPersistence;
    
    /**
     * Agregar un Usuario a la competencia
     *
     * @param usuariosId El id Usuario a guardar
     * @param competenciasId El id de la competencia en la cual se va a guardar el
     * Usuario.
     * @return El libro creado.
     */
    public UsuarioEntity addJuez(Long usuariosId, Long competenciasId) {
        LOGGER.log(Level.INFO, "Inicia proceso de agregarle un Usuario a la competencia con id = {0}", competenciasId);
        CompetenciaEntity competenciaEntity = competenciaPersistence.find(competenciasId);
        UsuarioEntity usuarioEntity = usuarioPersistence.find(usuariosId);
        List<CompetenciaEntity> competenciasJuez = usuarioEntity.getCompetencias();
        competenciasJuez.add(competenciaEntity);
        List<UsuarioEntity> jueces = competenciaEntity.getJueces();
        competenciaEntity.setJueces(jueces);
        usuarioEntity.setCompetencias(competenciasJuez);
        LOGGER.log(Level.INFO, "Termina proceso de agregarle un Usuario a la competencia con id = {0}", competenciasId);
        return usuarioEntity;
    }
    
    /**
     * Retorna todos los books asociados a una competencia
     *
     * @param competenciasId El ID de la competencia buscada
     * @return La lista de Usuario de la competencia.
     */
    public List<UsuarioEntity> getJueces(Long competenciasId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar los Usuario asociados a la competencia con id = {0}", competenciasId);
        return competenciaPersistence.find(competenciasId).getJueces();
    }
    
     /**
     * Retorna un Usuario asociado a una competencia
     *
     * @param competenciasId El id de la competencia a buscar.
     * @param usuariosId El id del Usuario a buscar
     * @return El Usuario encontrado dentro de la competencia.
     * @throws BusinessLogicException Si el Usuario no se encuentra en la
     * competencia
     */
    public UsuarioEntity getUsuario(Long competenciasId, Long usuariosId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el Usuario con id = {0} de la competencia con id = " + competenciasId, usuariosId);
        List<UsuarioEntity> Usuarios = competenciaPersistence.find(competenciasId).getJueces();
        UsuarioEntity usuarioEntity = usuarioPersistence.find(usuariosId);
        int index = Usuarios.indexOf(usuarioEntity);
        LOGGER.log(Level.INFO, "Termina proceso de consultar el Usuario con id = {0} de la competencia con id = " + competenciasId, usuariosId);
        if (index >= 0) {
            return Usuarios.get(index);
        }
        throw new BusinessLogicException("El Usuario no está asociado a la competencia");
    }
    
}
