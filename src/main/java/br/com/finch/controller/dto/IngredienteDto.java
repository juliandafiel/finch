package br.com.finch.controller.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import br.com.finch.modelo.Ingrediente;

public class IngredienteDto {

	private Long id;
	private String descricao;
	private Double valor;
	private LocalDateTime dataCriacao;
	
	public IngredienteDto(Ingrediente ingrediente) {
		this.id = ingrediente.getId();
		this.descricao = ingrediente.getDescricao();
		this.valor = ingrediente.getValor();
	}

	public Long getId() {
		return id;
	}

	 

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public static List<IngredienteDto> converter(List<Ingrediente> ingredientes) {
		return ingredientes.stream().map(IngredienteDto::new).collect(Collectors.toList());
	}

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

	public void setId(Long id) {
		this.id = id;
	}

	public void setDataCriacao(LocalDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

}
