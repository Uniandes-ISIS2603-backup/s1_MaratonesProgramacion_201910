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
    * Atributo que modela el nombre del lenguaje
    */
    private String nombre;
    
    /*
    * Atributo que modela la experiencia medida en anhos del usuario
    */
    private Integer experiencia;
    
    /*
    * Atributo que representa el usuario que tiene asociado este lenguaje
    */
    private UsuarioDTO programador;
    
    /**
     * Atributo que modela el id unico del lenguaje
     */
    private Long id;
    
    /**
     * Metodo que permite construir un lenguaje a partir de una entidad 
     * @param entity la entidad persistida que representa el lenguaje
     */
    public LenguajeDTO(LenguajeEntity entity)
    {
        if(entity != null)
        {
            this.id = entity.getId();
            this.nombre = entity.getNombre();
            this.experiencia = entity.getExperiencia();
                if(entity.getProgramador()!= null)
                {
                    this.programador = new UsuarioDTO(entity.getProgramador());
                }
        }
    }

    /**
     * Constructor basico, vacio
     */
    public LenguajeDTO() {
    }
    
    
    /**
     * Metodo que permite obtener el nombre del lenguaje 
     * @return the nombre el nombre del lenguaje
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Metodo que permite cambiar el nombre del lenguaje
     * @param nombre the nombre to set el nuevo nombre del lenguaje
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Metodo que permite obtener la experiencia del lenguaje
     * @return the experiencia la experiencia medida en anhos que se tiene del lenguaje
     */
    public Integer getExperiencia() {
        return experiencia;
    }

    /**
     * Metodo que permite cambiar la experiencia que se tiene del lenguaje
     * @param experiencia the experiencia to set la nueva experiencia del lenguaje
     */
    public void setExperiencia(Integer experiencia) {
        this.experiencia = experiencia;
    }

    /**
     * Metodo que permite consultar el programador que conoce el lenguaje
     * @return the programador
     */
    public UsuarioDTO getProgramador() {
        return programador;
    }

    /**
     * Metodo que permite cambiar el programador de un lenguaje
     * @param programador the programador to set
     */
    public void setProgramador(UsuarioDTO programador) {
        this.programador = programador;
    }
    
    /**
     * Metodo que permite  convertir el lenguaje en una unidad persistible
     * @return la entidad que fue creada a partir del DTO
     */
    public LenguajeEntity toEntity()
    {
        LenguajeEntity entity = new LenguajeEntity();
        entity.setId(this.id);
        entity.setNombre(this.nombre);
        entity.setExperiencia(this.experiencia);
        if(this.programador != null)
        {
            entity.setProgramador(programador.toEntity());
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
