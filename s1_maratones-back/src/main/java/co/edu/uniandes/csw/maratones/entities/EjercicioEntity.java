/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.maratones.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author Angel Rodriguez aa.rodriguezv
 */
@Entity
public class EjercicioEntity extends BaseEntity implements Serializable{
    
    
     /*
    
    */
    private String nombre;
    
    /*
    
    */
    private String descripcion;
    
    /*
    
    */
    private String inputt;
    
    /*
    
    */
    private String outputt;
    
    /*
    
    */
    private int puntaje;
    
    /*
    
    */
    private int nivel;

    @PodamExclude
    @ManyToMany 
    private List<CompetenciaEntity> competencias;
    
    @PodamExclude
    @OneToMany(mappedBy = "ejercicioEntitys",cascade = {
        CascadeType.PERSIST,
        CascadeType.MERGE
    })
    private List<SubmissionEntity> submissions;
    
    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the input
     */
    public String getInputt() {
        return inputt;
    }

    /**
     * @param inputt the input to set
     */
    public void setInputt(String inputt) {
        this.inputt = inputt;
    }

    /**
     * @return the output
     */
    public String getOutputt() {
        return outputt;
    }

    /**
     * @param outputt the output to set
     */
    public void setOutputt(String outputt) {
        this.outputt = outputt;
    }

    /**
     * @return the puntaje
     */
    public int getPuntaje() {
        return puntaje;
    }

    /**
     * @param puntaje the puntaje to set
     */
    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }

    /**
     * @return the nivel
     */
    public int getNivel() {
        return nivel;
    }

    /**
     * @param nivel the nivel to set
     */
    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    /**
     * @return the competencias
     */
    public List<CompetenciaEntity> getCompetencias() {
        return competencias;
    }
    
    
    /**
     * @param competencias the competencias to set
     */
    public void setCompetencias(List<CompetenciaEntity> competencias) {
        this.competencias = competencias;
    }

    /**
     * @return the submissions
     */
    public List<SubmissionEntity> getSubmissions() {
        return submissions;
    }

    /**
     * @param submissions the submissions to set
     */
    public void setSubmissions(List<SubmissionEntity> submissions) {
        this.submissions = submissions;
    }

    
    
}
