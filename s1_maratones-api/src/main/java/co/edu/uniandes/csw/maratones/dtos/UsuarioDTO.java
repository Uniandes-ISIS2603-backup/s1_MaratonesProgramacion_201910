/*
MIT License
Copyright (c) 2019 Universidad de los Andes - ISIS2603
Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:
The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.
THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 */
package co.edu.uniandes.csw.maratones.dtos;

import co.edu.uniandes.csw.maratones.entities.UsuarioEntity;
import java.io.Serializable;
/**
 * UsuarioDTO Objeto de transferencia de datos de Usuario. Los DTO contienen las
 * representaciones de los JSON que se transfieren entre el cliente y el
 * servidor.
 *
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   {
 *       "rol": string, 
 *       "nombre": string,
 *       "apellido": string, 
 *       "nombreUsuario": string, 
 *       "clave": string, 
 *       "correo": string,
 *       "imagen": string,
 *       "institucion": string
 * 
 * 
 *   }
 * </pre> Por ejemplo un usuario se representa asi:<br>
 *
 * <pre>
 *
 *   {
 *       "rol": “PARTICIPANTE”, 
 *       "nombre": "Maria Camila",
 *       "apellido": "Londono", 
 *       "nombreUsuario": “camilalonart”, 
 *       "clave": “blablabla”, 
 *       "correo": “mc.londono@uniandes.edu.co”,
 *       "imagen": “www.blablabla.com/bla.jpg”,
 *       "institucion": “Universidad de los Andes”
 *   }
 *
 * </pre>
/**
 * @author camilalonart
 */
public class UsuarioDTO implements Serializable {
    public static final String COACH = "COACH";
    public static final String PARTICIPANTE = "PARTICIPANTE";
    public static final String RESPONSABLE = "RESPONSABLE";

    
    private String rol;
    private String nombreUsuario;
    private String nombre;
    private String imagen;
    private String correo;
    private String clave;
    private Integer puntaje;
    private Long id;
    
    public UsuarioDTO() {
    }

    /**
     * Constructor a partir de la entidad
     *
     * @param usuarioEntity La entidad del libro
     */
    public UsuarioDTO(UsuarioEntity usuarioEntity) {
        if (usuarioEntity != null) {
            this.nombre = usuarioEntity.getNombre();
            this.nombreUsuario = usuarioEntity.getNombreUsuario();
            this.clave = usuarioEntity.getClave();
            this.correo = usuarioEntity.getCorreo();
            this.imagen = usuarioEntity.getImagen();
            this.rol = usuarioEntity.getRol();
            this.puntaje = usuarioEntity.getPuntaje();
            this.id=usuarioEntity.getId();
        }
    }
    
    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Integer getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(Integer puntaje) {
        this.puntaje = puntaje;
    }

    public UsuarioEntity toEntity() {
        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setRol(this.rol);
        usuario.setNombreUsuario(this.nombreUsuario);
        usuario.setNombre(this.nombre);    
        usuario.setImagen(this.imagen);
        usuario.setCorreo(this.correo);
        usuario.setClave(this.clave);
        usuario.setPuntaje(this.puntaje);
        usuario.setId(id);
        return usuario;
    }
    
   
}