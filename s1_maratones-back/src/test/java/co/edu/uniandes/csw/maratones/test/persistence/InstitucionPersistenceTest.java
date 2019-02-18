/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.maratones.test.persistence;
import co.edu.uniandes.csw.maratones.entities.InstitucionEntity;
import co.edu.uniandes.csw.maratones.persistence.InstitucionPersistence;
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
public class InstitucionPersistenceTest {
    @PersistenceContext
    private EntityManager em; 
    @Inject
    private InstitucionPersistence ip;
     @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(InstitucionEntity.class.getPackage())
                .addPackage(InstitucionPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    @Test
    public void createInstitucionTest() {
        PodamFactory factory = new PodamFactoryImpl();
        InstitucionEntity newEntity = factory.manufacturePojo(InstitucionEntity.class);
        InstitucionEntity result = ip.create(newEntity);
        Assert.assertNotNull(result);

        InstitucionEntity entity = em.find(InstitucionEntity.class, result.getId());

        Assert.assertEquals(newEntity.getId(), entity.getId());
    }
}
