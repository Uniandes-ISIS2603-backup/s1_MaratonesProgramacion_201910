/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.maratones.entities;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author Juan Felipe Peña
 */

@Entity
public class ComentarioEntity extends BaseEntity implements Serializable{
    
    private String mensaje;
    
    private Integer votosAFavor;
    
    private Integer votosEnContra;
    
    @PodamExclude
    @ManyToOne(cascade = CascadeType.PERSIST)
    private ForoEntity foro;
    
    @PodamExclude
    @ManyToOne(cascade = CascadeType.PERSIST)
    private UsuarioEntity usuario;
    
    public ComentarioEntity(){
        
    }

    /**
     * @return the mensaje
     */
    public String getMensaje() {
        return mensaje;
    }

    /**
     * @param mensaje the mensaje to set
     */
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    /**
     * @return the foro
     */
    public ForoEntity getForo() {
        return foro;
    }

    /**
     * @param foro the foro to set
     */
    public void setForo(ForoEntity foro) {
        this.foro = foro;
    }

    /**
     * @return the votosAFavor
     */
    public Integer getVotosAFavor() {
        return votosAFavor;
    }

    /**
     * @param votosAFavor the votosAFavor to set
     */
    public void setVotosAFavor(Integer votosAFavor) {
        this.votosAFavor = votosAFavor;
    }

    /**
     * @return the votosEnContra
     */
    public Integer getVotosEnContra() {
        return votosEnContra;
    }

    /**
     * @param votosEnContra the votosEnContra to set
     */
    public void setVotosEnContra(Integer votosEnContra) {
        this.votosEnContra = votosEnContra;
    }

}
