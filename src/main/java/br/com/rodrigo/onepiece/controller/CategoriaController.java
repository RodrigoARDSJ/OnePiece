package br.com.rodrigo.onepiece.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.rodrigo.onepiece.dto.CategoriaDTO;
import br.com.rodrigo.onepiece.model.Categoria;
import br.com.rodrigo.onepiece.service.CategoriaService;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {
	
	@Autowired
	private CategoriaService service;
	

	
	@GetMapping
	public ResponseEntity<List<CategoriaDTO>> getAllCategoria() {
		return ResponseEntity.ok(service.listCategoria());
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<CategoriaDTO> getCategoriaById(@PathVariable Long id) {
		return ResponseEntity.ok(service.getcategoriaById(id));
	}
	
	
	@PostMapping
	public Categoria criar(@RequestBody @Valid CategoriaDTO dto) { 
		return service.create(dto);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<CategoriaDTO> update(@RequestBody @Valid CategoriaDTO dto, @PathVariable Long id) {
		return ResponseEntity.ok(service.updateCategoria(dto, id));
	}
}
