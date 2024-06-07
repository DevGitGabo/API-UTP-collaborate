package utp.edu.pe.UTPcollaborate.Domain.Curso;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CursoRepository extends JpaRepository<Curso,Long> {
    @Query("SELECT c FROM Curso c WHERE c.id_curso = :id_curso")
    Optional<Curso> findCursoById_curso(Long id_curso);

    @Query("""
        SELECT c FROM Curso c
        JOIN DetalleCurso dc  on c.id_curso=dc.curso.id_curso
        WHERE dc.usuario.id_usuario = :id_usuario
    """)
    List<Curso> findCursosByIdUser(Long id_usuario);
}
