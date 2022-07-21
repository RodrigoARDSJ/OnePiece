package br.com.rodrigo.onepiece.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.rodrigo.onepiece.dto.PersonagemDTO;
import br.com.rodrigo.onepiece.service.PersonagemService;

@RestController
@RequestMapping("/personagem")
public class PersonagemController {

	@Autowired
	private PersonagemService service;

	@GetMapping
	public ResponseEntity<List<PersonagemDTO>> lista() {
		return ResponseEntity.ok(service.getAllPersonagem());
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<PersonagemDTO> buscar(@PathVariable Long id) {
		return ResponseEntity.ok(service.getPersonagemById(id));
	}

	@PostMapping
	public ResponseEntity<PersonagemDTO> create(@RequestBody @Valid PersonagemDTO dto) {
		service.create(dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(dto);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<PersonagemDTO> update(@RequestBody @Valid PersonagemDTO dto, @PathVariable Long id) {
		return ResponseEntity.ok(service.updatePersonagem(dto, id));
	}
}