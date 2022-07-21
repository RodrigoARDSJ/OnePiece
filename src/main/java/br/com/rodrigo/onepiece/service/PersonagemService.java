package br.com.rodrigo.onepiece.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.rodrigo.onepiece.dto.PersonagemDTO;
import br.com.rodrigo.onepiece.exception.NaoEncontradoException;
import br.com.rodrigo.onepiece.model.Fruta;
import br.com.rodrigo.onepiece.model.Personagem;
import br.com.rodrigo.onepiece.repository.FrutaRepository;
import br.com.rodrigo.onepiece.repository.PersonagemRepository;

@Service
public class PersonagemService {

	@Autowired
	private PersonagemRepository personagemRepository;

	@Autowired
	private FrutaRepository frutaRepository;

	public Personagem converteVoToEntity(PersonagemDTO dto) {
		Fruta fruta = frutaRepository.findById(dto.getIdfruta())
				.orElseThrow(() -> new NaoEncontradoException("Fruta não encontrada"));
		Personagem personagem = new Personagem();
		personagem.setId(dto.getId());
		personagem.setNome(dto.getNome());
		personagem.setDescricao(dto.getDescricao());
		personagem.setFruta(fruta);
		return personagem;
	}

	public PersonagemDTO converteEntityToVo(Personagem personagem) {
		PersonagemDTO dto = new PersonagemDTO();
		dto.setId(personagem.getId());
		dto.setNome(personagem.getNome());
		dto.setDescricao(personagem.getDescricao());
		dto.setFruta(personagem.getFruta());
		return dto;
	}

	public PersonagemDTO getPersonagemById(Long id) {
		Personagem personagem = personagemRepository.findById(id)
				.orElseThrow(() -> new NaoEncontradoException("Personagem não encontrado"));
		return converteEntityToVo(personagem);
	}

	public List<PersonagemDTO> getAllPersonagem() {
		List<Personagem> personagem = personagemRepository.findAll();
		List<PersonagemDTO> dto = personagem.stream().map(this::converteEntityToVo).collect(Collectors.toList());
		return dto;
	}

	public Personagem create(PersonagemDTO personagem) {
		return personagemRepository.save(converteVoToEntity(personagem));
	}

	public PersonagemDTO updatePersonagem(PersonagemDTO dto, Long id) {

		personagemRepository.findById(id).map(item -> {
			item.setId(dto.getId());
			item.setNome(dto.getNome());
			item.setDescricao(dto.getDescricao());
			item.setFruta(frutaRepository.findById(dto.getIdfruta())
					.orElseThrow(() -> new NaoEncontradoException("Fruta não encontrado!")));
			return item;
		});
		return dto;
	}

	public void delete(Long id) {
		getPersonagemById(id);
		personagemRepository.deleteById(id);

	}
}