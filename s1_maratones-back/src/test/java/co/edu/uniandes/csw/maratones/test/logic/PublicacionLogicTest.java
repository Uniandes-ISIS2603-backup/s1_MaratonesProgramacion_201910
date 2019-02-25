/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.maratones.test.logic;

import co.edu.uniandes.csw.maratones.ejb.PublicacionLogic;
import co.edu.uniandes.csw.maratones.entities.PublicacionEntity;
import co.edu.uniandes.csw.maratones.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.maratones.persistence.PublicacionPersistence;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
public class PublicacionLogicTest {
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
    private PublicacionLogic pl;
    
    private PodamFactory factory = new PodamFactoryImpl();
     
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(PublicacionEntity.class.getPackage())
                .addPackage(PublicacionLogic.class.getPackage())
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
    public void createPublicacionTest() throws BusinessLogicException{
       
        PublicacionEntity newEntity = factory.manufacturePojo(PublicacionEntity.class);
        PublicacionEntity result = pl.createPublicacion(newEntity);
        Assert.assertNotNull(result);
        PublicacionEntity entity = em.find(PublicacionEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(entity.getTexto(), newEntity.getTexto());
       
    }
    @Test(expected = BusinessLogicException.class)
    public void createPublicacionTestNull() throws BusinessLogicException {
        PublicacionEntity newEntity = factory.manufacturePojo(PublicacionEntity.class);
        newEntity.setFecha(null);
        pl.createPublicacion(newEntity);
    }
    @Test(expected = BusinessLogicException.class)
    public void createPublicacionTestFecha() throws BusinessLogicException, ParseException {
        PublicacionEntity newEntity = factory.manufacturePojo(PublicacionEntity.class);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha=null;
        try{
        fecha = format.parse("2007-12-25");
         } catch (ParseException ex) {
             ex.printStackTrace();
            }
        newEntity.setFecha(fecha);
        pl.createPublicacion(newEntity);
    }
    @Test
    public void getPublicacionTest() {
        PublicacionEntity entity = data.get(0);
        PublicacionEntity newEntity = pl.getPublicacion(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(newEntity.getId(), entity.getId());
       
    }
     @Test
    public void getPublicacionesTest() {
        List<PublicacionEntity> list = pl.getPublicaciones();
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
    public void deletePublicacionTest() {
        PublicacionEntity entity = data.get(0);
        pl.deletePublicacion(entity.getId());
        PublicacionEntity deleted = em.find(PublicacionEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
    public void updatePublicacionTest()  {
        PublicacionEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        PublicacionEntity newEntity = factory.manufacturePojo(PublicacionEntity.class);
        newEntity.setId(entity.getId());
        Long id= newEntity.getId();
        pl.updatePublicacion(id,newEntity);
        PublicacionEntity resp = em.find(PublicacionEntity.class, entity.getId());
        
        Assert.assertEquals(newEntity.getTexto(), resp.getTexto());
    }
    
}
