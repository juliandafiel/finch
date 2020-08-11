package br.com.finch.controller.form;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import br.com.finch.controller.dto.LancheDto;
import br.com.finch.modelo.Ingrediente;
import br.com.finch.modelo.Lanche;
import br.com.finch.modelo.PorcaoIngrediente;
import br.com.finch.repository.IngredienteRepository;

public class LancheForm {
	
	private List<LancheDto> lancheDto = new ArrayList<>();
	
	 
	public Lanche converter(IngredienteRepository repo) {
		// TODO Auto-generated method stub
		Lanche lanche = new Lanche();
		PorcaoIngrediente porcao = new PorcaoIngrediente(); 
		for(LancheDto l : lancheDto) {
			Optional<Ingrediente> ingrediente = repo.findById(l.getIdIngrediente());
			if(ingrediente.isPresent()) {
				porcao.setIngrediente(ingrediente.get());
				porcao.setLanche(lanche);
				lanche.getPorcoesIngredientes().add(porcao);
			}
		}
		
		 
		return lanche;
	}


	public List<LancheDto> getLancheDto() {
		return lancheDto;
	}


	public void setLancheDto(List<LancheDto> lancheDto) {
		this.lancheDto = lancheDto;
	}
 
 
	
	
 
}
