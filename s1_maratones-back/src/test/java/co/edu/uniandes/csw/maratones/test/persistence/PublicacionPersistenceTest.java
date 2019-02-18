/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.maratones.test.persistence;
import co.edu.uniandes.csw.maratones.entities.PublicacionEntity;
import co.edu.uniandes.csw.maratones.persistence.PublicacionPersistence;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author c.mendez11
 */
@RunWith(Arquillian.class)
public class PublicacionPersistenceTest {
    @PersistenceContext
    private EntityManager em; 
    @Inject
    private PublicacionPersistence pp;
     @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(PublicacionEntity.class.getPackage())
                .addPackage(PublicacionPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    @Test
    public void createPublicacionTest() {
        PodamFactory factory = new PodamFactoryImpl();
        PublicacionEntity newEntity = factory.manufacturePojo(PublicacionEntity.class);
        PublicacionEntity result = pp.create(newEntity);
        Assert.assertNotNull(result);

        PublicacionEntity entity = em.find(PublicacionEntity.class, result.getId());

        Assert.assertEquals(newEntity.getId(), entity.getId());
    }
}
