package com.etec.Arthur.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.etec.Arthur.model.Departamento;

public interface DepartamentoRepository extends JpaRepository<Departamento, String>{

		/*List<Departamento> findByNome(String nome);*/

		List<Departamento> findByDescricao(String descricao);

		Optional<Departamento> findById(Long id);

		boolean existsById(Long id);

}
