package br.com.finch.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.finch.controller.dto.PedidoDto;
import br.com.finch.controller.form.PedidoForm;
import br.com.finch.modelo.Lanche;
import br.com.finch.modelo.Pedido;
import br.com.finch.modelo.PorcaoIngrediente;
import br.com.finch.modelo.Usuario;
import br.com.finch.repository.PedidoRepository;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	 
	
	@GetMapping
	public List<Pedido> listar() {
		
		List<Pedido> pedidos = new ArrayList<Pedido>();
		Pedido pedido = new Pedido();
		Usuario usuario = (Usuario)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		pedido.setCliente(usuario);
		pedidos.add(pedido);
		return pedidos;
//		return pedidoRepository.findAll();
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<PedidoDto> inserir(@RequestBody @Valid PedidoForm pedidoForm, UriComponentsBuilder uriBuilder) {
		Pedido pedido = new Pedido();
		
		Usuario usuario = (Usuario)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		pedido.setCliente(usuario);
		
		double valorTotal = 0;
		for(PorcaoIngrediente porcao : pedidoForm.getLanche().getPorcoesIngredientes()) {
			valorTotal += porcao.getIngrediente().getValor().doubleValue()*porcao.getQuantidade();
		}
		
		pedido.setValorTotal(valorTotal);

		pedidoRepository.save(pedido);
		
		URI uri = uriBuilder.path("/pedidos/{id}").buildAndExpand(pedido.getId()).toUri();
		return ResponseEntity.created(uri).body(new PedidoDto(pedido));
	}
//	
//	
//	@PutMapping("/{id}")
//	@Transactional
//	public ResponseEntity<IngredienteDto> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizacaoIngredienteForm form) {
//		Optional<Ingrediente> optional = ingredienteRepository.findById(id);
//		if (optional.isPresent()) {
//			Ingrediente ingrediente = form.atualizar(id, ingredienteRepository);
//			return ResponseEntity.ok(new IngredienteDto(ingrediente));
//		}
//		
//		return ResponseEntity.notFound().build();
//	}
//	
//	@DeleteMapping("/{id}")
//	@Transactional
//	public ResponseEntity<?> deletar(@PathVariable Long id) {
//		Optional<Ingrediente> optional = ingredienteRepository.findById(id);
//		if (optional.isPresent()) {
//			ingredienteRepository.deleteById(id);
//			return ResponseEntity.ok().build();
//		}
//		
//		return ResponseEntity.notFound().build();
//	}

}







