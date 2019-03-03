/*
MIT License

Copyright (c) 2017 Universidad de los Andes - ISIS2603

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
package co.edu.uniandes.csw.maratones.ejb;

import co.edu.uniandes.csw.maratones.entities.ComentarioEntity;
import co.edu.uniandes.csw.maratones.entities.ForoEntity;
import co.edu.uniandes.csw.maratones.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.maratones.persistence.ForoPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Juan Felipe Peña
 */
@Stateless
public class ForoLogic {
    
    private static final Logger LOGGER = Logger.getLogger(ForoLogic.class.getName());

    @Inject
    private ForoPersistence persistence; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.

    public ForoEntity createForo(ForoEntity foroEntity) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "Inicia proceso de creación del foro");

        if (persistence.findByName(foroEntity.getNombre()) != null) {
            throw new BusinessLogicException("Ya existe una foro con el nombre \"" + foroEntity.getNombre() + "\"");
        }
        if(foroEntity.getVotosAFavor()<0|| foroEntity.getVotosEnContra()<0)
        {
            throw new BusinessLogicException("Los votos no pueden ser números negativos.");
        }
        if("".equals(foroEntity.getNombre()))
        {
            throw new BusinessLogicException("El nombre del foro no puede ser vacío.");
        }
        // Invoca la persistencia para crear la editorial
        persistence.create(foroEntity);
        LOGGER.log(Level.INFO, "Termina proceso de creación del foro");
        return foroEntity;
    }
    
    /**
     *
     * Obtener todos los foros existentes en la base de datos.
     *
     * @return una lista de foros.
     */
    public List<ForoEntity> getForos() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los foros");
        // Note que, por medio de la inyección de dependencias se llama al método "findAll()" que se encuentra en la persistencia.
        List<ForoEntity> foros = persistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todos los foros");
        return foros;
    }
    
    /**
     *
     * Actualizar un foro.
     *
     * @param forosId: id de la editorial para buscarla en la base de
     * datos.
     * @param foroEntity: editorial con los cambios para ser actualizada,
     * por ejemplo el nombre.
     * @return la editorial con los cambios actualizados en la base de datos.
     */
    public ForoEntity updateForo(Long forosId,ForoEntity foroEntity) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el foro con id = {0}", forosId);
        // Note que, por medio de la inyección de dependencias se llama al método "update(entity)" que se encuentra en la persistencia.
        ForoEntity newEntity = persistence.update(foroEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar el foro con id = {0}", foroEntity.getId());
        return newEntity;
    }

    /**
     * Borrar un foro
     *
     * @param forosId: id del foro a borrar

     */
    public void deleteForo(Long forosId)
    {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar el foro con id = {0}", forosId);
        // Note que, por medio de la inyección de dependencias se llama al método "delete(id)" que se encuentra en la persistencia.
        persistence.delete(forosId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar el foro con id = {0}", forosId);
    }
}