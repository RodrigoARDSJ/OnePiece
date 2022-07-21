package br.com.rodrigo.onepiece.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;

import br.com.rodrigo.onepiece.model.Fruta;

public class CategoriaDTO {

	private Long id;
	@NotBlank(message = "Digite a categoria!")
	private String nome;
	@NotBlank(message = "Digite a descrção da categoria!")
	private String descricao;

	private List<Fruta> fruta;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String name) {
		this.nome = name;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Fruta> getFruta() {
		return fruta;
	}

	public void setFruta(List<Fruta> fruta) {
		this.fruta = fruta;
	}

}
