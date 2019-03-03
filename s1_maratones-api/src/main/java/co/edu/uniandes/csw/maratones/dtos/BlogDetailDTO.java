/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.maratones.dtos;

import co.edu.uniandes.csw.maratones.entities.BlogEntity;
import co.edu.uniandes.csw.maratones.entities.PublicacionEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author estudiante
 */
public class BlogDetailDTO extends BlogDTO implements Serializable{
    
    private List<PublicacionDTO> publicaciones;

    public BlogDetailDTO(){
        super();
    }
       public BlogDetailDTO(BlogEntity blogEntity){
        super(blogEntity);
        if(blogEntity.getPublicaciones()!=null)
        {
            publicaciones= new ArrayList<>();
            for(PublicacionEntity publicacionEntity:blogEntity.getPublicaciones()){
                publicaciones.add(new PublicacionDTO(publicacionEntity));
            }
        }
        
    }     
       @Override
       public BlogEntity toEntity()
       {
           BlogEntity blogEntity= super.toEntity();
           if(publicaciones!=null)
           {
               List<PublicacionEntity> publicacionEntity = new ArrayList<>();
            for (PublicacionDTO publicacionDto : getPublicaciones()) {
                publicacionEntity.add(publicacionDto.toEntity());
            }
            blogEntity.setPublicaciones(publicacionEntity);
           }
           return blogEntity;
       }
    /**
     * @return the publicaciones
     */
    public List<PublicacionDTO> getPublicaciones() {
        return publicaciones;
    }

    /**
     * @param publicaciones the publicaciones to set
     */
    public void setPublicaciones(List<PublicacionDTO> publicaciones) {
        this.publicaciones = publicaciones;
    }
    
}
