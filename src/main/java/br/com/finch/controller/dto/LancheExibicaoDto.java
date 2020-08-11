package br.com.finch.controller.dto;

import br.com.finch.modelo.Lanche;

public class LancheExibicaoDto {

	private String lanche;
	private Double valor;
	
	public static final String ALFACE = "alface";
	public static final String BACON = "bacon";
	public static final String HAMBURGER = "hambúrguer";
	public static final String QUEIJO = "queijo";

	public LancheExibicaoDto(Lanche lanche) {
		// TODO Auto-generated constructor stub
		this.lanche = lanche.getDescricao();
		this.valor = lanche.getPedido().getValorTotal();
		  
	}

	public String getLanche() {
		return lanche;
	}

	public void setLanche(String lanche) {
		this.lanche = lanche;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

}
