/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.maratones.dtos;

import java.util.List;

/**
 *
 * @author estudiante
 */
public class BlogDetailDTO extends BlogDTO{
    private List<PublicacionDTO> publicaciones;

    public BlogDetailDTO(){
        
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
