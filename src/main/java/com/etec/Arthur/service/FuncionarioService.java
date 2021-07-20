package com.etec.Arthur.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import com.etec.Arthur.model.Funcionario;
import com.etec.Arthur.repository.FuncionarioRepository;

@Service
public class FuncionarioService {

	private FuncionarioRepository funcionarioRepository;
	
	public FuncionarioService(FuncionarioRepository funcionarioRepository) {
		this.funcionarioRepository = funcionarioRepository;
	}
	
	public List<Funcionario> listarFuncionarios(){
		return funcionarioRepository.findAll();
	}
	
	public List<Funcionario> buscarPorNome(String nome){
		return funcionarioRepository.findByNome(nome);
	}
	
	public Optional<Funcionario> buscarPorId(Long id){
		return funcionarioRepository.findById(id);
	}
	
	public Funcionario salvar(Funcionario funcionario) {
		return funcionarioRepository.save(funcionario);
	}
	
	public Funcionario alterar(Funcionario funcionario) {
		if(funcionario.getId() != null && !funcionarioRepository.existsById(funcionario.getId())) {
			throw new EntityNotFoundException("Id não localizado");
		}
		return funcionarioRepository.save(funcionario);
	}
		
	public void deletar(Funcionario funcionario) {
		funcionarioRepository.delete(funcionario);
	}
	
	public Double SalarioPeloRepo(Double comissao, Long id) {
		Optional<Funcionario> funcionarioSalario = funcionarioRepository.findById(id);
		if(funcionarioSalario.isPresent()) {
			Double salario = (funcionarioSalario.get().getSalario() + comissao) * 1.10;
			return salario;
		} else {
			return null;
		}
	}
	
/*public Double calculaSalario(Double comissao, Funcionario funcionario) {
		Double salarioFinal;
		salarioFinal = (funcionario.getSalario() + comissao) * 1.10;
		return salarioFinal;
	}*/
}

