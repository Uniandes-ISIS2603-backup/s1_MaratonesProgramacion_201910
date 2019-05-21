/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.maratones.test.logic;

import co.edu.uniandes.csw.maratones.ejb.CompetenciaLogic;
import co.edu.uniandes.csw.maratones.ejb.EjercicioLogic;
import co.edu.uniandes.csw.maratones.ejb.LenguajeLogic;
import co.edu.uniandes.csw.maratones.ejb.UsuarioLogic;
import co.edu.uniandes.csw.maratones.entities.CompetenciaEntity;
import co.edu.uniandes.csw.maratones.entities.EjercicioEntity;
import co.edu.uniandes.csw.maratones.entities.CompetenciaEntity;
import co.edu.uniandes.csw.maratones.entities.LenguajeEntity;
import co.edu.uniandes.csw.maratones.entities.UsuarioEntity;
import co.edu.uniandes.csw.maratones.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.maratones.persistence.CompetenciaPersistence;
import co.edu.uniandes.csw.maratones.persistence.EjercicioPersistence;
import co.edu.uniandes.csw.maratones.persistence.UsuarioPersistence;
import java.util.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;
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
public class CompetenciaLogicTest {
 
    @Inject
    private CompetenciaLogic competenciaLogic;
    
   private PodamFactory factory = new PodamFactoryImpl();
    
   
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
     * Lista que tiene los datos de prueba.
     */
    private List<CompetenciaEntity> competenciaData = new ArrayList<CompetenciaEntity>();
    
    private List<UsuarioEntity> usuarioData = new ArrayList<UsuarioEntity>();
    
    private List<EjercicioEntity> ejercicioData = new ArrayList<EjercicioEntity>();
    
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
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(CompetenciaEntity.class.getPackage())
                .addPackage(CompetenciaLogic.class.getPackage())
                .addPackage(CompetenciaPersistence.class.getPackage())
                .addPackage(UsuarioEntity.class.getPackage())
                .addPackage(UsuarioLogic.class.getPackage())
                .addPackage(UsuarioPersistence.class.getPackage())
                .addPackage(EjercicioEntity.class.getPackage())
                .addPackage(EjercicioLogic.class.getPackage())
                .addPackage(EjercicioPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    

    /**
     * Limpia las tablas que están implicadas en la prueba.
     *
     *
     */
    private void clearData() {
        
       
        
        em.createQuery("DELETE FROM UsuarioEntity").executeUpdate();
         em.createQuery("DELETE FROM EjercicioEntity").executeUpdate();
        em.createQuery("delete from CompetenciaEntity").executeUpdate();
        em.createQuery("delete from EquipoEntity").executeUpdate();
        usuarioData.clear();
        ejercicioData.clear();
        competenciaData.clear();
        
    }

     /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     *
     *
     */
    private void insertData() {
        
        for (int i = 0; i < 3; i++) {
            UsuarioEntity usuario = factory.manufacturePojo(UsuarioEntity.class);
            em.persist(usuario);
            usuarioData.add(usuario);
        }
        
        for (int i = 0; i < 3; i++) {
            EjercicioEntity ejercicio = factory.manufacturePojo(EjercicioEntity.class);
            em.persist(ejercicio);
            ejercicioData.add(ejercicio);
        }
        
        for (int i = 0; i < 3; i++) {
            CompetenciaEntity competencia = factory.manufacturePojo(CompetenciaEntity.class);
            competencia.setJueces(usuarioData);
            List<EjercicioEntity> ejer = new ArrayList<EjercicioEntity>();
            ejer.add(ejercicioData.get(i));
            ejercicioData.get(i).setCompetencia(competencia);
            competencia.setEjercicioEntitys(ejer);
            Date inicio= competencia.getFechaInicio();
            Calendar cal = Calendar.getInstance();
            cal.setTime(inicio);
            cal.add(Calendar.HOUR_OF_DAY,10);
            Date fin = cal.getTime();
            competencia.setFechaFin(fin);
            em.persist(competencia);
            competenciaData.add(competencia);
        }
    }

     /**
     * Prueba para crear una competencia .
     *
     *
     * @throws co.edu.uniandes.csw.maratones.exceptions.BusinessLogicException
     */
    @Test
    public void createCompetenciaTest() throws BusinessLogicException {
        UsuarioEntity usuario = factory.manufacturePojo(UsuarioEntity.class);
        ArrayList usuarios = new ArrayList <>();
        usuarios.add(usuario);
        EjercicioEntity ejercicio = factory.manufacturePojo(EjercicioEntity.class);
        ejercicio.setDescripcion("Descripcion");
        ejercicio.setNombre("Nombre");
        ejercicio.setPuntaje(1);
        ejercicio.setNivel(1);
        
        ArrayList ejercicios = new ArrayList<>();
        ejercicios.add (ejercicio);
        CompetenciaEntity newEntity = factory.manufacturePojo(CompetenciaEntity.class);
            newEntity.setEjercicioEntitys(ejercicios);
            newEntity.setJueces(usuarios);
            Date inicio= newEntity.getFechaInicio();
            Calendar cal = Calendar.getInstance();
            cal.setTime(inicio);
            cal.add(Calendar.HOUR_OF_DAY,10);
            Date fin = cal.getTime();
            newEntity.setFechaFin(fin);
            ejercicio.setCompetencia(newEntity);
        CompetenciaEntity result = competenciaLogic.create(newEntity);

        Assert.assertNotNull(result);
        CompetenciaEntity entity = em.find(CompetenciaEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertTrue("La lista de ejercicios está vacia",!result.getEjercicioEntitys().isEmpty());
        Assert.assertEquals(result.getEjercicioEntitys(), entity.getEjercicioEntitys());
        
    }
    
    /**
     * Prueba para consultar la lista de competencias.
     */
    @Test
    public void getCompetenciasTest() {
        List<CompetenciaEntity> list = competenciaLogic.getCompetencias();
        Assert.assertEquals(competenciaData.size(), list.size());
        for (CompetenciaEntity entity : list) {
            boolean found = false;
            for (CompetenciaEntity storedEntity : competenciaData) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
    /**
     * Prueba para consultar una Competencia.
     */
    @Test
    public void getCompetenciaTest() {
        CompetenciaEntity entity = competenciaData.get(0);
        CompetenciaEntity resultEntity = competenciaLogic.getCompetencia(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getNombre(), resultEntity.getNombre());
        Assert.assertEquals(entity.getCondiciones(), resultEntity.getCondiciones());
        Assert.assertEquals(entity.getDescripcion(), resultEntity.getDescripcion() );
        Assert.assertEquals(entity.getPuntos(),resultEntity.getPuntos() );
        Assert.assertEquals(entity.getNivel(), resultEntity.getNivel() );
        Assert.assertEquals("Falla los lenguajes",entity.getLenguajes(),resultEntity.getLenguajes() );
        Assert.assertEquals("Falla los ejercicios",entity.getEjercicioEntitys(), resultEntity.getEjercicioEntitys());
        Assert.assertEquals("Falla los equipos",entity.getEquipos(), resultEntity.getEquipos());
        
//        Assert.assertEquals(entity.getJueces(), resultEntity.getJueces());
        ;
    }
    
    /**
     * Prueba para actualizar un Competencia.
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test
    public void updateCompetenciaTest() throws BusinessLogicException {
        CompetenciaEntity entity = competenciaData.get(0);
        CompetenciaEntity pojoEntity = factory.manufacturePojo(CompetenciaEntity.class);
        pojoEntity.setJueces(usuarioData);
        Date inicio = pojoEntity.getFechaInicio();
        Calendar cal = Calendar.getInstance();
            cal.setTime(inicio);
            cal.add(Calendar.HOUR_OF_DAY,10);
            Date fin = cal.getTime();
        pojoEntity.setFechaFin(fin);
        pojoEntity.setId(entity.getId());
        pojoEntity.setEjercicioEntitys(ejercicioData);
        competenciaLogic.updateCompetencia(pojoEntity.getId(), pojoEntity);
        CompetenciaEntity resp = em.find(CompetenciaEntity.class, entity.getId());
        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getNombre(), resp.getNombre());
        Assert.assertEquals(pojoEntity.getCondiciones(), resp.getCondiciones());
        Assert.assertEquals(pojoEntity.getDescripcion(), resp.getDescripcion() );
//        Assert.assertEquals(pojoEntity.getEjercicioEntitys(), resp.getEjercicioEntitys());
        Assert.assertEquals(pojoEntity.getEquipos(), resp.getEquipos());
//        Assert.assertEquals(pojoEntity.getJueces(), resp.getJueces());
        Assert.assertEquals(pojoEntity.getPuntos(),resp.getPuntos() );
        Assert.assertEquals(pojoEntity.getLenguajes(),resp.getLenguajes() );
        Assert.assertEquals(pojoEntity.getNivel(), resp.getNivel() );
    }
    /**
     * Prueba para eliminar una competencia.
     *
     *
     */
    @Test
    public void deleteCompetenciaTest() {
        CompetenciaEntity entity = competenciaData.get(0);
        competenciaLogic.delete(entity.getId());
        CompetenciaEntity deleted = em.find(CompetenciaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }   
}
