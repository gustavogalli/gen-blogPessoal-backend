package com.generation.blogPessoal.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity // indica que a classe Postagem é uma entidade do JPA Hibernate
@Table(name = "postagem") // indica que a entidade criará uma TABELA de nome "postagem"
public class Postagem {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY) // indica que o ID será uma chave primária
	private long id;
	
	@NotNull
	@Size(min = 5, max = 100) // quantidade de caracteres
	private String titulo;
	
	@NotNull
	@Size(min = 10, max = 500)
	private String texto;
	
	@Temporal(TemporalType.TIMESTAMP) // indica para o JPA Hibernate que estamos trabalhando com tempo
	private Date data = new java.sql.Date(System.currentTimeMillis()); // pega a data e horário exatos em que foi postado
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

}
