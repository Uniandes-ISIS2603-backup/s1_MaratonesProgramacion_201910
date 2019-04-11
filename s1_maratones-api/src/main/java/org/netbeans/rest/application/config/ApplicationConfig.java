/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.netbeans.rest.application.config;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author estudiante
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(co.edu.uniandes.csw.maratones.filters.CORSFilter.class);
        resources.add(co.edu.uniandes.csw.maratones.mappers.BusinessLogicExceptionMapper.class);
        resources.add(co.edu.uniandes.csw.maratones.mappers.ExceptionMapperA.class);
        resources.add(co.edu.uniandes.csw.maratones.mappers.WebApplicationExceptionMapper.class);
        resources.add(co.edu.uniandes.csw.maratones.resources.BlogResource.class);
        resources.add(co.edu.uniandes.csw.maratones.resources.ComentarioResource.class);
        resources.add(co.edu.uniandes.csw.maratones.resources.CompetenciaLugarCompetenciasResource.class);
        resources.add(co.edu.uniandes.csw.maratones.resources.CompetenciaResource.class);
        resources.add(co.edu.uniandes.csw.maratones.resources.EjercicioResource.class);
        resources.add(co.edu.uniandes.csw.maratones.resources.EjercicioSubmissionsResource.class);
        resources.add(co.edu.uniandes.csw.maratones.resources.EquipoResource.class);
        resources.add(co.edu.uniandes.csw.maratones.resources.ForoResource.class);
        resources.add(co.edu.uniandes.csw.maratones.resources.InstitucionResource.class);
        resources.add(co.edu.uniandes.csw.maratones.resources.LenguajeResource.class);
        resources.add(co.edu.uniandes.csw.maratones.resources.LenguajeUsuarioResource.class);
        resources.add(co.edu.uniandes.csw.maratones.resources.LugarCompetenciaCompetenciaResource.class);
        resources.add(co.edu.uniandes.csw.maratones.resources.LugarCompetenciaResource.class);
        resources.add(co.edu.uniandes.csw.maratones.resources.PublicacionResource.class);
        resources.add(co.edu.uniandes.csw.maratones.resources.SubmissionEquipoResource.class);
        resources.add(co.edu.uniandes.csw.maratones.resources.SubmissionResource.class);
        resources.add(co.edu.uniandes.csw.maratones.resources.UsuarioResource.class);
    }
    
}
