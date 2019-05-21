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
  *      "ejercicio": {@link EjercicioDTO}
 * 
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
 *       "ejercicio":
 *      {
 *          "id" : 1,
 *          "nombre" : "Prediccion de terremotos"
 *      }
 *   }
 *
 * </pre>
/**
 *
 * @author aa.rodriguezv
 */
public class SubmissionDTO implements Serializable{
    
    /*
    * Atributo que modela el tiempo que tomo hacer la submission
    */
    private Double tiempo;
    
    /*
    * Atributo que modela el veredicto de una submission, este atributo solo puede modelarse con las constantes definidas
    */
    private String veredicto;
    
    /*
    * Atributo que modela la ruta del archivo de una entrega
    */
    private String archivo;
    
    /*
    *Atributo que modela la fecha en la que fue creada esta submission
    */
    @XmlJavaTypeAdapter(DateAdapter.class)
    private Date fecha;
    
    /*
    * Atributo que modela la memoria que ocupa la submission
    */
    private Double memoria;
    
    /*
    * Atributo que modela el codigo de la submission
    */
    private String codigo;
    
    /*
    * Atributo que modela el id del submission
    */
    private Long id;
    
    /*
    * Atributo que modela el ejercicio al que fue entregada la submission
    */
    private EjercicioDTO ejercicio;
    
    /**
     * COnstante para modelar que una entrega esta en revision
     */
    public static final String EN_REVISION = "En revision";
    
     /**
     * Constante para modelar que una entrega esta aprobada
     */
    public static final String APROBADA = "Aprobada";
    
        /**
     * Constante para modelar que una entrega no compila
     */
    public static final String ERROR_COMPILACION = "Error de compilacion";
    
        /**
     * Constante para modelar que una entrega fue recibida despues del tiempo permitido por el ejercicio
     */
    public static final String ERROR_TIEMPO= "Error de tiempo limite excedido";
    

    /**
     * Constructor basico, vacio
     */
    public SubmissionDTO()
    {
        
    }
    
    /**
     * Metodo que permite construir una entrega a partir de una entidad persistida 
     * @param entity la entidad que contiene la informacion de la submission
     */
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
            if (entity.getEjercicioEntity() != null) {
                this.ejercicio = new EjercicioDTO(entity.getEjercicioEntity());
            } else {
                this.ejercicio = null;
            }
        }
        
    }       
        
    
    /**
     * Metodo para consultar el tiempo de una submission
     * @return the tiempo el tiempo que se demoro en realizar una submission
     */
    public Double getTiempo() {
        return tiempo;
    }

    /**
     * Metodo para cmabiar el tiemp ode entrega de una submission
     * @param tiempo the tiempo to set el tiempo nuevo de una submission
     */
    public void setTiempo(Double tiempo) {
        this.tiempo = tiempo;
    }

    /**
     * Metodo para consultar el veredicto de una submission 
     * @return the veredicto el veredicto de una submission
     */
    public String getVeredicto() {
        return veredicto;
    }

    /**
     * Metodo para cambiar el veredicto de una submission
     * @param veredicto the veredicto to set el veredicto nuevo de una submission
     */
    public void setVeredicto(String veredicto) {
        this.veredicto = veredicto;
    }

    /**
     * Metodo para obtener la ruta de archivo de una submission
     * @return the archivo la ruta del archivo de una submission 
     */
    public String getArchivo() {
        return archivo;
    }

    /**
     * Metodo para cambiar el archivo de una submission
     * @param archivo the archivo to set el nuevo archivo de una submission
     */
    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }

    /**
     * Metodo para obtener la fecha de entrega de una submission
     * @return the fecha la fecha de la submission
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * Metodo para cambiar la fecha de una submission
     * @param fecha the fecha to set la nueva fecha de la submission
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * Metodo para obtener la memoria que ocupa una submission
     * @return the memoria el espacio en memoria que ocupa una submission
     */
    public Double getMemoria() {
        return memoria;
    }

    /**
     * Metodo para cambiar el espacio que ocupa en memoria una submission
     * @param memoria the memoria to set el nuevo espacio que ocupa
     */
    public void setMemoria(Double memoria) {
        this.memoria = memoria;
    }

    /**
     * Metodo para obtener el codigo de una submission
     * @return the codigo el codigo de la submission
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * Metodo para cambiar el codigo de una submission
     * @param codigo the codigo to set el nuevo codigo de la submission
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    
    
    /**
     * Metodo que permite convertir el DTO en una unidad de persistencia
     * @return la entidad creada
     */
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
     * Metodo para consultar El ejercicio al cual esta asociada la submission
     * @return the ejercicio el ejercicio de la submission
     */
    public EjercicioDTO getEjercicio() {
        return ejercicio;
    }

    /**
     * Metodo para cambiar el ejercicio de una submission
     * @param ejercicio the ejercicio to set el ejercicio nuevo de la submission
     */
    public void setEjercicio(EjercicioDTO ejercicio) {
        this.ejercicio = ejercicio;
    }
}
