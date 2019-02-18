/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.maratones.test.persistence;

import co.edu.uniandes.csw.maratones.entities.EjercicioEntity;
import co.edu.uniandes.csw.maratones.persistence.EjercicioPersistence;
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
public class EjercicioPersistenceTest {
    
   @Inject
    private EjercicioPersistence ejercicioPersistence;
    
    /**
     * Lista que tiene los datos de prueba.
     */
    private List<EjercicioEntity> data = new ArrayList<EjercicioEntity>();
    /**
     * Contexto de Persistencia que se va a utilizar para acceder a la Base de
     * datos por fuera de los métodos que se están probando.
     */
    @PersistenceContext
    private EntityManager em;   

/**
     * Variable para martcar las transacciones del em anterior cuando se
     * crean/borran datos para las pruebas.
     */
    @Inject
    UserTransaction utx;

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(EjercicioEntity.class.getPackage())
                .addPackage(EjercicioPersistence.class.getPackage())
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
        em.createQuery("delete from EjercicioEntity").executeUpdate();
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

            EjercicioEntity entity = factory.manufacturePojo(EjercicioEntity.class);

            em.persist(entity);

            data.add(entity);
        }
    }
    
    
    /**
     * Prueba para crear un ejercicio.
     *
     *
     */
    @Test
    public void createEjercicioTest() {
        PodamFactory factory = new PodamFactoryImpl();
        EjercicioEntity newEntity = factory.manufacturePojo(EjercicioEntity.class);
        EjercicioEntity result = ejercicioPersistence.create(newEntity);

        Assert.assertNotNull(result);

        EjercicioEntity entity = em.find(EjercicioEntity.class, result.getId());

        Assert.assertEquals(newEntity.getId(), entity.getId());
    }
    
    /**
     * Prueba para eliminar un Ejercicio.
     *
     *
     */
    @Test
    public void deleteEjercicioTest() {
        EjercicioEntity entity = data.get(0);
        System.out.println(entity.getId() +" El Id de entity");
        ejercicioPersistence.delete(entity.getId());
        EjercicioEntity deleted = em.find(EjercicioEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
        /**
     * Prueba para consultar un ejercicio por nombre.
     *
     *
     */
    @Test
    public void FindEjercicioByNameTest() {
        EjercicioEntity entity = data.get(0);
        EjercicioEntity newEntity = ejercicioPersistence.findByName(entity.getNombre());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getNombre(), newEntity.getNombre());
    } 
    
    
    
}
