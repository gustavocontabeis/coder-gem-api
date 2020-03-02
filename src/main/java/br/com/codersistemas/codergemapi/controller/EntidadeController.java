package br.com.codersistemas.codergemapi.controller;

import java.util.ArrayList;
import java.util.Collections;
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

import br.com.codersistemas.codergemapi.domain.Atributo;
import br.com.codersistemas.codergemapi.domain.Entidade;
import br.com.codersistemas.codergemapi.repository.EntidadeRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/entidades")
public class EntidadeController {
	
	@Autowired
	private EntidadeRepository posts;
	
	@GetMapping
	public List<Entidade> listar() {
		log.debug("listar!");
		List<Entidade> findAll = posts.findAll(Sort.by(Order.asc("nome")));
		findAll.forEach(i->i.getAplicacao().setEntidades(new ArrayList<>()));
		return findAll;
	}

	@GetMapping("/{id}")
	public ResponseEntity<Entidade> buscar(@PathVariable Long id) {
		Optional<Entidade> findById = posts.findById(id);
		if(!findById.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(findById.get());
	}
	
	@GetMapping("/aplicacao/{id}")
	public ResponseEntity<List<Entidade>> buscarEntidadesPorAplicacao(@PathVariable("id") Long id) {
		Optional<List<Entidade>> findById = posts.findByAplicacaoId(id);
		if(!findById.isPresent()) {
			return ResponseEntity.ok(Collections.EMPTY_LIST);
		}else {
			findById.get().forEach(obj->{
				obj.setAtributos(new ArrayList<Atributo>());
				obj.getAplicacao().setEntidades(null);;
				});
		}
		return ResponseEntity.ok(findById.get());
	}
	
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public Entidade adicionar(@Valid @RequestBody Entidade entity) {
		@Valid	
		Entidade save = posts.save(entity);
		save.setAplicacao(null);
		save.setAtributos(new ArrayList<>());
		return save;
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Entidade> excluir(@PathVariable Long id) {
		Optional<Entidade> findById = posts.findById(id);
		if(!findById.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		posts.delete(findById.get());
		return new ResponseEntity<Entidade>(HttpStatus.NO_CONTENT);
	}

}

