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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.rodrigo.onepiece.dto.FrutaDTO;
import br.com.rodrigo.onepiece.model.Fruta;
import br.com.rodrigo.onepiece.service.FrutaService;

@RequestMapping("/fruta")
@RestController
public class FrutaController {

	@Autowired
	private FrutaService service;

	@GetMapping
	public ResponseEntity<List<FrutaDTO>> list() {
		return ResponseEntity.ok(service.findAll());
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<FrutaDTO> listarFrutasDto(@PathVariable Long id) {
		return ResponseEntity.ok(service.findId(id));
	}

	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public Fruta craete(@RequestBody @Valid FrutaDTO dto) {
		return service.create(dto);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<FrutaDTO> updateFruta(@RequestBody @Valid FrutaDTO dto, @PathVariable Long id) {
		return ResponseEntity.ok(service.updateFruta(dto, id));
	}
}
