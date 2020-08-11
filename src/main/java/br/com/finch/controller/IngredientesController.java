package br.com.finch.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.finch.controller.dto.IngredienteDto;
import br.com.finch.controller.form.AtualizacaoIngredienteForm;
import br.com.finch.controller.form.IngredienteForm;
import br.com.finch.modelo.Ingrediente;
import br.com.finch.repository.IngredienteRepository;

@RestController
@RequestMapping("/ingredientes")
public class IngredientesController {
	
	@Autowired
	private IngredienteRepository ingredienteRepository;
	
	 
	
	@GetMapping
	public List<Ingrediente> listar() {
		return ingredienteRepository.findAll();
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<IngredienteDto> inserir(@RequestBody @Valid IngredienteForm form, UriComponentsBuilder uriBuilder) {
		Ingrediente ingrediente = form.converter(ingredienteRepository);
		ingredienteRepository.save(ingrediente);
		
		URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(ingrediente.getId()).toUri();
		return ResponseEntity.created(uri).body(new IngredienteDto(ingrediente));
	}
	
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<IngredienteDto> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizacaoIngredienteForm form) {
		Optional<Ingrediente> optional = ingredienteRepository.findById(id);
		if (optional.isPresent()) {
			Ingrediente ingrediente = form.atualizar(id, ingredienteRepository);
			return ResponseEntity.ok(new IngredienteDto(ingrediente));
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> deletar(@PathVariable Long id) {
		Optional<Ingrediente> optional = ingredienteRepository.findById(id);
		if (optional.isPresent()) {
			ingredienteRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.notFound().build();
	}

}







