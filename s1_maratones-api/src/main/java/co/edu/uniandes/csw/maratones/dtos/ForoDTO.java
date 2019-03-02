/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.maratones.dtos;

import co.edu.uniandes.csw.maratones.entities.ForoEntity;
import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * @author Juan Felipe Peña
 */
public class ForoDTO implements Serializable{
        
   private String nombre;
   private int votosAFavor;
   private int votosEnContra;
   private String descripcion;
   private Date fecha;
   private String tags;
   
   public ForoDTO()
   {}

   /**
     * Conviertir Entity a DTO (Crea un nuevo DTO con los valores que recibe en
     * la entidad que viene de argumento.
     *
     * @param foroEntity: Es la entidad que se va a convertir a DTO
     */
    public ForoDTO(ForoEntity foroEntity) {
        if (foroEntity != null) {
            this.nombre = foroEntity.getNombre();
            this.votosAFavor = foroEntity.getVotosAFavor();
            this.votosEnContra = foroEntity.getVotosEnContra();
            this.descripcion = foroEntity.getDescripcion();
            this.fecha = foroEntity.getFecha();
            this.tags = foroEntity.getTags();
        }
    }
    
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
     * @return the fecha
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the tags
     */
    public String getTags() {
        return tags;
    }

    /**
     * @param tags the tags to set
     */
    public void setTags(String tags) {
        this.tags = tags;
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
    
    /**
     * Convertir DTO a Entity
     *
     * @return Un Entity con los valores del DTO
     */
    public ForoEntity toEntity()
    {
        ForoEntity foroEntity = new ForoEntity();
        foroEntity.setNombre(this.nombre);
        foroEntity.setDescripcion(this.descripcion);
        foroEntity.setVotosAFavor(this.votosAFavor);
        foroEntity.setVotosEnContra(this.votosEnContra);
        foroEntity.setFecha(this.fecha);
        foroEntity.setTags(this.tags);
        return foroEntity;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
