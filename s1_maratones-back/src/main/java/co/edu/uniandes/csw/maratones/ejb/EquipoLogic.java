/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.maratones.ejb;

import co.edu.uniandes.csw.maratones.entities.EquipoEntity;
import co.edu.uniandes.csw.maratones.entities.UsuarioEntity;
import co.edu.uniandes.csw.maratones.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.maratones.persistence.EquipoPersistence;
import co.edu.uniandes.csw.maratones.persistence.UsuarioPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author camilalonart
 */
@Stateless
public class EquipoLogic {
    private static final Logger LOGGER = Logger.getLogger(UsuarioLogic.class.getName());

    @Inject
    private EquipoPersistence persistence;
    
    /**
     * Se encarga de crear un usuario en la base de datos.
     *
     * @param usuarioEntity Objeto de UsuarioEntity con los datos nuevos
     * @return Objeto de UsuarioEntity con los datos nuevos y su ID.
     */
    public EquipoEntity create(EquipoEntity equipo)throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de creación del equipo");
        EquipoEntity newequipoEntity = persistence.create(equipo);
        if (equipo.getCoach()!= null) {
            throw new BusinessLogicException("necesita haber un coach");
        }
        if (equipo.getNombreEquipo().equals("")) {
            throw new BusinessLogicException("no puede ser vacio el nombre del equipo");
        }
        if (equipo.getParticipantes().size() >7) {
            throw new BusinessLogicException("solo pueden haber 6 integrantes");
        }
        LOGGER.log(Level.INFO, "Termina proceso de creación del equipo");
        return newequipoEntity;
    }

    public void delete(Long equipoId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar premio con id = {0}", equipoId);
        if (persistence.find(equipoId).getParticipantes().size()!=0) {
            throw new BusinessLogicException("No se puede borrar el equipo con id = " + equipoId + " porque tiene un participante asociado");
        }
        persistence.delete(equipoId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar equipo con id = {0}", equipoId);
    }
    
    public List<EquipoEntity> gets() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los equipos");
        List<EquipoEntity> books = persistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todos los equipos");
        return books;
    }
    
    public EquipoEntity getEquipo(Long equiposId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el equipo con id = {0}", equiposId);
        EquipoEntity entity = persistence.find(equiposId);
        if (entity == null) {
            LOGGER.log(Level.SEVERE, "El equipo con el id = {0} no existe", equiposId);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar el equipo con id = {0}", equiposId);
        return entity;
    }
    
    public EquipoEntity update(Long equipoId, EquipoEntity equipoEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el equipo con id = {0}", equipoId);
        EquipoEntity newEntity = persistence.update(equipoEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar el equipo con id = {0}", equipoEntity.getId());
        return newEntity;
    }
    

}