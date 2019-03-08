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
    private Integer votosAFavor;
    private Integer votosEnContra;
    private Long id;
    
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
            this.id = comentarioEntity.getId();
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
        comentarioEntity.setId(this.getId());
        if (this.getForo() != null) {
            comentarioEntity.setForo(this.getForo().toEntity());
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
    
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the foro
     */
    public ForoDTO getForo() {
        return foro;
    }

    /**
     * @param foro the foro to set
     */
    public void setForo(ForoDTO foro) {
        this.foro = foro;
    }
}
