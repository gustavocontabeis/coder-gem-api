package br.com.codersistemas.codergemapi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.codersistemas.codergemapi.domain.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

	@Query("FROM Pessoa c WHERE LOWER(c.nome) like %:searchTerm%")
	Page<Pessoa> search(@Param("searchTerm") String nome, Pageable pageable);

}
