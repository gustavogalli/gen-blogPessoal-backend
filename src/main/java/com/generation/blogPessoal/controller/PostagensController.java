package com.generation.blogPessoal.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.generation.blogPessoal.model.Postagem;
import com.generation.blogPessoal.repository.PostagemRepository;

@RestController // indica que a classe é um controlador
@RequestMapping("/postagens") // define a rota (url)
@CrossOrigin("*") // indica que aceitará requisições de qualquer origem
public class PostagensController {

	private @Autowired PostagemRepository repository;

	@GetMapping("/all")
	public ResponseEntity<List<Postagem>> findall() {
		List<Postagem> list = repository.findAll();

		if (list.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(list);
		}
	}

	@GetMapping("/{id_user}")
	public ResponseEntity<Postagem> findById(@PathVariable(value = "id_user") Long id_user) {

		Optional<Postagem> optional = repository.findById(id_user);

		if (optional.isPresent()) {
			return ResponseEntity.status(200).body(optional.get());
		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id não existe!");
		}
	}

	@GetMapping("/titulo/{titulo}")
	public Postagem getByTitulo(@PathVariable(value = "titulo") String titulo) {
		return repository.getByTitulo(titulo);
	}

	@GetMapping("/titulo/*/{titulo}")
	public List<Postagem> findAllByTituloContaining(@PathVariable(value = "titulo") String titulo) {
		return repository.findAllByTituloContaining(titulo);
	}

	@PostMapping("/save")
	public ResponseEntity<Postagem> save(@RequestBody Postagem newUser) {
		return ResponseEntity.status(201).body(repository.save(newUser));
	}

	@PutMapping("/update")
	public Postagem update(@Valid @RequestBody Postagem newUser) {
		return repository.save(newUser);
	}

	@DeleteMapping("/delete/{id_user}")
	public void delete(@PathVariable(value = "id_user") Long id_user) {
		repository.deleteById(id_user);
	}

}
