/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.maratones.test.logic;

import co.edu.uniandes.csw.maratones.ejb.CompetenciaLogic;
import co.edu.uniandes.csw.maratones.entities.CompetenciaEntity;
import co.edu.uniandes.csw.maratones.exceptions.BusinessLogicException;
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
public class CompetenciaLogicTest {
 @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(CompetenciaEntity.class.getPackage())
                .addPackage(CompetenciaLogic.class.getPackage())
                .addPackage(CompetenciaPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    @Inject
    private CompetenciaLogic competenciaLogic;
    
   
    
    /**
     * Lista que tiene los datos de prueba.
     */
    private List<CompetenciaEntity> data = new ArrayList<CompetenciaEntity>();
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
        em.createQuery("delete from CompetenciaEntity").executeUpdate();
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

            CompetenciaEntity entity = factory.manufacturePojo(CompetenciaEntity.class);

          
            em.persist(entity);

            data.add(entity);
        }
    }

     /**
     * Prueba para crear un Prerequisito.
     *
     *
     * @throws co.edu.uniandes.csw.maratones.exceptions.BusinessLogicException
     */
    @Test
    public void createCompetenciaTest() throws BusinessLogicException {
        PodamFactory factory = new PodamFactoryImpl();
        CompetenciaEntity newEntity = factory.manufacturePojo(CompetenciaEntity.class);
       
        CompetenciaEntity result = competenciaLogic.create(newEntity);

        Assert.assertNotNull(result);

        CompetenciaEntity entity = em.find(CompetenciaEntity.class, result.getId());
        

        Assert.assertEquals(newEntity.getId(), entity.getId());
    }
    
    /**
     * Prueba para eliminar un Prerequisito.
     *
     *
     */
    @Test
    public void deleteCompetenciaTest() {
        CompetenciaEntity entity = data.get(0);
        competenciaLogic.delete(entity.getId());
        CompetenciaEntity deleted = em.find(CompetenciaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }   
}
