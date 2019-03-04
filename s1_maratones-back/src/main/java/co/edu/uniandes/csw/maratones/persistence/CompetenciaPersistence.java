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
    
     /**
     * Busca si hay alguna competencia con el id que se envía de argumento
     *
     * @param competenciasId: id correspondiente a la editorial buscada.
     * @return una competencia.
     */
    public CompetenciaEntity find(Long competenciasId) {
        LOGGER.log(Level.INFO, "Consultando competencia con id={0}", competenciasId);
        /* Note que se hace uso del metodo "find" propio del EntityManager, el cual recibe como argumento 
        el tipo de la clase y el objeto que nos hara el filtro en la base de datos en este caso el "id"
        Suponga que es algo similar a "select * from EditorialEntity where id=id;" - "SELECT * FROM table_name WHERE condition;" en SQL.
         */
        return em.find(CompetenciaEntity.class, competenciasId);
    }
    
	/**
     * Devuelve todas las competencias de la base de datos.
     *
     * @return una lista con todas las competencias que encuentre en la base de
     * datos, "select u from CompetenciaEntity u" es como un "select * from
     * CompetneciaEntity;" - "SELECT * FROM table_name" en SQL.
     */
    public List<CompetenciaEntity> findAll() {
        LOGGER.log(Level.INFO, "Consultando todas las competencias");
        // Se crea un query para buscar todas las editoriales en la base de datos.
        TypedQuery query = em.createQuery("select u from CompetenciaEntity u", CompetenciaEntity.class);
        // Note que en el query se hace uso del método getResultList() que obtiene una lista de editoriales.
        return query.getResultList();
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
    
     /**
     * Actualiza una editorial.
     *
     * @param competencialEntity: la editorial que viene con los nuevos cambios.
     * Por ejemplo el nombre pudo cambiar. En ese caso, se haria uso del método
     * update.
     * @return una editorial con los cambios aplicados.
     */
    public CompetenciaEntity update(CompetenciaEntity competencialEntity) {
        LOGGER.log(Level.INFO, "Actualizando competencia con id = {0}", competencialEntity.getId());
        /* Note que hacemos uso de un método propio del EntityManager llamado merge() que recibe como argumento
        la editorial con los cambios, esto es similar a 
        "UPDATE table_name SET column1 = value1, column2 = value2, ... WHERE condition;" en SQL.
         */
        LOGGER.log(Level.INFO, "Saliendo de actualizar la competencia con id = {0}", competencialEntity.getId());
        return em.merge(competencialEntity);
    }
}
