package utp.edu.pe.UTPcollaborate.Domain.MiembroGrupo;

import org.antlr.v4.runtime.misc.NotNull;

public record DTOInvitations(
        @NotNull Long idGrupo,
        @NotNull String text,
        @NotNull String label) {
}
