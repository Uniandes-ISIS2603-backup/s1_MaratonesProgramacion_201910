/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.maratones.test.logic;

import co.edu.uniandes.csw.maratones.ejb.SubmissionLogic;
import co.edu.uniandes.csw.maratones.entities.SubmissionEntity;
import co.edu.uniandes.csw.maratones.exceptions.BusinessLogicException;
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
public class SubmissionLogicTest {
    
  private PodamFactory factory = new PodamFactoryImpl();
    
  
  @PersistenceContext
   private EntityManager em;

  /**
     * Variable para marcar las transacciones del em anterior cuando se
     * crean/borran datos para las pruebas.
     */
    @Inject
    private UserTransaction utx;

    
     /**
     * Lista que tiene los datos de prueba.
     */
    private List<SubmissionEntity> data = new ArrayList<SubmissionEntity>();

  
   
    
    @Inject 
    private SubmissionLogic submissionLogic;
    
    
    
    
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
                .addPackage(SubmissionLogic.class.getPackage())
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
        em.createQuery("delete from SubmissionEntity").executeUpdate();
    }
    
    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            SubmissionEntity entity = factory.manufacturePojo(SubmissionEntity.class);

            em.persist(entity);
            data.add(entity);

        }
    }

    
    
    @Test 
    public void createSubmissionTest() throws BusinessLogicException
    {
        SubmissionEntity newEntity = factory.manufacturePojo(SubmissionEntity.class);
        SubmissionEntity result = submissionLogic.createSubmission(newEntity);
        Assert.assertNotNull(result);
        SubmissionEntity entity = em.find(SubmissionEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getCodigo(), entity.getCodigo());
        
    }
    
    
    
    @Test(expected = BusinessLogicException.class)
    
    public void createSubmissionConMismoCodigoTest() throws BusinessLogicException {
        SubmissionEntity newEntity = factory.manufacturePojo(SubmissionEntity.class);
        newEntity.setCodigo(data.get(0).getCodigo());
        submissionLogic.createSubmission(newEntity);
    }

    
}
