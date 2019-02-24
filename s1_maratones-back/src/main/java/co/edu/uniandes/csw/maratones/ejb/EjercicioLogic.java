/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.maratones.ejb;

import co.edu.uniandes.csw.maratones.entities.EjercicioEntity;
import co.edu.uniandes.csw.maratones.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.maratones.persistence.EjercicioPersistence;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Angel Rodriguez aa.rodriguezv
 */
@Stateless
public class EjercicioLogic {
    
    
  @Inject  
  private EjercicioPersistence persistence;
  
    
    public EjercicioEntity createEjercicio(EjercicioEntity ejercicio) throws BusinessLogicException
    {
        if(persistence.findByName(ejercicio.getNombre()) != null)
        {
            throw new BusinessLogicException("Ya existe una ejercicio con el nombre: " + ejercicio.getNombre());
        }
        
        
        ejercicio = persistence.create(ejercicio);
        
        return ejercicio;
    }
}
