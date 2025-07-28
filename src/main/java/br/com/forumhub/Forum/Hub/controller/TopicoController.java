package br.com.forumhub.Forum.Hub.controller;

import br.com.forumhub.Forum.Hub.domain.topico.DadosCadastroTopico;
import br.com.forumhub.Forum.Hub.domain.topico.DadosDetalhamentoTopico;
import br.com.forumhub.Forum.Hub.domain.topico.Topico;
import br.com.forumhub.Forum.Hub.repository.CursoRepository;
import br.com.forumhub.Forum.Hub.repository.TopicoRepository;
import br.com.forumhub.Forum.Hub.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository repository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    public ResponseEntity<DadosDetalhamentoTopico> cadastrar(@RequestBody @Valid DadosCadastroTopico dados) {
        var curso = cursoRepository.getReferenceById(dados.idCurso());
        var autor = usuarioRepository.getReferenceById(dados.idAutor());
        var topico = new Topico();
        topico.setTitulo(dados.titulo());
        topico.setMensagem(dados.mensagem());
        topico.setCurso(curso);
        topico.setAutor(autor);
        repository.save(topico);
        return ResponseEntity.ok(new DadosDetalhamentoTopico(topico));
    }

    @GetMapping
    public ResponseEntity<List<DadosDetalhamentoTopico>> listar() {
        var topicos = repository.findAll().stream().map(DadosDetalhamentoTopico::new).toList();
        return ResponseEntity.ok(topicos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoTopico> buscarPorId(@PathVariable Long id) {
        var topico = repository.findById(id);
        return topico.map(value -> ResponseEntity.ok(new DadosDetalhamentoTopico(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping
    public ResponseEntity<DadosDetalhamentoTopico> atualizar(@RequestBody @Valid DadosAtualizacaoTopico dados) {
        var topico = repository.getReferenceById(dados.id());
        if (dados.titulo() != null) topico.setTitulo(dados.titulo());
        if (dados.mensagem() != null) topico.setMensagem(dados.mensagem());
        return ResponseEntity.ok(new DadosDetalhamentoTopico(topico));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
