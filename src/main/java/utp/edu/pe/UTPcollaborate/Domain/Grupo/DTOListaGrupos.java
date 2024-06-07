package utp.edu.pe.UTPcollaborate.Domain.Grupo;

import java.util.List;

public record DTOListaGrupos(int idGrupo, String nombre, String descripcion, List<String> label, String nombreTarea) {
}
