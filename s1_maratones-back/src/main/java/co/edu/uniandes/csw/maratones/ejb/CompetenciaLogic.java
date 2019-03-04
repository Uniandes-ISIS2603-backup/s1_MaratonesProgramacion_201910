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
public class CompetenciaLogic {
    private static final Logger LOGGER = Logger.getLogger(CompetenciaLogic.class.getName());
    
    @Inject
    private CompetenciaPersistence persistence;
    
    public CompetenciaEntity create(CompetenciaEntity competenciaEntity)throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "Iniciar proceso de creacion de la competencia");
        
        if(persistence.findByName(competenciaEntity.getNombre())!= null)
        {
            throw new BusinessLogicException("El nombre de la competencia ya existe");
        }
        if(competenciaEntity.getNombre().equals(""))
        {
            throw new BusinessLogicException("El nombre no puede ser un String vacio");
        }
        if(competenciaEntity.getDescripcion().equals(""))
        {
            throw new BusinessLogicException("La descripcion no puede ser un String vacio");
        }
        
        competenciaEntity = persistence.create(competenciaEntity);
        return competenciaEntity;
    }
    
    /**
     *
     * Obtener todas las competencias existentes en la base de datos.
     *
     * @return una lista de competencias.
     */
    public List<CompetenciaEntity> getCompetencias() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todas las competencias");
        // Note que, por medio de la inyección de dependencias se llama al método "findAll()" que se encuentra en la persistencia.
        List<CompetenciaEntity> competencias = persistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todas las editoriales");
        return competencias;
    }
    
    /**
     *
     * Obtener una editorial por medio de su id.
     *
     * @param competenciasId: id de la editorial para ser buscada.
     * @return la editorial solicitada por medio de su id.
     */
    public CompetenciaEntity getCompetencia(Long competenciasId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar la editorial con id = {0}", competenciasId);
        // Note que, por medio de la inyección de dependencias se llama al método "find(id)" que se encuentra en la persistencia.
        CompetenciaEntity competenciaEntity = persistence.find(competenciasId);
        if (competenciaEntity == null) {
            LOGGER.log(Level.SEVERE, "La competencia con el id = {0} no existe", competenciasId);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar la competencia con id = {0}", competenciasId);
        return competenciaEntity;
    }
    
    /**
     *
     * Actualizar una editorial.
     *
     * @param competenciasId: id de la editorial para buscarla en la base de
     * datos.
     * @param competenciaEntity: editorial con los cambios para ser actualizada,
     * por ejemplo el nombre.
     * @return la editorial con los cambios actualizados en la base de datos.
     */
    public CompetenciaEntity updateEditorial(Long competenciasId, CompetenciaEntity competenciaEntity) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar la competencia con id = {0}", competenciasId);
        // Note que, por medio de la inyección de dependencias se llama al método "update(entity)" que se encuentra en la persistencia.
        CompetenciaEntity newEntity = persistence.update(competenciaEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar la competencia con id = {0}", competenciaEntity.getId());
        return newEntity;
    }
    public CompetenciaEntity delete (Long competenciaId)
    {
        LOGGER.log(Level.INFO, "Borrando prerequisito con id = {0}", competenciaId);
        // Se hace uso de mismo método que esta explicado en public PrerequisitoEntity find(Long id) para obtener el prerequisito a borrar.
        CompetenciaEntity entity = persistence.find(competenciaId);
        /* Note que una vez obtenido el objeto desde la base de datos llamado "entity", volvemos hacer uso de un método propio del
         EntityManager para eliminar de la base de datos el objeto que encontramos y queremos borrar.
         Es similar a "delete from PrerequisitoEntity where id=id;" - "DELETE FROM table_name WHERE condition;" en SQL.*/
        persistence.delete(entity.getId());
        LOGGER.log(Level.INFO, "Saliendo de borrar el prerequisito con id = {0}", competenciaId);
        return entity;
    }
}
