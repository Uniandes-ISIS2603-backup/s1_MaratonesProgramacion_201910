/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.maratones.dtos;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Juan Felipe Peña
 */
public class ForoDetailDTO extends ForoDTO implements Serializable {

    /**
     * Esta lista de tipo RespuestaDTO representa las respuestas de un foro.
     */
    private List<RespuestaDTO> respuestas;
    
    
    /**
     * Constructor
     */
    public ForoDetailDTO()
    {}
}
