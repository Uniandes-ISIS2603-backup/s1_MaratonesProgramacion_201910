/*
MIT License

Copyright (c) 2017 Universidad de los Andes - ISIS2603

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 */
package co.edu.uniandes.csw.maratones.persistence;

import co.edu.uniandes.csw.maratones.entities.ForoEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Juan Felipe Peña
 */

@Stateless
public class ForoPersistence {
    
    private static final Logger LOGGER = Logger.getLogger(ForoPersistence.class.getName());
    
    @PersistenceContext(unitName = "maratonesPU")
    protected EntityManager em;
    
    
    /**
     * Método para persisitir la entidad en la base de datos.
     *
     * @param foroEntity objeto forol que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public ForoEntity create(ForoEntity foroEntity) {
        LOGGER.log(Level.INFO, "Creando un foro nueva");
        /* Note que hacemos uso de un método propio de EntityManager para persistir la editorial en la base de datos.
        Es similar a "INSERT INTO table_name (column1, column2, column3, ...) VALUES (value1, value2, value3, ...);" en SQL.
         */
        em.persist(foroEntity);
        LOGGER.log(Level.INFO, "Saliendo de crear un foro nuevo");
        return foroEntity;
    }
    
     public ForoEntity update(ForoEntity foroEntity)
     {
        LOGGER.log(Level.INFO, "Actualizando foro con id = {0}", foroEntity.getId());
        /* Note que hacemos uso de un método propio del EntityManager llamado merge() que recibe como argumento
        la editorial con los cambios, esto es similar a 
        "UPDATE table_name SET column1 = value1, column2 = value2, ... WHERE condition;" en SQL.
         */
        LOGGER.log(Level.INFO, "Saliendo de actualizar la editorial con id = {0}", foroEntity.getId());
        return em.merge(foroEntity);
     }
     
     public void delete(Long forosId)
     {
        LOGGER.log(Level.INFO, "Borrando foro con id = {0}", forosId);
        // Se hace uso de mismo método que esta explicado en public EditorialEntity find(Long id) para obtener la editorial a borrar.
        ForoEntity entity = em.find(ForoEntity.class, forosId);
        /* Note que una vez obtenido el objeto desde la base de datos llamado "entity", volvemos hacer uso de un método propio del
         EntityManager para eliminar de la base de datos el objeto que encontramos y queremos borrar.
         Es similar a "delete from EditorialEntity where id=id;" - "DELETE FROM table_name WHERE condition;" en SQL.*/
        em.remove(entity);
        LOGGER.log(Level.INFO, "Saliendo de borrar el foro con id = {0}", forosId);
     }
    
    public ForoEntity find(Long forosId){
        
        return em.find(ForoEntity.class, forosId);
    }
    
    public List<ForoEntity> findAll()
    {
        LOGGER.log(Level.INFO, "Consultando todos los foros");
        TypedQuery<ForoEntity> query = em.createQuery("select u from ForoEntity u", ForoEntity.class);
        return query.getResultList();
    }
    
    /**
     * Busca si hay algún foro con el nombre que se envía de argumento
     *
     * @param name: Nombre del foro que se está buscando
     * @return null si no existe ningún foro con el nombre del argumento.
     * Si existe alguna devuelve el primero.
     */
    public ForoEntity findByName(String name)
    {
        LOGGER.log(Level.INFO, "Consultando foro por nombre ", name);
        // Se crea un query para buscar foros con el nombre que recibe el método como argumento. ":name" es un placeholder que debe ser remplazado
        TypedQuery query = em.createQuery("Select e From ForoEntity e where e.nombre = :name", ForoEntity.class);
        // Se remplaza el placeholder ":name" con el valor del argumento 
        query = query.setParameter("name", name);
        // Se invoca el query se obtiene la lista resultado
        List<ForoEntity> sameName = query.getResultList();
        ForoEntity result;
        if (sameName == null) {
            result = null;
        } else if (sameName.isEmpty()) {
            result = null;
        } else {
            result = sameName.get(0);
        }
        LOGGER.log(Level.INFO, "Saliendo de consultar foro por nombre ", name);
        return result;
    }
}
