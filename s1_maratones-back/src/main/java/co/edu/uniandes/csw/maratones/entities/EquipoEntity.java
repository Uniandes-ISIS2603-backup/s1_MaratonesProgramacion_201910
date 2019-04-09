/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.maratones.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author camilalonart
 */
@Entity
public class EquipoEntity extends BaseEntity implements Serializable{

    private UsuarioEntity coach;
    private String nombreEquipo;
    
    @PodamExclude
    @ManyToMany (mappedBy = "equipos", cascade = {
        CascadeType.PERSIST,
        CascadeType.MERGE
    })
    private List<UsuarioEntity> participantes;
    
    
    @PodamExclude
    @ManyToMany 
    private List<CompetenciaEntity> competencias;
     
    @PodamExclude
    @OneToMany(mappedBy = "equipo")
    private List<SubmissionEntity> submissions = new ArrayList<SubmissionEntity>();
    
    public UsuarioEntity getCoach() {
        return coach;
    }

    public void setCoach(UsuarioEntity coach) {
        this.coach = coach;
    }

    public String getNombreEquipo() {
        return nombreEquipo;
    }

    public void setNombreEquipo(String nombreEquipo) {
        this.nombreEquipo = nombreEquipo;
    }

    public List<UsuarioEntity> getParticipantes() {
        return participantes;
    }

    public void setParticipantes(List<UsuarioEntity> participantes) {
        this.participantes = participantes;
    }
    
    public EquipoEntity(){
        }
   
    
}
