/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.maratones.test.persistence;

import co.edu.uniandes.csw.maratones.entities.LenguajeEntity;
import co.edu.uniandes.csw.maratones.persistence.LenguajePersistence;
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
 * @author Angel Rodriguez aa.rodriguezv
 */
@RunWith(Arquillian.class)
public class LenguajePersistenceTest {
    
     
    @Inject
    private LenguajePersistence lenguajePersistence;
    
    
    @PersistenceContext
    private EntityManager em;
    
    
    /**
     * Lista que tiene los datos de prueba.
     */
    private List<LenguajeEntity> data = new ArrayList<LenguajeEntity>();
    
    
    /**
     * Variable para martcar las transacciones del em anterior cuando se
     * crean/borran datos para las pruebas.
     */
    @Inject
    UserTransaction utx;

    
     /**
     *
     * @return Devuelve el jar que Arquillian va a desplegar en el Glassfish
     * embebido. El jar contiene las clases de Editorial, el descriptor de la
     * base de datos y el archivo beans.xml para resolver la inyección de
     * dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(LenguajeEntity.class.getPackage())
                .addPackage(LenguajePersistence.class.getPackage())
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
        em.createQuery("delete from LenguajeEntity").executeUpdate();
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

            LenguajeEntity entity = factory.manufacturePojo(LenguajeEntity.class);

            em.persist(entity);

            data.add(entity);
        }
    }
    
    
    
    
    /**
     * Prueba para crear un lenguaje.
     *
     *
     */
    @Test 
    public void createLenguajeTest() {
        PodamFactory factory = new PodamFactoryImpl();
        LenguajeEntity newEntity = factory.manufacturePojo(LenguajeEntity.class);
        LenguajeEntity result = lenguajePersistence.create(newEntity);

        Assert.assertNotNull(result);

        LenguajeEntity entity = em.find(LenguajeEntity.class, result.getId());

        Assert.assertEquals(newEntity.getId(), entity.getId());
    }
    
    /**
     * Prueba para eliminar un Competencia.
     *
     *
     */
    @Test
    public void deleteLenguajeTest() {
        LenguajeEntity entity = data.get(0);
        System.out.println(entity.getId() +" El Id de entity");
        lenguajePersistence.delete(entity.getId());
        LenguajeEntity deleted = em.find(LenguajeEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
        /**
     * Prueba para consultar un lenguaje por nombre.
     *
     *
     */
    @Test
    public void findLenguajeByNameTest() {
        LenguajeEntity entity = data.get(0);
        LenguajeEntity newEntity = lenguajePersistence.findByName(entity.getNombre());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getNombre(), newEntity.getNombre());
    }
    
    
    @Test
    public void updateLenguajeTest() {
        LenguajeEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        LenguajeEntity newEntity = factory.manufacturePojo(LenguajeEntity.class);

        newEntity.setId(entity.getId());

        lenguajePersistence.update(newEntity);

        LenguajeEntity resp = em.find(LenguajeEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getNombre(), resp.getNombre());
        Assert.assertEquals(newEntity.getExperiencia(), resp.getExperiencia());
    }
            
    
}
