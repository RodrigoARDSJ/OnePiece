package br.com.rodrigo.onepiece.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.rodrigo.onepiece.dto.FrutaDTO;

@Entity
@Table(name = "FRUTA_CATEGORIA")
public class Categoria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;

	private String descricao;

	@OneToMany(mappedBy = "categoria")
	@JsonIgnore
	private List<Fruta> fruta;

	public Categoria() {

	}

	public Categoria(Long id, String nome, String descricao, List<Fruta> fruta) {
		super();
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.fruta = fruta;
	}

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

	public void setFruta(List<Fruta> fruta) {
		this.fruta = fruta;
	}

	public List<Fruta> getFruta() {
		return fruta;
	}
	
}