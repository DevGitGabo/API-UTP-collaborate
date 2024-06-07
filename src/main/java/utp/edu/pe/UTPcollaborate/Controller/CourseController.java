package utp.edu.pe.UTPcollaborate.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import utp.edu.pe.UTPcollaborate.Domain.Curso.CursoService;
import utp.edu.pe.UTPcollaborate.Domain.Curso.DTOListaAlumnosCurso;
import utp.edu.pe.UTPcollaborate.Domain.Curso.DTOListaCursos;

import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private CursoService cursoService;

    // Obtener toda la lista de cursos pero de un alumno.
    @GetMapping("/listCourses/{idUsuario}")
    public ResponseEntity<List<DTOListaCursos>> getListCourseForUserId(@PathVariable Long idUsuario){
        List<DTOListaCursos> list = cursoService.getAllCursosForUserId(idUsuario);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/list")
    public ResponseEntity<List<DTOListaCursos>> getListCourse(){
        List<DTOListaCursos> list = cursoService.getAllCursos();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/alumnosCurso/{idCurso}")
    public ResponseEntity<List<DTOListaAlumnosCurso>> getListUsersByCursoId(@PathVariable Long idCurso){
        List<DTOListaAlumnosCurso> list = cursoService.getAlumnosByCurso(idCurso);
        return ResponseEntity.ok(list);
    }


}
