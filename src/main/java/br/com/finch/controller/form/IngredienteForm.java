package br.com.finch.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.finch.modelo.Ingrediente;
import br.com.finch.repository.IngredienteRepository;

public class IngredienteForm {

	@NotNull
	@NotEmpty
	private String descricao;

	@NotNull
	private Double valor;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Ingrediente converter(IngredienteRepository ingredienteRepository) {
		Ingrediente ingrediente = ingredienteRepository.findByDescricao(descricao);
		return new Ingrediente(descricao,valor);
	}
}
