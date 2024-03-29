/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.maratones.ejb;

import co.edu.uniandes.csw.maratones.entities.LenguajeEntity;
import co.edu.uniandes.csw.maratones.entities.UsuarioEntity;
import co.edu.uniandes.csw.maratones.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.maratones.persistence.LenguajePersistence;
import co.edu.uniandes.csw.maratones.persistence.UsuarioPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Angel Rodriguez aa.rodriguezv
 */
@Stateless
public class UsuarioLenguajesLogic {
    
    private static final Logger LOGGER = Logger.getLogger(UsuarioLenguajesLogic.class.getName());

    @Inject
    private UsuarioPersistence usuarioPersistence; 
    
    @Inject
    private LenguajePersistence lenguajePersistence;
    
    
     
    public UsuarioEntity addLenguaje(Long usuarioId, LenguajeEntity lenguaje) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de asociarle un lenguaje al usuario con id = {0}", usuarioId);
        UsuarioEntity usuarioEntity = usuarioPersistence.find(usuarioId);
        
        for(LenguajeEntity lenguajesUsuario: usuarioEntity.getLenguajes())
        {
            if(lenguaje.getNombre().equals(lenguajesUsuario.getNombre()))
            {
                throw new BusinessLogicException("El usuario ya tiene un lenguaje con el nombre especificado");
            }
        }
        
        lenguaje.setProgramador(usuarioEntity);
        usuarioEntity.getLenguajes().add(lenguaje);
        
        lenguajePersistence.update(lenguaje);
        usuarioPersistence.update(usuarioEntity);
        LOGGER.log(Level.INFO, "Termina proceso de asociarle un lenguaje al autor con id = {0}", usuarioId);
        return usuarioPersistence.find(usuarioId);
    }
    
    /**
     * Obtiene una colección de instancias de LenguajeEntity asociadas a una
     * instancia de Usuario
     *
     * @param usuarioId Identificador de la instancia de usuario
     * @return Colección de instancias de Lenguajes asociadas a la instancia de
     * Usuario
     */
    public List<LenguajeEntity> getLenguajes(Long usuarioId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los lenguajes del usuario con id = {0}", usuarioId);
        return usuarioPersistence.find(usuarioId).getLenguajes();
    }

    /**
     * Obtiene una instancia de LenguajeEntity asociada a una instancia de Usuario
     *
     * @param usuarioId Identificador de la instancia de Usuario
     * @param lenguajeId Identificador de la instancia de Lenguaje
     * @return La entidadd de Lenguaje del usuario
     * @throws BusinessLogicException Si el lenguaje no esta asociado con el usuario
     */
    public LenguajeEntity getLenguaje(Long usuarioId, Long lenguajeId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el lenguaje con id = {0} del usuario con id = " + usuarioId, lenguajeId);
       List<LenguajeEntity> lenguajes = usuarioPersistence.find(usuarioId).getLenguajes();
        LenguajeEntity lenguajeEntity = lenguajePersistence.find(lenguajeId);
       int index = lenguajes.indexOf(lenguajeEntity);
        LOGGER.log(Level.INFO, "Termina proceso de consultar el lenguaje con id = {0} del usuario con id = " + usuarioId, lenguajeId);
        if (index >= 0) {
            return lenguajes.get(index);}
        
        throw new BusinessLogicException("El lenguaje no esta asociado al usuario");
    }
    
    
     /**
     * Desasocia un lenguaje existente de un usuario existente
     *
     * @param usuarioId Identificador de la instancia de Author
     * @param lenguajeId Identificador de la instancia de Book
     */
    public void removeLenguaje(Long usuarioId, Long lenguajeId){
        LOGGER.log(Level.INFO, "Inicia proceso de borrar un libro del author con id = {0}", usuarioId);
        UsuarioEntity usuarioEntity = usuarioPersistence.find(usuarioId);
        LenguajeEntity lenguajeEntity = lenguajePersistence.find(lenguajeId);
        usuarioEntity.getLenguajes().remove(lenguajeEntity);
        LOGGER.log(Level.INFO, "Termina proceso de borrar un libro del author con id = {0}", usuarioId);
    }
    
    
    
}
