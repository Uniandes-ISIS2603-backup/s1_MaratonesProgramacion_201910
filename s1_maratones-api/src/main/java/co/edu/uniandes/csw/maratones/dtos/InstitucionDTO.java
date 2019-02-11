/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.maratones.dtos;

import java.io.Serializable;


/**
 * InstitucionDTO Objeto de transferencia de datos de Institucion. Los DTO contienen las
 * representaciones de los JSON que se transfieren entre el cliente y el
 * servidor.
 *
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 * {
 * "nombre":String,
 * "imagen":String,
 * "Ubicacion":String,
 * "descripcion":String
 * }
 * </pre> Por ejemplo una institucion se representa asi:<br>
 *
 * <pre>
 * {
 * "nombre":"Universidad de los Andes",
 * "imagen":"./data/imagenes/foto.jpg",
 * "Ubicacion":"Bogotá DC",
 * "descripcion":"institucion unversitaria"
 * }
 * </pre>
/**
 * @author c.mendez11
 */
public class InstitucionDTO implements Serializable{
    private String nombre;
    private String imagen;
    private String ubicacion;
    private String descripcion;

    public InstitucionDTO(){
        
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
     * @return the imagen
     */
    public String getImagen() {
        return imagen;
    }

    /**
     * @param imagen the imagen to set
     */
    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    /**
     * @return the ubicacion
     */
    public String getUbicacion() {
        return ubicacion;
    }

    /**
     * @param ubicacion the ubicacion to set
     */
    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
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
