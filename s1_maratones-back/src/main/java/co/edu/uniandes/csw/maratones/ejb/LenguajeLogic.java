/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.maratones.ejb;

import co.edu.uniandes.csw.maratones.entities.LenguajeEntity;
import co.edu.uniandes.csw.maratones.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.maratones.persistence.LenguajePersistence;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Angel Rodriguez aa.rodriguezv
 */
@Stateless
public class LenguajeLogic {
    
    
  @Inject  
  private LenguajePersistence persistence;
  
    
    public LenguajeEntity createLenguaje(LenguajeEntity lenguaje) throws BusinessLogicException
    {
        if(persistence.findByName(lenguaje.getNombre()) != null)
        {
            throw new BusinessLogicException("Ya existe un lenguaje con el nombre: " + lenguaje.getNombre());
        }
        
        
        lenguaje = persistence.create(lenguaje);
        
        return lenguaje;
    }
}
