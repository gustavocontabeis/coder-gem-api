package br.com.codersistemas.codergemapi.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import br.com.codersistemas.codergemapi.domain.Entidade;

public interface EntidadeRepository extends JpaRepository<Entidade, Long> {
	
}

