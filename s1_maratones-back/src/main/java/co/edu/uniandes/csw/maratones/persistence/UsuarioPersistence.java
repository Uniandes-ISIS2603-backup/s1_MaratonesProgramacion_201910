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
public class UsuarioPersistence {

    private static final Logger LOGGER = Logger.getLogger(UsuarioPersistence.class.getName());

    @PersistenceContext(unitName = "maratonesPU")
    protected EntityManager em;

    public UsuarioEntity create(UsuarioEntity usuarioEntity){
        em.persist(usuarioEntity);
        return  usuarioEntity;
    }
    
    /**
     * Busca si hay alguna editorial con el nombre que se envía de argumento
     *
     * @param name: Nombre de la editorial que se está buscando
     * @return null si no existe ninguna editorial con el nombre del argumento.
     * Si existe alguna devuelve la primera.
     */
    public UsuarioEntity findByUsername(String name) {
        LOGGER.log(Level.INFO, "Consultando usuario por nombre de usuario", name);
        TypedQuery query = em.createQuery("Select e From UsuarioEntity e where e.nombreUsuario = :nombreUsuario", UsuarioEntity.class);
        query = query.setParameter("nombreUsuario", name);
        // Se invoca el query se obtiene la lista resultado
        List<UsuarioEntity> sameName = query.getResultList();
        UsuarioEntity result;
        if (sameName == null) {
            result = null;
        } else if (sameName.isEmpty()) {
            result = null;
        } else {
            result = sameName.get(0);
        }
        LOGGER.log(Level.INFO, "Saliendo de consultar usuario por nombre de usuario ", name);
        return result;
    }
    
    /**
     *
     * Borra un usuario de la base de datos recibiendo como argumento el id del
     * usuario
     *
     * @param usuarioId: id correspondiente al usuario a borrar.
     */
    public void delete(Long usuarioId) {
        LOGGER.log(Level.INFO, "Borrando el usuario con id={0}", usuarioId);
        UsuarioEntity usuarioEntity = em.find(UsuarioEntity.class, usuarioId);
        em.remove(usuarioEntity);
    }
    
    public UsuarioEntity update(UsuarioEntity laEntity) {
        LOGGER.log(Level.INFO, "Actualizando el usuario con id={0}", laEntity.getId());
        return em.merge(laEntity);
    }
    
    /**
     * Devuelve todos loslibros de la base de datos.
     *
     * @return una lista con todos los usuarios que encuentre en la base de datos,
     * "select u from UsuarioEntity u" es como un "select * from UsuarioEntity;" -
     * "SELECT * FROM table_name" en SQL.
     */
    public List<UsuarioEntity> findAll() {
        LOGGER.log(Level.INFO, "Consultando todos los usuarios");
        Query q = em.createQuery("select u from UsuarioEntity u");
        return q.getResultList();
    }
    
    /**
     * Busca si hay algun usuario con el id que se envía de argumento
     *
     * @param usuarioId: id correspondiente al libro buscado.
     * @return un usuario.
     */
    public UsuarioEntity find(Long usuarioId) {
        LOGGER.log(Level.INFO, "Consultando el usuario con id={0}", usuarioId);
        
        return em.find(UsuarioEntity.class, usuarioId);
    }

    public UsuarioEntity findByIp(String ip) {
        TypedQuery<UsuarioEntity> query = em.createQuery("Select e from UsuarioEntity e where e.rol= :rol", UsuarioEntity.class);
        query = query.setParameter("rol", ip);
        List<UsuarioEntity > sameParam = query.getResultList();

        UsuarioEntity result;
        
        if (sameParam == null) {
            result = null;
        } 
        else if (sameParam.isEmpty()) {
            result = null;
        } 
        else {
            result = sameParam.get(0);
        }
        return result;
    }
    public List<UsuarioEntity> findBy(String atribute, String parameter)
    {
        Query q = null;
        if(atribute.equals("id"))
        {
            q = em.createQuery("SELECT u from UsuarioEntity u WHERE u."+atribute+" = :parameter").setParameter("parameter", Integer.valueOf(parameter));
        }
        else{
            q = em.createQuery("SELECT u from UsuarioEntity u WHERE u."+atribute+" LIKE :parameter").setParameter("parameter", "%"+parameter+"%");
        }
        return q.getResultList();
    }
}
