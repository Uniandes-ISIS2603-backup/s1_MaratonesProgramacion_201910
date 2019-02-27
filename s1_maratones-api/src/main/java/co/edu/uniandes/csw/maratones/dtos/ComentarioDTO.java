/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.maratones.dtos;

import co.edu.uniandes.csw.maratones.entities.ComentarioEntity;
import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * @author Juan Felipe Peña
 */
public class ComentarioDTO implements Serializable{
    
    private String mensaje;
    private int votosAFavor;
    private int votosEnContra;
    
    /*
    * Relación a un foro  
    * dado que esta tiene cardinalidad 1.
     */
    private ForoDTO foro;

    /**
     * Constructor por defecto
     */
    public ComentarioDTO() {
    }

    /**
     * Constructor a partir de la entidad
     *
     * @param comentarioEntity La entidad del comentario
     */
    public ComentarioDTO(ComentarioEntity comentarioEntity) {
        if (comentarioEntity != null) {
            this.mensaje = comentarioEntity.getMensaje();
            this.votosAFavor = comentarioEntity.getVotosAFavor();
            this.votosEnContra = comentarioEntity.getVotosEnContra();
            if (comentarioEntity.getForo() != null) {
                this.foro = new ForoDTO(comentarioEntity.getForo());
            } else {
                this.foro = null;
            }
        }       
    }
    
     /**
     * Método para transformar el DTO a una entidad.
     *
     * @return La entidad del comentario asociado.
     */
    public ComentarioEntity toEntity() {
        ComentarioEntity comentarioEntity = new ComentarioEntity();
        comentarioEntity.setMensaje(this.mensaje);
        comentarioEntity.setVotosAFavor(this.votosAFavor);
        comentarioEntity.setVotosEnContra(this.votosEnContra);
        if (this.foro != null) {
            comentarioEntity.setForo(this.foro.toEntity());
        }
        return comentarioEntity;
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
     * @return the votosAFavor
     */
    public int getVotosAFavor() {
        return votosAFavor;
    }

    /**
     * @param votosAFavor the votosAFavor to set
     */
    public void setVotosAFavor(int votosAFavor) {
        this.votosAFavor = votosAFavor;
    }

    /**
     * @return the votosEnContra
     */
    public int getVotosEnContra() {
        return votosEnContra;
    }

    /**
     * @param votosEnContra the votosEnContra to set
     */
    public void setVotosEnContra(int votosEnContra) {
        this.votosEnContra = votosEnContra;
    }
    
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
