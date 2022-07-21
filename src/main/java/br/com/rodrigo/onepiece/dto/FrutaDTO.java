package br.com.rodrigo.onepiece.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import br.com.rodrigo.onepiece.model.Categoria;
import br.com.rodrigo.onepiece.model.Personagem;

public class FrutaDTO {

	private Long id;
	@NotBlank(message = "Digite o nome da fruta!")
	private String nome;
	
	@NotBlank(message = "Digite uma descrição para a fruta!")
	private String descricao;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	private Long idCategoria;
	
	private Categoria categoria;
	
	private List<Personagem> personagens;

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

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public List<Personagem> getPersonagens() {
		return personagens;
	}

	public void setPersonagens(List<Personagem> personagens) {
		this.personagens = personagens;
	}

	public Long getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Long idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	

}
