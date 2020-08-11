package br.com.finch.controller;

import java.net.URI;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.finch.controller.dto.LancheDto;
import br.com.finch.controller.dto.LancheExibicaoDto;
import br.com.finch.controller.form.LancheForm;
import br.com.finch.modelo.Lanche;
import br.com.finch.modelo.PorcaoIngrediente;
import br.com.finch.repository.IngredienteRepository;
import br.com.finch.repository.LancheRepository;

@RestController
@RequestMapping("/lanches")
public class LancheController {
	
	@Autowired
	private LancheRepository lancheRepository;
	
	@Autowired
	private IngredienteRepository ingredienteRepository;
	 
	
	@GetMapping
	public List<Lanche> listar() {
		return lancheRepository.findAll();
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<LancheExibicaoDto> inserir(@RequestBody @Valid LancheForm lancheForm, UriComponentsBuilder uriBuilder) {
		  
		Lanche lanche = lancheForm.converter(ingredienteRepository);
		
		//aplica as regras no controller
		boolean contemAlface = false;
		boolean contemBacon = false;
		int porcoesCarne = 0;
		int porcoesQueijo = 0;
		double valor = 0;
		for(PorcaoIngrediente p : lanche.getPorcoesIngredientes()) {
//			
			if(p.getIngrediente().getDescricao().toLowerCase().contains(LancheExibicaoDto.ALFACE)) {
				contemAlface = true;
			}
			if(p.getIngrediente().getDescricao().toLowerCase().contains(LancheExibicaoDto.BACON)) {
				contemBacon = true;
			}
			
			if(p.getIngrediente().getDescricao().toLowerCase().contains(LancheExibicaoDto.HAMBURGER)
					&& p.getQuantidade() >= 3) {
				porcoesCarne += p.getQuantidade();
				
//				Muita carne: A cada 3 porções de hambúrguer o cliente só paga 2, a cada 6
//				porções, o cliente pagará 4 e assim sucessivamente.
				int qtdPorcoesPagas = (porcoesCarne % 3)*2;
				valor += qtdPorcoesPagas * p.getIngrediente().getValor(); 
				continue;
			}else
			if(p.getIngrediente().getDescricao().toLowerCase().contains(LancheExibicaoDto.QUEIJO)
					&& p.getQuantidade() >= 3) {
				porcoesQueijo += p.getQuantidade();
				
//				Muito queijo: A cada 3 porções de queijo o cliente só paga 2, a cada 6
//				porções, o cliente pagará 4 e assim sucessivamente.
				int qtdPorcoesPagas = (porcoesQueijo % 3)*2;
				valor += qtdPorcoesPagas * p.getIngrediente().getValor(); 
				continue;
			}
			 
			valor += p.getQuantidade() * p.getIngrediente().getValor();
		}
		
		
		//promoções
		//desconto de 10% Light
		if(contemAlface && !contemBacon) {
			valor *= 0.9;
		}
		
		lanche.getPedido().setValorTotal(valor);
		
		lancheRepository.save(lanche);
		
		URI uri = uriBuilder.path("/lanches/{id}").buildAndExpand(lanche.getId()).toUri();
		return ResponseEntity.created(uri).body(new LancheExibicaoDto(lanche));
	}
 

}







