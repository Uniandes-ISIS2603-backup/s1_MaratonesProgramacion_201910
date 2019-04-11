/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.maratones.dtos;

import co.edu.uniandes.csw.maratones.entities.CompetenciaEntity;
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
    
    */
    private List<SubmissionDTO> submissions;

    
    
    
    public EjercicioDetailDTO()
    {
        super();
    }
    
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
     * @return the submissions
     */
    public List<SubmissionDTO> getSubmissions() {
        return submissions;
    }

    /**
     * @param submissions the submissions to set
     */
    public void setSubmissions(List<SubmissionDTO> submissions) {
        this.submissions = submissions;
    }
    
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
