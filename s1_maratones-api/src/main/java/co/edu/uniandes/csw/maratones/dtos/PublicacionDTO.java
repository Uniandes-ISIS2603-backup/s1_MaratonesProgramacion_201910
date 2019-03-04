/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.maratones.dtos;

import co.edu.uniandes.csw.maratones.entities.PublicacionEntity;
import java.io.Serializable;
import java.util.Date;

/**
 * PublicacionDTO Objeto de transferencia de datos de Publicacion. Los DTO contienen las
 * representaciones de los JSON que se transfieren entre el cliente y el
 * servidor.
 *
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   {
 *"fecha":date,
 *"texto":String
}
 * </pre> Por ejemplo una publicacion se representa asi:<br>
 *
 * <pre>
 *  {
 *"fecha":"02-02-2019",
 *"texto":"La realización de ejercicios fuera de la competencia fortalece..."
 *}
 * </pre>
/**
 * @author c.mendez11
 */
public class PublicacionDTO implements Serializable{
    private Long id;
    private Date fecha;
    private String texto;
    private BlogDTO blog;
    
    public PublicacionDTO(){
        
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
     * @return the texto
     */
    public String getTexto() {
        return texto;
    }

    /**
     * @param texto the texto to set
     */
    public void setTexto(String texto) {
        this.texto = texto;
    }
    /**
     * Convertir DTO a Entity
     *
     * @return Un Entity con los valores del DTO
     */
    public PublicacionEntity toEntity(){
        PublicacionEntity publicacionEntity = new PublicacionEntity();
        publicacionEntity.setFecha(this.fecha);
        publicacionEntity.setTexto(this.texto);
        if(this.blog!=null)
        {
            publicacionEntity.setBlog(this.blog.toEntity());
        }
       return publicacionEntity;
    }
    /**
     * Conviertir Entity a DTO (Crea un nuevo DTO con los valores que recibe en
     * la entidad que viene de argumento.
     */
     public PublicacionDTO(PublicacionEntity publicacionEntity) {
        if (publicacionEntity != null) {
            this.fecha = publicacionEntity.getFecha();
            this.texto = publicacionEntity.getTexto();
         if (publicacionEntity.getBlog() != null) {
                this.blog = new BlogDTO(publicacionEntity.getBlog());
            } else {
                this.blog = null;
            }
        }
     }

    /**
     * @return the blog
     */
    public BlogDTO getBlog() {
        return blog;
    }

    /**
     * @param blogDto the blogD to set
     */
    public void setBlog(BlogDTO blogDto) {
        this.blog = blogDto;
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
