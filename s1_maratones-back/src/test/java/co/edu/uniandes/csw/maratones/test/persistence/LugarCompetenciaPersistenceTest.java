/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.maratones.test.persistence;

import co.edu.uniandes.csw.maratones.entities.LugarCompetenciaEntity;
import co.edu.uniandes.csw.maratones.persistence.LugarCompetenciaPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author Julian David Mendoza Ruiz <jd.mendozar@uniandes.edu.co>
 */
@RunWith(Arquillian.class)
public class LugarCompetenciaPersistenceTest {
    @Inject
    private LugarCompetenciaPersistence lugarCompetenciaPersistence;

    @PersistenceContext
    private EntityManager em;

    @Inject
    UserTransaction utx;

    private List<LugarCompetenciaEntity> data = new ArrayList<LugarCompetenciaEntity>();

    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(LugarCompetenciaEntity.class.getPackage())
                .addPackage(LugarCompetenciaPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * Configuración inicial de la prueba.
     */
    @Before
    public void configTest() {
        try {
            utx.begin();
            em.joinTransaction();
            clearData();
            insertData();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

    /**
     * Limpia las tablas que están implicadas en la prueba.
     */
    private void clearData() {
        em.createQuery("delete from LugarCompetenciaEntity").executeUpdate();
    }
    
    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            LugarCompetenciaEntity entity = factory.manufacturePojo(LugarCompetenciaEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }

     /**
     * Prueba para crear un Book.
     */
    @Test
    public void createLugarCompetenciaTest() {
        PodamFactory factory = new PodamFactoryImpl();
        LugarCompetenciaEntity newEntity = factory.manufacturePojo(LugarCompetenciaEntity.class);
        LugarCompetenciaEntity result = lugarCompetenciaPersistence.create(newEntity);

        Assert.assertNotNull(result);

        LugarCompetenciaEntity entity = em.find(LugarCompetenciaEntity.class, result.getId());

        Assert.assertEquals(newEntity.getFecha(), entity.getFecha());
        Assert.assertEquals(newEntity.getUbicaciones(), entity.getUbicaciones());
    }

    /**
     * Prueba para consultar la lista de Books.
     */
    @Test
    public void getBooksTest() {
        List<LugarCompetenciaEntity> list = lugarCompetenciaPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (LugarCompetenciaEntity ent : list) {
            boolean found = false;
            for (LugarCompetenciaEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un Book.
     */
    @Test
    public void getBookTest() {
        LugarCompetenciaEntity entity = data.get(0);
        LugarCompetenciaEntity newEntity = lugarCompetenciaPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getFecha(), newEntity.getFecha());
        Assert.assertEquals(entity.getUbicaciones(), newEntity.getUbicaciones());
    }

    /**
     * Prueba para eliminar un Book.
     */
    @Test
    public void deleteBookTest() {
        LugarCompetenciaEntity entity = data.get(0);
        lugarCompetenciaPersistence.delete(entity.getId());
        LugarCompetenciaEntity deleted = em.find(LugarCompetenciaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar un Book.
     */
    @Test
    public void updateBookTest() {
        LugarCompetenciaEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        LugarCompetenciaEntity newEntity = factory.manufacturePojo(LugarCompetenciaEntity.class);

        newEntity.setId(entity.getId());

        lugarCompetenciaPersistence.update(newEntity);

        LugarCompetenciaEntity resp = em.find(LugarCompetenciaEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getFecha(), resp.getFecha());
        Assert.assertEquals(newEntity.getUbicaciones(), resp.getUbicaciones());
    }

}
