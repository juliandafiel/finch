package br.com.finch.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.finch.modelo.Ingrediente;
import br.com.finch.repository.IngredienteRepository;

public class AtualizacaoIngredienteForm {
	
	@NotNull @NotEmpty 
	private Double valor;


	public Ingrediente atualizar(Long id, IngredienteRepository ingredienteRepository) {
		Ingrediente ingrediente = ingredienteRepository.getOne(id);
		ingrediente.setValor(valor);
		return ingrediente;
	}


	public Double getValor() {
		return valor;
	}


	public void setValor(Double valor) {
		this.valor = valor;
	}


	
	
}
