package utp.edu.pe.UTPcollaborate.Domain.UsuarioMision;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import utp.edu.pe.UTPcollaborate.Domain.UsuarioInsignia.UsuarioInsignia;

import java.util.List;

@Repository
public interface UsuarioMisionRepository extends JpaRepository<UsuarioMision,UsuarioMisionId> {
    List<UsuarioMision> findById_Usuario(Long Id);
}
