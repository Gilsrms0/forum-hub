package br.com.forumhub.Forum.Hub.repository;

import br.com.forumhub.Forum.Hub.domain.topico.Topico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicoRepository extends JpaRepository<Topico, Long> {
}
