/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.maratones.test.logic;

import co.edu.uniandes.csw.maratones.ejb.LugarCompetenciaLogic;
import co.edu.uniandes.csw.maratones.entities.LugarCompetenciaEntity;
import co.edu.uniandes.csw.maratones.exceptions.BusinessLogicException;
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
public class LugarCompetenciaLogicTest {
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(LugarCompetenciaEntity.class.getPackage())
                .addPackage(LugarCompetenciaLogic.class.getPackage())
                .addPackage(LugarCompetenciaPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    @Inject
    private LugarCompetenciaLogic lugarCompetenciaLogic;
    
   
    
    /**
     * Lista que tiene los datos de prueba.
     */
    private List<LugarCompetenciaEntity> data = new ArrayList<LugarCompetenciaEntity>();
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
        em.createQuery("delete from LugarCompetenciaEntity").executeUpdate();
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

            LugarCompetenciaEntity entity = factory.manufacturePojo(LugarCompetenciaEntity.class);

          
            em.persist(entity);

            data.add(entity);
        }
    }

     /**
     * Prueba para crear un Prerequisito.
     *
     *
     */
    @Test
    public void createLugarCompetenciaTest() throws BusinessLogicException {
        PodamFactory factory = new PodamFactoryImpl();
        LugarCompetenciaEntity newEntity = factory.manufacturePojo(LugarCompetenciaEntity.class);
       
        LugarCompetenciaEntity result = lugarCompetenciaLogic.create(newEntity);

        Assert.assertNotNull(result);
        
        LugarCompetenciaEntity entity = em.find(LugarCompetenciaEntity.class, result.getId());


        Assert.assertEquals(newEntity.getId(), entity.getId());
    }
    
    /**
     * Prueba para eliminar un Prerequisito.
     *
     *
     */
    @Test
    public void deleteLugarCompetenciaTest() {
        LugarCompetenciaEntity entity = data.get(0);
        lugarCompetenciaLogic.delete(entity.getId());
        LugarCompetenciaEntity deleted = em.find(LugarCompetenciaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
}
