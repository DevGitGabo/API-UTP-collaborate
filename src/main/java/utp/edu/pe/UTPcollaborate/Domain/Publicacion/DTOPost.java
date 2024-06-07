package utp.edu.pe.UTPcollaborate.Domain.Publicacion;

import org.antlr.v4.runtime.misc.NotNull;

public record DTOPost(
        String text,
        String imgLink,
        @NotNull String nombre,
        @NotNull String Tiempo) {

    public DTOPost(Publicacion publicacion) {
        this(
                publicacion.getDescripcion(),
                publicacion.getImagen(),
                publicacion.getUsuario().getNombre(),  // Asumiendo que Usuario tiene un método getNombre()
                publicacion.getFecha_publicacion().toString()  // Convertir LocalDateTime a String
        );
    }
}