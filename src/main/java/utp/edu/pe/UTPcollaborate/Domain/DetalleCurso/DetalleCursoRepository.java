package utp.edu.pe.UTPcollaborate.Domain.DetalleCurso;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import utp.edu.pe.UTPcollaborate.Domain.Curso.Curso;
import utp.edu.pe.UTPcollaborate.Domain.Usuario.Usuario;

import java.util.List;
import java.util.Optional;

@Repository
public interface DetalleCursoRepository extends JpaRepository<DetalleCurso, Long> {

    @Query("SELECT dc.usuario FROM DetalleCurso dc WHERE dc.curso.id_curso = :cursoId AND dc.usuario.id_usuario <> :userId")
    List<Usuario> findUsuariosByCursoIdAndNotUserId(Long cursoId, Long userId);

    @Query("SELECT dc.curso.id_curso FROM DetalleCurso dc WHERE dc.usuario.id_usuario = :usuarioId")
    List<Long> findCursoIdsByUserId(Long usuarioId);

    @Query("SELECT dc.curso FROM DetalleCurso dc WHERE dc.usuario.id_usuario = :id_usuario")
    List<Curso> buscarCursoPorUserId(Long id_usuario);

    @Query("""
            SELECT u FROM Usuario u
            JOIN DetalleCurso dc ON u.id_usuario=dc.usuario.id_usuario
            JOIN Curso c ON c.id_curso=dc.curso.id_curso
            WHERE c.id_curso=?1
            """)
    List<Usuario> getUsuariosByCourse(Long id_curso);
}
