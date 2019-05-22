/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.maratones.ejb;

import co.edu.uniandes.csw.maratones.entities.BlogEntity;
import co.edu.uniandes.csw.maratones.entities.UsuarioEntity;
import co.edu.uniandes.csw.maratones.exceptions.BusinessLogicException;
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
        
        if (persistence.findByUsername(usuarioEntity.getNombreUsuario())!=null) {
            throw new BusinessLogicException("ya existe un usuario con ese nombre de usuario");
        }
        if (usuarioEntity.getNombreUsuario().length() < 8) {
            throw new BusinessLogicException("el nombre usuario debe tener minimo 8 caracteres");
        }
        if (usuarioEntity.getNombreUsuario().equals("")) {
            throw new BusinessLogicException("el nombre usuario no puede estar vacio");
        }
        if (usuarioEntity.getNombre().equals("")) {
            throw new BusinessLogicException("el nombre no puede estar vacio");
        }
        if (!usuarioEntity.getCorreo().matches("^(.+)@(.+)$")) {
            throw new BusinessLogicException("el correo no es validod");
        }
        if (usuarioEntity.getClave().length()< 7) {
            throw new BusinessLogicException("la clave debe tener un minimo de 8 caracteres");
        }
        if (usuarioEntity.getClave().matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$")) {
            throw new BusinessLogicException("la clave debe contener al menos: un digito una letra en minuscula y otro mayuscula y un caracter");
        }
        if (usuarioEntity.getClave().contains(usuarioEntity.getNombreUsuario())) {
            throw new BusinessLogicException("la clave no debe incluir el nombre de usuario");
        }
        if (!(usuarioEntity.getRol().equals("COUCH")||usuarioEntity.getRol().equals("RESPONSABLE")||usuarioEntity.getRol().equals("PARTICIPANTE"))) {
            throw new BusinessLogicException("rol invalido");
        }
        persistence.create(usuarioEntity);
        LOGGER.log(Level.INFO, "Termina proceso de creación del usuario");
        
        return usuarioEntity;
    }
    
    /**
     * Devuelve todos los usuarios que hay en la base de datos.
     *
     * @return Lista de entidades de tipo usuario.
     */
    public List<UsuarioEntity> getTodosLosUsuarios() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los libros");
        List<UsuarioEntity> books = persistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todos los libros");
        return books;
    }
    
    /**
     * Actualiza la información de una instancia de usuario.
     *
     * @param usuarioId Identificador de la instancia a actualizar
     * @param authorEntity Instancia de UsuarioEntity con los nuevos datos.
     * @return Instancia de AuthorEntity con los datos actualizados.
     */
    public UsuarioEntity update(Long usuarioId, UsuarioEntity usuarioEntity) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el usuario con id = {0}", usuarioId);
        UsuarioEntity newAuthorEntity = persistence.update(usuarioEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar el usuario con id = {0}", usuarioId);
        return newAuthorEntity;
    }
    
   
    public void delete(Long usuarioId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar usuario con id = {0}", usuarioId);
        persistence.delete(usuarioId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar usuario con id = {0}", usuarioId);
    }
    
    /**
     * Busca un usuario por nombreUsuario
     *
     * @param nombreUsuario El nombreUsuario del usuario a buscar
     * @return El usuario encontrado, null si no lo encuentra.
     */
    public UsuarioEntity getPorNombreDeUsuario(String nombreUsuario) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el usuario con el nombre de usuario  = {0}", nombreUsuario);
        UsuarioEntity usuarioEntity = persistence.findByUsername(nombreUsuario);
        if (usuarioEntity == null) {
            LOGGER.log(Level.SEVERE, "El usuario con el nombreUsuario = {0} no existe", nombreUsuario);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar el usuario con nombreUsuario = {0}", nombreUsuario);
        return usuarioEntity;
    }
    
    /**
     * Busca un usuario por usuarioId
     *
     * @param usuarioId El usuarioId del usuario a buscar
     * @return El usuario encontrado, null si no lo encuentra.
     */
    public UsuarioEntity getUsuarioPorId(Long usuarioId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el usuario con el nombre de usuario  = {0}", usuarioId);
        UsuarioEntity usuarioEntity = persistence.find(usuarioId);
        if (usuarioEntity == null) {
            LOGGER.log(Level.SEVERE, "El usuario con el usuarioId = {0} no existe", usuarioId);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar el usuario con usuarioId = {0}", usuarioId);
        return usuarioEntity;
    }

    public List<UsuarioEntity> getUsuarios() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los usuarios");
        List<UsuarioEntity> books = persistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todos los usuarios");
        return books;
    }
    
    
}
