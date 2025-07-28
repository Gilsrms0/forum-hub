package br.com.forumhub.Forum.Hub.domain.topico;

import java.time.LocalDateTime;

public record DadosDetalhamentoTopico(
        Long id, String titulo, String mensagem, LocalDateTime dataCriacao,
        String status, String nomeAutor, String nomeCurso
) {
    public DadosDetalhamentoTopico(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensagem(), topico.getDataCriacao(),
                topico.getStatus().toString(), topico.getAutor().getNome(), topico.getCurso().getNome());

    }
}
