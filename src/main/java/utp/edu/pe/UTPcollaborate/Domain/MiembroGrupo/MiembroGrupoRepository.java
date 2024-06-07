package utp.edu.pe.UTPcollaborate.Domain.MiembroGrupo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import utp.edu.pe.UTPcollaborate.Domain.Grupo.Grupo;

import java.util.List;

@Repository
public interface MiembroGrupoRepository extends JpaRepository<MiembroGrupo, Long> {

    @Query("SELECT mg FROM MiembroGrupo mg WHERE mg.usuario.id_usuario = :userId AND mg.status IS NULL")
    List<MiembroGrupo> findPendingInvitationsByUserId(Long userId);
    List<MiembroGrupo> findByGrupo(Grupo grupo);
    @Query("SELECT mg.grupo FROM MiembroGrupo mg WHERE mg.usuario.id_usuario = :userId AND mg.grupo.id_curso.id_curso = :cursoId")
    List<Grupo> findGrupobyIdUserAndCursoId(Long userId, Long cursoId);
}
