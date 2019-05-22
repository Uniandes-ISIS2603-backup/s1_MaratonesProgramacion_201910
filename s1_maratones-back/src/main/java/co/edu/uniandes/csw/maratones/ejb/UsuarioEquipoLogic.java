/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.maratones.ejb;

import co.edu.uniandes.csw.maratones.entities.EquipoEntity;
import co.edu.uniandes.csw.maratones.entities.UsuarioEntity;
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
public class UsuarioEquipoLogic {
    private static final Logger LOGGER = Logger.getLogger(UsuarioEquipoLogic.class.getName());

    @Inject
    private UsuarioPersistence usuarioPersistence;

    @Inject
    private EquipoPersistence equipoPersistence;

    /**
     * Asocia un usuario existente a un equipo
     *
     * @param equipoId Identificador de la instancia de equipo
     * @param usuarioId Identificador de la instancia de usuario
     * @return Instancia de UsuarioEntity que fue asociada a equipo
     */
    public UsuarioEntity addUsuario(Long equipoId, Long usuarioId) {
        LOGGER.log(Level.INFO, "Inicia proceso de asociarle un usuario al equipo con id = {0}", equipoId);
        UsuarioEntity usuarioEntity = usuarioPersistence.find(usuarioId);
        EquipoEntity bookEntity = equipoPersistence.find(equipoId);
        bookEntity.getParticipantes().add(usuarioEntity);
        LOGGER.log(Level.INFO, "Termina proceso de asociarle un usuario al equipo con id = {0}", equipoId);
        return usuarioPersistence.find(usuarioId);
    }

    /**
     * Obtiene una colección de instancias de UsuarioEntity asociadas a una
     * instancia de equipo
     *
     * @param equipoId Identificador de la instancia de equipo
     * @return Colección de instancias de UsuarioEntity asociadas a la instancia
     * de equipo
     */
    public List<UsuarioEntity> getUsuarios(Long equipoId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los usuarios del equipo con id = {0}", equipoId);
        return equipoPersistence.find(equipoId).getParticipantes();
    }

    /**
     * Obtiene una instancia de UsuarioEntity asociada a una instancia de equipo
     *
     * @param usuarioId Identificador de la instancia de usuario
     * @param equipoId Identificador de la instancia de equipo
     * @return La entidad del user asociada al equipo
     */
    public UsuarioEntity getParticipante(Long usuarioId, Long equipoId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar un participante del equipo con id = {0}", equipoId);
        List<UsuarioEntity> participantes = equipoPersistence.find(equipoId).getParticipantes();
        UsuarioEntity usuarioEntity = usuarioPersistence.find(usuarioId);
        int index = participantes.indexOf(usuarioEntity);
        LOGGER.log(Level.INFO, "Termina proceso de consultar un participante del equipo con id = {0}", equipoId);
        if (index >= 0) {
            return participantes.get(index);
        }
        return null;
    }

    /**
     * Remplaza las instancias de Author asociadas a una instancia de Book
     *
     * @param booksId Identificador de la instancia de Book
     * @param list Colección de instancias de AuthorEntity a asociar a instancia
     * de Book
     * @return Nueva colección de AuthorEntity asociada a la instancia de Book
     */
    public List<UsuarioEntity> replaceParticipantes(Long equipoId, List<UsuarioEntity> list) {
        LOGGER.log(Level.INFO, "Inicia proceso de reemplazar los participantes del equipo con id = {0}", equipoId);
        EquipoEntity equipoEntity = equipoPersistence.find(equipoId);
        equipoEntity.setParticipantes(list);
        LOGGER.log(Level.INFO, "Termina proceso de reemplazar los participantes del equipo con id = {0}", equipoId);
        return equipoPersistence.find(equipoId).getParticipantes();
    }

    /**
     * Desasocia un Author existente de un Book existente
     *
     * @param usuarioId Identificador de la instancia de usuario
     * @param equipoId Identificador de la instancia de equipo
     */
    public void removeParticipante(Long equipoId, Long usuarioId) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar un usuario del equipo con id = {0}", equipoId);
        UsuarioEntity usuarioEntity = usuarioPersistence.find(usuarioId);
        EquipoEntity equipoEntity = equipoPersistence.find(equipoId);
        equipoEntity.getParticipantes().remove(usuarioEntity);
        LOGGER.log(Level.INFO, "Termina proceso de borrar un usuario del equipo con id = {0}", equipoId);
    }
    

}