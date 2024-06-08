package utp.edu.pe.UTPcollaborate.Domain.Usuario;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import utp.edu.pe.UTPcollaborate.Domain.DetalleCurso.DetalleCursoRepository;
import utp.edu.pe.UTPcollaborate.Domain.MiembroGrupo.DTOAceptInvitations;
import utp.edu.pe.UTPcollaborate.Domain.MiembroGrupo.MiembroGrupo;
import utp.edu.pe.UTPcollaborate.Domain.MiembroGrupo.MiembroGrupoRepository;
import utp.edu.pe.UTPcollaborate.Domain.Publicacion.DTOPost;
import utp.edu.pe.UTPcollaborate.Domain.Publicacion.PublicacionRepository;
import utp.edu.pe.UTPcollaborate.Domain.UsuarioInsignia.UsuarioInsigniaRepository;
import utp.edu.pe.UTPcollaborate.Domain.UsuarioMision.UsuarioMisionRepository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioInsigniaRepository usuarioInsigniaRepository;
    private final DetalleCursoRepository detalleCursoRepository;
    private final UsuarioMisionRepository usuarioMisionRepository;
    private final PublicacionRepository publicacionRepository;
    private final MiembroGrupoRepository miembroGrupoRepository;

    public UsuarioService(UsuarioRepository usuarioRepository, UsuarioInsigniaRepository usuarioInsigniaRepository, DetalleCursoRepository detalleCursoRepository, UsuarioMisionRepository usuarioMisionRepository, PublicacionRepository publicacionRepository, MiembroGrupoRepository miembroGrupoRepository) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioInsigniaRepository = usuarioInsigniaRepository;
        this.detalleCursoRepository = detalleCursoRepository;
        this.usuarioMisionRepository = usuarioMisionRepository;
        this.publicacionRepository = publicacionRepository;
        this.miembroGrupoRepository = miembroGrupoRepository;
    }

    public List<DTOListadoUsuarios> getRecomendedUsuarios(Long userId) {
        // Obtener los IDs de los cursos en los que está inscrito el usuario actual
        List<Long> cursoIds = detalleCursoRepository.findCursoIdsByUserId(userId);

        // Obtener los usuarios inscritos en los mismos cursos (excluyendo al usuario actual)
        List<Usuario> recomendados = cursoIds.stream()
                .flatMap(cursoId -> detalleCursoRepository.findUsuariosByCursoIdAndNotUserId(cursoId, userId).stream())
                .distinct()
                .limit(10) // Limitar a los primeros 10 resultados
                .collect(Collectors.toList());

        // Convertir a DTOListadoUsuarios
        return recomendados.stream()
                .map(DTOListadoUsuarios::new)
                .collect(Collectors.toList());
    }

    public DTOInfoUsuario getUsuarioDTOById(Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
        List<String> insignias = usuarioInsigniaRepository.findById_Usuario(id).stream()
                .map(usuarioInsignia -> usuarioInsignia.getInsignia().getNombre())
                .collect(Collectors.toList());
        return new DTOInfoUsuario(usuario, insignias);
    }

    public List<DTOListadoUsuarios> findBySearchTerm(String searchTerm) {
        List<Usuario> usuarios = usuarioRepository.findBySearchTerm(searchTerm);
        return usuarios.stream()
                .map(DTOListadoUsuarios::new)
                .collect(Collectors.toList());
    }

    public DTOPerfil getPerfilUserById(Long id){
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        List<String> insignias = usuarioInsigniaRepository.findById_Usuario(id).stream()
                .map(usuarioInsignia -> usuarioInsignia.getInsignia().getNombre())
                .collect(Collectors.toList());

        List<String> logros = usuarioMisionRepository.findById_Usuario(id).stream()
                .map(usuarioMision -> usuarioMision.getMision().getNombre())
                .collect(Collectors.toList());

        DTOPost dtoPost = publicacionRepository.findByUserId(id).stream()
                .map(DTOPost::new)  // Convierte Publicacion a DTOPost
                .min(Comparator.comparing(DTOPost::Tiempo))
                .orElseThrow(() -> new IllegalArgumentException("Usted no ha realizado ninguna publicacion"));

        return new DTOPerfil(usuario,insignias,logros,dtoPost);
    }

    @Transactional
    public void updatePerfil(Long idUser, DTOEditarPerfil dtoEditarPerfil) {
        Usuario userExist = usuarioRepository.findById(idUser)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        // Verificar y actualizar el nombre si no es nulo
        if (dtoEditarPerfil.nombre() != null) {
            userExist.setNombre(dtoEditarPerfil.nombre());
        }

        // Verificar y actualizar la descripción si no es nula
        if (dtoEditarPerfil.descripcion() != null) {
            userExist.setDescripccion(dtoEditarPerfil.descripcion());
        }

        // Verificar y actualizar las etiquetas si no son nulas
        if (dtoEditarPerfil.etiquetas() != null && !dtoEditarPerfil.etiquetas().isEmpty()) {
            String etiquetas = String.join(",", dtoEditarPerfil.etiquetas());
            userExist.setEtiquetas(etiquetas);
        }

        // Verificar y actualizar las insignias si no son nulas
        if (dtoEditarPerfil.insignias() != null && !dtoEditarPerfil.insignias().isEmpty()) {
            //String insignias = String.join(",", dtoEditarPerfil.insignias());
            //userExist.setEtiquetas(insignias);
        }

        // Actualizar el estado de UTPET
        userExist.setVisible_utpet(dtoEditarPerfil.utpet());

        usuarioRepository.save(userExist);
    }

    @Transactional
    public void updateMiembroGrupo(DTOAceptInvitations dtoAceptInvitations) {



        /*//usuario al que fue enviado la solicitud
        Usuario usuario = usuarioRepository.findById(dtoAceptInvitations.id_usuario())
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));*/

        MiembroGrupo miembroGrupo = miembroGrupoRepository.findByUsuarioIdAndCursoId(
                        dtoAceptInvitations.id_usuario(), dtoAceptInvitations.id_grupo())
                .orElseThrow(() -> new IllegalArgumentException("Miembro del grupo no encontrado"));

        //
        miembroGrupo.setStatus(dtoAceptInvitations.status());

        miembroGrupoRepository.save(miembroGrupo);
    }
}
