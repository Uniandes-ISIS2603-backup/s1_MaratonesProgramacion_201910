/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.maratones.persistence;

import co.edu.uniandes.csw.maratones.entities.SubmissionEntity;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Angel Rodriguez aa.rodriguezv
 */
@Stateless // EJB usted es un objeto que el ciclo de vida lo gestiono yo, independiente en memoria, no tiene estados
public class SubmissionPersistence {
    
    // contante para manejar logs en servidor o en consola
    private static final Logger LOGGER = Logger.getLogger(SubmissionPersistence.class.getName());
    
    // maratonesPU apuntador a donde esta la configuracion de la base de datos
    // em gestiona las entiddes que van y vuelven de la base de datos 
    @PersistenceContext (unitName = "maratonesPU")
    protected EntityManager em;
    
    
    public SubmissionEntity create(SubmissionEntity submissionEntity)
    {
        em.persist(submissionEntity);
        return submissionEntity;
    }
    
    
}
