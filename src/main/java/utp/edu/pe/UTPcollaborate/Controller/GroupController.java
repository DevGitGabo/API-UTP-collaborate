package utp.edu.pe.UTPcollaborate.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utp.edu.pe.UTPcollaborate.Domain.Curso.CursoService;
import utp.edu.pe.UTPcollaborate.Domain.Grupo.*;
import utp.edu.pe.UTPcollaborate.Domain.MiembroGrupo.MiembroGrupo;
import utp.edu.pe.UTPcollaborate.Domain.MiembroGrupo.MiembroGrupoService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/group")
public class GroupController {
    @Autowired
    private GrupoService grupoService;
    @Autowired
    private MiembroGrupoService miembroGrupoService;
    @Autowired
    private CursoService cursoService;

    @PostMapping("/newGroup")
    public ResponseEntity<String> newGroup(@RequestBody DTOGuardarGrupo dtoGuardarGrupo){
        grupoService.createGroup(dtoGuardarGrupo,dtoGuardarGrupo.idsUsuarios());
        return ResponseEntity.status(HttpStatus.CREATED).body("Grupo creado exitosamente");
    }

    @GetMapping("/{id}")
    public ResponseEntity<DTOGrupoExistente> getGroupExist(@PathVariable Long id){

        Grupo grupo = grupoService.getGroupById(id);

        List<MiembroGrupo> miembrosGrupo = miembroGrupoService.getMiembrosByGrupo(grupo);

        // Crear la lista de DTOListaMiembrosGrupo
        List<DTOListaMiembrosGrupo> dtoListaMiembrosGrupo = miembrosGrupo.stream()
                .map(miembro -> {
                    // Obtener el ID del usuario asociado a este miembro
                    Long userId = miembro.getUsuario().getId_usuario();
                    // Obtener el horario del curso del usuario
                    List<String> horarioCurso = cursoService.getHorarioByUserId(userId);
                    // Crear el DTOListaMiembrosGrupo con el horario obtenido
                    return new DTOListaMiembrosGrupo(miembro, horarioCurso);
                })
                .collect(Collectors.toList());

        // Crear el DTOGrupoExistente con la lista de DTOListaMiembrosGrupo y el nombre de la tarea
        DTOGrupoExistente dtoGrupoExistente = new DTOGrupoExistente(dtoListaMiembrosGrupo, grupo.getTarea());

        return ResponseEntity.ok(dtoGrupoExistente);
    }

}
