/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.maratones.dtos;

import java.io.Serializable;

/**
 * BlogDTO Objeto de transferencia de datos de Blog. Los DTO contienen las
 * representaciones de los JSON que se transfieren entre el cliente y el
 * servidor.
 *
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 * {
 * "nombre":String,
 * "descripcion":String,
 * }
 * </pre> Por ejemplo un blog se representa asi:<br>
 *
 * <pre>
 * {
 * "nombre":"parcticar",
 * "descripcion":"dara a conocer la imp..."
 * }
 * </pre>
/**
 * @author c.mendez11
 */
public class BlogDTO implements Serializable{
    private String nombre;
    private String descripcion;

    public BlogDTO(){
        
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
}
