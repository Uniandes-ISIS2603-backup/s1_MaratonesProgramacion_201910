/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.maratones.dtos;

import co.edu.uniandes.csw.maratones.entities.SubmissionEntity;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author aa.rodriguezv
 */
public class SubmissionDTO implements Serializable{
    
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
    private Date fecha;
    
    /*
    
    */
    private double memoria;
    
    /*
    
    */
    private String codigo;
    
    

    public SubmissionDTO()
    {
        
    }
    
    public SubmissionDTO(SubmissionEntity entity)
    {
        if(entity != null)
        {
            this.archivo = entity.getArchivo();
            this.codigo = entity.getCodigo();
            this.fecha = entity.getFecha();
            this.memoria = entity.getMemoria();
            this.tiempo = entity.getTiempo();
            this.veredicto = entity.getVeredicto();
        }
    }       
        
    
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
    
    
    
    public SubmissionEntity toEntity()
    {
        SubmissionEntity submission = new SubmissionEntity();
        
        submission.setArchivo(archivo);
        submission.setCodigo(codigo);
        submission.setFecha(fecha);
        submission.setMemoria(memoria);
        submission.setVeredicto(veredicto);
        submission.setTiempo(tiempo);
        
        
        return submission;
    }
}
