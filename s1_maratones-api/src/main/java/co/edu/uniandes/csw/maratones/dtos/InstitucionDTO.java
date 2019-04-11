/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.maratones.dtos;

import co.edu.uniandes.csw.maratones.entities.InstitucionEntity;
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
    private Long id;
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
    /**
     * Convertir DTO a Entity
     *
     * @return Un Entity con los valores del DTO
     */
    public InstitucionEntity toEntity(){
        InstitucionEntity institucionEntity = new InstitucionEntity();
        institucionEntity.setNombre(this.nombre);
        institucionEntity.setDescripcion(this.descripcion);
        institucionEntity.setImagen(this.imagen);
        institucionEntity.setUbicacion(this.ubicacion);
        institucionEntity.setId(this.id);
       return institucionEntity;
    }
    /**
     * Conviertir Entity a DTO (Crea un nuevo DTO con los valores que recibe en
     * la entidad que viene de argumento.
     */
     public InstitucionDTO(InstitucionEntity institucionEntity) {
        if (institucionEntity != null) {
            this.nombre = institucionEntity.getNombre();
            this.descripcion = institucionEntity.getDescripcion();
            this.imagen = institucionEntity.getImagen();
            this.ubicacion = institucionEntity.getUbicacion();
            this.id=institucionEntity.getId();
        }
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
}
