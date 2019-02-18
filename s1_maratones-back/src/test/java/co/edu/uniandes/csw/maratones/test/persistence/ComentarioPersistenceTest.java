/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.maratones.test.persistence;

import co.edu.uniandes.csw.maratones.entities.ComentarioEntity;
import co.edu.uniandes.csw.maratones.persistence.ComentarioPersistence;
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
public class ComentarioPersistenceTest {
    
    @Inject
    private ComentarioPersistence cp;
    
    @PersistenceContext
    private EntityManager em;
    
    @Deployment
    public static JavaArchive deployment(){
        
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ComentarioEntity.class.getPackage())
                .addPackage(ComentarioPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    @Test
    public void createComentarioTest(){
        
        PodamFactory factory = new PodamFactoryImpl();
        ComentarioEntity newEntity = factory.manufacturePojo(ComentarioEntity.class);
        
        ComentarioEntity ce = cp.create(newEntity);
        
        Assert.assertNotNull(ce);
        
        ComentarioEntity entity = em.find(ComentarioEntity.class, ce.getId());
        
        Assert.assertEquals(newEntity.getMensaje(), entity.getMensaje());    
    }
}
