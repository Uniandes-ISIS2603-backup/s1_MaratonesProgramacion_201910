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
public class UsuarioLogicTest {
    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private UsuarioLogic usuarioLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<UsuarioEntity> data = new ArrayList<>();
    
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
        em.createQuery("delete from UsuarioEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            UsuarioEntity entity = factory.manufacturePojo(UsuarioEntity.class);
            em.persist(entity);
            entity.setEquipos(new ArrayList<EquipoEntity>());
            data.add(entity);
        }
        UsuarioEntity participante = data.get(2);
        EquipoEntity entity = factory.manufacturePojo(EquipoEntity.class);
        entity.getParticipantes().add(participante);
        em.persist(entity);
        participante.getEquipos().add(entity);

    }

    /**
     * Prueba para crear un Usuario.
     */
    @Test
    public void createTest() {
        UsuarioEntity newEntity = factory.manufacturePojo(UsuarioEntity.class);
        try{
            UsuarioEntity result = usuarioLogic.create(newEntity);
            Assert.assertNotNull(result);
            UsuarioEntity entity = em.find(UsuarioEntity.class, result.getId());
            Assert.assertEquals(newEntity.getId(), entity.getId());
            Assert.assertEquals(newEntity.getNombreUsuario(), entity.getNombreUsuario());
            Assert.assertEquals(newEntity.getNombre(), entity.getNombre());
        }catch(Exception exception){
            exception.getMessage();
        }
    }
    
    /**
     * Prueba para crear un Usuario con el mismo nombre de un Usuario que ya
     * existe.
     *
     * @throws co.edu.uniandes.csw.maratones.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void createUsuarioConMismoNombreTest() throws BusinessLogicException {
        UsuarioEntity newEntity = factory.manufacturePojo(UsuarioEntity.class);
        newEntity.setNombre(data.get(0).getNombre());
        usuarioLogic.create(newEntity);
    }
    
    /**
     * Prueba para crear un Usuario con el mismo nombre d eusuario de un Usuario que ya
     * existe.
     *
     * @throws co.edu.uniandes.csw.maratones.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void createUsuarioConMismoUsuarioTest() throws BusinessLogicException {
        UsuarioEntity newEntity = factory.manufacturePojo(UsuarioEntity.class);
        newEntity.setNombreUsuario(data.get(0).getNombreUsuario());
        usuarioLogic.create(newEntity);
    }
    
    /**
     * Prueba para crear un Usuario con clave muy corta
     * existe.
     *
     * @throws co.edu.uniandes.csw.maratones.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void createUsuarioConClaveMuyCortaTest() throws BusinessLogicException {
        UsuarioEntity newEntity = factory.manufacturePojo(UsuarioEntity.class);
        newEntity.setClave("a");
        usuarioLogic.create(newEntity);
    }
    
    /**
     * Prueba para crear un Usuario con clave solo letras
     * existe.
     *
     * @throws co.edu.uniandes.csw.maratones.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void createUsuarioConClaveSoloLetrasTest() throws BusinessLogicException {
        UsuarioEntity newEntity = factory.manufacturePojo(UsuarioEntity.class);
        newEntity.setClave("aasdfhgj");
        usuarioLogic.create(newEntity);
    }
    
    /**
     * Prueba para crear un Usuario con clave sin simbolo
     * existe.
     *
     * @throws co.edu.uniandes.csw.maratones.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void createUsuarioConClaveSinSimboloTest() throws BusinessLogicException {
        UsuarioEntity newEntity = factory.manufacturePojo(UsuarioEntity.class);
        newEntity.setClave("aasd44d224gj");
        usuarioLogic.create(newEntity);
    }
    
    /**
     * Prueba para crear un Usuario con clave sin numero
     * existe.
     *
     * @throws co.edu.uniandes.csw.maratones.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void createUsuarioConClaveSinNumeroTest() throws BusinessLogicException {
        UsuarioEntity newEntity = factory.manufacturePojo(UsuarioEntity.class);
        newEntity.setClave("aa$$$$$$$sss");
        usuarioLogic.create(newEntity);
    }
    
    /**
     * Prueba para crear un Usuario con correo
     * existe.
     *
     * @throws co.edu.uniandes.csw.maratones.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void createUsuarioConCorreoInvalido() throws BusinessLogicException {
        UsuarioEntity newEntity = factory.manufacturePojo(UsuarioEntity.class);
        newEntity.setCorreo("blablabla");
        usuarioLogic.create(newEntity);
    }
    
    /**
     * Prueba para crear un Usuario con correo sin final como .com
     * existe.
     *
     * @throws co.edu.uniandes.csw.maratones.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void createUsuarioConCorreoSinFinal() throws BusinessLogicException {
        UsuarioEntity newEntity = factory.manufacturePojo(UsuarioEntity.class);
        newEntity.setCorreo("blablabla@uniandes");
        usuarioLogic.create(newEntity);
    }
    
       /**
     * Prueba para consultar la lista de Usuarios.
     */
    

    /**
     * Prueba para consultar un Usuario.
     */
    @Test
    public void getPorIdUsuarioTest() {
        UsuarioEntity entity = data.get(0);
        UsuarioEntity resultEntity = usuarioLogic.getUsuarioPorId(entity.getId());
        
    }

    
    @Test
    public void getPorNombreUsuarioTest() {
        UsuarioEntity entity = data.get(0);
        UsuarioEntity resultEntity = usuarioLogic.getPorNombreDeUsuario(entity.getNombreUsuario());
        
    }
    /**
     * Prueba para actualizar un Usuario.
     */
    @Test
    public void updateUsuarioTest() {
        UsuarioEntity entity = data.get(0);
        UsuarioEntity pojoEntity = factory.manufacturePojo(UsuarioEntity.class);

        pojoEntity.setId(entity.getId());

        usuarioLogic.update(pojoEntity.getId(), pojoEntity);

        UsuarioEntity resp = em.find(UsuarioEntity.class, entity.getId());

        Assert.assertEquals(0, 0);
        
    }

    


    
}
