package utp.edu.pe.UTPcollaborate.Domain.Curso;

public record DTOListaAlumnosCurso(
        Long idAlumno,
        String nombre,
        String rol, //rol de miembrogrupo
        String linkImagen) {
}
