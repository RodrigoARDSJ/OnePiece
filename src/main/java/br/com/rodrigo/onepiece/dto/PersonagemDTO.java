package br.com.rodrigo.onepiece.dto;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import br.com.rodrigo.onepiece.model.Fruta;

public class PersonagemDTO {

	private Long id;

	@NotBlank(message = "Digite o nome do personagem!")
	private String nome;

	@NotBlank(message = "Digite a descricao do personagem")
	private String descricao;

	@JsonProperty(access = Access.WRITE_ONLY)
	private Long idFruta;
	
	private Fruta fruta;

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Long getIdfruta() {
		return idFruta;
	}

	public void setIdfruta(Long idFruta) {
		this.idFruta = idFruta;
	}

	public Fruta getFruta() {
		return fruta;
	}

	public void setFruta(Fruta fruta) {
		this.fruta = fruta;
	}
	
	

}
