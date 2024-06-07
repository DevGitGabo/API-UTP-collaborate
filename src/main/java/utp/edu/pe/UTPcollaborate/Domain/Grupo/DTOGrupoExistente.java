package utp.edu.pe.UTPcollaborate.Domain.Grupo;

import org.springframework.beans.factory.annotation.Autowired;
import utp.edu.pe.UTPcollaborate.Domain.Curso.CursoService;
import utp.edu.pe.UTPcollaborate.Domain.MiembroGrupo.MiembroGrupo;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public record DTOGrupoExistente(List<DTOListaMiembrosGrupo> miembrosGrupo, String nombreTarea) {
    public DTOGrupoExistente(List<DTOListaMiembrosGrupo> miembrosGrupo, String nombreTarea) {
        this.miembrosGrupo = miembrosGrupo;
        this.nombreTarea = nombreTarea;
    }
}
