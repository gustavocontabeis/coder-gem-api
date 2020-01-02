package br.com.codersistemas.codergemapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.codersistemas.codergemapi.domain.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
	
}

