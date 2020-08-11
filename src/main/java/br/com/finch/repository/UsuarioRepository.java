package br.com.finch.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.finch.modelo.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
 
	Optional<Usuario> findByEmail(String email);
	 

}
