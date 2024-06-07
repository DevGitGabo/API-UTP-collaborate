package utp.edu.pe.UTPcollaborate.Domain.Grupo;

import java.util.List;

public record DTOGuardarGrupo(
        String nombre,
        String descripcion,
        List<String> label, //etiquetas
        String nombreTarea,
        Long idCurso,
        List<Long> idsUsuarios) {
}

