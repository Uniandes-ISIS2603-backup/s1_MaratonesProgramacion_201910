/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.maratones.ejb;

import co.edu.uniandes.csw.maratones.entities.EquipoEntity;
import co.edu.uniandes.csw.maratones.entities.SubmissionEntity;
import co.edu.uniandes.csw.maratones.persistence.EquipoPersistence;
import co.edu.uniandes.csw.maratones.persistence.SubmissionPersistence;
import java.util.List;
import java.util.logging.Level;
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

    public void removeParticipante(Long submissionId, Long equipoId) {
          LOGGER.log(Level.INFO, "Inicia proceso de borrar un libro del author con id = {0}", equipoId);
        EquipoEntity equipoEntity = equipoPersistence.find(equipoId);
        SubmissionEntity submmissionEntity = submissionPersistence.find(submissionId);
        //
        LOGGER.log(Level.INFO, "Termina proceso de borrar un libro del author con id = {0}", equipoId);
   
    
    }

    public List<SubmissionEntity> replaceParticipantes(Long submissionId, List<SubmissionEntity> participantesListDTO2Entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public SubmissionEntity getSubmission(Long submissionId, Long equipoId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<SubmissionEntity> getEquipos(Long equipoId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public SubmissionEntity addEquipo(Long submissionId, Long equipoId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   
    
    
    
}
