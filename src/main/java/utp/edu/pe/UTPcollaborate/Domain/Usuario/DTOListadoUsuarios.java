package utp.edu.pe.UTPcollaborate.Domain.Usuario;

import org.antlr.v4.runtime.misc.NotNull;

import java.util.Arrays;
import java.util.List;

public record DTOListadoUsuarios(@NotNull String name, List<String> label, String linkImagen
        , @NotNull int idUsuario) {
    public DTOListadoUsuarios(Usuario usuario) {
        this(
                usuario.getNombre() + " " + usuario.getApellido(),
                usuario.getEtiquetas() != null ? Arrays.asList(usuario.getEtiquetas().trim().split("\\s*-\\s*")) : List.of(),
                usuario.getImagen_perfil(),
                usuario.getId_usuario().intValue()
        );
    }
}
