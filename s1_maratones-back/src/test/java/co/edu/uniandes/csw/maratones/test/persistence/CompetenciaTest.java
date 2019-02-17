/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.maratones.test.persistence;

import co.edu.uniandes.csw.maratones.entities.CompetenciaEntity;
import co.edu.uniandes.csw.maratones.entities.LugarCompetenciaEntity;
import co.edu.uniandes.csw.maratones.entities.PrerequisitoEntity;
import co.edu.uniandes.csw.maratones.persistence.CompetenciaPersistence;
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
public class CompetenciaTest {
    @Inject
    private CompetenciaPersistence competenciaPersistence;
    
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

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(CompetenciaEntity.class.getPackage())
                .addPackage(CompetenciaPersistence.class.getPackage())
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
     * Prueba para crear una competencia.
     *
     *
     */
    @Test
    public void createCompetenciaTest() {
        PodamFactory factory = new PodamFactoryImpl();
        CompetenciaEntity newEntity = factory.manufacturePojo(CompetenciaEntity.class);
        CompetenciaEntity result = competenciaPersistence.create(newEntity);

        Assert.assertNotNull(result);

        CompetenciaEntity entity = em.find(CompetenciaEntity.class, result.getId());

        Assert.assertEquals(newEntity.getId(), entity.getId());
    }
    
    /**
     * Prueba para eliminar un Competencia.
     *
     *
     */
    @Test
    public void deleteCompetenciaTest() {
        CompetenciaEntity entity = data.get(0);
        System.out.println(entity.getId() +" El Id de entity");
        competenciaPersistence.delete(entity.getId());
        CompetenciaEntity deleted = em.find(CompetenciaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
        /**
     * Prueba para consultar una competencia por nombre.
     *
     *
     */
    @Test
    public void FindEditorialByNameTest() {
        CompetenciaEntity entity = data.get(0);
        CompetenciaEntity newEntity = competenciaPersistence.findByName(entity.getNombre());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getNombre(), newEntity.getNombre());
    }

}
