package utp.edu.pe.UTPcollaborate.Domain.MiembroGrupo;
import org.antlr.v4.runtime.misc.NotNull;
public record DTOAceptInvitations(
        @NotNull Long id_usuario,
        @NotNull Long id_grupo, //idgrupo
        @NotNull boolean status
) {
}
