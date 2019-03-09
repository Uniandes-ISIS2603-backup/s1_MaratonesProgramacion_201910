/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.maratones.persistence;

import co.edu.uniandes.csw.maratones.entities.LugarCompetenciaEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Julian David Mendoza Ruiz <jd.mendozar@uniandes.edu.co>
 */
@Stateless
public class LugarCompetenciaPersistence {
    private static final Logger LOGGER = Logger.getLogger(LugarCompetenciaPersistence.class.getName());
    
    //Gestiona las entidades que persisten en la base de datos.
    @PersistenceContext(unitName = "maratonesPU")
    protected EntityManager em;
    
    public LugarCompetenciaEntity create (LugarCompetenciaEntity lugarCompetenciaEntity){
        
        em.persist(lugarCompetenciaEntity);
        return lugarCompetenciaEntity;
    }
    
    public LugarCompetenciaEntity find (Long lugarCompetenicaId)
    {
        return em.find(LugarCompetenciaEntity.class, lugarCompetenicaId);
    }
    
   /**
     * Devuelve todas las ubicaciones de la base de datos.
     *
     * @return una lista con todas las ubicaciones que encuentre en la base de
     * datos, "select u from LugarCompetenciaEntity u" es como un "select * from
     * LugarCompetenciaEntity;" - "SELECT * FROM table_name" en SQL.
     */
    public List<LugarCompetenciaEntity> findAll() {
        LOGGER.log(Level.INFO, "Consultando todas las ubicaciones");
        // Se crea un query para buscar todas las ubicaciones en la base de datos.
        TypedQuery query = em.createQuery("select u from LugarCompetenciaEntity u", LugarCompetenciaEntity.class);
        // Note que en el query se hace uso del método getResultList() que obtiene una lista de editoriales.
        return query.getResultList();
    }
    
     /**
     * Actualiza una ubicacion.
     *
     * @param LugarCompetenciaEntity: la editorial que viene con los nuevos cambios.
     * Por ejemplo el nombre pudo cambiar. En ese caso, se haria uso del método
     * update.
     * @return una ubicacion con los cambios aplicados.
     */
    public LugarCompetenciaEntity update(LugarCompetenciaEntity lugarCompetenciaEntity) {
        LOGGER.log(Level.INFO, "Actualizando ubicacion con id = {0}", lugarCompetenciaEntity.getId());
        /* Note que hacemos uso de un método propio del EntityManager llamado merge() que recibe como argumento
        la ubicacion con los cambios, esto es similar a 
        "UPDATE table_name SET column1 = value1, column2 = value2, ... WHERE condition;" en SQL.
         */
        LOGGER.log(Level.INFO, "Saliendo de actualizar la ubicacion con id = {0}", lugarCompetenciaEntity.getId());
        return em.merge(lugarCompetenciaEntity);
    }
    
    public void delete(Long lugarCompetenciaId) {
        LOGGER.log(Level.INFO, "Borrando LugarCompetencia con id = {0}", lugarCompetenciaId);
        // Se hace uso de mismo método que esta explicado en public LugarCompetenciaEntity find(Long id) para obtener el LugarCompetencia a borrar.
        LugarCompetenciaEntity entity = em.find(LugarCompetenciaEntity.class, lugarCompetenciaId);
        /* Note que una vez obtenido el objeto desde la base de datos llamado "entity", volvemos hacer uso de un método propio del
         EntityManager para eliminar de la base de datos el objeto que encontramos y queremos borrar.
         Es similar a "delete from LugarCompetenciaEntity where id=id;" - "DELETE FROM table_name WHERE condition;" en SQL.*/
        em.remove(entity);
        LOGGER.log(Level.INFO, "Saliendo de borrar la LugarCompetencia con id = {0}", lugarCompetenciaId);
    }
}
