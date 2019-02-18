/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.maratones.test.persistence;

import co.edu.uniandes.csw.maratones.entities.SubmissionEntity;
import co.edu.uniandes.csw.maratones.persistence.SubmissionPersistence;
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
public class SubmissionPersistenceTest {
    
    
    @Inject
    private SubmissionPersistence submissionPersistence;
    
    
    @PersistenceContext
    private EntityManager em;
    
    
    /**
     * Lista que tiene los datos de prueba.
     */
    private List<SubmissionEntity> data = new ArrayList<SubmissionEntity>();
    
    
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
                .addPackage(SubmissionEntity.class.getPackage())
                .addPackage(SubmissionPersistence.class.getPackage())
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
        em.createQuery("delete from SubmissionEntity").executeUpdate();
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

            SubmissionEntity entity = factory.manufacturePojo(SubmissionEntity.class);

            em.persist(entity);

            data.add(entity);
        }
    }
    
    
    
    /**
     * Prueba para crear una competencia.
     *
     *
     */
    @Test
    public void createSubmissionTest() {
        PodamFactory factory = new PodamFactoryImpl();
        SubmissionEntity newEntity = factory.manufacturePojo(SubmissionEntity.class);
        SubmissionEntity result = submissionPersistence.create(newEntity);

        Assert.assertNotNull(result);

        SubmissionEntity entity = em.find(SubmissionEntity.class, result.getId());

        Assert.assertEquals(newEntity.getId(), entity.getId());
    }
    
    /**
     * Prueba para eliminar un Competencia.
     *
     *
     */
    @Test
    public void deleteSubmissionTest() {
        SubmissionEntity entity = data.get(0);
        System.out.println(entity.getId() +" El Id de entity");
        submissionPersistence.delete(entity.getId());
        SubmissionEntity deleted = em.find(SubmissionEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
        /**
     * Prueba para consultar una competencia por nombre.
     *
     *
     */
    @Test
    public void FindSubmissionByNameTest() {
        SubmissionEntity entity = data.get(0);
        SubmissionEntity newEntity = submissionPersistence.findByName(entity.getCodigo());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getCodigo(), newEntity.getCodigo());
    }
    
      
            
    
    
}
