package br.com.codersistemas.codergemapi.controller;

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

import br.com.codersistemas.codergemapi.domain.Pessoa;
import br.com.codersistemas.codergemapi.repository.PessoaRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/pessoas")
public class PessoaController {
	
	@Autowired
	private PessoaRepository posts;
	
	@GetMapping
	public List<Pessoa> listar() {
		log.debug("listar!");
		return posts.findAll(Sort.by(Order.asc("nome")));
	}

	@GetMapping("/{id}")
	public ResponseEntity<Pessoa> buscar(@PathVariable Long id) {
		Optional<Pessoa> findById = posts.findById(id);
		if(!findById.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(findById.get());
	}
	
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public Pessoa adicionar(@Valid @RequestBody Pessoa entity) {
		return posts.save(entity);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Pessoa> excluir(@PathVariable Long id) {
		Optional<Pessoa> findById = posts.findById(id);
		if(!findById.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		posts.delete(findById.get());
		return new ResponseEntity<Pessoa>(HttpStatus.NO_CONTENT);
	}

}

