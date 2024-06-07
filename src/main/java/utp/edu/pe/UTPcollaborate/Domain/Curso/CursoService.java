package utp.edu.pe.UTPcollaborate.Domain.Curso;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utp.edu.pe.UTPcollaborate.Domain.DetalleCurso.DetalleCurso;
import utp.edu.pe.UTPcollaborate.Domain.DetalleCurso.DetalleCursoRepository;
import utp.edu.pe.UTPcollaborate.Domain.Usuario.Usuario;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CursoService {
    @Autowired
    private final CursoRepository cursoRepository;
    @Autowired
    private final DetalleCursoRepository detalleCursoRepository;

    public CursoService(CursoRepository cursoRepository, DetalleCursoRepository detalleCursoRepository) {
        this.cursoRepository = cursoRepository;
        this.detalleCursoRepository = detalleCursoRepository;
    }

    public List<DTOListaCursos> getAllCursos(){
        List<Curso> listCursos = cursoRepository.findAll();
        return listCursos.stream()
                .map(curso -> new DTOListaCursos(curso.getId_curso(), curso.getNombre_curso()))
                .collect(Collectors.toList());
    }

    public List<DTOListaCursos> getAllCursosForUserId(Long idCurso){
        List<Curso> listCursosForUser = cursoRepository.findCursosByIdUser(idCurso);
        return listCursosForUser.stream()
                .map(curso -> new DTOListaCursos(curso.getId_curso(), curso.getNombre_curso()))
                .collect(Collectors.toList());
    }


    public List<DTOListaAlumnosCurso> getAlumnosByCurso(Long idCurso){
        List<Usuario> listUsuario = detalleCursoRepository.getUsuariosByCourse(idCurso);
        return listUsuario.stream()
                .map(usuario -> new DTOListaAlumnosCurso(usuario.getId_usuario(),usuario.getNombre(),
                        "Lider",usuario.getImagen_perfil())).collect(Collectors.toList());

    }

    public List<String> getHorarioByUserId(Long userId) {
        List<Curso> cursos = detalleCursoRepository.buscarCursoPorUserId(userId);

        // Si no se encuentra ningún curso, devuelve una lista vacía
        if (cursos.isEmpty()) {
            return Collections.emptyList();
        }

        // Mapea la lista de cursos a una lista de horarios
        return cursos.stream()
                .map(Curso::getHorario)
                .collect(Collectors.toList());
    }
}
