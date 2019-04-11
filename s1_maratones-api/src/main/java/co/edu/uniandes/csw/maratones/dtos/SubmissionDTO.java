/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.maratones.dtos;

import co.edu.uniandes.csw.maratones.adapters.DateAdapter;
import co.edu.uniandes.csw.maratones.entities.SubmissionEntity;
import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * SubmissionDTO Objeto de transferencia de datos de Submission. Los DTO contienen las
 * representaciones de los JSON que se transfieren entre el cliente y el
 * servidor.
 *
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   {

 *       "tiempo": number,  
 *       "veredicto": string,
 *       "archivo" : string,
 *       "fecha" : date,
 *       "memoria" : number,
 *       "codigo" : string,
 *       "id": number
 * 
 * 
 *   }
 * </pre> Por ejemplo una submission se representa asi:<br>
 *
 * <pre>
 *
 *   {
 *      "tiempo": 123,  
 *       "veredicto": "En revision",
 *       "archivo" : string,
 *       "fecha" : "2019-02-23T00:00:00-05:00",
 *       "memoria" : 9876,
 *       "codigo" : "col-12345",
 *       "id": 123456
 *   }
 *
 * </pre>
/**
 *
 * @author aa.rodriguezv
 */
public class SubmissionDTO implements Serializable{
    
    /*
    
    */
    private Double tiempo;
    
    /*
    
    */
    private String veredicto;
    
    /*
    
    */
    private String archivo;
    
    /*
    
    */
    @XmlJavaTypeAdapter(DateAdapter.class)
    private Date fecha;
    
    /*
    
    */
    private Double memoria;
    
    /*
    
    */
    private String codigo;
    
    /*
    
    */
    private Long id;
    
    /*
    
    */
    private EjercicioDTO ejercicio;
    
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
    

    public SubmissionDTO()
    {
        
    }
    
    public SubmissionDTO(SubmissionEntity entity)
    {
        if(entity != null)
        {
            this.id = entity.getId();
            this.archivo = entity.getArchivo();
            this.codigo = entity.getCodigo();
            this.fecha = entity.getFecha();
            this.memoria = entity.getMemoria();
            this.tiempo = entity.getTiempo();
            this.veredicto = entity.getVeredicto();
        }
        if (entity.getEjercicioEntity() != null) {
                this.ejercicio = new EjercicioDTO(entity.getEjercicioEntity());
            } else {
                this.ejercicio = null;
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
        
        submission.setId(id);
        submission.setArchivo(archivo);
        submission.setCodigo(codigo);
        submission.setFecha(fecha);
        submission.setMemoria(memoria);
        submission.setVeredicto(veredicto);
        submission.setTiempo(tiempo);
        if (this.ejercicio != null) {
            submission.setEjercicioEntity(this.ejercicio.toEntity());
        }
        
        
        return submission;
    }
    
          /**
     * Obtiene el atributo id.
     *
     * @return atributo id.
     *
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el valor del atributo id.
     *
     * @param id nuevo valor del atributo
     *
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the ejercicio
     */
    public EjercicioDTO getEjercicio() {
        return ejercicio;
    }

    /**
     * @param ejercicio the ejercicio to set
     */
    public void setEjercicio(EjercicioDTO ejercicio) {
        this.ejercicio = ejercicio;
    }
}
