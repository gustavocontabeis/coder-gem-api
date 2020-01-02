package br.com.codersistemas.codergemapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.codersistemas.codergemapi.domain.Aplicacao;

public interface AplicacaoRepository extends JpaRepository<Aplicacao, Long> {
	
}

