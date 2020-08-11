package br.com.finch.repository;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.finch.modelo.Ingrediente;

public interface IngredienteRepository extends JpaRepository<Ingrediente, Long> {

	Ingrediente findByDescricao(@NotNull @NotEmpty String descricao);

	 

}
