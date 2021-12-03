package com.generation.blogPessoal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.blogPessoal.model.Postagem;
import com.generation.blogPessoal.repository.PostagemRepository;

@RestController // indica que a classe é um controlador
@RequestMapping("/postagens") // define a rota (url)
@CrossOrigin("*") // indica que aceitará requisições de qualquer origem

public class PostagensController {

	@Autowired
	private PostagemRepository repository; // é uma interface, então não conseguimos instanciar, então botamos @Autowired
	
	@GetMapping("/todas")
	public ResponseEntity<List<Postagem>> getAll(){ // método que pega uma lista de todas as postagens
		return ResponseEntity.ok(repository.findAll()); // retorna uma tabela pegando do repositório de postagens
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Postagem> getById(@PathVariable long id){ // informa que o parâmetro ID é variável
		return repository.findById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/titulo/{titulo}") // criamos /titulo/{atributo} pq senão daria duplicidade de end point com o de getById
	public ResponseEntity<List<Postagem>> getByTitulo(@PathVariable String titulo){
		return ResponseEntity.ok(repository.findAllByTituloContainingIgnoreCase(titulo));
	}
	
	@PostMapping
	public ResponseEntity<Postagem> post(@RequestBody Postagem postagem){ // consegue pegar o que está no corpo da requisição
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(postagem));
	}
	
}
