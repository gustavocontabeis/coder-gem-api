package br.com.codersistemas.codergemapi.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.codersistemas.codergemapi.domain.Pessoa;
import br.com.codersistemas.codergemapi.repository.PessoaRepository;
import br.com.codersistemas.codergemapi.repository.PessoaSpecification;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/pessoas")
public class PessoaController {
	
	@Autowired
	private PessoaRepository repository;
	
	@GetMapping
	public List<Pessoa> listar(Pessoa pessoa) {
		log.debug("listar!");
		//return repository.findAll(Sort.by(Order.asc("nome")));
		return repository.findAll(new PessoaSpecification(pessoa));
	}
	
	@GetMapping(path="listar2")
	public List<Pessoa> listar2() {
		log.debug("listar2!");
		return repository.findAll(new PessoaSpecification(new Pessoa()));
	}
	
	/**
	 * http://localhost:8084/coder-gem/pessoas/buscar?searchTerm=da&size=2&page=2
	 */
	@GetMapping("/buscar")
    public Page<Pessoa> buscar(
            @RequestParam("searchTerm") String searchTerm,
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
		log.debug("buscar! page: {}, size:{}", page, size);
		PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.ASC, "nome");
        return repository.search(searchTerm, pageRequest);

    }

	@GetMapping("/{id}")
	public ResponseEntity<Pessoa> buscar(@PathVariable Long id) {
		Optional<Pessoa> findById = repository.findById(id);
		if(!findById.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(findById.get());
	}
	
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public Pessoa adicionar(@Valid @RequestBody Pessoa entity) {
		return repository.save(entity);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Pessoa> excluir(@PathVariable Long id) {
		Optional<Pessoa> findById = repository.findById(id);
		if(!findById.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		repository.delete(findById.get());
		return new ResponseEntity<Pessoa>(HttpStatus.NO_CONTENT);
	}

}

