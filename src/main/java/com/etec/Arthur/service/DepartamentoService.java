package com.etec.Arthur.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import com.etec.Arthur.model.Departamento;

import com.etec.Arthur.repository.DepartamentoRepository;


@Service
public class DepartamentoService {
	
private DepartamentoRepository departamentoRepository;
	
	public DepartamentoService(DepartamentoRepository departamentoRepository) {
		this.departamentoRepository = departamentoRepository;
	}
	
	public List<Departamento> listarDepartamentos(){
		return departamentoRepository.findAll();
	}
	
	public List<Departamento> buscarPorDescricao(String descricao){
		return departamentoRepository.findByDescricao(descricao);
	}
	
	public Optional<Departamento> buscarPorId(Long id){ //option trabalha com nulos
		return departamentoRepository.findById(id);
	}
	
	public Departamento salvar(Departamento departamento) {
		return departamentoRepository.save(departamento);
	}
	
	public Departamento alterar(Departamento departamento) { //se o id nao for nulo e nao existir dara erro
		if(departamento.getId() != null && !departamentoRepository.existsById(departamento.getId())) {
			throw new EntityNotFoundException("Id nao localizado.");
		}
		return departamentoRepository.save(departamento);
	}
	
	public void deletar(Departamento departamento) {
		departamentoRepository.delete(departamento);
	}
	
}