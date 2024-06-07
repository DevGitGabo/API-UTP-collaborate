package utp.edu.pe.UTPcollaborate.Domain.Grupo;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utp.edu.pe.UTPcollaborate.Domain.Curso.Curso;
import utp.edu.pe.UTPcollaborate.Domain.Curso.CursoRepository;
import utp.edu.pe.UTPcollaborate.Domain.MiembroGrupo.MiembroGrupo;
import utp.edu.pe.UTPcollaborate.Domain.MiembroGrupo.MiembroGrupoRepository;
import utp.edu.pe.UTPcollaborate.Domain.Usuario.Usuario;
import utp.edu.pe.UTPcollaborate.Domain.Usuario.UsuarioRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class GrupoService {
    @Autowired
    private final UsuarioRepository usuarioRepository;
    @Autowired
    private final GrupoRepository grupoRepository;
    @Autowired
    private final MiembroGrupoRepository miembroGrupoRepository;
    @Autowired
    private final CursoRepository cursoRepository;

    public GrupoService(UsuarioRepository usuarioRepository, GrupoRepository grupoRepository,
                        MiembroGrupoRepository miembroGrupoRepository, CursoRepository cursoRepository) {
        this.usuarioRepository = usuarioRepository;
        this.grupoRepository = grupoRepository;
        this.miembroGrupoRepository = miembroGrupoRepository;
        this.cursoRepository = cursoRepository;
    }

    @Transactional
    public void createGroup(DTOGuardarGrupo dtoGuardarGrupo, List<Long> idUsers){
        Grupo grupoNuevo = new Grupo();
        grupoNuevo.setNombre_grupo(dtoGuardarGrupo.nombre());
        grupoNuevo.setDescripcion(dtoGuardarGrupo.descripcion());
        String etiquetas = String.join(",", dtoGuardarGrupo.label());
        grupoNuevo.setEtiquetas(etiquetas);
        grupoNuevo.setTarea(dtoGuardarGrupo.nombreTarea());

        Optional<Curso> cursoOptional = cursoRepository.findCursoById_curso(dtoGuardarGrupo.idCurso());
        grupoNuevo.setId_curso(cursoOptional.get());

        // Crear una instancia de Date que represente la fecha y hora actuales
        Date fechaActual = new Date();
        // Establecer la fecha y hora actuales en el objeto grupoNuevo
        grupoNuevo.setFecha_creacion(fechaActual);

        grupoRepository.save(grupoNuevo);

        for(Long iduser: idUsers){
            Usuario usuario = usuarioRepository.findById(iduser)
                    .orElseThrow(()->new IllegalArgumentException("Usuario no se puede agregar porque no existe"));

            MiembroGrupo miembroGrupo = new MiembroGrupo();
            miembroGrupo.setUsuario(usuario);
            miembroGrupo.setGrupo(grupoNuevo);
            miembroGrupo.setStatus(true);
            miembroGrupo.setFecha_union(fechaActual);
            miembroGrupoRepository.save(miembroGrupo);
        }

    }

    public Grupo getGroupById(Long id){
        return grupoRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("Grupo con ID "+id+" no encontrado"));
    }

}
