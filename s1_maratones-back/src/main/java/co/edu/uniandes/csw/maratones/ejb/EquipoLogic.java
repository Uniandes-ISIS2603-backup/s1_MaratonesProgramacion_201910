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
    public EquipoEntity create(EquipoEntity equipo) {
        LOGGER.log(Level.INFO, "Inicia proceso de creación del equipo");
        EquipoEntity newequipoEntity = persistence.create(equipo);
        LOGGER.log(Level.INFO, "Termina proceso de creación del equipo");
        return newequipoEntity;
    }

}


