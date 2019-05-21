/*
MIT License

Copyright (c) 2019 Universidad de los Andes - ISIS2603

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 */
package co.edu.uniandes.csw.maratones.test.logic;

import co.edu.uniandes.csw.maratones.ejb.EquipoLogic;
import co.edu.uniandes.csw.maratones.entities.UsuarioEntity;
import co.edu.uniandes.csw.maratones.ejb.UsuarioLogic;
import co.edu.uniandes.csw.maratones.entities.EquipoEntity;
import co.edu.uniandes.csw.maratones.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.maratones.persistence.UsuarioPersistence;
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
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 * Pruebas de logica para la cascara.
 *
 * @author camila
 */
@RunWith(Arquillian.class)
public class EquipoLogicTest {
    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private EquipoLogic equipoLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<EquipoEntity> data = new ArrayList<>();
    
    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(UsuarioEntity.class.getPackage())
                .addPackage(UsuarioLogic.class.getPackage())
                .addPackage(UsuarioPersistence.class.getPackage())
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
        em.createQuery("delete from EquipoEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            EquipoEntity entity = factory.manufacturePojo(EquipoEntity.class);
            em.persist(entity);
            entity.setParticipantes(new ArrayList<UsuarioEntity>());
            data.add(entity);
        }
        UsuarioEntity participante = factory.manufacturePojo(UsuarioEntity.class);
        EquipoEntity newEntity = data.get(0);
        newEntity.getParticipantes().add(participante);
        em.persist(newEntity);
        participante.getEquipos().add(newEntity);

    }

    /**
     * Prueba para crear un Usuario.
     */
    @Test
    public void createTest() {
        EquipoEntity newEntity = factory.manufacturePojo(EquipoEntity.class);
        newEntity.setParticipantes(new ArrayList<UsuarioEntity>());
        try{
            EquipoEntity result = equipoLogic.create(newEntity);
            Assert.assertNotNull(result);
            EquipoEntity entity = em.find(EquipoEntity.class, result.getId());
            Assert.assertEquals(newEntity.getId(), entity.getId());
        }catch(Exception exception){
            exception.getMessage();
        }
    }
    
    

    /**
     * Prueba para consultar un Equipo.
     */
    @Test
    public void getEquipoTest() {
        EquipoEntity entity = data.get(0);
        EquipoEntity resultEntity = equipoLogic.getEquipo(entity.getId());
       
    }

    /**
     * Prueba para actualizar un Equipo.
     *
     * @throws co.edu.uniandes.csw.equipostore.exceptions.BusinessLogicException
     */
    @Test
    public void updateEquipoTest() throws Exception {
        EquipoEntity entity = data.get(0);
        EquipoEntity pojoEntity = factory.manufacturePojo(EquipoEntity.class);
        pojoEntity.setId(entity.getId());
        equipoLogic.update(pojoEntity.getId(), pojoEntity);
        EquipoEntity resp = em.find(EquipoEntity.class, entity.getId());
       
    }

    

 
    
    
    
}
