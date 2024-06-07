package utp.edu.pe.UTPcollaborate.Domain.Publicacion;

import org.antlr.v4.runtime.misc.NotNull;

public record DTONewPost(int idUsuario, String text, String imgLink, @NotNull String Tiempo) {
}
