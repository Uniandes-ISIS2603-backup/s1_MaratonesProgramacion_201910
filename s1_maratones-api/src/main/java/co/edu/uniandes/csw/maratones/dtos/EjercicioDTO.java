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
 * 
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
 *   }
 *
 * </pre>
/**
 *
 * @author aa.rodriguezv
 */
public class EjercicioDTO implements Serializable{
    
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
    private Integer puntaje;
    
    /*
    
    */
    private Integer nivel;
    
    /**
     * 
     */
    private Long id;
    

    
    
    public EjercicioDTO()
    {
        
    }
    
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
     * @return the input
     */
    public String getInputt() {
        return inputt;
    }

    /**
     * @param input the input to set
     */
    public void setInputt(String input) {
        this.inputt = input;
    }

    /**
     * @return the output
     */
    public String getOutputt() {
        return outputt;
    }

    /**
     * @param output the output to set
     */
    public void setOutputt(String output) {
        this.outputt = output;
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

    public EjercicioEntity toEntity() {
        
        EjercicioEntity entity = new EjercicioEntity();
       
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
    
    
}
