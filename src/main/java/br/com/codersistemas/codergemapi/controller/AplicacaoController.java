package br.com.codersistemas.codergemapi.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.codersistemas.codergemapi.domain.Aplicacao;
import br.com.codersistemas.codergemapi.domain.Entidade;
import br.com.codersistemas.codergemapi.repository.AplicacaoRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/aplicacoes")
public class AplicacaoController {
	
	@Autowired
	private AplicacaoRepository repository;
	
	@GetMapping
	public List<Aplicacao> listar(){
		log.debug("listar!2");
		List<Aplicacao> findAll = repository.findAll(Sort.by(Order.asc("nome")));
		for (Aplicacao aplicacao : findAll) {
			aplicacao.setEntidades(new ArrayList<Entidade>());
		}
		//findAll.stream().forEach(a->{a.setEntidades(new ArrayList<Entidade>()}));
		return findAll;
	}

	@GetMapping("/{id}")
	public ResponseEntity<Aplicacao> buscar(@PathVariable Long id) {
		Optional<Aplicacao> findById = repository.findById(id);
		if(!findById.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		Aplicacao aplicacao = findById.get();
		aplicacao.setEntidades(new ArrayList<Entidade>());
		return ResponseEntity.ok(findById.get());
	}
	
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public Aplicacao adicionar(@Valid @RequestBody Aplicacao entity) {
		@Valid
		Aplicacao save = repository.save(entity);
		save.setEntidades(new ArrayList<Entidade>());
		return save;
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Aplicacao> excluir(@PathVariable Long id) {
		Optional<Aplicacao> findById = repository.findById(id);
		if(!findById.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		repository.delete(findById.get());
		return new ResponseEntity<Aplicacao>(HttpStatus.NO_CONTENT);
	}

}

