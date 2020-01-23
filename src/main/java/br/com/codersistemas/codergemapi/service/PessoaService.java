package br.com.codersistemas.codergemapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.codersistemas.codergemapi.domain.Pessoa;
import br.com.codersistemas.codergemapi.repository.PessoaRepository;
import br.com.codersistemas.codergemapi.repository.PessoaSpecification;
import lombok.extern.slf4j.Slf4j;

//@Slf4j
//@Service
public class PessoaService {
	
	private PessoaRepository repository;
	private PessoaSpecification specification;
	
	//@Autowired
    public PessoaService(PessoaRepository repository, PessoaSpecification specification) {
		super();
		this.repository = repository;
		this.specification = specification;
	}

	@Transactional(readOnly = false)
    public List<Pessoa> findAll(Pessoa filter) {
        return repository.findAll(specification);
    }
	
	@Transactional(readOnly = false)
    public List<Pessoa> findAll(Sort sort) {
        return repository.findAll(sort);
    }

	public Page<Pessoa> search(String searchTerm, int page, int size) {
		PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.ASC, "nome");
		return repository.search(searchTerm, pageRequest);
	}
	
}
