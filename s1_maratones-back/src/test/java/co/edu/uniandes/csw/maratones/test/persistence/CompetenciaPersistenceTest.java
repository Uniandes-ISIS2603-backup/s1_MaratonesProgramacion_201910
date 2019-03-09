/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.maratones.test.persistence;


import co.edu.uniandes.csw.maratones.entities.CompetenciaEntity;
import co.edu.uniandes.csw.maratones.persistence.CompetenciaPersistence;
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
public class CompetenciaPersistenceTest {
   
    @Inject
    private CompetenciaPersistence competenciaPersistence;

    @PersistenceContext
    private EntityManager em;

    @Inject
    UserTransaction utx;

    private List<CompetenciaEntity> data = new ArrayList<CompetenciaEntity>();

    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(CompetenciaEntity.class.getPackage())
                .addPackage(CompetenciaPersistence.class.getPackage())
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
        em.createQuery("delete from CompetenciaEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {

            CompetenciaEntity entity = factory.manufacturePojo(CompetenciaEntity.class);

            em.persist(entity);

            data.add(entity);
        }
    }

    /**
     * Prueba para crear una Editorial.
     */
    @Test
    public void createEditorialTest() {
        PodamFactory factory = new PodamFactoryImpl();
        CompetenciaEntity newEntity = factory.manufacturePojo(CompetenciaEntity.class);
        CompetenciaEntity result = competenciaPersistence.create(newEntity);

        Assert.assertNotNull(result);

        CompetenciaEntity entity = em.find(CompetenciaEntity.class, result.getId());

        Assert.assertEquals(newEntity.getNombre(), entity.getNombre());
    }

    /**
     * Prueba para consultar la lista de Editoriales.
     */
    @Test
    public void getEditorialsTest() {
        List<CompetenciaEntity> list = competenciaPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (CompetenciaEntity ent : list) {
            boolean found = false;
            for (CompetenciaEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar una Editorial.
     */
    @Test
    public void getEditorialTest() {
        CompetenciaEntity entity = data.get(0);
        CompetenciaEntity newEntity = competenciaPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getNombre(), newEntity.getNombre());
    }

    /**
     * Prueba para eliminar una Editorial.
     */
    @Test
    public void deleteEditorialTest() {
        CompetenciaEntity entity = data.get(0);
        competenciaPersistence.delete(entity.getId());
        CompetenciaEntity deleted = em.find(CompetenciaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar una Editorial.
     */
    @Test
    public void updateEditorialTest() {
        CompetenciaEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        CompetenciaEntity newEntity = factory.manufacturePojo(CompetenciaEntity.class);

        newEntity.setId(entity.getId());

        competenciaPersistence.update(newEntity);

        CompetenciaEntity resp = em.find(CompetenciaEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getNombre(), resp.getNombre());
    }

    /**
     * Prueba para consultar una Editorial por nombre.
     */
    @Test
    public void findEditorialByNameTest() {
        CompetenciaEntity entity = data.get(0);
        CompetenciaEntity newEntity = competenciaPersistence.findByName(entity.getNombre());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getNombre(), newEntity.getNombre());

        newEntity = competenciaPersistence.findByName(null);
        Assert.assertNull(newEntity);
    }

}
