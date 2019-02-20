/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.maratones.test.persistence;
import co.edu.uniandes.csw.maratones.entities.PublicacionEntity;
import co.edu.uniandes.csw.maratones.persistence.PublicacionPersistence;
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
 * @author c.mendez11
 */
@RunWith(Arquillian.class)
public class PublicacionPersistenceTest {
    /**
     * Lista que tiene los datos de prueba.
     */
    private List<PublicacionEntity> data = new ArrayList<PublicacionEntity>();
    /**
     * Variable para martcar las transacciones del em anterior cuando se
     * crean/borran datos para las pruebas.
     */
    @Inject
    UserTransaction utx;
    
    @PersistenceContext
    private EntityManager em; 
    @Inject
    private PublicacionPersistence pp;
     @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(PublicacionEntity.class.getPackage())
                .addPackage(PublicacionPersistence.class.getPackage())
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
     *
     *
     */
    private void clearData() {
        em.createQuery("delete from PublicacionEntity").executeUpdate();
    }

     /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     *
     *
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {

            PublicacionEntity entity = factory.manufacturePojo(PublicacionEntity.class);

            em.persist(entity);

            data.add(entity);
        }
    }
    
    @Test
    public void createPublicacionTest() {
        PodamFactory factory = new PodamFactoryImpl();
        PublicacionEntity newEntity = factory.manufacturePojo(PublicacionEntity.class);
        PublicacionEntity result = pp.create(newEntity);
        Assert.assertNotNull(result);

        PublicacionEntity entity = em.find(PublicacionEntity.class, result.getId());

        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(entity.getTexto(), newEntity.getTexto());
    }
    
    @Test
    public void getPublicaionesTest() {
        List<PublicacionEntity> list = pp.findall();
        Assert.assertEquals(data.size(), list.size());
        for (PublicacionEntity ent : list) {
            boolean found = false;
            for (PublicacionEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    @Test
    public void getPublicacionTest() {
        PublicacionEntity entity = data.get(0);
        PublicacionEntity newEntity = pp.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(entity.getTexto(), newEntity.getTexto());
    }
     @Test
    public void deletePublicacionTest() {
        PublicacionEntity entity = data.get(0);
        pp.delete(entity.getId());
        PublicacionEntity deleted = em.find(PublicacionEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
     @Test
    public void updatePublicacionTest() {
        PublicacionEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        PublicacionEntity newEntity = factory.manufacturePojo(PublicacionEntity.class);

        newEntity.setId(entity.getId());

        pp.update(newEntity);

        PublicacionEntity resp = em.find(PublicacionEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getTexto(), resp.getTexto());
    }
}
