/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.maratones.ejb;

import co.edu.uniandes.csw.maratones.entities.CompetenciaEntity;
import co.edu.uniandes.csw.maratones.entities.LugarCompetenciaEntity;
import co.edu.uniandes.csw.maratones.entities.UsuarioEntity;
import co.edu.uniandes.csw.maratones.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.maratones.persistence.CompetenciaPersistence;
import co.edu.uniandes.csw.maratones.persistence.LugarCompetenciaPersistence;
import java.util.Date;
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
public class LugarCompetenciaLogic {
    
    private static final Logger LOGGER = Logger.getLogger(LugarCompetenciaLogic.class.getName());
    
    @Inject
    private LugarCompetenciaPersistence lugarCompetenciaPersistence;
    
    @Inject
    private CompetenciaPersistence competenciaPersistence;
   
    /**
     * Crea un lugarCompetencia en la persistencia.
     *
     * @param lugarCompetenciaEntity La entidad que representa la lugarCompetencia a
     * persistir.
     * @return La entiddad de el LugarCompetencia luego de persistirla.
     * @throws BusinessLogicException Si la lugarCompetencia a persistir ya existe.
     */
    public LugarCompetenciaEntity createLugarCompetencia(LugarCompetenciaEntity lugarCompetenciaEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de creación del lugarCompetencia");
        if(lugarCompetenciaEntity.getCompetencia()==null)
                {
                    throw new BusinessLogicException("La competencia es inválida");
                }
            
        //TODO Verifica la regla de negocio que dice que no puede haber dos lugarCompetencia con el mismo id
        if (lugarCompetenciaPersistence.find(lugarCompetenciaEntity.getId())!=null) {
            throw new BusinessLogicException("Ya existe un lugarCompetencia con el id \"" + lugarCompetenciaEntity.getId() + "\"");
        }
        //TODO ubicaciones del lugar de competencias puede llegar nulo, validar donde se certifica que no llega a esta línea como valor nulo
        if(lugarCompetenciaEntity.getUbicaciones()==null)
        {
            throw new BusinessLogicException("La ubicacion no puede ser null");
        }
       
         //TODO Verifica la regla de negocio que dice que no puede haber dos lugarCompetencia con la misma ubicación
         List<LugarCompetenciaEntity> ubicaciones = lugarCompetenciaPersistence.findAll();
         if(ubicaciones!= null){
         for (int i = 0; i < ubicaciones.size(); i++) {
             LugarCompetenciaEntity ubicacion = ubicaciones.get(i);
            if(lugarCompetenciaEntity.getUbicaciones().equals(ubicacion.getUbicaciones()))
            {
                throw new BusinessLogicException("El lugarCompetencia tiene una ubicación que ya existe");
            }
            
        }
         }
        if(lugarCompetenciaEntity.getUbicaciones()==null||lugarCompetenciaEntity.getUbicaciones().equals("") )
        {
            throw new BusinessLogicException("El lugarCompetencia tiene una ubicación no valida");
        }
        CompetenciaEntity competencia =lugarCompetenciaEntity.getCompetencia();
        //TODO date o dateC... es nulo, es necesario certificar que los valores no llegarán nulos a esta parte del método
        Date date = lugarCompetenciaEntity.getFecha();
        System.out.println(date+"=================================");
        if(competencia!= null )
        {
            Date dateCompetenciaInicio = competencia.getFechaInicio();
            
            System.out.println("competenia inicio fecha"+ dateCompetenciaInicio+"======================================");
            Date dateCompetenciaFin = competencia.getFechaFin();
            if(date.before(dateCompetenciaInicio)||date.after(dateCompetenciaFin))
            {
                throw new BusinessLogicException("Fecha invalida.");
            }
        }
        
         if(competenciaPersistence.find(lugarCompetenciaEntity.getCompetencia().getId())==null)
        {
            throw new BusinessLogicException("La competencia no existe");
        }
        // Invoca la persistencia para crear la editorial
        lugarCompetenciaPersistence.create(lugarCompetenciaEntity);
        LOGGER.log(Level.INFO, "Termina proceso de creación del lugarCompetencia");
        return lugarCompetenciaEntity;
    }
    
    /**
     *
     * Obtener todas los lugarCompetencia existentes en la base de datos.
     *
     * @return una lista de editoriales.
     */
    public List<LugarCompetenciaEntity> getLugarCompetencias() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todas los LugarCompetencia");
        // Note que, por medio de la inyección de dependencias se llama al método "findAll()" que se encuentra en la persistencia.
        List<LugarCompetenciaEntity> ubicaciones = lugarCompetenciaPersistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todas los lugarCompetencia");
        return ubicaciones;
    }
    
    
    /**
     *
     * Obtener un lugarCompetencia por medio de su id.
     *
     * @param lugarCompetenciaId: id de la editorial para ser buscada.
     * @return la editorial solicitada por medio de su id.
     */
    public LugarCompetenciaEntity getLugarCompetencia(Long lugarCompetenciaId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el lugarCompetencia con id = {0}", lugarCompetenciaId);
        // Note que, por medio de la inyección de dependencias se llama al método "find(id)" que se encuentra en la persistencia.
        LugarCompetenciaEntity lugarCompetenciaEntity = lugarCompetenciaPersistence.find(lugarCompetenciaId);
        if (lugarCompetenciaEntity == null) {
            LOGGER.log(Level.SEVERE, "El lugarCompetencia con el id = {0} no existe", lugarCompetenciaId);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar el LugarCompetencia con id = {0}", lugarCompetenciaId);
        return lugarCompetenciaEntity;
    }
    
    /**
     *
     * Actualizar un lugarCompetencia.
     *
     * @param lugarCompetenciaId: id de la editorial para buscarla en la base de
     * datos.
     * @param lugarCompetenciaEntity: editorial con los cambios para ser actualizada,
     * por ejemplo el nombre.
     * @return la editorial con los cambios actualizados en la base de datos.
     */
    public LugarCompetenciaEntity updateLugarCompetencia(Long lugarCompetenciaId, LugarCompetenciaEntity lugarCompetenciaEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el lugarCompetencia con id = {0}", lugarCompetenciaId);
        if(!validateUbicaciones(lugarCompetenciaEntity.getUbicaciones()))
        {
            throw new BusinessLogicException("La ubicacion es invalida.");
        }
        // Note que, por medio de la inyección de dependencias se llama al método "update(entity)" que se encuentra en la persistencia.
        LugarCompetenciaEntity newEntity = lugarCompetenciaPersistence.update(lugarCompetenciaEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar el lugarCompetencia con id = {0}", lugarCompetenciaEntity.getId());
        return newEntity;
    }
    
    
    /**
     * Borrar un lugarCompetencia
     *
     * @param lugarCompetenciaId: id del lugarCompetencia a borrar
     * @throws BusinessLogicException Si el lugarCompetencia .
     */
    public void deleteLugarCompetencia(Long lugarCompetenciaId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar el lugarCompetencia con id = {0}", lugarCompetenciaId);
        // Note que, por medio de la inyección de dependencias se llama al método "delete(id)" que se encuentra en la persistencia.
      
        lugarCompetenciaPersistence.delete(lugarCompetenciaId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar el lugarCompetencia con id = {0}", lugarCompetenciaId);
    }
    
    
     /**
     * Verifica que el Ubicaciones no sea invalido.
     *
     * @param ubicaciones a verificar
     * @return true si el Ubicaciones es valido.
     */
    private boolean validateUbicaciones(String ubicaciones) {
        return !(ubicaciones == null || ubicaciones.isEmpty());
    }

}
