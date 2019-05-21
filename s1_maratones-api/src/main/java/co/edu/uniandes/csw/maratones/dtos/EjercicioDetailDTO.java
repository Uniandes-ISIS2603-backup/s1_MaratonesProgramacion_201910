/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.maratones.dtos;

import co.edu.uniandes.csw.maratones.entities.EjercicioEntity;
import co.edu.uniandes.csw.maratones.entities.SubmissionEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/**
 * Clase que extiende de {@link EjercicioDTO} para manejar las relaciones entre los
 * EjercicioDTO y otros DTOs. Para conocer el contenido de un Ejericio vaya a la
 * documentacion de {@link EjercicioDTO}
 *
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   {

 *       "nombre": string,  
 *       "descripcion": string,
 *       "inputt" : string,
 *       "outputt" : string,
 *       "puntaje" : number,
 *       "nivel" : number,
 *       "id": number
 *       "competencias" : [{@link CompetenciaDTO}],
 *       "submissions" : [{@link SubmissionDTO}]
 * 
 * 
 *   }
 * </pre> Por ejemplo un ejercicio se representa asi:<br>
 *
 * <pre>
 *
 *   {
 *       "nombre": "Prediccion de Terremotos",  
 *       "descripcion": "blablablabla",
 *       "inputt" : "inputdelejercicio",
 *       "outputt" : "putputdelejercicio",
 *       "puntaje" : 12345,
 *       "nivel" : 12345,
 *       "id": 123456
 *       "competencias":
 *        {
 *         }
 *       "submissions":
 *        {
 *         }
 *   }
 *
 * </pre>
/**
 *
 * @author aa.rodriguezv
 */
public class EjercicioDetailDTO extends EjercicioDTO implements Serializable{
    
    
    /*
    * Lista de submissions que estan asociadas al ejercicio 
    */
    private List<SubmissionDTO> submissions;

    
    
    /**
     * Constructor basico requerido
     */
    public EjercicioDetailDTO()
    {
        super();
    }
    
    /**
     * Ampliacion del metodo padre, Este metodo construye el ejercicio y el detalle de este agregando las submissions asociadas 
     * @param entity entidad del ejercicio persistida
     */
    public EjercicioDetailDTO(EjercicioEntity entity)
    {
        super(entity);
        if(entity.getSubmissions() != null)
        {
            submissions = new ArrayList<>();
            for(SubmissionEntity submission: entity.getSubmissions())
            {
                submissions.add(new SubmissionDTO(submission));
            }
        }
    }

    
    
    /**
     * Metodo para obtener la listas de submissions del ejercicio
     * @return the submissions las submissions del ejercicio
     */
    public List<SubmissionDTO> getSubmissions() {
        return submissions;
    }

    /**
     * Metodo para cambiar las submissions de un ejercicio
     * @param submissions the submissions to set
     */
    public void setSubmissions(List<SubmissionDTO> submissions) {
        this.submissions = submissions;
    }
    
    /**
     * Metodo que sobreescribe el metodo del padre, permitiendo persistir en una entidad las submissions asociadas al detalle del ejercicio
     * @return la entidad creada del ejercicio con sus submissions
     */
    @Override
    public EjercicioEntity toEntity()
    {
        EjercicioEntity entity = super.toEntity();
        
        if(getSubmissions() != null)
        {
            List<SubmissionEntity> subEntity = new ArrayList<>();
            for(SubmissionDTO sub : getSubmissions())
            {
                subEntity.add(sub.toEntity());
            }
            entity.setSubmissions(subEntity);
        }
        
        
        return entity;
    }
}
