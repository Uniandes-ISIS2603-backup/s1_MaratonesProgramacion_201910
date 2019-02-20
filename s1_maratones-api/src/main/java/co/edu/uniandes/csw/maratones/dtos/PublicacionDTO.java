/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.maratones.dtos;

import co.edu.uniandes.csw.maratones.entities.PublicacionEntity;
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
public class PublicacionDTO {
    private Date fecha;
    private String texto;
    
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
         
        }
     }
}
