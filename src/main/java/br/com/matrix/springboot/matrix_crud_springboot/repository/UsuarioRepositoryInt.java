package br.com.matrix.springboot.matrix_crud_springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.matrix.springboot.matrix_crud_springboot.model.Usuario;

public interface UsuarioRepositoryInt extends JpaRepository<Usuario, Long> {

	@Query(value = "select u from Usuario u where upper(trim(u.nome)) like %?1%")
	List<Usuario> buscarUserNome(String name);
	
	

}
