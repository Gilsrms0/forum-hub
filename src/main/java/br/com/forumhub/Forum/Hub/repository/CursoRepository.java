package br.com.forumhub.Forum.Hub.repository;

import br.com.forumhub.Forum.Hub.domain.curso.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {
}
