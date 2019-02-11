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
 * @author Julian David Mendoza Ruiz
 */
public class CompetenciaDetailDTO extends CompetenciaDTO implements Serializable {
    private List<PrerequisitoDetailDTO> prerequisitos;
    
    private List<LugarCompetenciaDTO> ubicaciones;
    
    private List<UsuarioDetailDTO> inscritos;
    
    private List<UsuarioDetailDTO> jueces;
    
    public CompetenciaDetailDTO () {
        
    }

    /**
     * @return the prerequisitos
     */
    public List<PrerequisitoDetailDTO> getPrerequisitos() {
        return prerequisitos;
    }

    /**
     * @param prerequisitos the prerequisitos to set
     */
    public void setPrerequisitos(List<PrerequisitoDetailDTO> prerequisitos) {
        this.prerequisitos = prerequisitos;
    }

    /**
     * @return the ubicaciones
     */
    public List<LugarCompetenciaDTO> getUbicaciones() {
        return ubicaciones;
    }

    /**
     * @param ubicaciones the ubicaciones to set
     */
    public void setUbicaciones(List<LugarCompetenciaDTO> ubicaciones) {
        this.ubicaciones = ubicaciones;
    }

    /**
     * @return the inscritos
     */
    public List<UsuarioDetailDTO> getInscritos() {
        return inscritos;
    }

    /**
     * @param inscritos the inscritos to set
     */
    public void setInscritos(List<UsuarioDetailDTO> inscritos) {
        this.inscritos = inscritos;
    }

    /**
     * @return the jueces
     */
    public List<UsuarioDetailDTO> getJueces() {
        return jueces;
    }

    /**
     * @param jueces the jueces to set
     */
    public void setJueces(List<UsuarioDetailDTO> jueces) {
        this.jueces = jueces;
    }
}
