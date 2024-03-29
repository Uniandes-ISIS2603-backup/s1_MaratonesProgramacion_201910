/*
MIT License
Copyright (c) 2019 Universidad de los Andes - ISIS2603
Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:
The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.
THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 */
package co.edu.uniandes.csw.maratones.dtos;

import co.edu.uniandes.csw.maratones.entities.EquipoEntity;
import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * CascaraDTO Objeto de transferencia de datos de la cascara. Los DTO contienen
 * las representaciones de los JSON que se transfieren entre el cliente y el
 * servidor.
 *
 *
 * @author ISIS2603
 */
public class EquipoDTO implements Serializable {
    private UsuarioDTO coach;
    private String nombreEquipo;
    
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public EquipoDTO(){
        
    }
    
    
    public EquipoDTO(EquipoEntity equipoEntity) {
        if (equipoEntity != null) {
            this.nombreEquipo = equipoEntity.getNombreEquipo();
            this.id = equipoEntity.getId();
            if(equipoEntity.getCoach() != null)
            {
                this.coach = new UsuarioDTO(equipoEntity.getCoach());
            }
        }
    }
    
    public UsuarioDTO getCoach() {
        return coach;
    }

    public void setCoach(UsuarioDTO coach) {
        this.coach = coach;
    }

    public String getNombreEquipo() {
        return nombreEquipo;
    }

    public void setNombreEquipo(String nombreEquipo) {
        this.nombreEquipo = nombreEquipo;
    }

    public EquipoEntity toEntity() {
        EquipoEntity equipoEntity = new EquipoEntity();
        equipoEntity.setNombreEquipo(this.nombreEquipo);
        return equipoEntity;
    }
    
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
