package com.generation.blogPessoal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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
	
	@GetMapping("/id")
	public ResponseEntity<List<Postagem>> findById(){
		return ResponseEntity.ok(repository.findById(1l));
	}
	
	@GetMapping("/titulo")
	public ResponseEntity<List<Postagem>> getByTitulo(){
		return ResponseEntity.ok(repository.getByTitulo("estudos de java"));
	}
	
	@GetMapping("/titulo2")
	public ResponseEntity<List<Postagem>> getByTituloContains(){
		return ResponseEntity.ok(repository.getByTituloContains("java"));
	}
}
