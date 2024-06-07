package utp.edu.pe.UTPcollaborate.Domain.Usuario;

import org.antlr.v4.runtime.misc.NotNull;

public record DTOLogin(@NotNull String usuario, @NotNull String password) {
}
