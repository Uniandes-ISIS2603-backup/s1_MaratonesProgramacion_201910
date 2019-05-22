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

import co.edu.uniandes.csw.maratones.entities.BlogEntity;
import co.edu.uniandes.csw.maratones.entities.CompetenciaEntity;
import co.edu.uniandes.csw.maratones.entities.EquipoEntity;
import co.edu.uniandes.csw.maratones.entities.LenguajeEntity;
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
 * 
/**
 * @author camilalonart
 */
public class UsuarioDetailDTO extends UsuarioDTO implements Serializable {

    private List<EquipoDTO> equipos;
    private List<LenguajeDTO> lenguajes;
    private List<CompetenciaDetailDTO> competencias;
    private List<BlogDTO> blog;
    /**
     * Constructor por defecto
     */
    public UsuarioDetailDTO() {
    }

    /**
     * Constructor a partir de la entidad
     *
     * @param usuarioEntity La entidad del libro
     */
    public UsuarioDetailDTO(UsuarioEntity usuarioEntity) {
        super(usuarioEntity);
        if (usuarioEntity.getLenguajes()!= null) {
            lenguajes = new ArrayList<>();
            for (LenguajeEntity entity : usuarioEntity.getLenguajes()) {
                lenguajes.add(new LenguajeDTO(entity));
            }
        }
        if (usuarioEntity.getEquipos()!= null) {
            equipos = new ArrayList<>();
            for (EquipoEntity entity : usuarioEntity.getEquipos()) {
                equipos.add(new EquipoDTO(entity));
            }
        }
        
        if (usuarioEntity.getCompetenciasJuez()!=null)
        {
            competencias = new ArrayList();
            for (CompetenciaEntity entity: usuarioEntity.getCompetenciasJuez())
            {
                competencias.add(new CompetenciaDetailDTO(entity));
            }
        }
        if (usuarioEntity != null) {
            if (usuarioEntity.getBlogs()!= null) {
                blog = new ArrayList<>();
                for (BlogEntity entityBlog : usuarioEntity.getBlogs()) {
                    blog.add(new BlogDTO(entityBlog));
                }
            }
        }
    }
    public List<EquipoDTO> getEquipos() {
        return equipos;
    }

    public void setEquipos(List<EquipoDTO> equipos) {
        this.equipos = equipos;
    }

    public List<LenguajeDTO> getLenguajes() {
        return lenguajes;
    }

    public void setLenguajes(List<LenguajeDTO> lenguajes) {
        this.lenguajes = lenguajes;
    }

    @Override
    public UsuarioEntity toEntity() {
        UsuarioEntity usuarioEntity = super.toEntity();
        if (lenguajes != null) {
            List<LenguajeEntity> entity = new ArrayList<>();
            for(LenguajeDTO eldto : lenguajes) {
                entity.add(eldto.toEntity());
            }
            usuarioEntity.setLenguajes(entity);
        }
        
        if (equipos != null) {
            List<EquipoEntity> entity = new ArrayList<>();
            for (EquipoDTO eldto : equipos) {
                entity.add(eldto.toEntity());
            }
            usuarioEntity.setEquipos(entity);
        }
        if(getCompetencias() !=null)
        {
            List<CompetenciaEntity> entity = new ArrayList<>();
            for(CompetenciaDetailDTO eldto: getCompetencias())
            {
                entity.add(eldto.toEntity());
            }
            usuarioEntity.setCompetenciasJuez(entity);
        }
        if (blog != null) {
            List<BlogEntity> blogsEntity = new ArrayList<>();
            for (BlogDTO dtoBlog: blog) {
                blogsEntity.add(dtoBlog.toEntity());
            }
            usuarioEntity.setBlogs(blogsEntity);
        }
        if (blog != null) {
            List<BlogEntity> blogsEntity = new ArrayList<>();
            for (BlogDTO dtoBlog: blog) {
                blogsEntity.add(dtoBlog.toEntity());
            }
            usuarioEntity.setBlogs(blogsEntity);
        }
        return usuarioEntity;
    }
    
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

    /**
     * @return the competencias
     */
    public List<CompetenciaDetailDTO> getCompetencias() {
        return competencias;
    }

    /**
     * @param competencias the competencias to set
     */
    public void setCompetencias(List<CompetenciaDetailDTO> competencias) {
        this.competencias = competencias;
    }

    /**
     * @return the blog
     */
    public List<BlogDTO> getBlog() {
        return blog;
    }

    /**
     * @param blog the blog to set
     */
    public void setBlog(List<BlogDTO> blog) {
        this.blog = blog;
    }

    
}
