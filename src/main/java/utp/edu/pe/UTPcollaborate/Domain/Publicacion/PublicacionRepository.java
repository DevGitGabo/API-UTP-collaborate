package utp.edu.pe.UTPcollaborate.Domain.Publicacion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublicacionRepository extends JpaRepository<Publicacion, Long> {
    @Query("SELECT p FROM Publicacion p WHERE p.usuario.id_usuario = :userId")
    List<Publicacion> findByUserId(Long userId);
}
