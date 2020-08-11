package br.com.finch.controller.form;

import br.com.finch.modelo.Lanche;
import br.com.finch.modelo.Pedido;
import br.com.finch.repository.PedidoRepository;

public class PedidoForm {
	
	private Lanche lanche;
	 
	public Pedido converter(PedidoRepository pedidoRepository) {
		// TODO Auto-generated method stub
//		Pedido ingrediente = pedidoRepository.findByDescricao(ingredienteDescricao);
		
		Pedido pedido = new Pedido();
//		if(ingrediente != null) {
//			pedido = new Pedido();
//			pedido.setValorTotal(ingrediente.getValor()*quantidadeIngrediente);
//		}
		return pedido;
	}

	public Lanche getLanche() {
		return lanche;
	}

	public void setLanche(Lanche lanche) {
		this.lanche = lanche;
	}

 
	
	
 
}
