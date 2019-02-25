/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.maratones.persistence;

import co.edu.uniandes.csw.maratones.entities.LugarCompetenciaEntity;
import co.edu.uniandes.csw.maratones.entities.PrerequisitoEntity;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
