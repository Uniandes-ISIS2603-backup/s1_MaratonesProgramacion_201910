/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.maratones.ejb;

import co.edu.uniandes.csw.maratones.entities.UsuarioEntity;
import co.edu.uniandes.csw.maratones.exceptions.BusinessLogicException;
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
public class UsuarioLogic {
    private static final Logger LOGGER = Logger.getLogger(UsuarioLogic.class.getName());

    @Inject
    private UsuarioPersistence persistence;
    
    /**
     * Se encarga de crear un usuario en la base de datos.
     *
     * @param usuarioEntity Objeto de UsuarioEntity con los datos nuevos
     * @return Objeto de UsuarioEntity con los datos nuevos y su ID.
     */
    public UsuarioEntity create(UsuarioEntity usuarioEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de creación del usuario");
        UsuarioEntity newusuarioEntity = persistence.create(usuarioEntity);
        /*if (persistence.findByUsername(usuarioEntity.getNombreUsuario()) != null) {
            throw new BusinessLogicException("ya existe un usuario con ese nombre de usuario");
        }*/
        if (usuarioEntity.getNombreUsuario().length() > 6) {
            throw new BusinessLogicException("el usuario debe tener minimo 6 caracteres");
        }
        
        LOGGER.log(Level.INFO, "Termina proceso de creación del usuario");
        return newusuarioEntity;
    }
    
   

}

