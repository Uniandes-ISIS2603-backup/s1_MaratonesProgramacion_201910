/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.maratones.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author c.mendez11
 */
@Entity
public class PublicacionEntity extends BaseEntity implements Serializable{

    @Temporal(TemporalType.DATE)
    private Date fecha;
    private String texto;
    @PodamExclude
    @ManyToOne(cascade = CascadeType.PERSIST)
    private BlogEntity blog;
    
    public PublicacionEntity(){
        
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
     * @return the blog
     */
    public BlogEntity getBlog() {
        return blog;
    }

    /**
     * @param blog the blog to set
     */
    public void setBlog(BlogEntity blog) {
        this.blog = blog;
    }
    
}
