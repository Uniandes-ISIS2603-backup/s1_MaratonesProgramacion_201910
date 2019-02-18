/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.maratones.persistence;

import co.edu.uniandes.csw.maratones.entities.SubmissionEntity;
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
@Stateless // EJB usted es un objeto que el ciclo de vida lo gestiono yo, independiente en memoria, no tiene estados
public class SubmissionPersistence {
    
    // contante para manejar logs en servidor o en consola
    private static final Logger LOGGER = Logger.getLogger(SubmissionPersistence.class.getName());
    
    // maratonesPU apuntador a donde esta la configuracion de la base de datos
    // em gestiona las entiddes que van y vuelven de la base de datos 
    @PersistenceContext (unitName = "maratonesPU")
    protected EntityManager em;
    
    
    public SubmissionEntity create (SubmissionEntity ejercicioEntity){
        
        em.persist(ejercicioEntity);
        return ejercicioEntity;
    }
    
    public void delete(Long ejercicioId) {
        LOGGER.log(Level.INFO, "Borrando submission con id = {0}", ejercicioId);
        // Se hace uso de mismo método que esta explicado en public EditorialEntity find(Long id) para obtener la editorial a borrar.
        SubmissionEntity entity = em.find(SubmissionEntity.class, ejercicioId);
        /* Note que una vez obtenido el objeto desde la base de datos llamado "entity", volvemos hacer uso de un método propio del
         EntityManager para eliminar de la base de datos el objeto que encontramos y queremos borrar.
         Es similar a "delete from EditorialEntity where id=id;" - "DELETE FROM table_name WHERE condition;" en SQL.*/
        em.remove(entity);
        LOGGER.log(Level.INFO, "Saliendo de borrar la submission con id = {0}", ejercicioId);
    }
    
    /**
     * Busca si hay alguna competencia con el nombre que se envía de argumento
     *
     * @param name: Nombre de la competencia que se está buscando
     * @return null si no existe ninguna competenica con el nombre del argumento.
     * Si existe alguna devuelve la primera.
     */
    public SubmissionEntity findByName(String nombre) {
        LOGGER.log(Level.INFO, "Consultando submission por nombre ", nombre);
        // Se crea un query para buscar competencias con el nombre que recibe el método como argumento. ":nombre" es un placeholder que debe ser remplazado
        TypedQuery query = em.createQuery("Select e From SubmissionEntity e where e.nombre = :nombre", SubmissionEntity.class);
        // Se remplaza el placeholder ":name" con el valor del argumento 
        query = query.setParameter("nombre", nombre);
        // Se invoca el query se obtiene la lista resultado
        List<SubmissionEntity> sameName = query.getResultList();
        SubmissionEntity result;
        if (sameName == null) {
            result = null;
        } else if (sameName.isEmpty()) {
            result = null;
        } else {
            result = sameName.get(0);
        }
        LOGGER.log(Level.INFO, "Saliendo de consultar submission por nombre ", nombre);
        return result;
    }
    
    
}
