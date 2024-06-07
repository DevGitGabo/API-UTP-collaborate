package utp.edu.pe.UTPcollaborate.Domain.MiembroGrupo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utp.edu.pe.UTPcollaborate.Domain.Grupo.Grupo;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MiembroGrupoService {

    @Autowired
    private MiembroGrupoRepository miembroGrupoRepository;

    public List<DTOInvitations> getPendingInvitations(Long userId) {
        List<MiembroGrupo> pendingInvitations = miembroGrupoRepository.findPendingInvitationsByUserId(userId);
        
        return pendingInvitations.stream()
                .map(invitation -> new DTOInvitations(
                        invitation.getGrupo().getId_grupo(),
                        invitation.getGrupo().getNombre_grupo(),
                        "Programación"
                ))
                .collect(Collectors.toList());
    }

    public List<MiembroGrupo> getMiembrosByGrupo(Grupo grupo) {
        return miembroGrupoRepository.findByGrupo(grupo);
    }

    public List<Grupo> findGrupobyIdUserAndCursoId(Long idUsuario, Long idCurso) {
        return miembroGrupoRepository.findGrupobyIdUserAndCursoId(idUsuario, idCurso);
    }
}
