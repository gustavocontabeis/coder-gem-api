package br.com.codersistemas.codergemapi.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Fetch;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import br.com.codersistemas.codergemapi.domain.Pessoa;

public class PessoaSpecification implements Specification<Pessoa>{
	
	private static final long serialVersionUID = 1L;
	
	private Pessoa filter;

	public PessoaSpecification(Pessoa pessoa) {
		filter = pessoa;
	}

	public Predicate toPredicate(Root<Pessoa> pessoa, CriteriaQuery<?> query, CriteriaBuilder cb) {
		
		Fetch<Object, Object> joinMae = pessoa.fetch("mae", JoinType.LEFT);

		List<Predicate> predicates = new ArrayList<>();

		if(filter.getId() != null){
		   predicates.add(cb.equal(pessoa.get("id"), filter.getId()));
		}

		if(filter.getNome() != null){
		   predicates.add(cb.equal(pessoa.get("nome"), filter.getNome()));
		}

		if(filter.getGenero() != null){
		   predicates.add(cb.equal(pessoa.get("genero"), filter.getGenero()));
		}

		if(filter.getAltura() != null){
		   predicates.add(cb.equal(pessoa.get("altura"), filter.getAltura()));
		}

		if(filter.getSalario() != null){
		   predicates.add(cb.equal(pessoa.get("salario"), filter.getSalario()));
		}

		if(filter.getAtivo() != null){
		   predicates.add(cb.equal(pessoa.get("ativo"), filter.getAtivo()));
		}

		if(filter.getDataDeNacimento() != null){
		   predicates.add(cb.equal(pessoa.get("dataDeNacimento"), filter.getDataDeNacimento()));
		}
		
		return cb.and(predicates.toArray(new Predicate[predicates.size()]));
	}
	
}
