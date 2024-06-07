package utp.edu.pe.UTPcollaborate.Domain.Publicacion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utp.edu.pe.UTPcollaborate.Domain.Usuario.Usuario;
import utp.edu.pe.UTPcollaborate.Domain.Usuario.UsuarioRepository;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class PublicacionService {
    @Autowired
    private final PublicacionRepository publicacionRepository;
    @Autowired
    private final UsuarioRepository usuarioRepository;

    public PublicacionService(PublicacionRepository publicacionRepository, UsuarioRepository usuarioRepository) {
        this.publicacionRepository = publicacionRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public List<DTOPost> getPublicacionesRandom() {
        List<Publicacion> publicaciones = publicacionRepository.findAll();
        int totalPublicaciones = publicaciones.size();

        Random random = new Random();

        return random.ints(0, totalPublicaciones)
                .limit(5)
                .mapToObj(publicaciones::get)
                .map(publicacion -> new DTOPost(
                        publicacion.getDescripcion(),
                        publicacion.getImagen(),
                        publicacion.getUsuario().getNombre() + " " + publicacion.getUsuario().getApellido(),
                        calcularTiempoTranscurrido(publicacion.getFecha_publicacion())
                ))
                .collect(Collectors.toList());
    }

    public String calcularTiempoTranscurrido(LocalDateTime fechaPublicacion) {
        LocalDateTime fechaActual = LocalDateTime.now();
        Duration duration = Duration.between(fechaPublicacion, fechaActual);

        long seconds = duration.getSeconds();
        long minutes = seconds / 60;
        long hours = minutes / 60;
        long days = hours / 24;

        if (days > 0) {
            return "Hace " + days + " día" + (days == 1 ? "" : "s");
        } else if (hours > 0) {
            return "Hace " + hours + " hora" + (hours == 1 ? "" : "s");
        } else if (minutes > 0) {
            return "Hace " + minutes + " minuto" + (minutes == 1 ? "" : "s");
        } else {
            return "Hace menos de un minuto";
        }
    }

    public void createPost(DTONewPost dtoNewPost) {
        // Buscar el usuario por ID
        Usuario usuario = usuarioRepository.findById((long) dtoNewPost.idUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Crear la nueva publicación
        Publicacion publicacion = new Publicacion();
        publicacion.setDescripcion(dtoNewPost.text());
        publicacion.setNum_interacciones(0L);
        publicacion.setImagen(dtoNewPost.imgLink());
        publicacion.setUsuario(usuario);

        // Parsear el tiempo
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        LocalDateTime fechaPublicacion = LocalDateTime.parse(dtoNewPost.Tiempo(), formatter);
        publicacion.setFecha_publicacion(fechaPublicacion);

        // Guardar la publicación en la base de datos
        publicacionRepository.save(publicacion);
    }
}
