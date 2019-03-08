/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.maratones.test.logic;

import co.edu.uniandes.csw.maratones.ejb.LenguajeLogic;
import co.edu.uniandes.csw.maratones.entities.LenguajeEntity;
import co.edu.uniandes.csw.maratones.exceptions.BusinessLogicException;
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
public class LenguajeLogicTest {
    
       
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
    private List<LenguajeEntity> data = new ArrayList<LenguajeEntity>();

  
   
    
    @Inject 
    private LenguajeLogic lenguajeLogic;
    
    
    
    
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
                .addPackage(LenguajeLogic.class.getPackage())
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
        em.createQuery("delete from LenguajeEntity").executeUpdate();
    }
    
    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            LenguajeEntity entity = factory.manufacturePojo(LenguajeEntity.class);

            em.persist(entity);
            data.add(entity);

        }
    }

    
    
    @Test 
    public void createLenguajeTest() throws BusinessLogicException
    {
        LenguajeEntity newEntity = factory.manufacturePojo(LenguajeEntity.class);
        newEntity.setExperiencia((int) Math.abs(Math.round(Math.random())) + 1);
        LenguajeEntity result = lenguajeLogic.createLenguaje(newEntity);
        Assert.assertNotNull(result);
        LenguajeEntity entity = em.find(LenguajeEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getNombre(), entity.getNombre());
        
    }
    
    
    
    @Test(expected = BusinessLogicException.class)
    
    public void createLenguajeConMismoNombreTest() throws BusinessLogicException {
        LenguajeEntity newEntity = factory.manufacturePojo(LenguajeEntity.class);
        newEntity.setNombre(data.get(0).getNombre());
        lenguajeLogic.createLenguaje(newEntity);
    }
    
    @Test(expected = BusinessLogicException.class)
    
    public void createLenguajeConExperienciaMenorIgualACeroTest() throws BusinessLogicException {
        LenguajeEntity newEntity = factory.manufacturePojo(LenguajeEntity.class);
        newEntity.setExperiencia(0);
        lenguajeLogic.createLenguaje(newEntity);
    }
    
    
    @Test(expected = BusinessLogicException.class)
    public void updateLenguajeQueNoExiste() throws BusinessLogicException
    {
        LenguajeEntity newEntity = factory.manufacturePojo(LenguajeEntity.class);
        lenguajeLogic.updateLenguaje(newEntity);
    }
    
    @Test(expected = BusinessLogicException.class)
    
    public void updateLenguajeConExperienciaMenorIgualACeroTest() throws BusinessLogicException {
        LenguajeEntity newEntity = factory.manufacturePojo(LenguajeEntity.class);
        newEntity.setId(data.get(0).getId());
        newEntity.setExperiencia(0);
        lenguajeLogic.updateLenguaje(newEntity);
    }
    
    @Test
    public void deleteLenguajeTest() throws BusinessLogicException
    {
        LenguajeEntity newEntity = factory.manufacturePojo(LenguajeEntity.class);
        newEntity.setId(data.get(0).getId());
        lenguajeLogic.deleteLenguaje(newEntity.getId());
    }
    
    @Test(expected = BusinessLogicException.class)
    public void deleteLenguajeQueNoExisteTest() throws BusinessLogicException
    {
        LenguajeEntity newEntity = factory.manufacturePojo(LenguajeEntity.class);
        lenguajeLogic.deleteLenguaje(newEntity.getId());
    }
    
    @Test
    public void getLenguajeTest()throws BusinessLogicException
    {
        LenguajeEntity entity = data.get(0);
        LenguajeEntity result = lenguajeLogic.getLenguaje(data.get(0).getId());
        Assert.assertEquals(entity.getId(), result.getId());
        Assert.assertEquals(entity.getNombre(), result.getNombre());
        Assert.assertEquals(entity.getExperiencia(), result.getExperiencia());
    }
    
    /**
     * Prueba para consultar la lista de Lenguajes.
     */
    @Test
    public void getLenguajesTest() {
        List<LenguajeEntity> list = lenguajeLogic.getLenguajes();
        Assert.assertEquals(data.size(), list.size());
        for (LenguajeEntity entity : list) {
            boolean found = false;
            for (LenguajeEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
    

}
