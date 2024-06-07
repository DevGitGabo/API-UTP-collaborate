package utp.edu.pe.UTPcollaborate.Domain.Usuario;

import utp.edu.pe.UTPcollaborate.Domain.Publicacion.DTOPost;

import java.util.List;

public record DTOPerfil(
        String nombreCompleto,
        String codigoAlumno,
        int numeroPublicaciones,
        int numeroSeguidores,
        int numeroSeguidos,
        int numeroMVP,
        List<String> insignias,
        List<String>  logros,
        DTOPost ultimaPublicacion) {

    public DTOPerfil(Usuario usuario, List<String> insignias, List<String> logros, DTOPost post){
        this(
                usuario.getNombre() + " " + usuario.getApellido(),
                usuario.getCodigoUsuario(),
                usuario.getNum_publicaciones().intValue(),
                usuario.getSeguidores().intValue(),
                usuario.getSeguidos().intValue(),
                usuario.getNum_mvp().intValue(),
                insignias,
                logros,
                post
        );
    }
}