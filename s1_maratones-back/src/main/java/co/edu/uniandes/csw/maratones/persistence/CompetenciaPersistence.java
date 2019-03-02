/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.maratones.persistence;


import co.edu.uniandes.csw.maratones.entities.CompetenciaEntity;
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
public class CompetenciaPersistence {
    private static final Logger LOGGER = Logger.getLogger(CompetenciaPersistence.class.getName());
    
    //Gestiona las entidades que persisten en la base de datos.
    @PersistenceContext(unitName = "maratonesPU")
    protected EntityManager em;
    
    public CompetenciaEntity create (CompetenciaEntity competenciaEntity){
        
        em.persist(competenciaEntity);
        return competenciaEntity;
    }
    
     public CompetenciaEntity find (Long competenciaId)
    {
        return em.find(CompetenciaEntity.class, competenciaId);
    }
    
    public void delete(Long competenciaId) {
        LOGGER.log(Level.INFO, "Borrando competencia con id = {0}", competenciaId);
        // Se hace uso de mismo método que esta explicado en public EditorialEntity find(Long id) para obtener la editorial a borrar.
        CompetenciaEntity entity = em.find(CompetenciaEntity.class, competenciaId);
        /* Note que una vez obtenido el objeto desde la base de datos llamado "entity", volvemos hacer uso de un método propio del
         EntityManager para eliminar de la base de datos el objeto que encontramos y queremos borrar.
         Es similar a "delete from EditorialEntity where id=id;" - "DELETE FROM table_name WHERE condition;" en SQL.*/
        em.remove(entity);
        LOGGER.log(Level.INFO, "Saliendo de borrar la competencia con id = {0}", competenciaId);
    }
    
    /**
     * Busca si hay alguna competencia con el nombre que se envía de argumento
     *
     * @param name: Nombre de la competencia que se está buscando
     * @return null si no existe ninguna competenica con el nombre del argumento.
     * Si existe alguna devuelve la primera.
     */
    public CompetenciaEntity findByName(String nombre) {
        LOGGER.log(Level.INFO, "Consultando competencia por nombre ", nombre);
        // Se crea un query para buscar competencias con el nombre que recibe el método como argumento. ":nombre" es un placeholder que debe ser remplazado
        TypedQuery query = em.createQuery("Select e From CompetenciaEntity e where e.nombre = :nombre", CompetenciaEntity.class);
        // Se remplaza el placeholder ":name" con el valor del argumento 
        query = query.setParameter("nombre", nombre);
        // Se invoca el query se obtiene la lista resultado
        List<CompetenciaEntity> sameName = query.getResultList();
        CompetenciaEntity result;
        if (sameName == null) {
            result = null;
        } else if (sameName.isEmpty()) {
            result = null;
        } else {
            result = sameName.get(0);
        }
        LOGGER.log(Level.INFO, "Saliendo de consultar competencia por nombre ", nombre);
        return result;
    }
}
