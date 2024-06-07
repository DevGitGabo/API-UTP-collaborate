package utp.edu.pe.UTPcollaborate.Domain.Grupo;

import utp.edu.pe.UTPcollaborate.Domain.MiembroGrupo.MiembroGrupo;

import java.util.List;
import java.util.Optional;

public record DTOListaMiembrosGrupo(String nombre, String linkImagen, String rol, List<String> infoHorario, Long idMiembro) {
    public DTOListaMiembrosGrupo(MiembroGrupo miembroGrupo, List<String> horarioCurso) {
        this(miembroGrupo.getUsuario().getNombre(),
                miembroGrupo.getUsuario().getImagen_perfil(),
                miembroGrupo.getRol(),
                horarioCurso,
                miembroGrupo.getUsuario().getId_usuario());
    }
}