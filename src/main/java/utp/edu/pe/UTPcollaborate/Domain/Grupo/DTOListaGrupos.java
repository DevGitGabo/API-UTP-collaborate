package utp.edu.pe.UTPcollaborate.Domain.Grupo;

import java.util.Arrays;
import java.util.List;

public record DTOListaGrupos(
        Long idGrupo,
        String nombre,
        String descripcion,
        List<String> label,
        String nombreTarea
) {
    public DTOListaGrupos(Grupo grupo) {
        this(grupo.getId_grupo(),
                grupo.getNombre_grupo(),
                grupo.getDescripcion(),
                Arrays.asList(grupo.getEtiquetas().split(",")),
                grupo.getTarea());
    }
}