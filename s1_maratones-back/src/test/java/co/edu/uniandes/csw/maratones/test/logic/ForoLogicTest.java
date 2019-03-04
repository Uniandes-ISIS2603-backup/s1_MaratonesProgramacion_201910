/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.maratones.test.logic;

import co.edu.uniandes.csw.maratones.ejb.ForoLogic;
import co.edu.uniandes.csw.maratones.entities.ComentarioEntity;
import co.edu.uniandes.csw.maratones.entities.ForoEntity;
import co.edu.uniandes.csw.maratones.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.maratones.persistence.ForoPersistence;
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
 * @author estudiante
 */
@RunWith(Arquillian.class)
public class ForoLogicTest {
    
    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private ForoLogic foroLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<ForoEntity> data = new ArrayList<ForoEntity>();

    private List<ComentarioEntity> comentariosData = new ArrayList();
    
    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ForoEntity.class.getPackage())
                .addPackage(ForoLogic.class.getPackage())
                .addPackage(ForoPersistence.class.getPackage())
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
        em.createQuery("delete from ComentarioEntity").executeUpdate();
        em.createQuery("delete from ForoEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            ForoEntity foro = factory.manufacturePojo(ForoEntity.class);
            em.persist(foro);
            data.add(foro);
        }
        for (int i = 0; i < 3; i++) {
            ComentarioEntity entity = factory.manufacturePojo(ComentarioEntity.class);
            entity.setForo(data.get(0));

            em.persist(entity);
            comentariosData.add(entity);
        }
    }
    
    @Test
    public void createForoTest() throws BusinessLogicException
    {
        ForoEntity newEntity = factory.manufacturePojo(ForoEntity.class);
        ForoEntity result = foroLogic.createForo(newEntity);
        Assert.assertNotNull(result);
        ForoEntity entity = em.find(ForoEntity.class, result.getId());
        Assert.assertEquals(newEntity.getNombre(), entity.getNombre());
    }
}
