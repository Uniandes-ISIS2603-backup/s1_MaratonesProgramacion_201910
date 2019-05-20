/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.maratones.dtos;

import co.edu.uniandes.csw.maratones.entities.EjercicioEntity;
import java.io.Serializable;

/**
 * EjercicioDTO Objeto de transferencia de datos de Ejercicio. Los DTO contienen las
 * representaciones de los JSON que se transfieren entre el cliente y el
 * servidor.
 *
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   {

 *       "nombre": string,  
 *       "descripcion": string,
 *       "inputt" : string,
 *       "outputt" : string,
 *       "puntaje" : number,
 *       "nivel" : number,
 *       "id": number
 *       "competencia": {@link CompetenciaDTO}
 * 
 *   }
 * </pre> Por ejemplo un ejercicio se representa asi:<br>
 *
 * <pre>
 *
 *   {
 *       "nombre": "Prediccion de Terremotos",  
 *       "descripcion": "blablablabla",
 *       "inputt" : "inputdelejercicio",
 *       "outputt" : "putputdelejercicio",
 *       "puntaje" : 12345,
 *       "nivel" : 12345,
 *       "id": 123456
 *       "competencia":
 *      {
 *          "id" : 1,
 *          "nombre" : "Nintendo"
 *      }
 *   }
 *
 * </pre>
/**
 *
 * @author aa.rodriguezv
 */
public class EjercicioDTO implements Serializable{
    
    /*
    *Atributo que modela el nombre del ejercicio
    */
    private String nombre;
    
    /*
    *Atributo que modela la descripcion del ejercicio
    */
    private String descripcion;
    
    /*
    *Atributo que representa la ruta de un input
    */
    private String inputt;
    
    /*
    *Atributo que representa la ruta de un output
    */
    private String outputt;
    
    /*
    *Atributp que modela el puntaje que ofrece a los equipos un ejercicio
    */
    private Integer puntaje;
    
    /*
    *Atributo que modela el nivel de dificultad de un ejercicio
    */
    private Integer nivel;
    
    /**
     * Atributo que modela el identificador de un ejercicio
     */
    private Long id;
    
    /**
     * Atributo que modela la competencia a la cual pertenece un ejercicio
     */
    private CompetenciaDTO competencia;
    

    
    /**
     * Constructor basico requerido
     */
    public EjercicioDTO()
    {
        
    }
    
    /**
     * Metodo constructor que permite crear un ejercicio a partir de una entidad persistida
     * @param entity la entidad ejercicio que ya existe en la capa de persistencia
     */
    public EjercicioDTO(EjercicioEntity entity)
    {
        if(entity != null)
        {
            this.id = entity.getId();
            this.nombre = entity.getNombre();
            this.inputt = entity.getInputt();
            this.outputt = entity.getOutputt();
            this.nivel = entity.getNivel();
            this.puntaje = entity.getPuntaje();
            this.descripcion = entity.getDescripcion();
            if(entity.getCompetencia()!= null)
            {
                this.competencia = new CompetenciaDTO(entity.getCompetencia());
            }
            else
            {
                this.competencia = null;
            }
        }
    }
    
    
    
    /**
     * Metodo para consultar el nombre de un ejercicio
     * @return the nombre el nombre del ejercicio
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Metodo para cambiar el nombre de un ejercicio
     * @param nombre the nombre to set el nombre nuevo del ejercicio
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Metodo para consultar la descripcion de un ejercicio
     * @return the descripcion la descripcion del ejercicio
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Metodo para cambiar la descripcion de un ejercicio
     * @param descripcion the descripcion to set la descripcion nueva del ejercicio
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Metodo para obtener el inputt de un ejercicio
     * @return the input el input del ejercicio
     */
    public String getInputt() {
        return inputt;
    }

    /**
     * Metodo para cambiar el inputt de un ejercicio
     * @param input the input to set el inputt nuevo del ejercicio
     */
    public void setInputt(String input) {
        this.inputt = input;
    }

    /**
     * Metodo para consultar el outputt de un ejercicio
     * @return the output el outputt del ejercicio
     */
    public String getOutputt() {
        return outputt;
    }

    /**
     * Metodo para cambiar el outputt de un ejercicio
     * @param output the output to set el outputt nuevo del ejercicio
     */
    public void setOutputt(String output) {
        this.outputt = output;
    }

    /**
     * Metodo para consultar el puntaje del ejercicio
     * @return the puntaje el puntaje del ejercicio
     */
    public Integer getPuntaje() {
        return puntaje;
    }

    /**
     * Metodo para cambiar el puntaje que ofrece el ejercicio
     * @param puntaje the puntaje to set el puntaje nuevo del ejercicio
     */
    public void setPuntaje(Integer puntaje) {
        this.puntaje = puntaje;
    }

    /**
     * Metodo para obtener el nivel de dificultad del ejercicio
     * @return the nivel el nivel de dificultad del ejercicio
     */
    public Integer getNivel() {
        return nivel;
    }

    /**
     * Metodo para cambiar el nivel de dificultad del ejercicio
     * @param nivel the nivel to set el nivel nuevo del ejercicio
     */
    public void setNivel(Integer nivel) {
        this.nivel = nivel;
    }

    /**
     * Metodo para poder cambiar el objeto DTO ejercicio a una Entidad persistible
     * @return la entidad ejercicio creada
     */
    public EjercicioEntity toEntity() {
        
        EjercicioEntity entity = new EjercicioEntity();
       
        entity.setId(id);
        entity.setDescripcion(descripcion);
        entity.setInputt(inputt);
        entity.setOutputt(outputt);
        entity.setPuntaje(puntaje);
        entity.setNivel(nivel);
        entity.setNombre(nombre);
        
        
        return entity;
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
     * Metodo para obtener el DTO de la competencia a la cual pertenece el ejercicio
     * @return the competencia la competencia que contiene el ejercicio
     */
    public CompetenciaDTO getCompetencia() {
        return competencia;
    }

    /**
     * Metodo para cambiar la competencia a la cual pertenece 
     * @param competencia the competencia to set la nueva competencia del ejercicio
     */
    public void setCompetencia(CompetenciaDTO competencia) {
        this.competencia = competencia;
    }
    
    
}
