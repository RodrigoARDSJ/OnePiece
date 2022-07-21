package br.com.rodrigo.onepiece.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.rodrigo.onepiece.dto.CategoriaDTO;
import br.com.rodrigo.onepiece.exception.NaoEncontradoException;
import br.com.rodrigo.onepiece.model.Categoria;
import br.com.rodrigo.onepiece.model.Fruta;
import br.com.rodrigo.onepiece.repository.CategoriaRepository;
import br.com.rodrigo.onepiece.repository.FrutaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private FrutaRepository frutaRepository;

	public Categoria converteVoToEntity(CategoriaDTO dto) {
		List<Fruta> frutas = frutaRepository.findAll();
		Categoria categoria = new Categoria();
		categoria.setId(dto.getId());
		categoria.setNome(dto.getNome());
		categoria.setDescricao(dto.getDescricao());
		categoria.setFruta(frutas);
		return categoria;

	}

	public CategoriaDTO converteEntityToVo(Categoria categoria) {
		CategoriaDTO dto = new CategoriaDTO();
		dto.setId(categoria.getId());
		dto.setDescricao(categoria.getDescricao());
		dto.setNome(categoria.getNome());
		dto.setFruta(categoria.getFruta());
		return dto;
	}

	public CategoriaDTO getcategoriaById(Long id) {
		Categoria categoria = categoriaRepository.findById(id).orElseThrow(() -> new NaoEncontradoException("Categoria não encontrado!"));
		return converteEntityToVo(categoria);
	}

	public List<CategoriaDTO> listCategoria() {
		List<Categoria> categoria = categoriaRepository.findAll();
		List<CategoriaDTO> dto = categoria.stream().map(this::converteEntityToVo).collect(Collectors.toList());
		return dto;
	}

	public Categoria create(CategoriaDTO dto) {
		return categoriaRepository.save(converteVoToEntity(dto));
	}

	public CategoriaDTO updateCategoria(CategoriaDTO dto, Long id) {
		
		categoriaRepository.findById(id).map(item -> {
			item.setNome(dto.getNome());
			item.setDescricao(dto.getDescricao());
			categoriaRepository.save(item);
			return item;
		}).orElseThrow(() -> new NaoEncontradoException("Categoria não encontrado"));
		return dto;
	}

	public void delete(Long id) {
		getcategoriaById(id);
		categoriaRepository.deleteById(id);
	}
}