/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.maratones.persistence;


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
public class PrerequisitoPersistence {
    private static final Logger LOGGER = Logger.getLogger(PrerequisitoPersistence.class.getName());
    
    //Gestiona las entidades que persisten en la base de datos.
    @PersistenceContext(unitName = "maratonesPU")
    protected EntityManager em;
    
    public PrerequisitoEntity create (PrerequisitoEntity prerequisitoEntity){
        
        em.persist(prerequisitoEntity);
        return prerequisitoEntity;
    }
    
    public PrerequisitoEntity find (Long prerequisitoId)
    {
        return em.find(PrerequisitoEntity.class, prerequisitoId);
    }
    
    public void delete(Long prerequisitoId) {
        LOGGER.log(Level.INFO, "Borrando prerequisito con id = {0}", prerequisitoId);
        // Se hace uso de mismo método que esta explicado en public PrerequisitoEntity find(Long id) para obtener el prerequisito a borrar.
        PrerequisitoEntity entity = em.find(PrerequisitoEntity.class, prerequisitoId);
        /* Note que una vez obtenido el objeto desde la base de datos llamado "entity", volvemos hacer uso de un método propio del
         EntityManager para eliminar de la base de datos el objeto que encontramos y queremos borrar.
         Es similar a "delete from PrerequisitoEntity where id=id;" - "DELETE FROM table_name WHERE condition;" en SQL.*/
        em.remove(entity);
        LOGGER.log(Level.INFO, "Saliendo de borrar el prerequisito con id = {0}", prerequisitoId);
    }
}
