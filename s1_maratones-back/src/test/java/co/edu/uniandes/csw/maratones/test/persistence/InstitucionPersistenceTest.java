/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.maratones.test.persistence;
import co.edu.uniandes.csw.maratones.entities.InstitucionEntity;
import co.edu.uniandes.csw.maratones.persistence.InstitucionPersistence;
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
public class InstitucionPersistenceTest {
    /**
     * Lista que tiene los datos de prueba.
     */
    private List<InstitucionEntity> data = new ArrayList<InstitucionEntity>();
    /**
     * Variable para martcar las transacciones del em anterior cuando se
     * crean/borran datos para las pruebas.
     */
    @Inject
    UserTransaction utx;
    
    @PersistenceContext
    private EntityManager em; 
    @Inject
    private InstitucionPersistence ip;
     @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(InstitucionEntity.class.getPackage())
                .addPackage(InstitucionPersistence.class.getPackage())
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
        em.createQuery("delete from InstitucionEntity").executeUpdate();
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

            InstitucionEntity entity = factory.manufacturePojo(InstitucionEntity.class);

            em.persist(entity);

            data.add(entity);
        }
    }
    
    @Test
    public void createInstitucionTest() {
        PodamFactory factory = new PodamFactoryImpl();
        InstitucionEntity newEntity = factory.manufacturePojo(InstitucionEntity.class);
        InstitucionEntity result = ip.create(newEntity);
        Assert.assertNotNull(result);

        InstitucionEntity entity = em.find(InstitucionEntity.class, result.getId());
        
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getDescripcion(), entity.getDescripcion());
        Assert.assertEquals(newEntity.getImagen(), entity.getImagen());
        Assert.assertEquals(newEntity.getNombre(), entity.getNombre());
        Assert.assertEquals(newEntity.getUbicacion(), entity.getUbicacion());
    }
    
    @Test
    public void getInstitucionesTest() {
        List<InstitucionEntity> list = ip.findall();
        Assert.assertEquals(data.size(), list.size());
        for (InstitucionEntity ent : list) {
            boolean found = false;
            for (InstitucionEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    @Test
    public void getInstitucionTest() {
        InstitucionEntity entity = data.get(0);
        InstitucionEntity newEntity = ip.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getDescripcion(), entity.getDescripcion());
        Assert.assertEquals(newEntity.getImagen(), entity.getImagen());
        Assert.assertEquals(newEntity.getNombre(), entity.getNombre());
        Assert.assertEquals(newEntity.getUbicacion(), entity.getUbicacion());
    }
     @Test
    public void deleteInstitucionTest() {
        InstitucionEntity entity = data.get(0);
        ip.delete(entity.getId());
        InstitucionEntity deleted = em.find(InstitucionEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
     @Test
    public void updateInstitucionTest() {
        InstitucionEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        InstitucionEntity newEntity = factory.manufacturePojo(InstitucionEntity.class);

        newEntity.setId(entity.getId());

        ip.update(newEntity);

        InstitucionEntity resp = em.find(InstitucionEntity.class, entity.getId());

        
        Assert.assertEquals(newEntity.getDescripcion(), resp.getDescripcion());
        Assert.assertEquals(newEntity.getImagen(), resp.getImagen());
        Assert.assertEquals(newEntity.getNombre(), resp.getNombre());
        Assert.assertEquals(newEntity.getUbicacion(), resp.getUbicacion());
    }
}
