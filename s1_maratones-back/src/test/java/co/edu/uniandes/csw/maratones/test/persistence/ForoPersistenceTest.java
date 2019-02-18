/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.maratones.test.persistence;

import co.edu.uniandes.csw.maratones.entities.ForoEntity;
import co.edu.uniandes.csw.maratones.persistence.ForoPersistence;
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
 * @author Juan Felipe Peña
 */

@RunWith(Arquillian.class)
public class ForoPersistenceTest {
    
    @Inject
    private ForoPersistence fp;
    
    @PersistenceContext
    private EntityManager em;
    
    @Deployment
    public static JavaArchive deployment(){
        
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ForoEntity.class.getPackage())
                .addPackage(ForoPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    @Test
    public void createForoTest(){
        
        PodamFactory factory = new PodamFactoryImpl();
        ForoEntity newEntity = factory.manufacturePojo(ForoEntity.class);
        
        ForoEntity fe = fp.create(newEntity);
        
        Assert.assertNotNull(fe);
        
        ForoEntity entity = em.find(ForoEntity.class, fe.getId());
        
        Assert.assertEquals(newEntity.getNombre(), entity.getNombre());    
    }
}
