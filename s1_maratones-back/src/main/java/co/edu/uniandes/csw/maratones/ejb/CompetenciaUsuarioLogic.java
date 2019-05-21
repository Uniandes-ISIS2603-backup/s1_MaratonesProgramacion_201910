/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.maratones.ejb;

import co.edu.uniandes.csw.maratones.entities.CompetenciaEntity;
import co.edu.uniandes.csw.maratones.entities.UsuarioEntity;
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
    public UsuarioEntity addUsuario(Long usuariosId, Long competenciasId) {
        LOGGER.log(Level.INFO, "Inicia proceso de agregarle un Usuario a la competencia con id = {0}", competenciasId);
        CompetenciaEntity competenciaEntity = competenciaPersistence.find(competenciasId);
        UsuarioEntity usuarioEntity = usuarioPersistence.find(usuariosId);
        List<CompetenciaEntity> competenciasJuez = new ArrayList<>();
        competenciasJuez = usuarioEntity.getCompetenciasJuez();
        usuarioEntity.setCompetenciasJuez(competenciasJuez);
        LOGGER.log(Level.INFO, "Termina proceso de agregarle un Usuario a la competencia con id = {0}", competenciasId);
        return usuarioEntity;
    }
    
}
