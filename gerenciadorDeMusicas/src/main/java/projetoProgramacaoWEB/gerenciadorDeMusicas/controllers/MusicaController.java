package projetoProgramacaoWEB.gerenciadorDeMusicas.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import projetoProgramacaoWEB.gerenciadorDeMusicas.service.MusicaService;
import projetoProgramacaoWEB.gerenciadorDeMusicas.vo.MusicaVo;

@RestController
@RequestMapping(value = "/musica")
public class MusicaController {

    @Autowired
    private MusicaService musicaService;
    
    //joão
    @GetMapping
    public ResponseEntity<List<MusicaVo>> buscaTodosMusicas() {
    	List<MusicaVo> lista = musicaService.findAll();
        return ResponseEntity.ok().body(lista);
    }
    
    //Angelica
    @GetMapping("/{id}")
    public ResponseEntity<MusicaVo> buscaMusicasId(@PathVariable(value = "id") Integer id) {
        MusicaVo musica = musicaService.findById(id);
        if (musica == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(musica);
    }
    
    //Angelica
    @DeleteMapping("/{id}")
    public ResponseEntity<MusicaVo> excluirMusica(@PathVariable(value = "id") Integer id) {
        if (musicaService.deleteById(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }


    //joão
    @PostMapping
    public ResponseEntity<MusicaVo> salvarMusica(@RequestBody MusicaVo musica) {
        MusicaVo novoMusica = musicaService.saveMusica(musica);
        return ResponseEntity.ok().body(novoMusica);
    }
    
    //Angelica
    @PutMapping("/{id}")
    public ResponseEntity<MusicaVo> atualizarMusica(@PathVariable Integer id, @RequestBody MusicaVo musica) {
        MusicaVo musicaAtualizada = musicaService.updateMusica(id, musica);
        if (musicaAtualizada == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(musicaAtualizada);
    }
}
