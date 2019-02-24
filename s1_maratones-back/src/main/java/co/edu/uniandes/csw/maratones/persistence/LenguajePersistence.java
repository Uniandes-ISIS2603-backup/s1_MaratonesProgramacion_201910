/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.maratones.persistence;

import co.edu.uniandes.csw.maratones.entities.LenguajeEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Angel Rodriguez aa.rodriguezv
 */
@Stateless
public class LenguajePersistence {
    
     private static final Logger LOGGER = Logger.getLogger(LenguajePersistence.class.getName());

    @PersistenceContext(unitName = "maratonesPU")
    protected EntityManager em;
    
    
    public LenguajeEntity create (LenguajeEntity lenguajeEntity){
        
        em.persist(lenguajeEntity);
        return lenguajeEntity;
    }
    
    public void delete(Long lenguajeId) {
        LOGGER.log(Level.INFO, "Borrando lenguaje con id = {0}", lenguajeId);
        // Se hace uso de mismo método que esta explicado en public LenguajeEntity find(Long id) para obtener el lenguaje a borrar.
        LenguajeEntity entity = em.find(LenguajeEntity.class, lenguajeId);
        /* Note que una vez obtenido el objeto desde la base de datos llamado "entity", volvemos hacer uso de un método propio del
         EntityManager para eliminar de la base de datos el objeto que encontramos y queremos borrar.
         Es similar a "delete from LenguajeEntity where id=id;" - "DELETE FROM table_name WHERE condition;" en SQL.*/
        em.remove(entity);
        LOGGER.log(Level.INFO, "Saliendo de borrar el lenguaje con id = {0}", lenguajeId);
    }
    
    /**
     * Busca si hay alguna competencia con el nombre que se envía de argumento
     *
     * @param name: Nombre de la competencia que se está buscando
     * @return null si no existe ninguna competenica con el nombre del argumento.
     * Si existe alguna devuelve la primera.
     */
    public LenguajeEntity findByName(String nombre) {
        LOGGER.log(Level.INFO, "Consultando Lenguaje por nombre ", nombre);
        // Se crea un query para buscar competencias con el nombre que recibe el método como argumento. ":nombre" es un placeholder que debe ser remplazado
        TypedQuery query = em.createQuery("Select e From LenguajeEntity e where e.nombre = :nombre", LenguajeEntity.class);
        // Se remplaza el placeholder ":name" con el valor del argumento 
        query = query.setParameter("nombre", nombre);
        // Se invoca el query se obtiene la lista resultado
        List<LenguajeEntity> sameName = query.getResultList();
        LenguajeEntity result;
        if (sameName == null) {
            result = null;
        } else if (sameName.isEmpty()) {
            result = null;
        } else {
            result = sameName.get(0);
        }
        LOGGER.log(Level.INFO, "Saliendo de consultar lenguaje por nombre ", nombre);
        return result;
    }
    
    /**
     * 
     * @return 
     */
    public List<LenguajeEntity> findAll()
    {
        TypedQuery<LenguajeEntity> query = em.createQuery("select u from LenguajeEntity u", LenguajeEntity.class);
        return query.getResultList();
    }
    
    /**
     * 
     * @param id
     * @return 
     */
    public LenguajeEntity find(Long id)
    {
        return em.find(LenguajeEntity.class, id);
    }
    
}
