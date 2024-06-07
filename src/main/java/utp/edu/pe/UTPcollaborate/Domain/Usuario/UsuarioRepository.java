package utp.edu.pe.UTPcollaborate.Domain.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    List<Usuario> findByNombreContainingIgnoreCase(String nombre);

    List<Usuario> findByApellidoContainingIgnoreCase(String apellido);

    List<Usuario> findByCodigoUsuarioContainingIgnoreCase(String codigoUsuario);

    @Query("SELECT u FROM Usuario u WHERE " +
           "LOWER(u.nombre) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
           "LOWER(u.apellido) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
           "LOWER(CONCAT(u.nombre, ' ', u.apellido)) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
           "LOWER(u.codigoUsuario) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    List<Usuario> findBySearchTerm(String searchTerm);
}
