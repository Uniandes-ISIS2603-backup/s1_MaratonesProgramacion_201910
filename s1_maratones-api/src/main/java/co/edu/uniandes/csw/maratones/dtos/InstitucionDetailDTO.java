/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.maratones.dtos;

import co.edu.uniandes.csw.maratones.entities.InstitucionEntity;
import co.edu.uniandes.csw.maratones.entities.UsuarioEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author c.mendez11
 */
public class InstitucionDetailDTO extends InstitucionDTO implements Serializable{
    private List<UsuarioDTO> usuarios;
    
    public InstitucionDetailDTO(){
        super();
    }
    public InstitucionDetailDTO(InstitucionEntity institucionEntity){
        super(institucionEntity);
        if(institucionEntity.getUsuarios()!=null)
        {
            usuarios= new ArrayList<>();
            for(UsuarioEntity usuarioEntity:institucionEntity.getUsuarios()){
                usuarios.add(new UsuarioDTO(usuarioEntity));
            }
        }
    }
    /**
     * @return the usuarios
     */
    public List<UsuarioDTO> getMiembros() {
        return usuarios;
    }

    /**
     * @param usuarios the usuarios to set
     */
    public void setMiembros(List<UsuarioDTO> usuarios) {
        this.usuarios = usuarios;
    }
}
