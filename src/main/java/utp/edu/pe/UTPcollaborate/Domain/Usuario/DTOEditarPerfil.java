package utp.edu.pe.UTPcollaborate.Domain.Usuario;

import java.util.List;

public record DTOEditarPerfil(
        Long idUsuario,
        String nombre,
        String descripcion, //intereses
        List<String> etiquetas,
        List<String> insignias,
        boolean utpet) {
}