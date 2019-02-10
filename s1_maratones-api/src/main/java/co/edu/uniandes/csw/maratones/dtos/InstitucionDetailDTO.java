/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.maratones.dtos;

import java.util.List;

/**
 *
 * @author estudiante
 */
public class InstitucionDetailDTO extends InstitucionDTO{
    private List<UsuarioDTO> miembros;
    private List<EquipoDTO> equipos;

    public InstitucionDetailDTO(){
        
    }
    /**
     * @return the miembros
     */
    public List<UsuarioDTO> getMiembros() {
        return miembros;
    }

    /**
     * @param miembros the miembros to set
     */
    public void setMiembros(List<UsuarioDTO> miembros) {
        this.miembros = miembros;
    }

    /**
     * @return the equipos
     */
    public List<EquipoDTO> getEquipos() {
        return equipos;
    }

    /**
     * @param equipos the equipos to set
     */
    public void setEquipos(List<EquipoDTO> equipos) {
        this.equipos = equipos;
    }
}
