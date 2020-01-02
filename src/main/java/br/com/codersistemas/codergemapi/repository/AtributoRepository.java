package br.com.codersistemas.codergemapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.codersistemas.codergemapi.domain.Atributo;


public interface AtributoRepository extends JpaRepository<Atributo, Long> {
	
}

