/*
MIT License

Copyright (c) 2019 Universidad de los Andes - ISIS2603

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

import co.edu.uniandes.csw.maratones.entities.EquipoEntity;
import co.edu.uniandes.csw.maratones.entities.UsuarioEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 * Clase que maneja la persistencia para la Cascara. Se conecta a través del
 * Entity Manager de javax.persistance con la base de datos SQL.
 *
 * @author ISIS2603
 */
@Stateless
public class EquipoPersistence {

    private static final Logger LOGGER = Logger.getLogger(EquipoPersistence.class.getName());

    @PersistenceContext(unitName = "maratonesPU")
    protected EntityManager em;

    public EquipoEntity create(EquipoEntity e){
        em.persist(e);
        return  e;
    }
    
    
    public EquipoEntity find(Long equipoId) {
        LOGGER.log(Level.INFO, "Consultando organizacion con id={0}", equipoId);
        /* Note que se hace uso del metodo "find" propio del EntityManager, el cual recibe como argumento 
        el tipo de la clase y el objeto que nos hara el filtro en la base de datos en este caso el "id"
        Suponga que es algo similar a "select * from OrganizationEntity where id=id;" - "SELECT * FROM table_name WHERE condition;" en SQL.
         */
        TypedQuery<EquipoEntity> query = em.createQuery("select u from EquipoEntity u left join FETCH u.participantes p where u.id =:id", EquipoEntity.class);
        query = query.setParameter("id", equipoId);
        List<EquipoEntity> organizations = query.getResultList();
        EquipoEntity result = null;
        if (!(organizations == null || organizations.isEmpty())) {
            result = organizations.get(0);
        }
        return result;
    }
    /**
     * Busca si hay alguna editorial con el nombre que se envía de argumento
     *
     * @param name: Nombre de la equipo que se está buscando
     * @return null si no existe ninguna editorial con el nombre del argumento.
     * Si existe alguna devuelve la primera.
     */
    public EquipoEntity findByName(String name) {
        LOGGER.log(Level.INFO, "Consultando usuario por nombre de equipo", name);
        TypedQuery query = em.createQuery("Select e From UsuarioEntity e where e.nombreEquipo = :nombreEquipo", EquipoEntity.class);
        query = query.setParameter("nombreEquipo", name);
        // Se invoca el query se obtiene la lista resultado
        List<EquipoEntity> sameName = query.getResultList();
        EquipoEntity result;
        if (sameName == null) {
            result = null;
        } else if (sameName.isEmpty()) {
            result = null;
        } else {
            result = sameName.get(0);
        }
        LOGGER.log(Level.INFO, "Saliendo de consultar equipo por nombre de equipo ", name);
        return result;
    }
    
    public EquipoEntity update(EquipoEntity laEntity) {
        LOGGER.log(Level.INFO, "Actualizando el equipo con id={0}", laEntity.getId());
        return em.merge(laEntity);
    }
    
    public List<EquipoEntity> findAll() {
        LOGGER.log(Level.INFO, "Consultando todos los equipos");
        Query q = em.createQuery("select u from EquipoEntity u");
        return q.getResultList();
    }
    
    public void delete(Long equipoId) {
        LOGGER.log(Level.INFO, "Borrando el equipo con id={0}", equipoId);
        EquipoEntity equipoEntity = em.find(EquipoEntity.class, equipoId);
        em.remove(equipoEntity);
    }
}