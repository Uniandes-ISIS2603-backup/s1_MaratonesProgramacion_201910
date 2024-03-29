/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor....
 */
package co.edu.uniandes.csw.maratones.entities;
import co.edu.uniandes.csw.maratones.persistence.EquipoPersistence;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author camilalonart
 */
@Entity
public class UsuarioEntity extends BaseEntity implements Serializable{

    private String rol;
    private String nombreUsuario;
    private String nombre;
    private String imagen;
    private String correo;
    private String clave;
    private int puntaje;
    // atributto nque represeta la institucion a la que pertenec el usuario
    @PodamExclude
    @ManyToOne(cascade = CascadeType.PERSIST)
    private InstitucionEntity institucion;
    
    @PodamExclude
    @ManyToMany
    private List<EquipoEntity> equipos = new ArrayList<>();
    
    @PodamExclude
    @OneToMany(mappedBy = "programadorEntity",cascade = {
        CascadeType.REMOVE
    })
    private List<LenguajeEntity> lenguajes = new ArrayList<>();

    @PodamExclude
    @ManyToMany( fetch = FetchType.LAZY)
    private List<CompetenciaEntity> competencias = new ArrayList<>();
    
     @PodamExclude
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BlogEntity> blogs = new ArrayList<BlogEntity>();
    
     
    public List<EquipoEntity> getEquipos() {
        return equipos;
    }
    
    
    public void setEquipos(List<EquipoEntity> equipos) {
        this.equipos = equipos;
    }
     
    
    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }
    
    
    public UsuarioEntity(){
        }

    /**
     * @return the lenguajes
     */
    public List<LenguajeEntity> getLenguajes() {
        return lenguajes;
    }

    /**
     * @param lenguajes the lenguajes to set
     */
    public void setLenguajes(List<LenguajeEntity> lenguajes) {
        this.lenguajes = lenguajes;
    }

    /**
     * @return the competencias
     */
    public List<CompetenciaEntity> getCompetencias() {
        return competencias;
    }

    /**
     * @param competencias the competencias to set
     */
    public void setCompetencias(List<CompetenciaEntity> competencias) {
        this.competencias = competencias;
    }

    /**
     * @return the blogs
     */
    public List<BlogEntity> getBlogs() {
        return blogs;
    }

    /**
     * @param blogs the blogs to set
     */
    public void setBlogs(List<BlogEntity> blogs) {
        this.blogs = blogs;
    }

    /**
     * @return the institucion
     */
    public InstitucionEntity getInstitucion() {
        return institucion;
    }

    /**
     * @param institucion the institucion to set
     */
    public void setInstitucion(InstitucionEntity institucion) {
        this.institucion = institucion;
    }

   
}

