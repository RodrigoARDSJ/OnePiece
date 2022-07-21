package br.com.rodrigo.onepiece.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.rodrigo.onepiece.dto.FrutaDTO;
import br.com.rodrigo.onepiece.exception.NaoEncontradoException;
import br.com.rodrigo.onepiece.model.Categoria;
import br.com.rodrigo.onepiece.model.Fruta;
import br.com.rodrigo.onepiece.repository.CategoriaRepository;
import br.com.rodrigo.onepiece.repository.FrutaRepository;
import br.com.rodrigo.onepiece.repository.PersonagemRepository;

@Service
public class FrutaService {

	@Autowired
	private FrutaRepository frutaRepository;

	@Autowired
	private CategoriaRepository categoriaRepository;

	private PersonagemRepository personagemRepository;

	public FrutaDTO converteEntityToVo(Fruta fruta) {
		FrutaDTO dto = new FrutaDTO();
		dto.setId(fruta.getId());
		dto.setNome(fruta.getNome());
		dto.setCategoria(fruta.getCategoria());
		dto.setPersonagens(fruta.getPersonagem());
		return dto;
	}

	public Fruta converteVotToEntity(FrutaDTO dto) {
		Categoria categoria = categoriaRepository.findById(dto.getIdCategoria())
				.orElseThrow(() -> new NaoEncontradoException("Categoria não encontrada para finalizar o cadastro"));
		Fruta fruta = new Fruta();
		fruta.setId(dto.getId());
		fruta.setNome(dto.getNome());
		fruta.setCategoria(categoria);
		fruta.setPersonagem(dto.getPersonagens());
		return fruta;
	}

	public FrutaDTO findId(Long id) {
		Fruta fruta = frutaRepository.findById(id).get();
		return converteEntityToVo(fruta);
	}

	public List<FrutaDTO> findAll() {
		List<Fruta> fruta = frutaRepository.findAll();
		List<FrutaDTO> dto = fruta.stream().map(this::converteEntityToVo).collect(Collectors.toList());
		return dto;
	}

	public Fruta create(FrutaDTO fruta) {
		return frutaRepository.save(converteVotToEntity(fruta));
	}

	public FrutaDTO updateFruta(FrutaDTO dto, Long id) {

		frutaRepository.findById(id).map(item -> {
			item.setNome(dto.getNome());
			item.setDescricao(dto.getDescricao());
			item.setCategoria(categoriaRepository.findById(dto.getIdCategoria())
					.orElseThrow(() -> new NaoEncontradoException("Categoria não encontrado!")));
			frutaRepository.save(item);
			return item;
		}).orElseThrow(() -> new NaoEncontradoException("Fruta não encontrada"));
		return dto;
	}

	public void deleteFruta(Long id) {
		findId(id);
		frutaRepository.deleteById(id);
	}

}
