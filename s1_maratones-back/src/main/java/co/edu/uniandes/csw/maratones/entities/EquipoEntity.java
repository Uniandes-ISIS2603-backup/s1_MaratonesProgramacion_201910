/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.maratones.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author camilalonart
 */
@Entity
public class EquipoEntity extends BaseEntity implements Serializable{

    private EquipoEntity coach;
    private String nombreEquipo;
    private EquipoEntity[] participantes;

    public EquipoEntity getCoach() {
        return coach;
    }

    public void setCoach(EquipoEntity coach) {
        this.coach = coach;
    }

    public String getNombreEquipo() {
        return nombreEquipo;
    }

    public void setNombreEquipo(String nombreEquipo) {
        this.nombreEquipo = nombreEquipo;
    }

    public EquipoEntity[] getParticipantes() {
        return participantes;
    }

    public void setParticipantes(EquipoEntity[] participantes) {
        this.participantes = participantes;
    }
    
    public EquipoEntity(){
        }
   
    
}