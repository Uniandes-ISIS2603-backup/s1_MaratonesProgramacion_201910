/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.maratones.test.logic;

import co.edu.uniandes.csw.maratones.ejb.BlogLogic;
import co.edu.uniandes.csw.maratones.entities.BlogEntity;
import co.edu.uniandes.csw.maratones.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.maratones.persistence.BlogPersistence;
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
 * @author c.mendez11
 */
@RunWith(Arquillian.class)
public class BlogLogicTest {
     /**
     * Lista que tiene los datos de prueba.
     */
    private List<BlogEntity> data = new ArrayList<BlogEntity>();
     /**
     * Variable para martcar las transacciones del em anterior cuando se
     * crean/borran datos para las pruebas.
     */
    @Inject
    UserTransaction utx;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    private BlogLogic bl;
    
    private PodamFactory factory = new PodamFactoryImpl();
     
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(BlogEntity.class.getPackage())
                .addPackage(BlogLogic.class.getPackage())
                .addPackage(BlogPersistence.class.getPackage())
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
        em.createQuery("delete from BlogEntity").executeUpdate();
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

            BlogEntity entity = factory.manufacturePojo(BlogEntity.class);

            em.persist(entity);

            data.add(entity);
        }
    }
      @Test
    public void createBlogTest() throws BusinessLogicException{
       
        BlogEntity newEntity = factory.manufacturePojo(BlogEntity.class);
        BlogEntity result = bl.createBlog(newEntity);
        Assert.assertNotNull(result);
        BlogEntity entity = em.find(BlogEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(entity.getNombre(), newEntity.getNombre());
        Assert.assertEquals(entity.getDescripcion(), newEntity.getDescripcion());
    }
    @Test(expected = BusinessLogicException.class)
    public void createBlogTestNombreNull() throws BusinessLogicException {
        BlogEntity newEntity = factory.manufacturePojo(BlogEntity.class);
        newEntity.setNombre(null);
        newEntity.setDescripcion("d");
        bl.createBlog(newEntity);
    }
    @Test(expected = BusinessLogicException.class)
    public void createBlogTestNombre60() throws BusinessLogicException {
        BlogEntity newEntity = factory.manufacturePojo(BlogEntity.class);
        newEntity.setNombre("vVit__+a*xumvBu(kV-q=a_$Z9j{iuT!M-4*Y:-Dw,ELVT=/@iEQ.RH{8UhVT");
        newEntity.setDescripcion("d");
        bl.createBlog(newEntity);
    }
    @Test(expected = BusinessLogicException.class)
    public void createBlogTestDescripcionNull() throws BusinessLogicException {
        BlogEntity newEntity = factory.manufacturePojo(BlogEntity.class);
        newEntity.setNombre("d");
        newEntity.setDescripcion(null);
        bl.createBlog(newEntity);
    }
    @Test(expected = BusinessLogicException.class)
    public void createBlogTestDescripcion500() throws BusinessLogicException {
        BlogEntity newEntity = factory.manufacturePojo(BlogEntity.class);
        newEntity.setNombre("d");
        newEntity.setDescripcion("WX6:5imn.WSKvMkPh]%%SAzV,{,}@2P_bz=7L=8nRfxb9tfuA-]Z,uPA*%mh-G$2emSn6S7xu4D2Ah}F,]GUxgBRx[qJ)n-/f4uAS;wuT5FxuBXd:z/Pg)ae,YfFSL#U[r6nUq2+}+cWZPRb7Y]kB#mpgz+vePyv6n?/4fg2S*UPj,i5BrkB/ATrmkxK;ix7?8t}mG8pa@FU-p.wTj}d{-7SQG:+W6$LxdkBhH72igH7M8tHVe]bT$qXfai!;QGB!{nF]3G74Yx)%BCE8a7HDpP@jt5p8.nx[b5R6$,ubx2wLrd7)rMY[Zj}g!)V*)pJZVAphHuYicyTY#=m%Yk2TXVCRuf,gWyFH:{Snym;5c=3Vjm27YAiy4B_%hAZKwLum8(87(bY,hcA,7Gt3DFdGvCF*7J%W$rA_Ni&tvYEvCN#gUz6rzHcTWEDZeZS*@}{L.$S5H5@!vY=pTqg*gA9*]_:SL9k/k?CRH$T%Rh,YgA2PD?X%;x+Z");
        bl.createBlog(newEntity);
    }
}
