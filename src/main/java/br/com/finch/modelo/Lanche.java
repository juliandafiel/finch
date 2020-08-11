package br.com.finch.modelo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Lanche {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private Pedido pedido;
	
	@OneToMany(mappedBy = "lanche")
	private List<PorcaoIngrediente> porcoesIngredientes = new ArrayList<>();
	
	private LocalDateTime dataCadastro  = LocalDateTime.now();
	
	private String descricao;
	
	
	public Lanche() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public List<PorcaoIngrediente> getPorcoesIngredientes() {
		return porcoesIngredientes;
	}

	public void setPorcoesIngredientes(List<PorcaoIngrediente> porcoesIngredientes) {
		this.porcoesIngredientes = porcoesIngredientes;
	}

	public LocalDateTime getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDateTime dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


}
