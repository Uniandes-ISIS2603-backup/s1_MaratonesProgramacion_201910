/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.maratones.test.logic;

import co.edu.uniandes.csw.maratones.ejb.LugarCompetenciaLogic;
import co.edu.uniandes.csw.maratones.entities.CompetenciaEntity;
import co.edu.uniandes.csw.maratones.entities.LugarCompetenciaEntity;
import co.edu.uniandes.csw.maratones.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.maratones.persistence.LugarCompetenciaPersistence;
import java.util.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
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
   
     private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private LugarCompetenciaLogic lugarCompetenciaLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<LugarCompetenciaEntity> lugarCompetenciaData = new ArrayList<LugarCompetenciaEntity>();

    private List<CompetenciaEntity> competenciaData = new ArrayList();
    
     /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(LugarCompetenciaEntity.class.getPackage())
                .addPackage(LugarCompetenciaLogic.class.getPackage())
                .addPackage(LugarCompetenciaPersistence.class.getPackage())
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
        em.createQuery("delete from LugarCompetenciaEntity").executeUpdate();
        em.createQuery("delete from CompetenciaEntity").executeUpdate();
    }
    
    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            CompetenciaEntity competencia = factory.manufacturePojo(CompetenciaEntity.class);
            Date inicio = competencia.getFechaInicio();
            Calendar cal = Calendar.getInstance();
            cal.setTime(inicio);
            cal.add(Calendar.HOUR_OF_DAY,10);
            Date fin = cal.getTime();
            competencia.setFechaFin(fin);
            em.persist(competencia);
            competenciaData.add(competencia);
        }
        for (int i = 0; i < 3; i++) {
            LugarCompetenciaEntity entity = factory.manufacturePojo(LugarCompetenciaEntity.class);
            entity.setCompetencia(competenciaData.get(0));

            em.persist(entity);
            lugarCompetenciaData.add(entity);
        }
    }
    /**
     * Prueba para crear un LugarCompetencia
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test
    public void createLugarCompetenciaTest() throws BusinessLogicException {
        LugarCompetenciaEntity newEntity = factory.manufacturePojo(LugarCompetenciaEntity.class);
        newEntity.setCompetencia(competenciaData.get(0));
        Date inicio = competenciaData.get(0).getFechaInicio();
        Calendar cal = Calendar.getInstance();
            cal.setTime(inicio);
            cal.add(Calendar.HOUR_OF_DAY,5);
            Date fin = cal.getTime();
        newEntity.setFecha(fin);
        LugarCompetenciaEntity result = lugarCompetenciaLogic.createLugarCompetencia(newEntity);
        Assert.assertNotNull(result);
        LugarCompetenciaEntity entity = em.find(LugarCompetenciaEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getFecha(), entity.getFecha());
        Assert.assertEquals(newEntity.getUbicaciones(), entity.getUbicaciones());
    }
    
    /**
     * Prueba para crear un LugarCompetencia con ubicaiones inválido
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void createLugarCompenteciaTestConUbicacionesInvalido() throws BusinessLogicException {
        LugarCompetenciaEntity newEntity = factory.manufacturePojo(LugarCompetenciaEntity.class);
        newEntity.setCompetencia(competenciaData.get(0));
        newEntity.setUbicaciones("");
        lugarCompetenciaLogic.createLugarCompetencia(newEntity);
    }
    
    /**
     * Prueba para crear un LugarCompetencia con ubicaiones inválido
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void createLugarCompetenciaTestConUbicacionesInvalido2() throws BusinessLogicException {
        LugarCompetenciaEntity newEntity = factory.manufacturePojo(LugarCompetenciaEntity.class);
        newEntity.setCompetencia(competenciaData.get(0));
        newEntity.setUbicaciones(null);
        System.out.println(newEntity.getId());
        lugarCompetenciaLogic.createLugarCompetencia(newEntity);
    }
    
    
    
    /**
     * Prueba para crear un LugarCompetencia con ubiciones existente.
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void createLugarCompetenciaTestConUbicacionesExistente() throws BusinessLogicException {
        LugarCompetenciaEntity newEntity = factory.manufacturePojo(LugarCompetenciaEntity.class);
        newEntity.setCompetencia(competenciaData.get(0));
        newEntity.setUbicaciones(lugarCompetenciaData.get(0).getUbicaciones());
        lugarCompetenciaLogic.createLugarCompetencia(newEntity);
        // debe revisarse en el create que la ubicacion no exista.
    }
    
    /**
     * Prueba para crear un LugarCompetencia con una competencia que no existe.
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void createLugarCompetenciaTestConCompetenciaInexistente() throws BusinessLogicException {
        LugarCompetenciaEntity newEntity = factory.manufacturePojo(LugarCompetenciaEntity.class);
        CompetenciaEntity competenciaEntity = factory.manufacturePojo(CompetenciaEntity.class);
        competenciaEntity.setId(Long.MIN_VALUE);
        newEntity.setCompetencia(competenciaEntity);
        lugarCompetenciaLogic.createLugarCompetencia(newEntity);
    }
    
    /**
     * Prueba para crear un LugarCompetencia con competencia en null.
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void createLugarCompetenciaTestConNullCompetencia() throws BusinessLogicException {
        LugarCompetenciaEntity newEntity = factory.manufacturePojo(LugarCompetenciaEntity.class);
        newEntity.setCompetencia(null);
        lugarCompetenciaLogic.createLugarCompetencia(newEntity);
    }
    
    /**
     * Prueba para consultar la lista de Books.
     */
    @Test
    public void getLugarCompetenciasTest() {
        List<LugarCompetenciaEntity> list = lugarCompetenciaLogic.getLugarCompetencias();
        Assert.assertEquals(lugarCompetenciaData.size(), list.size());
        for (LugarCompetenciaEntity entity : list) {
            boolean found = false;
            for (LugarCompetenciaEntity storedEntity : lugarCompetenciaData) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
     /**
     * Prueba para consultar un LugarCompetencia.
     */
    @Test
    public void getLugarCompetenciaTest() {
        LugarCompetenciaEntity entity = lugarCompetenciaData.get(0);
        LugarCompetenciaEntity resultEntity = lugarCompetenciaLogic.getLugarCompetencia(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getFecha(), resultEntity.getFecha());
        Assert.assertEquals(entity.getUbicaciones(), resultEntity.getUbicaciones());
    }
    
     /**
     * Prueba para actualizar un LugarCompetencia.
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test
    public void updateLugarCompetenciaTest() throws BusinessLogicException {
        LugarCompetenciaEntity entity = lugarCompetenciaData.get(0);
        LugarCompetenciaEntity pojoEntity = factory.manufacturePojo(LugarCompetenciaEntity.class);
        pojoEntity.setId(entity.getId());
        lugarCompetenciaLogic.updateLugarCompetencia(pojoEntity.getId(), pojoEntity);
        LugarCompetenciaEntity resp = em.find(LugarCompetenciaEntity.class, entity.getId());
        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getFecha(), resp.getFecha());
        Assert.assertEquals(pojoEntity.getUbicaciones(), resp.getUbicaciones());
    }
    
    /**
     * Prueba para actualizar un LugarCompetencia con ubicaciones inválido.
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void updateLugarCompetenciaConUbicacionesInvalidoTest() throws BusinessLogicException {
        LugarCompetenciaEntity entity = lugarCompetenciaData.get(0);
        LugarCompetenciaEntity pojoEntity = factory.manufacturePojo(LugarCompetenciaEntity.class);
        pojoEntity.setUbicaciones("");
        pojoEntity.setId(entity.getId());
        lugarCompetenciaLogic.updateLugarCompetencia(pojoEntity.getId(), pojoEntity);
    }
    
    /**
     * Prueba para actualizar un LugarCompetencia con Ubicaciones inválido.
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void updateLugarCompetenciaConUbicacionesInvalidoTest2() throws BusinessLogicException {
        LugarCompetenciaEntity entity = lugarCompetenciaData.get(0);
        LugarCompetenciaEntity pojoEntity = factory.manufacturePojo(LugarCompetenciaEntity.class);
        pojoEntity.setUbicaciones(null);
        pojoEntity.setId(entity.getId());
        lugarCompetenciaLogic.updateLugarCompetencia(pojoEntity.getId(), pojoEntity);
    }
    
    /**
     * Prueba para eliminar un LugarCompetencia.
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test
    public void deleteLugarCompetenciaTest() throws BusinessLogicException {
        LugarCompetenciaEntity entity = lugarCompetenciaData.get(0);
        lugarCompetenciaLogic.deleteLugarCompetencia(entity.getId());
        LugarCompetenciaEntity deleted = em.find(LugarCompetenciaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
}
