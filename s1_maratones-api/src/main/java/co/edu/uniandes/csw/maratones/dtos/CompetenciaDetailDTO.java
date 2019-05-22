/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.maratones.dtos;


import co.edu.uniandes.csw.maratones.entities.CompetenciaEntity;
import co.edu.uniandes.csw.maratones.entities.EquipoEntity;
import co.edu.uniandes.csw.maratones.entities.LenguajeEntity;
import co.edu.uniandes.csw.maratones.entities.LugarCompetenciaEntity;
import co.edu.uniandes.csw.maratones.entities.UsuarioEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Julian David Mendoza Ruiz
 */
public class CompetenciaDetailDTO extends CompetenciaDTO implements Serializable {
    
    private List<LugarCompetenciaDTO> lugarCompetencias;  
    private List<UsuarioDetailDTO> jueces;
    
    private List<EquipoDTO> equipos;
    
    public CompetenciaDetailDTO (CompetenciaEntity entity) {
        super(entity);
        
        if(entity!= null)
        {
            if(entity.getlugarCompetencias()!= null)
            {
                lugarCompetencias = new ArrayList<>();
                for(LugarCompetenciaEntity entityLugarCompetencia: entity.getlugarCompetencias())
                {
                    lugarCompetencias.add(new LugarCompetenciaDTO(entityLugarCompetencia));
                }
            }
            if(entity.getJueces()!=null)
            {
                jueces = new ArrayList<>();
                for(UsuarioEntity entityUsuario: entity.getJueces())
                {
                    jueces.add(new UsuarioDetailDTO(entityUsuario));
                }
            }
            if(entity.getEquipoEntity()!= null){
                equipos = new ArrayList<>();
                for(EquipoEntity entityEquipo: entity.getEquipoEntity())
                {
                    equipos.add(new EquipoDTO(entityEquipo));
                }
            }
            

            
        }
    }
    
    /**
     *
     * @return
     */
    public CompetenciaEntity toEntity ()
    {
        CompetenciaEntity competenciaEntity= super.toEntity();
        if(lugarCompetencias!= null)
        {
            List<LugarCompetenciaEntity> ubicacionesEntity = new ArrayList<>();
            for (LugarCompetenciaDTO dtoLugarCompetencia : lugarCompetencias) {
                ubicacionesEntity.add(dtoLugarCompetencia.toEntity());
            }
            competenciaEntity.setLugarCompetencias(ubicacionesEntity);
        }
       if(jueces!= null)
       {
           List<UsuarioEntity> juecesEntity= new ArrayList<>();
           for(UsuarioDetailDTO dtoJueces: jueces)
           {
               juecesEntity.add(dtoJueces.toEntity());
           }
           competenciaEntity.setJueces(juecesEntity);
       }
       if(equipos!=null)
       {
           List<EquipoEntity> equiposEntity = new ArrayList<>();
           for(EquipoDTO dtoEquipos: equipos)
           {
               equiposEntity.add(dtoEquipos.toEntity());
           }
           competenciaEntity.setEquipoEntity(equiposEntity);
       }
            
        return competenciaEntity;         
    }

    /**
     * @return the lugarCompetencias
     */
    public List<LugarCompetenciaDTO> getLugarCompetencias() {
        return lugarCompetencias;
    }

    /**
     * @param lugarCompetencias the lugarCompetencias to set
     */
    public void setLugarCompetencias(List<LugarCompetenciaDTO> lugarCompetencias) {
        this.lugarCompetencias = lugarCompetencias;
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