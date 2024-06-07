package utp.edu.pe.UTPcollaborate.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utp.edu.pe.UTPcollaborate.Domain.MiembroGrupo.DTOInvitations;
import utp.edu.pe.UTPcollaborate.Domain.MiembroGrupo.MiembroGrupoService;
import utp.edu.pe.UTPcollaborate.Domain.Publicacion.DTONewPost;
import utp.edu.pe.UTPcollaborate.Domain.Publicacion.DTOPost;
import utp.edu.pe.UTPcollaborate.Domain.Publicacion.PublicacionService;

import java.util.List;

@RestController
@RequestMapping("/feed")
public class FeedController {

    @Autowired
    private PublicacionService publicacionService;
    @Autowired
    private MiembroGrupoService miembroGrupoService;

    @GetMapping("/randomPosts")
    public ResponseEntity<List<DTOPost>> getRandomPosts() {
        List<DTOPost> posts = publicacionService.getPublicacionesRandom();
        return ResponseEntity.ok(posts);
    }

    @PostMapping("/post")
    public ResponseEntity<String> createPost(@RequestBody DTONewPost newDTOPost) {
        publicacionService.createPost(newDTOPost);
        return ResponseEntity.status(HttpStatus.CREATED).body("Publicación creada exitosamente");
    }

    @GetMapping("/invitations")
    public ResponseEntity<List<DTOInvitations>> getPendingInvitations(@RequestParam Long userId) {
        List<DTOInvitations> invitations = miembroGrupoService.getPendingInvitations(userId);
        return ResponseEntity.ok(invitations);
    }
}
