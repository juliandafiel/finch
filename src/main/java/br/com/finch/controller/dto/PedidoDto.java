package br.com.finch.controller.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import br.com.finch.modelo.Pedido;

public class PedidoDto {

	private Long id;
	private String descricao;
	private Double valor;
	private LocalDateTime dataCriacao;
	
	public PedidoDto(Pedido pedido) {
		this.id = pedido.getId();
//		this.descricao = pedido.getDescricao();
//		this.valor = pedido.getValor();
	}

	public Long getId() {
		return id;
	}

	 

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public static List<PedidoDto> converter(List<Pedido> pedidos) {
		return pedidos.stream().map(PedidoDto::new).collect(Collectors.toList());
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
