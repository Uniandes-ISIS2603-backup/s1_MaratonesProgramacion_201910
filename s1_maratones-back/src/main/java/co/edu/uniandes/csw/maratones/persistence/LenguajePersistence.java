/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.maratones.persistence;

import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Angel Rodriguez aa.rodriguezv
 */
@Stateless
public class LenguajePersistence {
    
     private static final Logger LOGGER = Logger.getLogger(LenguajePersistence.class.getName());

    @PersistenceContext(unitName = "maratonesPU")
    protected EntityManager em;
}
