/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.maratones.ejb;

import co.edu.uniandes.csw.maratones.persistence.EquipoPersistence;
import co.edu.uniandes.csw.maratones.persistence.SubmissionPersistence;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Angel Rodriguez aa.rodriguezv
 */
@Stateless
public class EquipoSubmissionLogic {
    
    private static final Logger LOGGER = Logger.getLogger(EquipoSubmissionLogic.class.getName());

    @Inject
    private EquipoPersistence equipoPersistence; 
    
    @Inject
    private SubmissionPersistence submissionPersistence;
   
    
    
    
}
