/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.maratones.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author Angel Rodriguez aa.rodriguezv
 */
@Entity
public class SubmissionEntity extends BaseEntity implements Serializable{

    @PodamExclude
    @ManyToOne
    private EjercicioEntity ejercicioEntity;
    
     /*
    */
    private double tiempo;
    
    /*
    */
    private String veredicto;
    
    /*
    */
    private String archivo;
    
    /*
    */
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    
    /*
    */
    private double memoria;
    
    /*
    
    */
    private String codigo;
     
    
    /**
     * 
     */
    public static final String EN_REVISION = "En revision";
    
        /**
     * 
     */
    public static final String APROBADA = "Aprobada";
    
     /**
     * 
     */
    public static final String ERROR_COMPILACION = "Error de compilacion";
    
        /**
     * 
     */
    public static final String ERROR_TIEMPO= "Error de tiempo limite excedido";
    


    /**
     * @return the tiempo
     */
    public double getTiempo() {
        return tiempo;
    }

    /**
     * @param tiempo the tiempo to set
     */
    public void setTiempo(double tiempo) {
        this.tiempo = tiempo;
    }

    /**
     * @return the veredicto
     */
    public String getVeredicto() {
        return veredicto;
    }

    /**
     * @param veredicto the veredicto to set
     */
    public void setVeredicto(String veredicto) {
        this.veredicto = veredicto;
    }

    /**
     * @return the archivo
     */
    public String getArchivo() {
        return archivo;
    }

    /**
     * @param archivo the archivo to set
     */
    public void setArchivo(String archivo) {
        this.archivo = archivo;
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
     * @return the memoria
     */
    public double getMemoria() {
        return memoria;
    }

    /**
     * @param memoria the memoria to set
     */
    public void setMemoria(double memoria) {
        this.memoria = memoria;
    }

    /**
     * @return the codigo
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the ejercicioEntity
     */
    public EjercicioEntity getEjercicioEntity() {
        return ejercicioEntity;
    }

    /**
     * @param ejercicioEntity the ejercicioEntity to set
     */
    public void setEjercicioEntity(EjercicioEntity ejercicioEntity) {
        this.ejercicioEntity = ejercicioEntity;
    }


    
    
    
    
    
    
    
      
}
