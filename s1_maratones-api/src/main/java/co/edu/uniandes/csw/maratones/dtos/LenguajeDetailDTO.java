/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.maratones.dtos;

import co.edu.uniandes.csw.maratones.entities.LenguajeEntity;
import java.io.Serializable;

/**
 *
 * @author Angel Rodriguez aa.rodriguezv
 */
public class LenguajeDetailDTO extends LenguajeDTO implements Serializable{

    public LenguajeDetailDTO() {
        super();
    }
    
    
    
    public LenguajeDetailDTO(LenguajeEntity lenguajeEntity) {
        super(lenguajeEntity);
    }
    
    
}
