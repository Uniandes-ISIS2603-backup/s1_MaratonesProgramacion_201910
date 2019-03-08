/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.maratones.dtos;

import co.edu.uniandes.csw.maratones.entities.LenguajeEntity;
import java.io.Serializable;

/**
 * LenguajeDTO Objeto de transferencia de datos de Lenguaje. Los DTO contienen las
 * representaciones de los JSON que se transfieren entre el cliente y el
 * servidor.
 *
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   {
 *      "nombre": string,
 *      "experiencia": number,
 *      "programador": {@link: UsuarioDTO},
 *      "id" : number
 *      
 * 
 *   }
 * </pre> Por ejemplo un lenguaje se representa asi:<br>
 *
 * <pre>
 *  {
 *      "nombre": "Python",
 *      "experiencia": 10,
 *      "programador": {
 *        "rol": “PARTICIPANTE”, 
 *       "nombre": "Maria Camila",
 *       "apellido": "Londono", 
 *       "nombreUsuario": “camilalonart”, 
 *       "clave": “blablabla”, 
 *       "correo": “mc.londono@uniandes.edu.co”,
 *       "imagen": “www.blablabla.com/bla.jpg”,
 *       "institucion": “Universidad de los Andes”
 *       }
 *      "id" : 123456
 *  }
 * </pre>
/**
 *
 * @author aa.rodriguezv
 */
public class LenguajeDTO implements Serializable{
   
    /*
    
    */
    private String nombre;
    
    /*
    
    */
    private Integer experiencia;
    
    /*
    
    */
    private UsuarioDTO programador;
    
    /**
     * 
     */
    private Long id;
    
    public LenguajeDTO(LenguajeEntity entity)
    {
        if(entity != null)
        {
            this.id = entity.getId();
            this.nombre = entity.getNombre();
            this.experiencia = entity.getExperiencia();
        
        }
    }

    public LenguajeDTO() {
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
     * @return the experiencia
     */
    public int getExperiencia() {
        return experiencia;
    }

    /**
     * @param experiencia the experiencia to set
     */
    public void setExperiencia(int experiencia) {
        this.experiencia = experiencia;
    }

    /**
     * @return the programador
     */
    public UsuarioDTO getProgramador() {
        return programador;
    }

    /**
     * @param programador the programador to set
     */
    public void setProgramador(UsuarioDTO programador) {
        this.programador = programador;
    }
    
    public LenguajeEntity toEntity()
    {
        LenguajeEntity entity = new LenguajeEntity();
        
        entity.setNombre(this.nombre);
        entity.setExperiencia(this.experiencia);
        if(this.programador != null)
        {
            
        }
        
        return entity;
    }
    
       /**
     * Obtiene el atributo id.
     *
     * @return atributo id.
     *
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el valor del atributo id.
     *
     * @param id nuevo valor del atributo
     *
     */
    public void setId(Long id) {
        this.id = id;
    }
    
}
