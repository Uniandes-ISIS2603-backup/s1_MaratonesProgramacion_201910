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
import co.edu.uniandes.csw.maratones.entities.UsuarioEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
/**
 * UsuarioDTO Objeto de transferencia de datos de Usuario. Los DTO contienen las
 * representaciones de los JSON que se transfieren entre el cliente y el
 * servidor.
 *
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   {
 *       "rol": string, 
 *       "nombre": string,
 *       "apellido": string, 
 *       "nombreUsuario": string, 
 *       "clave": string, 
 *       "correo": string,
 *       "imagen": string,
 *       "institucion": string
 * 
 * 
 *   }
 * </pre> Por ejemplo un usuario se representa asi:<br>
 *
 * <pre>
 *
 *   {
 *       "rol": “PARTICIPANTE”, 
 *       "nombre": "Maria Camila",
 *       "apellido": "Londono", 
 *       "nombreUsuario": “camilalonart”, 
 *       "clave": “blablabla”, 
 *       "correo": “mc.londono@uniandes.edu.co”,
 *       "imagen": “www.blablabla.com/bla.jpg”,
 *       "institucion": “Universidad de los Andes”
 *   }
 *
 * </pre>
/**
 * @author camilalonart
 */
public class EquipoDetailDTO extends EquipoDTO implements Serializable {

    private List<UsuarioDTO> participantes;
    private List<SubmissionDTO> submissions;
    private List<CompetenciaDTO> competencias;

    
    /**
     * Constructor por defecto
     */
    public EquipoDetailDTO(EquipoEntity equipoEntity) {
        
        super(equipoEntity);
        if (equipoEntity != null) {
            if (equipoEntity.getParticipantes() != null) {
                participantes = new ArrayList<>();
                for (UsuarioEntity entityBook : equipoEntity.getParticipantes()) {
                    participantes.add(new UsuarioDTO(entityBook));
                }
            }
        }
    
    }
    
    @Override
    public EquipoEntity toEntity() {
        EquipoEntity equipoEntity = super.toEntity();
        if (participantes != null) {
            List<UsuarioEntity> entity = new ArrayList<>();
            for (UsuarioDTO eldto : participantes) {
                entity.add(eldto.toEntity());
            }
            equipoEntity.setParticipantes(entity);
        }
        return equipoEntity;
    }

    /**
     * Devuelve la lista de participantes.
     *
     * @return the participantes
     */
    public List<UsuarioDTO> getParticipantes() {
        return participantes;
    }

    /**
     * Modifica la lista de participantes 
     *
     * @param participantes the books to set
     */
    public void setParticipantes(List<UsuarioDTO> participantes) {
        this.participantes = participantes;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

    /**
     * @return the submissions
     */
    public List<SubmissionDTO> getSubmissions() {
        return submissions;
    }

    /**
     * @param submissions the submissions to set
     */
    public void setSubmissions(List<SubmissionDTO> submissions) {
        this.submissions = submissions;
    }

    /**
     * @return the competencias
     */
    public List<CompetenciaDTO> getCompetencias() {
        return competencias;
    }

    /**
     * @param competencias the competencias to set
     */
    public void setCompetencias(List<CompetenciaDTO> competencias) {
        this.competencias = competencias;
    }
}
