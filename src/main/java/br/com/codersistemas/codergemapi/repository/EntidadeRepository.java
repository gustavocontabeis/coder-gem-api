package br.com.codersistemas.codergemapi.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.codersistemas.codergemapi.domain.Entidade;

public interface EntidadeRepository extends JpaRepository<Entidade, Long> {

	Optional<List<Entidade>> findByAplicacaoId(Long id);
	
}

