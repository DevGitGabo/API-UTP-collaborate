package utp.edu.pe.UTPcollaborate.Domain.UsuarioInsignia;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public interface UsuarioInsigniaRepository extends JpaRepository<UsuarioInsignia, UsuarioInsigniaId> {
    List<UsuarioInsignia> findById_Usuario(Long Id);
}