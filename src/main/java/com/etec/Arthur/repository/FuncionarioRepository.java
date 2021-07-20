package com.etec.Arthur.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.etec.Arthur.model.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
	
	List<Funcionario> findByNome(String nome);

}
